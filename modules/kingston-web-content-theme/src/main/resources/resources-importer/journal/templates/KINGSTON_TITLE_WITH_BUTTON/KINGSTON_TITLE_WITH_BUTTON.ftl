#set ($layoutLocalService = $serviceLocator.findService("com.liferay.portal.service.LayoutLocalService"))
#set ($layoutId = $getterUtil.getLong($internallink.getData()))



#set ($themeDisplay = $request.get('theme-display'))
#set ($currentPlid = $getterUtil.getLong($themeDisplay.get('plid')))
#set ($currentLayout = $layoutLocalService.getLayout($currentPlid))

#set ($layout = $layoutLocalService.getLayout($getterUtil.getLong($groupId), $currentLayout.isPrivateLayout(), $layoutId))

<div>
	<div class="column_50">
		<h2 class="portlet-header-title">$title.getData()</h2>
	</div>
	<div class="column_50 align-right">
		<a class="rounded-button-link" href="@friendly_url_current@@group_friendly_url@$layout.getFriendlyURL()" alt="$altlink.getData()">$labellink.getData()</a>
	</div>
</div>