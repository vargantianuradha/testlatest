package ca.cityofkingston.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import ca.cityofkingston.constants.KingstonMediaGalleryPortletKeys;

import java.io.IOException;
import java.util.Map;

import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(
		configurationPid = "ca.cityofkingston.portlet.DemoConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
		property = "javax.portlet.name=" + KingstonMediaGalleryPortletKeys.KingstonMediaGallery,
		service = ConfigurationAction.class
	)

public class MediaGalleryConfigurationActionImpl extends DefaultConfigurationAction {

	public static final String CONFIGURATION_JSP = "/configuration.jsp";

	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		try {
			String portletResource = ParamUtil.getString(actionRequest,	MediaGalleryConfiguration.PORTLET_RESOURCE);
			PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
			store(portletPreferences, actionRequest);
			SessionMessages.add(actionRequest, "success");
		}
		catch (Exception e) {
			SessionMessages.add(actionRequest, "error");
		}
		SessionMessages.add(actionRequest, portletConfig.getPortletName()+ ".doConfigure");

	}

	public void store(PortletPreferences preferences, ActionRequest actionRequest) throws ReadOnlyException, ValidatorException, IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long repositoryId = themeDisplay.getScopeGroupId();
		String folderId = actionRequest.getParameter(MediaGalleryConfiguration.FOLDER_ID);
		String mediaStatus = actionRequest.getParameter(MediaGalleryConfiguration.MEDIA_STATUS_KEY);
		String showUserImages = actionRequest.getParameter(MediaGalleryConfiguration.SHOW_CURRENT_USER_IMAGES);
		String maxNumImages = actionRequest.getParameter(MediaGalleryConfiguration.MAX_NUM_IMAGES );

		if (Validator.isNull(maxNumImages))
			maxNumImages = MediaGalleryConfiguration.DEFAULT_MAX_NUM_IMAGES;
		
		preferences.setValue(MediaGalleryConfiguration.REPOSITORY_ID, repositoryId + "");
		preferences.setValue(MediaGalleryConfiguration.FOLDER_ID, folderId + "");
		preferences.setValue(MediaGalleryConfiguration.MEDIA_STATUS_KEY, mediaStatus);
		preferences.setValue(MediaGalleryConfiguration.SHOW_CURRENT_USER_IMAGES, showUserImages);
		preferences.setValue(MediaGalleryConfiguration.MAX_NUM_IMAGES, maxNumImages);
		preferences.store();
	}

	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		return CONFIGURATION_JSP;
	}

	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _log.info("#####Calling activate() method######");
        
        _demoConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
    }

    private static final Log _log = LogFactoryUtil.getLog(MediaGalleryConfigurationActionImpl.class);

    private volatile DemoConfiguration _demoConfiguration;  


}
