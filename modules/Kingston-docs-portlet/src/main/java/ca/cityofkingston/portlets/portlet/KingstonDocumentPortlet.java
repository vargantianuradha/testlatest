package ca.cityofkingston.portlets.portlet;

import ca.cityofkingston.portlets.constants.KingstonDocumentPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author RST021
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Kingstons document portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/kingstondocs/view.jsp",
		"javax.portlet.init-param.config-template=/html/kingstondocs/config.jsp",
		"javax.portlet.name=" + KingstonDocumentPortletKeys.KingstonDocument,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class KingstonDocumentPortlet extends MVCPortlet {
}