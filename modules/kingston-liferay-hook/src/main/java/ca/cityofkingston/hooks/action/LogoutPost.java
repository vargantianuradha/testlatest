package ca.cityofkingston.hooks.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;

/**
 * @author RST028
 */
@Component(
		immediate = true,
		property = {
		        "key=logout.events.post"
		    },
		service = LifecycleAction.class
	)
	public class LogoutPost extends Action {
		/* (non-Java-doc)
		 * @see com.liferay.portal.kernel.events.SimpleAction#SimpleAction()
		 */
		public LogoutPost() {
			super();
		}


		@Override
		public void run(HttpServletRequest request, HttpServletResponse response)
				throws ActionException {
			// TODO Auto-generated method stub
			System.out.println("## My custom logout action");
		}

	}