package com.jaxbot.viscen;

import android.app.Activity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.HttpAuthHandler;

import android.content.SharedPreferences;

public class MainActivity extends Activity
{
    public static final String PREFS_NAME = "viscensettings";
    private WebView webview;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);
        webview.setWebViewClient(new AuthWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadUrl("http://me.jaxbot.me/viscen/?c=" + System.currentTimeMillis());

        setContentView(webview);
    }

    @Override
    protected void onStop() {
        super.onStop();

        webview.loadUrl("about:blank");
    }

    private class AuthWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            handler.proceed(settings.getString("username", ""), settings.getString("password", ""));
        }
    }
}

