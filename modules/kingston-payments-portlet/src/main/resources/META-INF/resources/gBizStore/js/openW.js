
//Function openW: Open a new window centered.

//URL: The target page or image, etc. you want to open.
//TITLE: The title of the new page. If you have 3 links opening this way to the same new window title, every link clicked will target that window. If you name the uniquely, the links will open separate windows.
//WIDTH, HEIGHT: Width and Height in pixels for the new window to be opened. This will be centered on screen.
//LINK: What appears on the page as linking.

function openW(mypage,myname,w,h,features) 
{
	var winl =0;
	var wint =0;
	if(screen.width)
	{
		winl=(screen.width-w)/2;
		wint = (screen.height-h)/2;
	}
	if (winl < 0) winl = 0;
	if (wint < 0) wint = 0;
	var settings = 'height=' + h + ',';
	settings += 'width=' + w + ',';
	settings += 'top=' + wint + ',';
	settings += 'left=' + winl + ',';
	settings += features;	
	win = window.open(mypage,myname,settings);
	win.window.focus();
}


