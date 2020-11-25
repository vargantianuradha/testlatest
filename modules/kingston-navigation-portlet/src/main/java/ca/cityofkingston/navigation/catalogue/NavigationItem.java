package ca.cityofkingston.navigation.catalogue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.NavItem;

public class NavigationItem implements Serializable, Comparable<NavigationItem> {

	private static final long serialVersionUID = 1L;

	private static final Log LOGGER = LogFactoryUtil.getLog(NavigationItem.class);

	public static final boolean EXPANDO_DEFAULT_ARE_NAVIGATION_CHILDREN_SORTED = Boolean.TRUE;
	public static final String EXPANDO_KEY_ARE_NAVIGATION_CHILDREN_SORTED = "are-navigation-children-sorted";

	public static final boolean EXPANDO_DEFAULT_IS_ON_SIDE_NAVIGATION = Boolean.TRUE;
	public static final String EXPANDO_KEY_IS_ON_SIDE_NAVIGATION = "is-on-side-navigation";
	
	private NavItem navItem;

	public static NavigationItem make(NavItem navItem) {
		Validate.notNull(navItem);
		NavigationItem result = new NavigationItem();
		result.navItem =  navItem;
		return result;
	}

	private NavigationItem() {}

	public List<NavigationItem> getChildren() {
		List<NavigationItem> results = new ArrayList<NavigationItem>();
		try {
			List<NavItem> children = navItem.getChildren();
			for (NavItem child : children) {
				
				if (isLayoutInNavigation(child.getLayout())) {
					results.add(NavigationItem.make( child));
				}
			}

			if(isLayoutSorted(navItem)){
				Collections.sort(results);
			}

		} catch (Exception e) {
			LOGGER.error("getChildren", e);
		}
		return results;
	}

	private boolean isLayoutSorted(NavItem navItem) {
		Layout layout = navItem.getLayout();

		if(layout == null || layout.getExpandoBridge() == null || !layout.getExpandoBridge().hasAttribute(EXPANDO_KEY_ARE_NAVIGATION_CHILDREN_SORTED)) {
			return EXPANDO_DEFAULT_ARE_NAVIGATION_CHILDREN_SORTED;
		}

		return Boolean.parseBoolean(layout.getExpandoBridge().getAttribute(EXPANDO_KEY_ARE_NAVIGATION_CHILDREN_SORTED, false).toString());

	}

	private boolean isLayoutInNavigation(Layout layout) {

		if(layout == null || layout.getExpandoBridge() == null || !layout.getExpandoBridge().hasAttribute(EXPANDO_KEY_IS_ON_SIDE_NAVIGATION)) {
			return EXPANDO_DEFAULT_IS_ON_SIDE_NAVIGATION;
		}

		return Boolean.parseBoolean(layout.getExpandoBridge().getAttribute(EXPANDO_KEY_IS_ON_SIDE_NAVIGATION, false).toString());

	}	
	
	public String getUnescapedName() {
		return navItem.getUnescapedName();
	}

	public boolean isTreeSelected() throws Exception {
		return isSelected() || isChildSelected();
	}

	public boolean isChildSelected() {
		try {
			return navItem.isChildSelected();
		} catch (Exception e) {
			LOGGER.error("isChildSelected", e);
			return false;
		}
	}

	public boolean isSelected() throws Exception {
		return navItem.isSelected();
	}

	public boolean hasChildren() {
		try {
			return navItem.hasChildren();
		} catch (Exception e) {
			LOGGER.error("hasChildren", e);
			return false;
		}
	}

	public String getURL() {
		try {
			return navItem.getURL();
		} catch (Exception e) {
			LOGGER.error("getUrl", e);
			return StringUtils.EMPTY;
		}
	}

	public NavItem getNavItem() {
		return navItem;
	}

	Layout getLayout() {
		return navItem.getLayout();
	}

	@Override
	public int compareTo(NavigationItem other) {
		return getUnescapedName().compareToIgnoreCase(other.getUnescapedName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((navItem == null) ? 0 : navItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NavigationItem other = (NavigationItem) obj;
		if (navItem == null) {
			if (other.navItem != null)
				return false;
		} else if (!navItem.equals(other.navItem))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}