<h3>Meeting Agendas, Minutes & Reports</h3>

<table class="agendaTable">
<tr>
	#if (($meetingNumber.streamLink.getData() != "") && ($validator.isNotNull($meetingNumber.streamLink)))
		<th class="agendaHeader">
			$meetingNumber.getData()
			<span style="float:right;"><a style="font-weight: normal;" href="$meetingNumber.streamLink.getData()" class="rounded-button-link" target="_blank">Watch video</a></span>
		</th>
	#else
		<th class="agendaHeader">
			$meetingNumber.getData()
		</th>
	#end
</tr>

#foreach ($item in $meetingNumber.meetingItem.getSiblings())
		
	#if ($validator.isNotNull($item.meetingDoc.getData()))
	
		#set($fileTitle = "")
		#set($fileTitle = $item.meetingDoc.getData().split("/").get(4))
  		#set($fileTitle = $httpUtil.decodeURL($htmlUtil.unescape($fileTitle)))		
  		
  		<tr>
			<td>
				<a style="font-weight:bold" href="$item.meetingDoc.getData()">$item.getData()</a>
			</td>
		</tr>
	#else
		<tr>
			<td >
				  	<span style="font-weight:bold">$item.getData()</span>
			</td>
		</tr>
	#end
	
	#if ($validator.isNotNull($item.meetingDesc.getData()) )
		<tr>
		  	<td class="itemDesc" colspan="2">
		  		$item.meetingDesc.getData()
		  	</td>
		</tr>
	#end

#end

#foreach ($part in $section.getSiblings())
	<tr>
		<th class="sectionHeader">
			$part.getData()
		</th>
	</tr>
	
	#foreach ($blah in $part.agendaItem.getSiblings())

		#if ($validator.isNotNull($blah.doc.getData()))
		
			#set($fileTitle = "")
			#set ($fileTitle = $blah.doc.getData().split("/").get(4))
	  		#set ($fileTitle = $httpUtil.decodeURL($htmlUtil.unescape($fileTitle)))
		
			<tr>
				<td>
					<a style="font-weight:bold" href="$blah.doc.getData()">$blah.getData()</a>
				 </td>
			</tr>
		#else
			<tr>
			  	<td>
			  		<span style="font-weight:bold">$blah.getData()</span>
			  	</td>
			</tr>
		#end
		
		#if ($validator.isNotNull($blah.itemDesc.getData()) )
			<tr>
			  <td class="itemDesc">
			  	$blah.itemDesc.getData()
			  </td>
			</tr>
		#end
	#end
#end
</table>