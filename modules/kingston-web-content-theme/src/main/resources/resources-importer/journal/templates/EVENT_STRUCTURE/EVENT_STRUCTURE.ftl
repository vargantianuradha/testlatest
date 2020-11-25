<div class="feature-event-container" >
    <div class="event-image" >
    	<a class="skip-external-icon" href="$eventUrl.getData()"><img style="width: 100%;" src="$eventImage.getData()" alt="$eventTitle.getData()"/></a>
	</div>
	<div class="event-details">
		#if ($validator.isNotNull($eventTitle.getData()))
			<h4>$eventTitle.getData()</h4>
		#end
        #if ($validator.isNotNull($eventDate.getData()))
		    <div class="event-date" >$eventDate.getData()</div>
        #end
        #if ($validator.isNotNull($eventLocation.getData()))
		    <div class="event-location" >$eventLocation.getData()</div>
        #end
        <div class="event-description">
			$eventDesc.getData()
        </div>
	</div>
</div>