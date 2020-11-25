#set ($gis-button-id = "gis-button-" + $reserved-article-id.data)
#set ($gis-meta-view-id = "gis-metaview-" + $reserved-article-id.data)
#set ($gis-preview-id = "gis-preview-" + $reserved-article-id.data)
#set ($gis-preview-id-button = "button-" + $gis-preview-id)

#set ($show-download-column = 1)

#if ($validator.isNull($csvUrl.getData()) && $validator.isNull($dwgUrl.getData()) && $validator.isNull($kmlUrl.getData()) && $validator.isNull($shpUrl.getData() ) )
   #set ($show-download-column = 0)
#end


<div class="GIS-dataset"> 
    <div class="image-col" > 
        <img class="GIS-dataset-image" style="width:182px;height:96px;" src="$image.getData()" alt="$image.altText.getData()" href="$metaUrl.getData"/> 
	</div> 
	<div class="details-col" >
		<h2 >$commonName.getData()</h2> 
		<p> $description.getData()

        <button id="$gis-button-id">Show Details</button>


        #if (($previewUrl.data != "") && ($validator.isNotNull($previewUrl)))
            #set ($previewExist = "true")
            <button id="$gis-preview-id-button">Preview Map</button>
        #else
            #set ($previewExist = "false")
        #end
        
        #if (($downloadUrl.data != "") && ($validator.isNotNull($downloadUrl)))
            <button onclick="window.location='$downloadUrl.getData()'";>Download</button>
        #end
        
        #if (($requestUrl.data != "") && ($validator.isNotNull($requestUrl)))
            <button onclick="window.location='$requestUrl.getData()'";>Request Data</button>
        #end
        
        #if ($previewExist == "true")
            <div id="$gis-preview-id" class="aui-helper-hidden" style="max-width:400px;height:300px;">
               
            </div>
        #end
		<div id="$gis-meta-view-id" class="aui-helper-hidden"></div>
	</div>
	#if ( $show-download-column == 1)
	    <div class="download-col" >
	    
	    #if ($validator.isNotNull($csvUrl.getData()))
	    	<a class="skip-external-icon" style="background-color: #cf4a19;" href="$csvUrl.getData()">CSV</a>
	    #end
	    #if ($validator.isNotNull($shpUrl.getData()))
	    	<a class="skip-external-icon" style="background-color: #a01d2b;" href="$shpUrl.getData()">SHP</a>
	    #end
	    #if ($validator.isNotNull($kmlUrl.getData()))
	    	<a class="skip-external-icon" style="background-color: #832069;" href="$kmlUrl.getData()">KML</a>
	    #end
	    #if ($validator.isNotNull($dwgUrl.getData()))
	    	<a class="skip-external-icon" style="background-color: #0059a3;" href="$dwgUrl.getData()">DWG</a>
	    #end
	    </div>
	#end
</div> 
<script type="text/javascript">
AUI().ready('aui-io-request','aui-dialog','node', function(A){

	var showMetaText = "Show Details";
	var hideMetaText = "Hide Details";
    
    var showPreviewText = "Preview Map";
    var hidePreviewText = "Hide Preview";
        	
	A.one("#$gis-button-id").on(
		'click',
		function () {
			var viewDataLink = A.one('#$gis-button-id');
			var dataDiv = A.one('#$gis-meta-view-id');
		
			if ( !dataDiv.one("#gis-meta-table-123") )
			{
				A.io.request( '$metaUrl.data', {   
					dataType: 'json',   
					method : 'GET',
					on: {   
						success:
							function() {
								var instance = this;
								var message = instance.get('responseData');
								if (message){
							        
									var t = A.Node.create(
										'<table id="gis-meta-table-123">' +
                                        '<tr><td colspan="2"><b>General</b></td></tr>' +
                                        '<tr><td>Name:</td><td>'+message.commonName+'</td></tr>' +
										'<tr><td>Description:</td><td>'+message.description+'</td></tr>' +
                                        '<tr><td>Tags:</td><td>'+ ( (Object.prototype.toString.call( message.tags ) === '[object Array]') ? message.tags.join(', ') : message.tags )  +'</td></tr>' +
                                        '<tr><td>File:</td><td>'+message.fileName+'</td></tr>' +
                                        '<tr><td>Spatial Accuracy:</td><td>'+message.spatialAccuracy+'</td></tr>' +
                                        '<tr><td>Update Frequency:</td><td>'+message.updateFreq+'</td></tr>' +
                                        '<tr><td colspan="2" style="height:10px"></td></tr>' +
                                        '<tr><td colspan="2"><b>Extents</b></td></tr>' +
                                        '<tr><td>Coverage:</td><td>'+message.extents.coverage+'</td></tr>' +
                                        '<tr><td>West:</td><td>'+message.extents.west+'</td></tr>' +
                                        '<tr><td>East:</td><td>'+message.extents.east+'</td></tr>' +
                                        '<tr><td>South:</td><td>'+message.extents.south+'</td></tr>' +
                                        '<tr><td>North:</td><td>'+message.extents.north+'</td></tr>' +
                                        '<tr><td colspan="2" style="height:10px"></td></tr>' +
                                        '<tr><td colspan="2"><b>Spatial</b></td></tr>' +
                                        '<tr><td>System:</td><td>'+message.spatial.coordSys+'</td></tr>' +
                                        '<tr><td>Projection:</td><td>'+message.spatial.projection+'</td></tr>' +
                                        '<tr><td>Units:</td><td>'+message.spatial.distUnits+'</td></tr>' +
                                        
                                        '<tr><td colspan="2" style="height:10px"></td></tr>' +
                                        '<tr><td colspan="2"><b>Ownership</b></td></tr>' +
                                        '<tr><td>Dept:</td><td>'+message.ownership.ownerDept+'</td></tr>' +
                                        '<tr><td>Name:</td><td>'+message.ownership.ownerName+'</td></tr>' +
                                        '<tr><td>Street:</td><td>'+message.ownership.streetAddress+'</td></tr>' +
                                        '<tr><td>City:</td><td>'+message.ownership.city+'</td></tr>' +
                                        '<tr><td>Province:</td><td>'+message.ownership.province+'</td></tr>' +
                                        '<tr><td>Postal Code:</td><td>'+message.ownership.postalCode+'</td></tr>' +
                                        '<tr><td colspan="2" style="height:10px"></td></tr>' +
                                        '<tr><td colspan="2"><b>Format</b></td></tr>' +
                                        '<tr><td>Data Type:</td><td>'+message.format.dataType+'</td></tr>' +
                                        '<tr><td>Data Format:</td><td>'+message.format.dataFormat+'</td></tr>' +
                                        '<tr><td>Geometry:</td><td>'+message.format.geometryType+'</td></tr>' +
                                        '</table>');
									
									var viewMeta = A.one('#$gis-meta-view-id');
									var viewDataLink = A.one('#$gis-button-id');

									viewMeta.append(t);
									viewMeta.show();
									viewDataLink.html(hideMetaText);
							   }    
							},
                          failure : function()
                {
                var dialog = new A.Dialog(
          {
            bodyContent: '<p style="margin-top:10px">The detailed information for this dataset is missing.  <a href="mailto:contactus@cityofkingston.ca?subject=GIS dataset error ">Please report this error to the site administrator.</a></p>',
            centered: true,
            height: 150,
            title: 'Data not found',
            width: 500,
            draggable: false,
            resizable: false
          }
        ).render();
        

        
                }
                
    				
                          
					}
				}); 
			}
			else
			{
				if (viewDataLink.text() == showMetaText)
				{
					viewDataLink.html(hideMetaText);
					dataDiv.show();
				}
				else
				{
					viewDataLink.html(showMetaText);
					dataDiv.hide();
				}
			}
		}
	);
    
if ("$previewExist" =="true"){
    
    A.one("#$gis-preview-id-button").on(
        'click',
		function () {
            var previewDiv = A.one('#$gis-preview-id');
            var previewButton = A.one('#$gis-preview-id-button');
if (previewButton.text() == showPreviewText){
    previewButton.html(hidePreviewText);
    var iframe = previewDiv.one('.gis-iframe');
    if (!iframe){
    var iframeTPL = '<iframe class="gis-iframe" title="Preview Map" src="$previewUrl.getData()" width="100%" height="100%" scrolling="no"></iframe>';
    var frame = A.Node.create(iframeTPL);
    previewDiv.append(frame);

    }
    
            previewDiv.show();
    }
    else{
        previewButton.html(showPreviewText);
        previewDiv.hide();
    }
		}
);
}
});
</script>