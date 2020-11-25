<link rel="stylesheet" media="screen" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.1/css/font-awesome.min.css">

<style>

.timeline ul.event-list {
    margin: 0;
    padding: 10px;
    border: 0;
    list-style: none;
}

.timeline ul.event-list li {
    border-bottom: 0;
    padding: 0;
    margin: 0;
    background: none;
    overflow: visible !important;
}

.timeline li .event-content {
    padding: 0 10px 20px 29px;
    min-height: 50px;
    border-left: 2px solid #909090;
}

.timeline .event-content h3 {
    color: inherit;
    margin: 0 0 4px 0;
    font-size: 14px;
    font-weight: bold;
}

.timeline li:last-child .event-content {
    border-left: 0;
    margin-left: 2px;
}

/*-- Icon Rules --*/
.timeline .event-status_span {
    display: block;
    border-radius: 50%;
    float: left;
    margin-left: -43px;
    border: 4px solid;
    background-color: #eee;
    border-color: #909090;
    position: relative;
}

.timeline .event-status_span .icon {
    border-radius: 50%;
    margin: 0 0 0 0;
    width: 12px;
    height: 12px;
    border: 3px solid #eee;
}

.timeline .event-status--current .icon {
    background-color: #315688;
}

.timeline .event-status--finished .icon {
	font-family: FontAwesome;
	color: #315688;
}
.timeline .event-status--finished .icon:before {
    content: "\f00c";
    top: -8px;
    left: 1px;
    font-size: 23px;
    
}

.timeline .event-status--finished .icon:before,.timeline .event-status--current .icon:before {
    speak: none;
    position: absolute;
}



</style>
<div class="timeline">
    <ul class="event-list">
		#foreach ($event in $heading.getSiblings())
			#set ($eventStatus = $event.status.getData())
			
			<li class="">
				<div class="event-content">
					<span class="event-status_span event-status--$eventStatus">
						<div class="icon"></div>
					</span>
					<h3>$event.getData()</h3>
					<div>
						$event.description.getData()
					</div>			
				</div>
			</li>
		#end
	</ul>
</div>