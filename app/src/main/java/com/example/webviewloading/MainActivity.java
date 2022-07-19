package com.example.webviewloading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    boolean redirect = false;
    boolean completely_loading = true;
    WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mywebView = (WebView) findViewById(R.id.mywebview);
        mywebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               if(completely_loading){
                   redirect = true;
               }
               completely_loading = false;
               mywebView.loadUrl(url);
                return true;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                completely_loading = false;
                Log.d("completely_loading",completely_loading+"");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!redirect){
                    completely_loading = true;
                }
                if(completely_loading && !redirect){
                    Log.d("completely_loaded","completely loaded");
                    Log.d("completely_loaded","page is completely loaded");
                }
                else{
                    redirect = false;
                }
            }
        });

        mywebView.getSettings().setUserAgentString("Chrome/56.0.0 Mobile");
        mywebView.loadUrl("https://www.dollarbirdinc.com/");


    }
}