#set ($title = "Links")

#if ($validator.isNotNull($titleText.getData()))
	#set ($title = $titleText.getData())
#end
<div class="sidebar no-list">
<h2>$title</h2>
<ul class="no-list" style="margin:0">
#foreach ($link in $linktext.getSiblings())
<li class="linkitem">
<a href="$link.urltext.getData()">$link.getData()</a>
</li>
#end
</ul>
</div>