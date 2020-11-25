#set ($serviceContext = $portal.getClass().forName("com.liferay.portal.service.ServiceContextThreadLocal").getServiceContext())
#set ($httpServletRequest = $serviceContext.getRequest())
#set ($objThemeDisplay = $httpServletRequest.getAttribute("THEME_DISPLAY"))

#set ($osvcURL = $objThemeDisplay.getThemeSetting("oracle-crm-base-url") )
#set ($ns = $request.portlet-namespace)
#set ($kbId = "oscv-kb-$ns")
#set ($kbInstanceId = "skw_$ns")

#set ($templateImagesURL = $objThemeDisplay.getPathThemeImages() )

<style>
 #$kbId div.rn_Suggestions, #$kbId div.rn_Documents .rn_List, #$kbId div.rn_Content .rn_List, #$kbId div.rn_Corrections, #$kbId div.rn_Navigation a, #$kbId div.rn_Documents h3 {
    font-size: inherit !important;
}
  
  #$kbId div.rn_Navigation {
     display: none !important;
}

#$kbId div.rn_Content {
    padding-bottom: 0;
}

#if ($showDescriptions && $showDescriptions.getData() == "true")
  #$kbId div.rn_Content .rn_List li.rn_Item span.rn_Description { 
    display: inherit;
}
#else
   #$kbId div.rn_Content .rn_List li.rn_Item span.rn_Description { 
    display: none;
}
#end

</style>

<div class="osvc_kb">
#if ($validator.isNotNull($heading.getData()))
    <h2>$heading.getData()</h2>
#end

	<div class="sidebar no-border">
		<div class="kb_header">
			<img src="$templateImagesURL/templates/LogoCRM.png" alt="CRM Logo">
		</div>
		
	    <div id="$kbId"></div>
	    
	    <div class="additional-options">
	        #if ($validator.isNotNull($more_url.getData()))
	             <a class="rounded-button-link" style="float:right" href="$more_url.getData()">More &raquo; </a>
	        #end    
	    </div>
	</div>
</div>
<script type="text/javascript">
    RightNow.Client.Controller.addComponent(
        {
            div_id: "$kbId",
            search_box: false,
            instance_id: "$kbInstanceId",
            module: "KnowledgeSyndication",
            c: "$category.getData()",
            type: 3
        },
        "$osvcURL/ci/ws/get"
    );
</script>