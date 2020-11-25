#set ($layoutLocalService = $serviceLocator.findService("com.liferay.portal.service.LayoutLocalService"))
#set ($themeDisplay = $request.get('theme-display'))

<div class="idliketo-container" role="presentation">
<ul role="presentation" class="no-margin">
	<li class="idliketo-menu no-list" role="presentation">
		<a class="idliketo-title default-link-cue" href="javascript:void(0)" role="menuitem"  aria-label="I'd like to" aria-haspopup="true">
			<span>I'd Like To...</span>
			<span class="idliketo-title-arrow"></span>
		</a>
		<ul class="idliketoitem-list no-list" role="menu">
			#foreach($item in $sortTool.sort($idliketoitem.getSiblings(), "data"))

			    #set($item-label = $item.getData())
			    #set($item-title = '')
			    #set($item-link = '')
			    #set($item-target = '')

				#foreach($item-child in $item.getChildren())

					#if ($item-child.getName() == "internallink" && $item-child.getData() != '' && $item-link == '')
						#set ($layoutId = $getterUtil.getLong($item-child.getData()))
						#set ($layout = $layoutLocalService.getLayout($getterUtil.getLong($groupId), false, $layoutId))
						#set($item-link = "@friendly_url_current@@group_friendly_url@" + $layout.getFriendlyURL())
					#end
					#if ($item-child.getName() == "externallink" && $item-child.getData() != '')
						 #set($item-link = $item-child.getData())
						 #set($item-target = 'target="_blank"')
					#end
					#if ($item-child.getName() == "title")
						 #set($item-title = $item-child.getData())
					#end

				#end
				<li class="idliketoitem" role="presentation">
					<a role="menuitem" tabindex="-1" class="idliketoitem-link default-link-cue" href="$item-link" title="$item-title" $item-target>$item-label</a>
				</li>
			#end

		</ul>
	</li>
</ul>
</div>