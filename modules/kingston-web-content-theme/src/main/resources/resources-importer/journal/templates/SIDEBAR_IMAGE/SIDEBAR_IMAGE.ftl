#if ($pic.picLink.data != "")
<a class="skip-external-icon" href="$pic.picLink.getData()"> <img class="innerimage"  src="$pic.getData()" alt="$pic.altText.getData()"></a>
#else
<img class="innerimage"  src="$pic.getData()" alt="$pic.altText.getData()"></a>
#end
#if ($pic.picTitle.data != "")
<p style="text-align:center">$pic.picTitle.getData()<p>
#end