package ca.cityofkingston.staffdirectory;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.StringPool;

import ca.cityofkingston.model.StaffPeople;
import ca.cityofkingston.service.StaffPeopleLocalServiceUtil;
import ca.cityofkingston.staffdirectory.constants.StaffDirectoryPortletKeys;

/**
 * @author RST021
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Kingston Staff Directory Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StaffDirectoryPortletKeys.StaffDirectory,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class StaffDirectoryPortlet extends MVCPortlet {
	
	public static final String FIRST_NAME_ATTRIBUTE = "firstname";
	public static final String LAST_NAME_ATTRIBUTE = "lastname";
	public static final String RESULT_LIST_ATTRIBUTE = "results";


		@Override
		public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

		String lastname = actionRequest.getParameter(LAST_NAME_ATTRIBUTE);
		actionRequest.setAttribute(LAST_NAME_ATTRIBUTE, lastname);

		String firstname = actionRequest.getParameter(FIRST_NAME_ATTRIBUTE);
		actionRequest.setAttribute(FIRST_NAME_ATTRIBUTE, firstname);

		try {
			List<StaffPeople> resultList = StaffPeopleLocalServiceUtil.findByLastnameAndFirstname(lastname, firstname);
			actionRequest.setAttribute(RESULT_LIST_ATTRIBUTE, resultList);
		} catch (SystemException e) {
			throw new PortletException(e);
		}

		super.processAction(actionRequest, actionResponse);
		actionResponse.setRenderParameter("jspPage", "/view.jsp");
	}

}