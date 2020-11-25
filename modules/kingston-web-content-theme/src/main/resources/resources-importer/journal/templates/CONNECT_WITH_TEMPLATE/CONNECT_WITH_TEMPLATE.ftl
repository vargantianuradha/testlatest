<h2 style="text-align: center;">
	$name.title.getData()</h2>
<span class="profile-box" style="display:block;padding-bottom:5px;">
	<h3 style="padding-left:5px;margin: .5em 0;text-align:center;c">Connect with $name.getData()</h3>

	<div style="display:inline-block; vertical-align:middle;float:left;">
		#if ($name.facebookurl.data != "")
		  <a class="skip-external-icon" href="$name.facebookurl.getData()" style="text-decoration:none;" title="Follow on Facebook">
		  <img class="large-icon" alt="Facebook logo. Links to Facebook account." src="@theme_image_path@/common/Logo_Facebook_large.png"/>
		  </a>
		#end
		#if ($name.twitterurl.data != "")
		  <a class="skip-external-icon" href="$name.twitterurl.getData()" style="text-decoration:none;" title="Follow on Twitter">
		  <img class="large-icon" alt="Twitter logo. Links to twitter account." src="@theme_image_path@/common/Logo_Twitter_large.png"/>
		  </a>
		#end
		#if ($name.bloglink.data != "")
		  <a class="skip-external-icon" href="$name.bloglink.getFriendlyUrl()" style="text-decoration:none;" title="Go to blog page">
		  <img class="large-icon" alt="Blog logo. Links to blog page." src="@theme_image_path@/common/Logo_Blog_large.png"/>
		  </a>
		#end
		#if ($name.siteurl.data != "")
		  <a class="skip-external-icon" href="$name.siteurl.getData()" style="text-decoration:none;" title="Go to website">
		  <img class="large-icon" alt="Web site logo. Links to contact's web page." src="@theme_image_path@/common/Logo_Website_large.png"/>
		  </a>
		#end
		#if ($name.email.data != "")
		  <a class="skip-external-icon" href="mailto:$name.email.getData()" style="text-decoration:none;" title="Email this contact">
		  <img class="large-icon" alt="Email logo. Opens a new message to the contact." src="@theme_image_path@/common/Logo_Email_large.png"/>
		  </a>
		#end
	</div>

</span>
<div style="clear:both">
#if ($misc.data != "")
$misc.getData()
#end
</div>