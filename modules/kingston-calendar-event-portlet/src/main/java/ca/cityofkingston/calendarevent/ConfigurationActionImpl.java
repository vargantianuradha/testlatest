package ca.cityofkingston.calendarevent;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import ca.cityofkingston.calendarevent.constants.CalendarEventPortletKeys;

@Component(
		configurationPid = "ca.cityofkingston.calendarevent.DemoConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
		property = "javax.portlet.name=" + CalendarEventPortletKeys.CalendarEvent,
		service = ConfigurationAction.class
	)

public class ConfigurationActionImpl extends DefaultConfigurationAction  {
	public static final String PORTLET_RESOURCE = "portletResource";

	public static final int [] DAYS_FORWARD = new int[] {5, 10, 15, 20, 25, 30, 60, 90, 120, 150, 180};

	public static final int NUM_DAYS_FORWARD_DEFAULT = 10;
	public static final String NUM_DAYS_FORWARD_KEY = "numDaysForward";

	public static final int [] NUM_ITEMS = new int[] {2, 4, 6, 8, 10, 12};

	public static final int NUM_ITEMS_DISPLAYED_DEFAULT = 4;
	public static final String NUM_ITEMS_DISPLAYED_KEY = "numItemsDisplayed";

	public static final String TAGS_DEFAULT = StringPool.BLANK;
	public static final String TAGS_KEY = "tags";

	public static final String CATEGORIES_DEFAULT = StringPool.BLANK;
	public static final String CATEGORIES_KEY = "categories";

	public static final String SHOW_STATUS_KEY = "Status";
	public static final String SHOW_STATUS_DEFAULT = "false";


	public static final String DISPLAY_TYPE_KEY = "displayTypeKey";
	public static final String DISPLAY_TYPE_WITH_ACCORDION = "With accordion (eg. Homepage)";
	public static final String DISPLAY_TYPE_LIST_ONLY = "List only (no accordion)";
	public static final String DISPLAY_TYPE_PLAIN_LIST = "Text List only";
	public static final String DISPLAY_TYPE_DEFAULT = DISPLAY_TYPE_WITH_ACCORDION;
	public static final String[] DISPLAY_TYPE_VALUES = new String[] {DISPLAY_TYPE_DEFAULT, DISPLAY_TYPE_LIST_ONLY, DISPLAY_TYPE_PLAIN_LIST};

	public static final String SHOW_PAST_EVENTS_KEY = "showPastEventsKey";
	public static final String SHOW_PAST_EVENTS_DEFAULT = "false";

	public static final String CALENDAR_LAYOUT_KEY = "calendarLayoutKey";

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception, PortletException {

		if (!Constants.UPDATE.equals(ParamUtil.getString(actionRequest, Constants.CMD))) {
			return;
		}
		String portletResource = ParamUtil.getString(actionRequest,	PORTLET_RESOURCE);
		actionRequest.setAttribute("portletResource", portletResource);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
		try {
			store(portletPreferences, actionRequest);
			SessionMessages.add(actionRequest, "success");
		}
		catch (Exception e) {
			SessionMessages.add(actionRequest, "error");
		}
		SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");

	}

	void store(PortletPreferences portletPreferences, ActionRequest actionRequest) throws ReadOnlyException, ValidatorException, IOException, PortletException {
		portletPreferences.setValue(NUM_DAYS_FORWARD_KEY, actionRequest.getParameter(NUM_DAYS_FORWARD_KEY));
		portletPreferences.setValue(NUM_ITEMS_DISPLAYED_KEY, actionRequest.getParameter(NUM_ITEMS_DISPLAYED_KEY));
		portletPreferences.setValue(TAGS_KEY, actionRequest.getParameter(TAGS_KEY));
		portletPreferences.setValue(CATEGORIES_KEY, actionRequest.getParameter(CATEGORIES_KEY));
		portletPreferences.setValue(SHOW_STATUS_KEY, actionRequest.getParameter(SHOW_STATUS_KEY));
		portletPreferences.setValue(DISPLAY_TYPE_KEY, actionRequest.getParameter(DISPLAY_TYPE_KEY));
		portletPreferences.setValue(SHOW_PAST_EVENTS_KEY, actionRequest.getParameter(SHOW_PAST_EVENTS_KEY));
		portletPreferences.setValue(CALENDAR_LAYOUT_KEY, actionRequest.getParameter(CALENDAR_LAYOUT_KEY));

		portletPreferences.store();
	}

	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		return "/configuration.jsp";
	}

	 @Activate
	    @Modified
	    protected void activate(Map<Object, Object> properties) {
	        _log.info("#####Calling activate() method of Payment Portlet######");
	        
	        _demoConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	    }

	    private static final Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

	    private volatile DemoConfiguration _demoConfiguration;  
}

	