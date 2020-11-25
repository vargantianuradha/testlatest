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
  #if ($validator.isNotNull($title.titlefrench))
  <h2>$title.titlefrench.getData()</h2>
  #end
  <div style="padding:5px">
  #if (($name.namefrench.data() != "") && ($validator.isNotNull($name.namefrench)))
       <span style="font-weight:bold">
       $name.namefrench.getData()
       </span></br>
  #end
  #if (($location.locationfrench.data != "") && ($validator.isNotNull($location.locationfrench)))
       <span class="contact">$location.locationfrench.getData()</span>
  #end
  #if ($email.data != "")
     <a class="skip-external-icon" style="padding-left:0" href="mailto:$email.getData()">$email.getData()</a>
  #end
  #if (($phone.phonefrench.data != "") && ($validator.isNotNull($phone.phonefrench)))
       Téléphone: $phone.phonefrench.getData()</br>
  #end
  #if (($fax.data != "") && ($validator.isNotNull($fax.faxfrench)))
       Télécopieur: $fax.faxfrench.getData()</br>
  #end
  #if (($hours.hoursfrench.data != "") && ($validator.isNotNull($hours.hoursfrench)))
       <span class="contact">$hours.hoursfrench.getData()</span>
  #end
  #if (($description.descriptionfrench.data != "") && ($validator.isNotNull($description.descriptionfrench)))
       <hr/>
       <span class="contact">$description.descriptionfrench.getData()</span>
  #end
#else
##English
 #if ($validator.isNotNull($title))
  <h2>$title.getData()</h2>
  #end
  <div style="padding:5px">
  #if ($name.data() != "")
       <span style="font-weight:bold">
       $name.getData()
       </span></br>
  #end
  #if ($location.data != "")
       <span class="contact">$location.getData()</span>
  #end
  #if ($email.data != "")
     <a class="skip-external-icon" style="padding-left:0;text-decoration:underline;" href="mailto:$email.getData()">$email.getData()</a>
  #end
  #if ($phone.data != "")
       Phone: $phone.getData()</br>
  #end
  #if ($fax.data != "")
       Fax: $fax.getData()</br>
  #end
  #if ($hours.data != "")
       <span class="contact">$hours.getData()</span>
  #end
  #if ($description.data != "")
       <hr/>
       <span class="contact">$description.getData()</span>
  #end
#end
</div>
</div>