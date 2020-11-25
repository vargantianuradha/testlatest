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
package ca.cityofkingston.kingstonUtils.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import ca.cityofkingston.kingstonUtils.util.ExpandoUtil;

@Component(
	immediate = true,
	property = {
        "javax.portlet.name=blade_portlet_filter_CludoFilterPortlet"
    },
    service = Filter.class
)
public class CludoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	   _log.debug("Called CludoFilter.init(" + filterConfig + ") where hello="
				+ filterConfig.getInitParameter("hello"));
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String uri = (String) servletRequest.getAttribute(WebKeys.INVOKER_FILTER_URI);

		_log.debug("Called CludoFilter.doFilter(" + servletRequest + ", "
				+ servletResponse + ", " + filterChain + ") for URI " + uri);

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		long companyId = PortalUtil.getCompanyId(request);

		String category = getCategory(companyId, uri);

		response.addHeader("Category", category);

		filterChain.doFilter(servletRequest, servletResponse);
		_log.info("doFilter Method");
	}
	
	private String getCategory(long companyId, String uri) {
		String category = "";

		_log.debug("getCategory(" + uri + ")");

		// -- Expecting format /documents/<company id>/<folder id>/<file name>/
		// ....
		if (uri.matches("^\\/documents\\/\\d+\\/\\d+\\/.*")) {
			_log.debug("getCategory(" + uri + ") matched!");

			String[] parts = uri.split("/");
			long groupId = GetterUtil.getLong(parts[2]);
			long folderId = GetterUtil.getLong(parts[3]);
			String fileName = parts[4];

			_log.debug("getCategory(" + uri + ") groupId=" + groupId
					+ " folderId=" + folderId + " fileName=" + fileName);

			// -- set permissions
			try {
				User guestUser = UserLocalServiceUtil.getDefaultUser(companyId);

				PrincipalThreadLocal.setName(guestUser.getUserId());
				PermissionChecker permissionChecker = PermissionCheckerFactoryUtil
						.create(guestUser);
				PermissionThreadLocal.setPermissionChecker(permissionChecker);

				category = getDLFolderCategory(folderId);

			} catch (Exception e) {
				_log.error("Unable to set permissions: " + e);
			} finally {
				PrincipalThreadLocal.setName(null);
				PermissionThreadLocal.setPermissionChecker(null);
			}

			_log.debug("getCategory(" + uri + ") category=" + category);

		} else {
			_log.debug("getCategory(" + uri + ") DID NOT MATCH!");
		}

		return (category == null) ? "" : category;
	}
	
	private String getDLFolderCategory(long folderId) {
		String category = "";

		try {
			DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(folderId);
			if (dlFolder != null) {
				category = ExpandoUtil.getSingleton(dlFolder,ExpandoUtil.EXPANDO_CATEGORY);
				_log.debug("getDLFolderCategory(" + folderId + ") category="
						+ category);

				// -- IF category is empty AND we aren't at top the top of the
				// heirarchy
				// -- THEN check the parent folder
				if ((category == null || category.isEmpty())
						&& dlFolder.getParentFolderId() != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)
					category = getDLFolderCategory(dlFolder.getParentFolderId());

			}

		} catch (Exception e) {
			_log.debug("getDLFolderCategory(" + folderId + ") error=" + e);
		}

		return (category == null) ? "" : category;
	}

	@Override
	public void destroy() {
		_log.debug("Called CludoFilter.destroy()");
	}
	
	private static Log _log = LogFactoryUtil.getLog(CludoFilter.class);
	
}