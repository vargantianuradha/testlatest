package ca.cityofkingston.portlet;

import ca.cityofkingston.constants.KingstonMediaGalleryPortletKeys;
import ca.cityofkingston.model.MediaItem;
import ca.cityofkingston.model.MediaItemFactory;
import ca.cityofkingston.model.PostDateComparator;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author RST019
 */
@Component(
    configurationPid = "ca.cityofkingston.portlet.DemoConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.header-portlet-css=/css/main.css", 
		"javax.portlet.display-name=kingston media gallery portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + KingstonMediaGalleryPortletKeys.KingstonMediaGallery,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MediaGalleryPortlet extends MVCPortlet {
	
	public static final String MEDIA_ITEM_LIST_ATTR = "MEDIA_ITEM_LIST";
	public static final String PORTLET_INSTANCE_ID = "PORTLET_INSTANCE_ID";

	private MediaItemFactory mediaItemFactory = new MediaItemFactory();

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portletInstanceId = (String) renderRequest.getAttribute(WebKeys.PORTLET_ID);		
		Long userID = PortalUtil.getUserId(renderRequest);
		
		List<MediaItem> mediaItemList = null;

		try {
			MediaGalleryConfiguration configuration = MediaGalleryConfiguration.make(renderRequest, themeDisplay, WebKeys.PORTLET_ID);
			mediaItemList = loadFiles(configuration, themeDisplay, userID);
			sortMediaItems(mediaItemList);
		}catch (Exception e) {
			e.printStackTrace();
			mediaItemList = new ArrayList<MediaItem>();
		}

		renderRequest.setAttribute(MEDIA_ITEM_LIST_ATTR, mediaItemList);
		renderRequest.setAttribute(PORTLET_INSTANCE_ID, portletInstanceId);

		super.doView(renderRequest, renderResponse);
	}


	List<MediaItem> loadFiles(MediaGalleryConfiguration configuration, ThemeDisplay themeDisplay, Long userID) throws SystemException, PortalException {

		List<MediaItem> mediaItemList = new ArrayList<MediaItem>();
		List<?> results = DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(configuration.getRepositoryId(), configuration.getFolderId(), configuration.getWorkflowStatus(), configuration.getMimeTypes(), true, 0, configuration.getMaxNumImages(), null);

		if(results != null) {
			for(Object o: results) {
				if(o instanceof FileEntry) {
					FileEntry fileEntry = (FileEntry) o;
					if (isFileEntryInCorrectStatus(configuration, fileEntry) && (!configuration.isShowingUsersImages() || fileEntry.getUserId() == userID)) {
						mediaItemList.add(getMediaItemFactory().makeMediaItem(fileEntry, themeDisplay));
					}
				}
			}
		}else {
			mediaItemList = new ArrayList<MediaItem>();
		}
		return mediaItemList;
	}

	boolean isFileEntryInCorrectStatus(MediaGalleryConfiguration configuration, FileEntry fileEntry) throws PortalException, SystemException {
		if (MediaGalleryConfiguration.MEDIA_STATUS_ALL.equals(configuration.getMediaStatus()))
			return true;
		
		if(MediaGalleryConfiguration.MEDIA_STATUS_APPROVED.equals(configuration.getMediaStatus()) && fileEntry.getFileVersion().isApproved())
				return true;
		
		if(MediaGalleryConfiguration.MEDIA_STATUS_EXPIRED.equals(configuration.getMediaStatus()) && fileEntry.getFileVersion().isExpired())
			return true;
		
		if(MediaGalleryConfiguration.MEDIA_STATUS_PENDING.equals(configuration.getMediaStatus()) && fileEntry.getFileVersion().isPending())
			return true;
		
		return false;
	}
	
	/* For Testing */
	public MediaItemFactory getMediaItemFactory() {
		return mediaItemFactory;
	}
	
	
	/* For Testing */
	void sortMediaItems(List<MediaItem> mediaItemList) {
		Collections.sort(mediaItemList, new PostDateComparator());
		Collections.reverse(mediaItemList);
	}
	
	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		 _log.info("#####Calling activate() method  ######");
		 _mediaGalleryConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(MediaGalleryPortlet.class);
	private volatile DemoConfiguration _mediaGalleryConfiguration;
}