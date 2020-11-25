package ca.cityofkingston.navigation;

import java.io.Serializable;

import javax.portlet.PortletPreferences;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;



public class Configuration implements Serializable,DemoConfiguration {
	private static final long serialVersionUID = 1L;

	private static final Log LOGGER = LogFactoryUtil.getLog(Configuration.class);

	private PortletPreferences portletPreferences;

	public static Configuration make(PortletPreferences portletPreferences) {
		Validate.notNull(portletPreferences);
		Configuration result = new Configuration();
		result.portletPreferences = portletPreferences;
		return result;
	}

	private Configuration() {}

	public int getMainNavigationLevel() {
		int result = ConfigurationActionImpl.MAIN_NAVIGATION_LEVEL_DEFAULT;
		try {
			String value = portletPreferences.getValue(ConfigurationActionImpl.MAIN_NAVIGATION_LEVEL, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = Integer.parseInt(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("getMainNavigationLevel", e);
		}
		return result;
	}

	public boolean isDisplayMainNavigation() {
		boolean result = Boolean.TRUE;
		try {
			String value = portletPreferences.getValue(ConfigurationActionImpl.DISPLAY_MAIN_NAVIGATION, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = Boolean.valueOf(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("isDisplayMainNavigation", e);
		}
		return result;
	}

	public boolean isDisplayCurrentNavigation() {
		boolean result = Boolean.TRUE;
		try {
			String value = portletPreferences.getValue(ConfigurationActionImpl.DISPLAY_CURRENT_NAVIGATION, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = Boolean.valueOf(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("isDisplayCurrentNavigation", e);
		}
		return result;
	}
}