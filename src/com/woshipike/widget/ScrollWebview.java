package com.woshipike.widget;

import java.util.Timer;

import android.content.Context;
import android.support.v4.util.TimeUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
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

	}
	
	public ScrollWebview(Context context,AttributeSet set){

		super(context, set);
		this.context=context;
		scroller=new Scroller(context,new LinearInterpolator());

	}
	
	



	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if(tag && scroller.computeScrollOffset()) {
			int x=scroller.getCurrX();
	    	int y=scroller.getCurrY();
	    	scrollTo(x, y);
	    	postInvalidate();
	
		}
		super.computeScroll();
	}


	
	
	public Scroller getScroller(){
		return scroller;
	}
	
	public void settag(){
		tag=true;
	}
	

}
