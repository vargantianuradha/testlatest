<h3>Meeting Minutes</h3>
<table class="agendaTable">
<tr>
<th colspan="2" class="agendaHeader">
$meetingNumber.getData()</th>
</tr>
#foreach ($item in $meetingNumber.meetingItem.getSiblings())
#set ($fileTitle = $item.meetingDoc.getData().split("/").get(4))
#set($fileTitle = $httpUtil.decodeURL($htmlUtil.unescape($fileTitle)))

<tr>
#if ($item.data() != "")
  <td style="width:50%;">
  <span style="font-weight:bold">$item.getData()</span>
  </td>
#end
#if ($item.meetingDoc.data() != "")  
  <td style="text-align:right;width:50%;">
  <a href="$item.meetingDoc.getData()">$fileTitle</a></br>
  </td>
#end
</tr>
#if ($item.meetingDesc.data() != "")
<tr>
  <td class="itemDesc" colspan="2">
  $item.meetingDesc.getData()
  </td>
</tr>
#end
#end
</table>