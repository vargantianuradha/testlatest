#if ($largephoto.link.data != "")
<a class="skip-external-icon" href="$largephoto.link.getData()">
<img  style="max-width:100%;width:100%;padding-bottom:5px;" src="$largephoto.getData()" alt="$largephoto.alttext.getData()"></a>
#else
<img  style="max-width:100%;width:100%;padding-bottom:5px;" src="$largephoto.getData()" alt="$largephoto.alttext.getData()">
#end