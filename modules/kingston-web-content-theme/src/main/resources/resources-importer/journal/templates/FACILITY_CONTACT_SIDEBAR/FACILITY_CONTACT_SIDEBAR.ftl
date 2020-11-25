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
##french

<h2>$name.namefrench.getData()</h2>
<div style="padding:5px">
#if ($location.locationfrench.data != "")
     <span class="contact">$location.locationfrench.getData()</span>
#end
#if ($email.data != "")
     <a class="skip-external-icon" style="text-decoration:underline;padding-left:0" href="mailto:$email.getData()">$email.getData()</a>
#end
#if ($phone.phonefrench.data != "")
     Téléphone: $phone.phonefrench.getData()</br>
#end
#if ($fax.faxfrench.data != "")
     Télécopieur: $fax.faxfrench.getData()</br>
#end
#if ($hours.hoursfrench.data != "")
<hr/>
<span class="contact">
     $hours.hoursfrench.getData()</span>
#end
#if ($description.descriptionfrench.data != "")
     <hr/>
     <span class="contact">$description.descriptionfrench.getData()</span>
#end
#else
##English
<h2>$name.getData()</h2>
<div style="padding:5px">
#if ($location.data != "")
     <span class="contact">$location.getData()</span>
#end
#if ($email.data != "")
     <a class="skip-external-icon" style="padding-left:0" href="mailto:$email.getData()">$email.getData()</a>
#end
#if ($phone.data != "")
     Phone: $phone.getData()</br>
#end
#if ($fax.data != "")
     Fax: $fax.getData()</br>
#end
#if ($hours.data != "")
<hr/>
<span class="contact">
     $hours.getData()</span>
#end
#if ($description.data != "")
     <hr/>
     <span class="contact">$description.getData()</span>
#end
#end
</div>
</div>

<p>
	<iframe frameborder="0" title="Map location" height="165" marginheight="0" marginwidth="0" scrolling="no" src="$map.getData()" width="100%">
	</iframe><br />
	<small>
	<a href="$map.getData()" style="color:#0000FF;text-align:left">
	View Larger Map</a>
	</small>
</p>