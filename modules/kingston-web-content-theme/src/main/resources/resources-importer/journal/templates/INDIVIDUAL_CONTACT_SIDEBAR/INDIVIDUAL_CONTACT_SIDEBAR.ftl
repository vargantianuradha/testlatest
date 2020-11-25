##Take layout id
#set ($layoutId = $request.get("theme-display").get("plid"))

## get the service for layout
#set($layoutService = $serviceLocator.findService("com.liferay.portal.service.LayoutLocalService"))

##convert the layout id into long
#set ($layoutLong = $getterUtil.getLong($layoutId))

##take a layout object
#set($layout = $layoutService.getLayout($layoutLong))

<div class="sidebar">
#if ($layout.getExpandoBridge().getAttribute("is-french-content") == true)
##french
  <h2>Personne-ressource</h2>
  <div style="padding:5px">
  #if ($name.data() != "")
       <span style="font-weight:bold">
       $name.getData()
       </span></br>
  #end
  #if (($jobtitle.jobtitlefrench.data != "") && ($validator.isNotNull($jobtitle.jobtitlefrench)))
       $jobtitle.jobtitlefrench.getData()</br>
  #end
  #if (($location.locationfrench.data != "") && ($validator.isNotNull($location.locationfrench)))
       <span class="contact">$location.locationfrench.getData()</span>
  #end
  #if ($email.data != "")
     <a class="skip-external-icon" style="text-decoration:underline; padding-left:0" href="mailto:$email.getData()"> $email.getData()</a>
  #end
  #if (($phone.phonefrench.data != "") && ($validator.isNotNull($phone.phonefrench)))
     Téléphone: $phone.phonefrench.getData()</br>
  #end
  #if (($fax.faxfrench.data != "") && ($validator.isNotNull($fax.faxfrench)))
       Télécopieur: $fax.faxfrench.getData()</br>
  #end
#else
##english
<h2>Contact</h2>
  <div style="padding:5px">
  #if ($name.data() != "")
       <span style="font-weight:bold">
       $name.getData()
       </span></br>
  #end
  #if ($jobtitle.data != "")
       $jobtitle.getData()</br>
  #end
  #if ($location.data != "")
       <span class="contact">$location.getData()</span>
  #end
  #if ($email.data != "")
     <a class="skip-external-icon" style="text-decoration:underline; padding-left:0" href="mailto:$email.getData()"> $email.getData()</a>
  #end
  #if ($phone.data != "")
     Phone: $phone.getData()</br>
  #end
  #if ($fax.data != "")
       Fax: $fax.getData()</br>
  #end
#end
</div>
</div>