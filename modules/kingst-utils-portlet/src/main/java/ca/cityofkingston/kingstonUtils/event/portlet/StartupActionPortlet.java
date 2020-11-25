package ca.cityofkingston.kingstonUtils.event.portlet;

import ca.cityofkingston.kingstonUtils.event.constants.StartupActionPortletKeys;
import ca.cityofkingston.kingstonUtils.util.LayoutMetaDataFactory;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author RST021
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=kingst-utils-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StartupActionPortletKeys.StartupAction,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class StartupActionPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		// TODO Auto-generated method stub
		super.doView(renderRequest, renderResponse);
	}
}