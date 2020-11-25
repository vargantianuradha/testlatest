<div class="sidebar">
<h2>$title.getData()</h2>
<ul class="no-list" style="margin-left:0">
#foreach ($link in $linkText.getSiblings())
<li class="linkitem"><a href="$link.linkUrl.getData()">$link.getData()</a>
#end
</div>