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
	protected int computeVerticalScrollOffset() {
		// TODO Auto-generated method stub
		Log.v("webview", "computeoffset");
		scrollBy(1, 1);
		return super.computeVerticalScrollOffset();
	}

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		//Log.v("webview", "***");
		if(!tag) return;
		int i=0;
		while(scroller.computeScrollOffset()){
			int x=scroller.getCurrX();
	    	int y=scroller.getCurrY();
	    	//Log.v("webview", x+", "+y);
	    	//Log.v("webview", getScrollX()+", "+getScrollY());

	    	//scrollTo(x, y);
	    	//postInvalidate();

        }
		i++;
		super.computeScroll();
	}
	
	
	
	public Scroller getScroller(){
		return scroller;
	}
	
	public void settag(){
		tag=true;
	}
	

}
