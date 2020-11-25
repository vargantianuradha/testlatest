#if ($date.data != "")
<p style="font-weight:bold">$date.getData()</p>
#end
#if ($image.data != "")
#if ($image.imagelink.data != "")
<a href="$image.imagelink.getData()"><img class="newsimage" src="$image.getData()"></a>
#else
<img class="newsimage" src="$image.getData()">
#end
#end
$body.getData()