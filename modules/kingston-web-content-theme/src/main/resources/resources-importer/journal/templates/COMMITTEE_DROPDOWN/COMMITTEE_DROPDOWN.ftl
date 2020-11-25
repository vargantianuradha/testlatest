#set ($ns = $request.portlet-namespace)
#set ($title = "Select a committee")

#if ($validator.isNotNull($titleText.getData()))
	#set ($title = $titleText.getData())
#end

<script>
function ${ns}gotoURL() {
	var url = document.getElementById("${ns}goToUrl");

	if (url.value != "")
		location.href = url.value;
		
	return false;
}
</script>
<div class="sidebar no-list">

<form name="${ns}quick-nav-go" onsubmit="return ${ns}gotoURL()" type="GET">
<h2><label for="${ns}goToUrl">$title</label></h2>
<select name="${ns}goToUrl" id="${ns}goToUrl" style="width:calc(100% - 10px);margin:0 5px;white-space:pre-wrap">
#foreach ($navitem in $linktext.getSiblings())
   <option value="$navitem.urltext.getData()">$navitem.getData()</option>
#end
</select>
<div style="text-align: right; padding: 3px">
   <input type="submit" value="Go">
</div>
</form>
</div>