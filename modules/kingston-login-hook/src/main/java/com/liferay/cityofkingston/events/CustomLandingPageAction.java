/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.cityofkingston.events;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	immediate = true,
	property = {
        "key=login.events.post"
    },
    service = LifecycleAction.class
)
public class CustomLandingPageAction implements LifecycleAction {

	public static final Log LOG = LogFactoryUtil.getLog(CustomLandingPageAction.class);

	public static final String WARN_MESSAGE = "Unable to find/go to the custom landing page. Redirect to the default one.";

	public static final List<String> STAFF_ROLES;

	public static final String STAFF_LANDING_PAGE = GetterUtil.getString(PropsUtil.get("login.staff.landing.page"));
	public static final String CITIZEN_GROUP_LANDING_PAGE = GetterUtil.getString(PropsUtil.get("login.citizen.group.landing.page"));

	static {
		String k = GetterUtil.getString(PropsUtil.get("login.staff.roles"));
		STAFF_ROLES = Arrays.asList(k.split(StringPool.COMMA));
	}
	
	 @Override
		public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
			throws ActionException {
	    	HttpServletRequest request = lifecycleEvent.getRequest();
	    	HttpServletResponse response = lifecycleEvent.getResponse();
	    	try {
				run(request, response);
			}
			catch (Exception e) {
				LOG.warn(WARN_MESSAGE, e);
			}
	    	
			
		}
	    
	    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
			try {
				String landingPageURL = isCitizen(request) ? CITIZEN_GROUP_LANDING_PAGE : STAFF_LANDING_PAGE;
				if(Validator.isNotNull(landingPageURL)) {
					response.sendRedirect(landingPageURL);
				}
				else {
					String redirect = GetterUtil.getString(request.getAttribute(WebKeys.CURRENT_URL));
					if(redirect != null && redirect.contains("p_auth")) {
						response.sendRedirect(HttpUtil.getPath(redirect));
					}
				}

			}
			catch (Exception e) {
				LOG.warn(WARN_MESSAGE, e);
			}

		}

		
	    boolean isCitizen(HttpServletRequest request) throws SystemException, PortalException{
			User user = PortalUtil.getUser(request);
			for(Role role: user.getRoles()) {
				if(STAFF_ROLES.contains(role.getName())) {
					return false;
				}
			}

			return true;
		}
}