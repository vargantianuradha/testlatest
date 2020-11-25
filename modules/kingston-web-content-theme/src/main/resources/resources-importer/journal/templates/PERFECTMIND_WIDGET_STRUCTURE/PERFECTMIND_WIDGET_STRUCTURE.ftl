<div class="perfectMindWidget">

#if ($validator.isNotNull($title.getData()))
<h2 class="title">
	$title.getData()
</h2>
#end

#if ($validator.isNotNull($headerText.getData()))
<div class="description">
	$headerText.getData()
</div>
#end

<div id="bookMe4Widget">
#if ($validator.isNotNull($widgetURL.getData()))

    <object data="$widgetURL.getData()&embed=true" type="text/html">
		<div id="errorContainer">
			The dynamic schedule view is not available.
		</div>
	</object>
	<script type="text/javascript">
        var widget = document.getElementById('bookMe4Widget');
        widget.children[0].style.width = "100%";
        widget.children[0].style.height = "100%";

        window.addEventListener('message', function (e) {
            // need to pad the height returned by 30 px to prevent scroll bars
            var heightStr = e.data;
            heightStr.replace( /^\D+/g, '');
            var height = parseInt(heightStr) + 30;
            widget.children[0].style.height = height + "px";
        }, false);
    </script>
    <div class="">
        <a target="perfect_mind" href="$widgetURL.scheduleURL.getData()">View the full schedule</a>
    </div>

#else
	<div id="errorContainer">
		The PerfectMind widget has not been configured.
	</div>
#end
</div>

</div>