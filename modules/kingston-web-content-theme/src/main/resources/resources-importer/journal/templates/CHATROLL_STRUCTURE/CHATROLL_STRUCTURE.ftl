#if ($eventState.getData() == "preEvent")

<div style="width:100%">
	<div class="portlet-msg-alert" style="max-width:100%">
    	The event has not yet started. Check back at the listed start time.
	</div>
	<div class="chat-event-info">
    	$eventInfo.getData()
	</div>
</div>

<script>
function reload(){
    location.reload(true);
}

setTimeout(reload,60000);

</script>

#elseif ($eventState.getData() == "inProgress")

<div id="chat-module">
    <iframe src="$iframeUrl.getData()" title="Chatroll" width="100%" height="100%" frameborder="0;"></iframe>
</div>
<div class="chat-event-info">
      $eventInfo.getData()
</div>

#elseif ($eventState.getData() == "finished")

$postEventMessage.getData()
<div class="chat-event-info">
    $eventInfo.getData()
</div>
#end