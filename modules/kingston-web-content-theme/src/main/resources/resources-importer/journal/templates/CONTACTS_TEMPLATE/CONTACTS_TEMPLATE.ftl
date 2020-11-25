##Take layout id
#set ($layoutId = $request.get("theme-display").get("plid"))

## get the service for layout
#set($layoutService = $serviceLocator.findService("com.liferay.portal.service.LayoutLocalService"))

##convert the layout id into long
#set ($layoutLong = $getterUtil.getLong($layoutId))

##take a layout object
#set($layout = $layoutService.getLayout($layoutLong))

<div class="sidebar">
#if (( $layout.getExpandoBridge().getAttribute("is-french-content") == true) && ($validator.isNotNull($title.titlefrench)))
<h2>$title.titlefrench.getData()</h2>
#elseif ($validator.isNotNull($title))
<h2>$title.getData()</h2>
#end
<div style="padding:5px">
#if ($name.data() != "")
     <span style="font-weight:bold">
     $name.getData()
     </span></br>
#end
#if ($name.title.data != "")
     $name.title.getData()</br>
#end
#if ($name.location.data != "")
     $name.location.getData()
     </br>
#end
#if ($name.email.data != "")
     <a class="skip-external-icon" style="text-decoration:underline; padding-left:0" href="mailto:$name.email.getData()"> $name.email.getData()</a>
#end
#if ((($layout.getExpandoBridge().getAttribute("is-french-content") == true) && ($name.phonenumber.data != "")) && ($validator.isNotNull($name)))
     Téléphone: $name.phonenumber.getData()</br>
#elseif ($name.phonenumber.data != "")
     Phone: $name.phonenumber.getData()</br>
#end
#if (($layout.getExpandoBridge().getAttribute("is-french-content") == true) && ($name.faxnumber.data != ""))
Télécopieur: $name.faxnumber.getData()</br>
#elseif ($name.faxnumber.data != "")
     Fax: $name.faxnumber.getData()</br>
#end
#if (($layout.getExpandoBridge().getAttribute("is-french-content") == true) && ($miscfrench.data != "") && ($validator.isNotNull($miscfrench)))
     <hr/>
     <span class="contact">$miscfrench.getData()</span>

#elseif ($misc.data != "")
     <hr/>
     <span class="contact">$misc.getData()</span>
#end
</div>
</div>