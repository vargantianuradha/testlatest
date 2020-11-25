<div class="picture-menu">
<ul>
#foreach ($picture in $pic.getSiblings())
	<li class="picture-menu-item">
	
	#set ($url = "")
	#if ($picture.internalLink.data != "")
		#set ($url = $picture.internalLink.getFriendlyUrl())
	#elseif ($picture.externalLink.data != "")
		#set ($url = $picture.externalLink.getData())
	#end
	
	#set ($altText = $picture.altText.getData())
	#if ($altText == $picture.caption.getData())
		#set ($altText = "")
	#end
	
	<a class="skip-external-icon" href="$url">
		<img src="$picture.getData()" alt="$altText">
		#if ($picture.caption.data != "")
			<span class="caption">$picture.caption.getData()</span>
		#end
	</a>

	</li>
#end
</ul>
</div>