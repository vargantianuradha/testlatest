##Take layout id
#set ($layoutId = $request.get("theme-display").get("plid"))

## get the service for layout
#set($layoutService = $serviceLocator.findService("com.liferay.portal.service.LayoutLocalService"))

##convert the layout id into long
#set ($layoutLong = $getterUtil.getLong($layoutId))

##take a layout object
#set($layout = $layoutService.getLayout($layoutLong))

<div class="sidebar">
#if ( $layout.getExpandoBridge().getAttribute("is-french-content") == true)
<h2>Documents Connexes</h2>
#else
<h2>Related Documents</h2>
#end
<ul class="no-list" style="margin-left:0">
#foreach($doc in $relatedDoc.getSiblings())
#set ($fileTitle = $doc.getData().split("/").get(4))
#set($fileTitle = $httpUtil.decodeURL($htmlUtil.unescape($fileTitle)))
<li class="linkitem">
<a href="$doc.getData()">$fileTitle</a></li>
#end
</ul>
</div>