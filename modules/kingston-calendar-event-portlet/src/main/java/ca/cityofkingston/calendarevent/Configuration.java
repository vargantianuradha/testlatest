package ca.cityofkingston.calendarevent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import ca.cityofkingston.calendarevent.utils.LiferayUtils;



public class Configuration implements Serializable,DemoConfiguration {
	private static final long serialVersionUID = 1L;

	private static final Log LOGGER = LogFactoryUtil.getLog(Configuration.class);

	private PortletPreferences portletPreferences;
	private LiferayUtils liferayUtils;

	public static Configuration make(PortletPreferences portletPreferences) {
		Validate.notNull(portletPreferences);
		Configuration result = new Configuration();
		result.portletPreferences = portletPreferences;
		result.setLiferayUtils(new LiferayUtils());
		return result;
	}

	private Configuration() {
	}

	public int getDaysForward() {
		int result = ConfigurationActionImpl.NUM_DAYS_FORWARD_DEFAULT;
		try {

			String value = portletPreferences.getValue(ConfigurationActionImpl.NUM_DAYS_FORWARD_KEY, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = Integer.parseInt(value);
			}
		} catch (Exception e) {
			LOGGER.error("getDaysForward", e);
		}
		return result;
	}

	public int getNumberOfItemsPerPage() {
		int result = ConfigurationActionImpl.NUM_ITEMS_DISPLAYED_DEFAULT;
		try {
			String value = portletPreferences.getValue(ConfigurationActionImpl.NUM_ITEMS_DISPLAYED_KEY, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = Integer.parseInt(value);
			}
		} catch (Exception e) {
			LOGGER.error("getNumberOfItemsPerPage", e);
		}
		return result;
	}

	public boolean isShowStatus() {
		boolean result = Boolean.parseBoolean(ConfigurationActionImpl.SHOW_STATUS_DEFAULT);
		try {
			result = Boolean.parseBoolean(portletPreferences.getValue(ConfigurationActionImpl.SHOW_STATUS_KEY, ConfigurationActionImpl.SHOW_STATUS_DEFAULT));
		} catch (Exception e) {
			LOGGER.error("getShowStatus", e);
		}
		return result;
	}


	public String getTagsToFilter() {
		String result = ConfigurationActionImpl.TAGS_DEFAULT;
		try {
			String value = portletPreferences.getValue(ConfigurationActionImpl.TAGS_KEY, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = value;
			}
		} catch (Exception e) {
			LOGGER.error("getTags", e);
		}
		return result;
	}

	public String getCategoriesToFilter() {
		String result = ConfigurationActionImpl.CATEGORIES_DEFAULT;
		try {
			String value = portletPreferences.getValue(ConfigurationActionImpl.CATEGORIES_KEY, StringUtils.EMPTY);
			if (!StringUtils.isBlank(value)) {
				result = value;
			}
		} catch (Exception e) {
			LOGGER.error("getCategories", e);
		}
		return result;
	}

	public List<AssetTag> getAssetTags(long groupId) throws SystemException {
		List<AssetTag> results = new ArrayList<AssetTag>();
		String tagNames = getTagsToFilter();
		String[] tags = tagNames.split(",");
		for (String name : tags) {
			AssetTag assetTag = liferayUtils.getAssetTag(groupId, name);
			if (assetTag != null) {
				results.add(assetTag);
			}
		}
		return results;
	}

	public List<AssetCategory> getAssetCategories(long groupId) throws SystemException {
		List<AssetCategory> results = new ArrayList<AssetCategory>();
		String[] categories = parseConfiguredCategories();

		for (String name : categories) {
			results.add(retrieveAssetCategory(groupId, name));
		}

		return results;
	}

	void setLiferayUtils(LiferayUtils liferayUtils) {
		Validate.notNull(liferayUtils);
		this.liferayUtils = liferayUtils;
	}



	public boolean isDisplayingPastEvents() {
		return Boolean.parseBoolean(portletPreferences.getValue(ConfigurationActionImpl.SHOW_PAST_EVENTS_KEY, ConfigurationActionImpl.SHOW_PAST_EVENTS_DEFAULT));
	}

	public String getDisplayType() {
		return portletPreferences.getValue(ConfigurationActionImpl.DISPLAY_TYPE_KEY, ConfigurationActionImpl.DISPLAY_TYPE_DEFAULT);
	}

	public String getCalendarLayoutURL() {
		return portletPreferences.getValue(ConfigurationActionImpl.CALENDAR_LAYOUT_KEY, ConfigurationActionImpl.CALENDAR_LAYOUT_KEY);
	}

	private String[] parseConfiguredCategories() {
		String categoryNames = getCategoriesToFilter();

		if(StringUtils.isBlank(categoryNames)) {
			return new String[]{};
		}

		return categoryNames.split(",");
	}

	private AssetCategory retrieveAssetCategory(long groupId, String name) throws SystemException {
		AssetCategory assetCategory = liferayUtils.getAssetCategory(groupId, name);

		if(assetCategory == null) {
			assetCategory = new NotFoundAssetCategory();
		}

		return assetCategory;
	}

}