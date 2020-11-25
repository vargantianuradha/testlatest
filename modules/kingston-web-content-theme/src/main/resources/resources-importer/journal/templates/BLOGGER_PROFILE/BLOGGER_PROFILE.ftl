#set($name = $name.getData())
#set($occupation = $occupation.getData())
#set($image_alt = $alt.getData())
#set($emailadd = $email.getData())
#set($phone = $phone.getData())

#if ($bloggerprofile.getData() != '')
	#set($image_url = $bloggerprofile.getData())
#end
#if ($internallink.getData() != '')
	 #set($link_url = $internallink.getFriendlyUrl())
#end
#if ($externallink.getData() != '')
	 #set($link_url = $externallink.getData())
#end


<div class="bloggerprofile">
#if ($internallink.getData() != '')
	<a class="blogger-image" href="$link_url" title="$name">
		<image src="$image_url" alt="$name"/>
	</a>
#else <image src="$image_url" alt="$name"/>
#end
	
	<div class="blogger-name">$name</div>
	<div class="blogger-occupation">$occupation</div>
	#if ($email.data != "")
	<div class="blogger-email"><a class="skip-external-icon" href="mailto:$emailadd">$emailadd</a></div>
	#end
	#if ($phone.data != "")
	<div class="blogger-phone">$phone</div>
	#end
##	#if (($facebookurl.data != "") || ($twitterurl.data != ""))
	<div>
	  #if ($facebookurl.data != "")
	  <a class="skip-external-icon" href="$facebookurl.getData()"><img src="@theme_image_path@/common/Logo_Facebook_large.png"></a>
	  #end
	  #if ($twitterurl.data != "")
	  <a class="skip-external-icon" href="$twitterurl.getData()"><img src="@theme_image_path@/common/Logo_Twitter_large.png"></a>
	  #end
	</div>  
##	#end
	
</div>