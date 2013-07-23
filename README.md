MyWebview
=========
To enable the webview to scroll to a specified position, we need to use the scrollto() or scrollby() method after the
webpage has been loaded successfully. (That means do sth in the onProgressChanged() method ,not the onPageFinished() in
webChromClient!!!)

The challenge is to scroll the webview smoothly, nobody on the Internet gives the answer...finally I find that not in the
computeScroll() method but the computeHorizontalScrollOffset() or the computeHorizontalScrollRange() method to invoke the 
scrollto() method. It took me the whole night to find the answer...
