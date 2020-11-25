#set($timerInt= 0)
#set($mobile_image= '')
<span class="timerInput" id="timerInput">$timerInt.parseInt($timer.getData())</span>
<span class="menu-color" id="menu-color">$menubackground.getData()</span>
<span class="menu-item-color" id="menu-item-color">$menuitembackground.getData()</span>
<div id="homepageCarousel">
	<h2 class="aui-helper-hidden-accessible">Showcase</h2>
 
	
	    #set ( $img-array = [$carouselimg1,$carouselimg2,$carouselimg3,$carouselimg4,$carouselimg5,$carouselimg6] )
		#foreach($image in $img-array)
	 
		#if ($image.getChildren().get(3).getData() !=   '')
			 #set($link_url = $image.getChildren().get(3).getData())
		#end
		#if ($image.getChildren().get(4).getData() != '')
			 #set($link_url = $image.getChildren().get(4).getData())
		#end  
	    		 
	    			<a class="image-viewer-base-image" href="$link_url.replace("/web/guest","")" title="$image.getChildren().get(1).getData()">
	    				<img src="$image.getData()" alt="$image.getChildren().get(2).getData()"/>
	    			</a> 
					  
	    		 
	        
		#end 
</div>

#if ($image.getChildren().get(0).getData() !=   '') 
	<div id="mobileCarousel" class="mobile-carousel"> 
		<a href="$link_url.replace("/web/guest","")" title="$image.getChildren().get(1).getData()">
	    				<img src="$image.getData()" alt="$image.getChildren().get(2).getData()"/>
	    </a> 
	</div>
#end

#set ($has-call-to-action = false)
#set ( $call-action-array = [$calltoaction1,$calltoaction2,$calltoaction3,$calltoaction4,$calltoaction5] )
   
#foreach($call-action in $call-action-array)   
	
   #if ($call-action.getChildren().get(2).getData() !=   '')
			 #set($link_url = $call-action.getChildren().get(2).getFriendlyUrl())
		#end
		#if ($call-action.getChildren().get(3).getData() != '')
			 #set($link_url = $call-action.getChildren().get(3).getFriendlyUrl())
		#end  
    #if ($call-action.getData() != '')

        #if (!$has-call-to-action)
            #set ($has-call-to-action = true)
            <div id="call-to-action" class="call-to-action-container">
            <h2 class="aui-helper-hidden-accessible"></h2>
            <ul class="no-list centered-block">
        #end 
        <li class="call-to-action-button" style="background:   $call-action.getChildren().get(0).getData()  ; padding: 0;"> 
            <a href="$link_url.replace("/web/guest","")" title="$call-action.getChildren().get(5).getData()">
                <img src="$call-action.getData()" alt="$call-action.getChildren().get(4).getData() "/>
                $call-action.getChildren().get(1).getData() 
				
            </a>    
        </li>
	#end
#end 
#if ($has-call-to-action)
    </ul>
    </div>
#end