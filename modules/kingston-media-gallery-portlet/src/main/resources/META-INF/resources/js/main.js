 
if(!$('.elastislide-list').is(':visible')){
	$(".elastislide-list").each(function() {
		$(this).elastislide({
			speed: 700,
			minItems: 1
		})
	});
}
$(".fancybox").fancybox({
    helpers: {
        title: {
            type: "inside"
        }
    },
    padding: 5,
    beforeShow: function() {
        this.title = this.element.next(".thumbnail-metadata").html(), this.minWidth = getBrowserMinWidth(this.margin[1] + this.margin[3], this.minWidth), this.maxWidth = getBrowserMaxWidth(this.margin[1] + this.margin[3], this.maxWidth)
    }
});

function getBrowserMinWidth(a, b) {
    if (isDesktopDevice()) {
        return b
    } else {
        return window.innerWidth - a
    }
}

function getBrowserMaxWidth(a, b) {
    if (isDesktopDevice()) {
        return b
    } else {
        return window.innerWidth - a
    }
}

function isDesktopDevice() {
    return !AUI().one("html").hasClass("mobile") && !AUI().one("html").hasClass("touch")
};