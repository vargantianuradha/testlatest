<!-- Begin of webcam code -->

<!-- Include webcam libs -->
<script src="https://relay.ozolio.com/js/jozolio.js" type="text/javascript"></script>
<script src="https://relay.ozolio.com/js/jwebcam.js" type="text/javascript"></script>
<script src="https://relay.ozolio.com/js/swfobject.js" type="text/javascript"></script>
<!-- Register webcam holder -->
<div id="webcam_container" class="container">
 <div id="webcam_holder"></div>
</div>
<!-- Create webcam viewer -->


<script type="text/javascript">
var sOzolioWebcamId = '$camId.getData()';
</script>

<script type="text/javascript">
if(typeof sOzolioWebcamId !== 'undefined' && sOzolioWebcamId.length){
	var camvars = {
		domain: "https://relay.ozolio.com",
		camera_doc: sOzolioWebcamId,
		app_mode: "view",
		logo_url: '$camId.logoUrl.getData()',
		logo_link: '$camId.logoLink.getData()'
	};
	
	
	var params = {
		allowFullScreen: "true",
		allowScriptAccess: "always"
	};
	
	var webcam = new jwebcam(camvars, "webcam_holder", 800, 450, params);
	
	$(document).ready(function(){
		var $playerOuter = $('#webcam_holder'),
			iRatio = 800 / 450,
			tmrPlayer;
		
		// Someone take IE7 out to the field and shoot it already...
        var heightcalc = Math.round($playerOuter.width() / iRatio);
		if($('html').hasClass('ie7')){
			$playerOuter.css({
				height: heightcalc,
                padding: 0
			});
			
			$(window).on('resize', function(e){
				clearTimeout(tmrPlayer);
				
				setTimeout(function(){
					$playerOuter.css('height', heightcalc);
				}, 100);				
			});
		}
	});
}
</script>
<!-- End of webcam code -->