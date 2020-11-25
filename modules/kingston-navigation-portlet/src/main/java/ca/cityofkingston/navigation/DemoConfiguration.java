package ca.cityofkingston.navigation;
import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(  
        id = "ca.cityofkingston.navigation.DemoConfiguration"
    )
public interface DemoConfiguration {
	
	public int getMainNavigationLevel();
	public boolean isDisplayMainNavigation();
	public boolean isDisplayCurrentNavigation();

}
