package ca.cityofkingston.navigation.catalogue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.NavItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;

public final class NavigationCatalogue {
	private static final Log LOGGER = LogFactoryUtil.getLog(NavigationCatalogue.class);

	private static final int CURRENT_LEVEL_OFFSET = 1;


	public static NavigationCatalogue make(Layout layout, ThemeDisplay themeDisplay, HttpServletRequest httpServletRequest, int levelMax) {
		Validate.notNull(layout);
		Validate.notNull( httpServletRequest);
		NavigationCatalogue result = new NavigationCatalogue();
		result.layout = layout;
		result.currentLayout = layout;
		result.themeDisplay = themeDisplay;
		result.httpServletRequest = httpServletRequest;
		result.levelMax = levelMax;
		return result;
	}

	private Layout layout;
	private NavigationRequestVars vars;
	private ThemeDisplay themeDisplay;
	private HttpServletRequest httpServletRequest;
	private int levelMax;

	private NavigationCatalogue() {}

	public NavigationItem getMainItem() throws PortalException {
		
		return getItem(levelMax);
	}

	public NavigationItem getCurrentItem() throws PortalException {
		return getItem(levelMax + CURRENT_LEVEL_OFFSET);
	}
	Layout currentLayout ;
	
	NavigationItem getItem(int levelMax) throws PortalException {
		if (levelMax < 0) {
			return null;
		}
		List<Layout> ancestors = null;
		try {
			ancestors = layout.getAncestors();
		} catch (PortalException e) {
			LOGGER.error("getAncestors", e);
			return null;
		} catch (SystemException e) {
			LOGGER.error("getAncestors", e);
			return null;
		}
		if (ancestors == null || ancestors.size() < levelMax) {
			return null;
		}
		if (ancestors.size() > levelMax) {
			Collections.reverse(ancestors);
			Layout currentLayout = ancestors.get(levelMax);
			NavItem navItem = new NavItem(httpServletRequest, currentLayout, new HashMap<String, Object>());
 
			return NavigationItem.make(navItem);
		}
		NavItem navItem = new NavItem(httpServletRequest, currentLayout, new HashMap<String, Object>());
		return NavigationItem.make(navItem);
	}

}