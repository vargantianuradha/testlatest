## all this to get an Int? Jeez
#set($int = 0)
#set($tempInt = $maxEntries.getData())
#set($maxEntries = $int.parseInt($tempInt)) 
##
## Got tabs made, now load em up with entries
#set ($pns=$request.get('portlet-namespace'))

<script>
    function LoadRSSFeed_$pns(id, url) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET",url,true);
        xhr.onreadystatechange = function() {
    		if (xhr.readyState == 4)
    		{
    			if (xhr.status == 200)
    			{
    			
    				if (xhr.responseText != null)
    					BuildHTML_$pns(id,xhr.responseXML);
    				else
    				{
    					console.log("Failed to receive RSS file from the server - file not found.");
    					return false;
    				}
    			}
    			else
    				console.log("Error code " + xhr.status + " received: " + xhr.statusText);
    		}
    	}
    	xhr.send(null);
    }

    function BuildHTML_$pns(id,xmlDoc) {
        var dateFormat = { month: 'short', day: 'numeric' };
        var items = xmlDoc.getElementsByTagName('item');
        var html ="<ul class=\"no-list\">";
        
        if (items.length == 0)
            html += "<li class=\"linkitem\">Nothing to report.</li>";
        else {    
            for (var i = 0; (i < items.length && ($maxEntries == 0 || i < $maxEntries)); i++) {
                var entryTitle = items[i].getElementsByTagName("title")[0].childNodes[0].nodeValue.substring(13);
    		var entryLink  = items[i].getElementsByTagName("link")[0].childNodes[0].nodeValue;
            var entryDate  = new Date(items[i].getElementsByTagName("pubDate")[0].childNodes[0].nodeValue);
            
                html += "<li class=\"linkitem\"><a href=\""+ entryLink + " target=\"_blank\">" +entryTitle+ "</a></li>";
            }
        }
        
        html += "</ul>";
        document.getElementById(id).innerHTML = html;
    }
    
</script>

<div class="sidebar" id="sidebar_$pns" >
<script>
    LoadRSSFeed_$pns("sidebar_$pns","$feedUrl.getData()");
</script>
    #if ($validator.isNotNull($moreUrl.getData()))
        <a class="more skip-external-icon rounded-button-link" style="margin: 5px 10px; float: right" href="$moreUrl.getData()">View All</a>
    #end
    
</div>

#if ($validator.isNotNull($fldHeight.getData()) && $fldHeight.getData() != "0" )
	<style>
	    #sidebar_$pns {
	        height: $fldHeight.getData()px; 
	        overflow-x: hidden;
	    }
	    #sidebar_.$pns:hover {
	        overflow-y: scroll;
	        overflow-x: hidden;
	    }
	</style>
#end