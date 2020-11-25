package ca.cityofkingston.navigation.catalogue;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;

public class NavigationRequestVars {
	public static NavigationRequestVars make(HttpServletRequest request, ThemeDisplay themeDisplay)
			throws PortalException, SystemException {
		Layout layout = themeDisplay.getLayout();
		long ancestorPlid = layout.getAncestorPlid();
		long ancestorLayoutId = layout.getAncestorLayoutId();
		return new NavigationRequestVars(request, themeDisplay, ancestorPlid, ancestorLayoutId);
	}

	private NavigationRequestVars(HttpServletRequest request, ThemeDisplay themeDisplay, long ancestorPlid, long ancestorLayoutId)
			throws PortalException, SystemException {
		super();
	}

}

