package com.woshipike.mywebview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class CustomizedWebview extends Activity {
	
	private TextView URL;
	private Button go;
	private WebView webView;
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
		webView=(WebView)findViewById(R.id.webView1);
		context=CustomizedWebview.this;
		
		go.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url=URL.getText().toString();
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
			
		});
		
		webView.setWebChromeClient(new WebChromeClient(){
			public void onProgressChanged(WebView view, int progress) {
			     // Activities and WebViews measure progress with different scales.
			     // The progress meter will automatically disappear when we reach 100%
			     ((CustomizedWebview)context).setProgress(progress * 1000);
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
