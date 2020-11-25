<div class="artist-profile-div">
<img class="artist-image" src="$artistPic.getData()"/>
<h3 style="display:inline">Name: $artistName.getData()</h3>
<h3>Description:</h3>
$practice.getData()
</div>
<div class="artist-projects-div">
<h3>Projects</h3>
#foreach ($proj in $project.getSiblings())
<div class="artist-project">
<h4 style="min-height:2.5em">$proj.getData()</h4>
<img src="$proj.photo.getData()" style="width:90%"/>
<p>$proj.projectDesc.getData()</p>
</div>
#end
</div>