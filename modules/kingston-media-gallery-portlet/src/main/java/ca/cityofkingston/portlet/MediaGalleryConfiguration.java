package ca.cityofkingston.portlet;



import java.io.Serializable;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

public class MediaGalleryConfiguration implements Serializable,DemoConfiguration {
	private static final long serialVersionUID = 1L;

	public static final String FOLDER_ID = "folder_id";
	public static final String REPOSITORY_ID = "repository_id";
	public static final String MEDIA_STATUS_KEY = "media_status";
	public static final String PORTLET_RESOURCE = "portletResource";
	public static final String SHOW_CURRENT_USER_IMAGES = "show_current_user_images"; 

	public static final String MEDIA_STATUS_APPROVED = "approved";
	public static final String MEDIA_STATUS_PENDING = "pending";
	public static final String MEDIA_STATUS_EXPIRED = "expired";
	public static final String MEDIA_STATUS_ALL = "all";
	public static final String[] MEDIA_STATUS = new String[]{"approved", "pending", "expired", "all"};	

	public static final int DEFAULT_WORKFLOW_STATUS = WorkflowConstants.STATUS_ANY;
	public static final String[] DEFAULT_MIME_TYPES =  new String[]{};
	public static final String DEFAULT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID + "";
	public static final String DEFAULT_REPOSITORY_ID = "0";
	public static final String DEFAULT_MEDIA_STATUS = "approved";
	public static final String DEFAULT_SHOW_CURRENT_USER_IMAGES = "false";

	public static final String MAX_NUM_IMAGES = "max_num_images";
	public static final String DEFAULT_MAX_NUM_IMAGES = "200";

	private long repositoryId;
	private long folderId;
	private String mediaStatus;
	private boolean isShowingUsersImages;
	private int maxNumImages;

	private PortletPreferences portletPreferences;


	public static MediaGalleryConfiguration make(PortletRequest portletRequest, ThemeDisplay themeDisplay, String key) throws SystemException, PortalException{

		String portletInstanceId = (String) portletRequest.getAttribute(key);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(portletRequest, portletInstanceId);

		return make(portletPreferences, themeDisplay);
	}

	public static MediaGalleryConfiguration make(PortletPreferences portletPreferences, ThemeDisplay themeDisplay) throws SystemException, PortalException{

		MediaGalleryConfiguration configuration = new MediaGalleryConfiguration();

		configuration.portletPreferences = portletPreferences;
		configuration.repositoryId = themeDisplay.getScopeGroupId();
		configuration.folderId = Long.parseLong(portletPreferences.getValue(FOLDER_ID, DEFAULT_FOLDER_ID));
		configuration.mediaStatus = portletPreferences.getValue(MEDIA_STATUS_KEY, DEFAULT_MEDIA_STATUS);
		configuration.isShowingUsersImages = Boolean.valueOf(portletPreferences.getValue(SHOW_CURRENT_USER_IMAGES, DEFAULT_SHOW_CURRENT_USER_IMAGES));
		
		String foo = portletPreferences.getValue(MAX_NUM_IMAGES, DEFAULT_MAX_NUM_IMAGES);
		if (Validator.isNull(foo))
			foo = DEFAULT_MAX_NUM_IMAGES;
		configuration.maxNumImages = Integer.parseInt(foo);
		
		return configuration;
	}

	public int getWorkflowStatus() {
		return DEFAULT_WORKFLOW_STATUS;
	}

	public String[] getMimeTypes() {
		return DEFAULT_MIME_TYPES;
	}

	public long getRepositoryId() {
		return repositoryId;
	}

	public long getFolderId() {
		return folderId;
	}

	public String getMediaStatus() {
		return mediaStatus;
	}

	public int getMaxNumImages() {
		return maxNumImages;
	}

	public boolean isShowingUsersImages() {
		return isShowingUsersImages;
	}
	
	public boolean isFolderSelected(long id){
		return id == Long.parseLong(portletPreferences.getValue(FOLDER_ID, DEFAULT_FOLDER_ID));
	}
	
	public boolean isStatusSelected(String status){
		return status.equals(portletPreferences.getValue(MEDIA_STATUS_KEY, DEFAULT_MEDIA_STATUS));
	}
	
	public boolean isShowUserImagesSelected (){
		return Boolean.valueOf(portletPreferences.getValue(SHOW_CURRENT_USER_IMAGES, DEFAULT_SHOW_CURRENT_USER_IMAGES));
	}

	private MediaGalleryConfiguration() {
	}





}
