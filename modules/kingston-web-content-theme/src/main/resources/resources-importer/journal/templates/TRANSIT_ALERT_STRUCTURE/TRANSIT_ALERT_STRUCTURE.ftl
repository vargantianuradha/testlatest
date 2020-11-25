<div class="GIS-dataset">

    <div style="float:right;margin-top:5px;margin-bottom:5px;min-width:25px">
	  #foreach ($route in $affectedRoutes.getOptions())
	     <img style="float:left;width:60px" src="@theme_image_path@/transit_icons/$route">
	  #end
	</div>
	<h4 style="clear:both;font-weight: bold">$headline.getData()</h4>
	<div class="details">
		$details.getData()
	</div>
	<div style="text-align:right;line-height:30px">
	#foreach ($imageObj in $mapTitle.getSiblings())
		#if ($validator.isNotNull($imageObj.mapImage.getData()))
		   <a class="rounded-button-link" style="white-space:nowrap" href="javascript:openImagePopup('$imageObj.mapImage.getData()')">$imageObj.getData()</a>
		#end
	#end
	</div>

</div>