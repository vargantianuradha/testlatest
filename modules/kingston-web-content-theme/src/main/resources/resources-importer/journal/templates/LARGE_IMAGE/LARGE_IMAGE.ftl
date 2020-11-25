#if ($pic.picLink.data != "")
<a class="skip-external-icon" href="$pic.picLink.getData()">
<img  style="max-width:100%;width:100%;padding-bottom:5px;" src="$pic.getData()" alt="$pic.altText.getData()"></a>
#else
<img  style="max-width:100%;width:100%;padding-bottom:5px;" src="$pic.getData()" alt="$pic.altText.getData()">
#end