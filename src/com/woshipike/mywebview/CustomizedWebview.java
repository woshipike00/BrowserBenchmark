package com.woshipike.mywebview;

import com.woshipike.widget.ScrollWebview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

public class CustomizedWebview extends Activity {
	
	private TextView URL;
	private Button go;
	private ScrollWebview webView;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_customized_webview);
		UIInit();
		WebviewConf();
	}
	
	public void UIInit(){
		URL=(TextView)findViewById(R.id.editText1);
		go=(Button)findViewById(R.id.button1);
		webView=(ScrollWebview)findViewById(R.id.webView1);
		context=CustomizedWebview.this;
		
		go.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String url=URL.getText().toString();
				String url="http://www.jd.com";
				webView.loadUrl(url);
				
			}
			
		});
	}
	
	public void WebviewConf(){
		
		webView.getSettings().setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
			public void onPageFinished(WebView view, String url) {
		        super.onPageFinished(view, url);
		        //Log.v("webview", "page loaded!");

		    } 
			
			
			
		});
		
		webView.setWebChromeClient(new WebChromeClient(){
			public void onProgressChanged(WebView view, int progress) {
			     // Activities and WebViews measure progress with different scales.
			     // The progress meter will automatically disappear when we reach 100%
			     Log.v("webview",progress+"");
			     if(progress==100){
			    	 Log.v("webview", "page loaded!");
				     Scroller scroller=webView.getScroller();
				     webView.settag();
				     scroller.startScroll(0, 0, 0, 100, 4000);
				     webView.postInvalidate();
			     }
			   }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customized_webview, menu);
		return true;
	}

}
