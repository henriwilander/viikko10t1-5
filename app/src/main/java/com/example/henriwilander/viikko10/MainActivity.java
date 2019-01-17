package com.example.henriwilander.viikko10;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends Activity {

    WebView web;
    EditText input;
    ArrayList<String> urls;
    ArrayList<String> urls2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        input = findViewById(R.id.editText);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://www.google.fi");
        urls = new ArrayList();
        urls2 = new ArrayList();
        String defaultPage = "http://www.google.fi";
        urls.add(defaultPage);
    }

    public void search(View v) {
        String userInput = input.getText().toString();
        if (userInput.contains("http://")) {
            web.loadUrl(userInput);
            web.loadUrl(userInput);
            urls.add(userInput);
        } else if (!userInput.contains("www") && !userInput.equals("index.html")) {
            userInput = "http://www." + userInput;
            web.loadUrl(userInput);
            urls.add(userInput);
        } else if (userInput.contains("www")) {
            userInput = "http://" + userInput;
            web.loadUrl(userInput);
            urls.add(userInput);
        } else if (userInput.equals("index.html")) {
            userInput = "file:///android_asset/index.html";
            web.loadUrl(userInput);
            urls.add(userInput);
        }
    }

    public void refresh(View v) {
        web.reload();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void executeJavaScript1(View v) {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void executeJavaScript2(View v) {
        web.evaluateJavascript("javascript:initialize()", null);
    }

    public void next(View v) {
        if(urls2.size()<=0) {
            System.out.println("Operaatiota ei voida suorittaa.");
    } else {
        web.loadUrl(urls2.get(urls2.size() - 1));
        urls.add(urls2.get(urls2.size() - 1));
        urls2.remove(urls2.size() - 1);
    }

}

    public void previous (View v) {
        if((urls.size()-1)<=0) {
            System.out.println("Operaatiota ei voida suorittaa.");
        } else {
            web.loadUrl(urls.get(urls.size() - 2));
            urls2.add(urls.get(urls.size() - 1));
            urls.remove(urls.get(urls.size() - 1));
        }
    }

    }
