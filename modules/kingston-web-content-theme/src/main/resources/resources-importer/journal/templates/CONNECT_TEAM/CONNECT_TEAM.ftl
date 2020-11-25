<style>
.profiles {
  margin-bottom: -20px;
}
.intro {
  padding-left: 140px;
}
.intro h2 {
  margin: 0 0 7px;
}
.intro .lead {
  line-height: 120%;
  font-size: 1.1em;
  font-style: italic;
  margin: 0 0 35px;
}
.profile {
  position: relative;
  margin: 0 0 20px;
}
.profile:nth-child(even) {
  clear: left;
}
.profile-header {
  position: absolute;
  top: 0;
}
.profile-header img {
  float: left;
  border-radius: 50%;
  width: 100px;
  height: 100px;
}
.profile-content {
  font-size: 14px;
  padding: 0 20px 0 0;
  line-height: 1.4em;
  margin: 0 0 0 125px;
}
.profile-content h3 {
  margin: 0;
}
.profile-content .lead {
  font-size: 1.3em;
  line-height: 100%;
  font-style: italic;
  margin: 3px 0 20px;
}
/*
.profile-content:before {
  content: '';
  width: 36px;
  height: 3px;
  background: #dededc;
  position: absolute;
  top: 0;
}
*/
.profile-content p {
  margin: 0 0 10px;
}
.profile-footer {
  position: absolute;
  top: 121px;
  width: 100px;
  text-align: center;
}
.profile-footer a {
  line-height: 18px;
  margin: 0 3px;
  display: inline-block;
}
.profile-footer a img {
	width: 24px;
}



</style>

<section class="row profiles">
#foreach($person in $name.getSiblings())
<article class="column twelve profile">

	<div class="profile-header">
		<img src="$person.picture.getData()" alt="Picture of $person.getData()" />
	</div>
	<div class="profile-content">
		<h3>$person.getData()</h3>
		<p class="title">$person.title.getData()</p>
		<p class="biography">$person.biography.getData()</p>
	</div>
	<div class="profile-footer">
		#if ($validator.isNotNull($person.phonenumber.getData()))
			<a href="tel:$person.phonenumber.getData()" title="Call $htmlUtil.escapeAttribute($person.phonenumber.getData())" class="skip-external-icon" ><img src="@theme_image_path@/social/phone.svg" alt="" /></a>
		#end
		#if ($validator.isNotNull($person.email.getData()))
			<a href="mailto:$person.email.getData()" title="Email $htmlUtil.escapeAttribute($person.getData())" class="skip-external-icon"><img src="@theme_image_path@/social/email.svg" alt="" /></a>
		#end
		
		#if ($validator.isNotNull($person.linkedInurl.getData()))
			<a href="$person.linkedInurl.getData()" title="Go to LinkedIn account" class="skip-external-icon"><img src="@theme_image_path@/social/linkedIn.svg" alt="" /></a>
		#end
		
		#if ($validator.isNotNull($person.facebookurl.getData()))
			<a href="$person.facebookurl.getData()" title="Go to Facebook page" class="skip-external-icon"><img src="@theme_image_path@/social/facebookF.svg" alt="" /></a>
		#end
		
		#if ($validator.isNotNull($person.twitterurl.getData()))
			<a href="$person.twitterurl.getData()" title="Go to Twitter account" class="skip-external-icon"><img src="@theme_image_path@/social/twitterBird.svg" alt="" /></a>
		#end
	</div>
</article>
#end
</section>