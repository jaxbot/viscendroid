package com.jaxbot.viscen;

import android.app.Activity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.HttpAuthHandler;

import android.content.SharedPreferences;
import android.content.Context;

import android.app.ActionBar;
import android.view.MenuInflater;
import android.view.Menu;

public class SettingsActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}


