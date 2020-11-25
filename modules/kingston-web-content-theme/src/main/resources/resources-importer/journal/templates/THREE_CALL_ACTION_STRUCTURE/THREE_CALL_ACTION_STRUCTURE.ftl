<div class="call-to-action-container">
    <div class="column four">
		<div class="call-to-action">
    	#if ($validator.isNotNull($callToAction1.getData()))
    		#set ($url = $callToAction1.linkToUrl.getData())
    		#if ($url == '' && $validator.isNotNull($callToAction1.linkToUrl.linkToPage.getFriendlyUrl()))
    			#set ($url = $callToAction1.linkToUrl.linkToPage.getFriendlyUrl() )
    		#end
			<div class="action-image">
				<a class="skip-external-icon" href="$url"> <img alt="Go to article" class="action-small-image" src="$callToAction1.image.getData()" /> </a></div>
			<div class="action-content">
				<h3 class="action-title">
					<a class="skip-external-icon" href="$url">$callToAction1.getData()</a></h3>
				<div class="action-description">
					$callToAction1.description.getData() </div>
			</div>
		#end
		</div>
	</div>
    <div class="column four">
    	<div class="call-to-action">
    	#if ($validator.isNotNull($callToAction2.getData()))
        	#set ($url = $callToAction2.linkToUrl.getData())
    		#if ($url == '' && $validator.isNotNull($callToAction2.linkToUrl.linkToPage.getFriendlyUrl()))
    			#set ($url = $callToAction2.linkToUrl.linkToPage.getFriendlyUrl() )
    		#end
			<div class="action-image">
				<a class="skip-external-icon" href="$url"> <img alt="Go to article" class="action-small-image" src="$callToAction2.image.getData()" /> </a></div>
			<div class="action-content">
				<h3 class="action-title">
					<a class="skip-external-icon" href="$url">$callToAction2.getData()</a></h3>
				<div class="action-description">
					$callToAction2.description.getData() </div>
			</div>
		#end
		</div>
	</div>
    <div class="column four">
    	<div class="call-to-action">
    	#if ($validator.isNotNull($callToAction3.getData()))
        	#set ($url = $callToAction3.linkToUrl.getData())
    		#if ($url == '' && $validator.isNotNull($callToAction3.linkToUrl.linkToPage.getFriendlyUrl()))
    			#set ($url = $callToAction3.linkToUrl.linkToPage.getFriendlyUrl() )
    		#end
			<div class="action-image">
				<a class="skip-external-icon" href="$url"> <img alt="Go to article" class="action-small-image" src="$callToAction3.image.getData()" /> </a></div>
			<div class="action-content">
				<h3 class="action-title">
					<a class="skip-external-icon" href="$url">$callToAction3.getData()</a></h3>
				<div class="action-description">
					$callToAction3.description.getData() </div>
			</div>
		#end
		</div>
	</div>
</div>