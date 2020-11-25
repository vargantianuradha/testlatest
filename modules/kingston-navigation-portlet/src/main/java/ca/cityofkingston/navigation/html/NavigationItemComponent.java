package ca.cityofkingston.navigation.html;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import ca.cityofkingston.navigation.catalogue.NavigationItem;


public final class NavigationItemComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	private NavigationItem navigationItem;
	private String level;
	
	public static String DATA_SENA_OFF = "data-sena-Off";

	public static NavigationItemComponent make(NavigationItem navigationItem, String level) {
		Validate.notNull(navigationItem);
		Validate.notNull(level);
		NavigationItemComponent result = new NavigationItemComponent();
		result.navigationItem = navigationItem;
		result.level = level;
		return result;
	}

	private NavigationItemComponent() {}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		try { 
			boolean dataSenaOff = Boolean.FALSE;
			if(navigationItem.getNavItem()!=null && navigationItem.getNavItem().getLayout()!=null && navigationItem.getNavItem().getLayout().getExpandoBridge().getAttribute(DATA_SENA_OFF)!=null);
			{   
				if((boolean) navigationItem.getNavItem().getLayout().getExpandoBridge().getAttribute(DATA_SENA_OFF)) {
					 dataSenaOff = Boolean.TRUE;
				}
			}
			 if(dataSenaOff){
				stringBuilder.append("<a data-senna-off='true' href='"+navigationItem.getURL()+"' class='"+getCssItem()+"'>");
			}else{
				stringBuilder.append("<a href='"+navigationItem.getURL()+"' class='"+getCssItem()+"'>");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stringBuilder.append(navigationItem.getUnescapedName());
		try {
			stringBuilder.append("<span class='"+getCssIcon()+"'></span>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stringBuilder.append("</a>");
		return stringBuilder.toString();
	}

	String getCssIcon() throws Exception {
		String cssIcon = StringUtils.EMPTY;
		if (navigationItem.hasChildren()) {
			cssIcon = "nav-icon ";
			cssIcon += navigationItem.isTreeSelected() ? "nav-item-open" : "nav-item-closed";
		}
		return cssIcon;
	}

	String getCssItem() throws Exception {
		String cssItem = "level-"+level;
		if (navigationItem.isSelected()) {
			cssItem += " nav-item-active";
		}
		return cssItem;
	}
}