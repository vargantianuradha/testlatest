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
package ca.cityofkingston.kingstonUtils.event;


import org.osgi.service.component.annotations.Component;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.util.GetterUtil;

import ca.cityofkingston.kingstonUtils.util.ExpandoUtil;

@Component(
		immediate = true, 
		property = {"key=application.startup.events"},
		service = LifecycleAction.class
	)
public class StartupAction extends SimpleAction {

	public void run(String[] arg0) throws ActionException {

		
		// The 0th index should be the company id given that we only run one
		// portal instance.
		long companyId = GetterUtil.getLong(arg0[0]);

		ExpandoUtil.createExpando(companyId, Layout.class.getName(), ExpandoUtil.EXPANDO_CATEGORY,
				ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST, ExpandoUtil.EXPANDO_CATEGORY_DEFAULT);
		ExpandoUtil.checkDefaultValueStartsWithEmpty(companyId, Layout.class.getName(), ExpandoUtil.EXPANDO_CATEGORY);
		ExpandoUtil.createExpando(companyId, DLFolder.class.getName(), ExpandoUtil.EXPANDO_CATEGORY,
				ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST, ExpandoUtil.EXPANDO_CATEGORY_DEFAULT);
		ExpandoUtil.checkDefaultValueStartsWithEmpty(companyId, DLFolder.class.getName(), ExpandoUtil.EXPANDO_CATEGORY);
	}

	private static Log logger = LogFactoryUtil.getLog(StartupAction.class);
}


	