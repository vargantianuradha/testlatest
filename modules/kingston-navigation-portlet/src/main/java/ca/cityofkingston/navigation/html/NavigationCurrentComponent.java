package ca.cityofkingston.navigation.html;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import ca.cityofkingston.navigation.catalogue.NavigationItem;

public final class NavigationCurrentComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	private NavigationItem navigationItem;

	public static NavigationCurrentComponent make(NavigationItem navigationItem) {
		NavigationCurrentComponent result = new NavigationCurrentComponent();
		result.navigationItem = navigationItem;
		return result;
	}

	private NavigationCurrentComponent() {}

	@Override
	public String toString() {
		if (navigationItem == null) {
			return StringUtils.EMPTY;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<ul class='current-nav no-list no-margin'>");
		stringBuilder.append("<li class='side-nav-item first-nav-item'>");
		stringBuilder.append(NavigationItemComponent.make(navigationItem, "0"));
		try {
			stringBuilder.append(children(navigationItem, 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stringBuilder.append("</li>");
		stringBuilder.append("</ul>");
		return stringBuilder.toString();
	}

	String children(NavigationItem parent, int level) throws Exception {
		if (!parent.isTreeSelected()) {
			return StringUtils.EMPTY;
		}
		if (!parent.hasChildren()) {
			return StringUtils.EMPTY;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<ul class='no-list no-margin'>");
		for (NavigationItem child: parent.getChildren()) {
			stringBuilder.append("<li>");
			stringBuilder.append(NavigationItemComponent.make(child, ""+level));
			stringBuilder.append(children(child, level+1));
			stringBuilder.append("</li>");
		}
		stringBuilder.append("</ul>");
		return stringBuilder.toString();
	}
}
