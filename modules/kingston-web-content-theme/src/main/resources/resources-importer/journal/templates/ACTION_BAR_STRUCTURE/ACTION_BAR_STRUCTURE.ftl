#set ($numColumns = $icon.getSiblings().size())

<style>
@media only screen and (min-width: 480px) {
    /* For mobile phones: */
    [class*="icon-image-4"] {
        width: 25% !important;
    }
}

.icon-row {
    width: 100%;
    padding: 0px
}

.icon-row .icon-image-4 {
   width: 49%;
   float: left;
   padding: 0;
}

.icon-row .icon-image-3 {
   width: 32%;
   float: left;
   padding: 0;
} 
.icon-row .icon-image-2 {
   width: 49%;
   float: left;
   padding: 0;
} 


</style>

<div class="icon-row">
#foreach ($icon-image in $icon.getSiblings())
    #set ($url = "")
    #if ($icon-image.linktopage.data != "" )
        #set ($url = $icon-image.linktopage.getFriendlyUrl()) 
    #else
	  #set ($url = $icon-image.exturl.getData())
	#end

	<div class="icon-image-$numColumns ">
	  <a class="skip-external-icon" href="$url">
	    <img style="width:100%" src="$icon-image.getData()" alt="$icon-image.alttext.getData()" />
	  </a>
	</div>  
#end
</div>