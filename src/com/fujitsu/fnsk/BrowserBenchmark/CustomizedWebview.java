package com.fujitsu.fnsk.BrowserBenchmark;

import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;

import com.fujitsu.fnsk.Utils.XMLReader;
import com.fujitsu.fnsk.Widget.ScrollWebview;
import com.woshipike.mywebview.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TimeUtils;
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
import android.widget.Toast;

public class CustomizedWebview extends Activity { 
	
	private TextView URL;
	private Button go; 
	private Button start;
	private ScrollWebview webView;
	private Context context;
	private ArrayList<String> list;
	//ensure one scrolling action at a time
	private static boolean isscrolling;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_customized_webview);
		UIInit();
		WebviewConf();
		isscrolling=false;
		
		//parse the scroll xml file
		try {
			XMLReader reader=new XMLReader(context, "scrolltest");
			list=reader.parse();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void UIInit(){
		URL=(TextView)findViewById(R.id.editText1);
		go=(Button)findViewById(R.id.button1);
		start=(Button)findViewById(R.id.button2);
		webView=(ScrollWebview)findViewById(R.id.webView1);
		context=CustomizedWebview.this;
		
		go.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String url=URL.getText().toString();
				if(!url.matches("http.*"))
					url="http://"+url;
				webView.loadUrl(url);
				
			}
			
		});
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				new Thread(new scrollTask(new MyHandler())).start();
				isscrolling=true;
			}
		});
	}
	
	public void WebviewConf(){
		
		webView.getSettings().setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
			
			public void onPageFinished(WebView view, String url) {
		        super.onPageFinished(view, url);
		    } 			
		});
		
		webView.setWebChromeClient(new WebChromeClient(){
			public void onProgressChanged(WebView view, int progress) {
			     if(progress==100){  
			    	 /*
			    	 //solve the problem that some pages maybe loaded more than once
			    	 if(view.getUrl()==null) return;
			    	 //if(view.getUrl()==null) Log.v("webview", "view null");
			    	 //if(webView.getoldurl()==null) Log.v("webview", "webview null");
			    	 if(view.getUrl().equals(webView.getoldurl()))
			    		 return;
			    	 webView.setoldurl(view.getUrl());
				     //once the page is loaded, create a new thread to control the movement of the webview
				     new Thread(new scrollTask(new MyHandler())).start();*/
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
	
	// handle scroll event
	private class MyHandler extends Handler{

		private Scroller scroller;
		
		public MyHandler(){
			scroller=webView.getScroller();
            scroller.setFinalX(0);
            scroller.setFinalY(0);
		}
		
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if(msg.what==0){
				int S=msg.arg1;
				int V=msg.arg2;
				
				int startx=scroller.getFinalX();
				int starty=scroller.getFinalY();
				//alow the webview to scroll
			    webView.settag();
			    scroller.startScroll(startx, starty, 0, S, Math.abs(S*1000/V));
			    webView.postInvalidate();
			}
			
			if(msg.what==1){
				Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
	private class scrollTask implements Runnable {
		private Handler myhandler;
		
		public scrollTask(Handler mHandler){
			myhandler=mHandler;
		}
		
		public void run() {
			
			for (int i=0;i<list.size();i++){
				Log.v("webview", "list: "+i);
				
				int S=Integer.parseInt(list.get(i).split(",")[0]);
				int T=Integer.parseInt(list.get(i).split(",")[1]);
				int V=Integer.parseInt(list.get(i).split(",")[2]);

				String toasttext="step"+(i+1)+": "+
				                 "S="+S+", "+
				                 "T="+T+", "+
				                 "V="+V;
				
				Message msg1=Message.obtain();
				msg1.what=1;
				msg1.obj=toasttext;
				myhandler.sendMessage(msg1);
				
				Message msg2=Message.obtain();
				msg2.what=0;
				msg2.arg1=S;
				msg2.arg2=V;
				myhandler.sendMessage(msg2);
				
				try {
					
					Thread.sleep(T);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			isscrolling=false;
				
		}
	};

}
