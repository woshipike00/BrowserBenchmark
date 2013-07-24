package com.woshipike.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Scroller;

public class ScrollWebview extends WebView{

	
	private Context context;
	private Scroller scroller;
	private boolean tag=false;


	public ScrollWebview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		scroller=new Scroller(context);
		//Log.v("webview", "construct:1");

	}
	
	public ScrollWebview(Context context,AttributeSet set){

		super(context, set);
		this.context=context;
		scroller=new Scroller(context);
		//Log.v("webview", "construct:2");

	}
	
	



	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		Log.v("webview", "computescroll "+tag);
		if(tag && scroller.computeScrollOffset()) {
			Log.v("webview", ""+tag);
			int x=scroller.getCurrX();
	    	int y=scroller.getCurrY();
	    	Log.v("webview", x+", "+y);
	    	//Log.v("webview", getScrollX()+", "+getScrollY());
            Log.v("webview", x+", "+y);
	    	//scrollTo(x, y);
	    	//postInvalidate();
	    	scrollBy(1, 1);	
		}
		super.computeScroll();
	}

	
	public void startcompute(){
		computeVerticalScrollOffset();
	}

	
	
	public Scroller getScroller(){
		return scroller;
	}
	
	public void settag(){
		tag=true;
	}
	

}
