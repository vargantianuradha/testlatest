package ca.cityofkingston.navigation.html;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ca.cityofkingston.navigation.catalogue.NavigationItem;

public final class NavigationMainComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	private NavigationItem navigationItem;

	public static NavigationMainComponent make(NavigationItem navigationItem) {
		NavigationMainComponent result = new NavigationMainComponent();
		result.navigationItem = navigationItem;
		return result;
	}

	private NavigationMainComponent() {}

	@Override
	public String toString() {
		if (navigationItem == null) {
			return StringUtils.EMPTY;
		}
		return children();
	}

	String children() {
		if (!navigationItem.hasChildren()) {
			return StringUtils.EMPTY;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<ul class='no-list no-margin'>");
		int count = 0;
		for (NavigationItem child: navigationItem.getChildren()) {
			try {
				if (child.isTreeSelected()) {
					continue;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stringBuilder.append("<li class='side-nav-item");
			if (count == 0) {
				stringBuilder.append(" first-nav-item");
			}
			stringBuilder.append("'>");
			stringBuilder.append(NavigationItemComponent.make(child, "0"));
			stringBuilder.append("</li>");
			count++;
		}
		stringBuilder.append("</ul>");
		return stringBuilder.toString();
	}
}

