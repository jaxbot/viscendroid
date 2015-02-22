package me.jaxbot.viscendroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

public class MainActivity extends ActionBarActivity
{
    public static final String PREFS_NAME = "viscensettings";
    private WebView webview;
    Context context;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume()
    {
        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new AuthWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadUrl("http://me.jaxbot.me/viscen/?c=" + System.currentTimeMillis());

        context = this;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    URL url = new URL("https://jaxbot.me/pics/nissan-leaf-reindeer.jpg");
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    WearAsset.init(context);
                    System.out.println("sending asset");
                    WearAsset.sendAsset(image);

                } catch (Exception e) {
                    System.out.println("boo hoo");
                    System.out.println(e);
                }
                return null;
            }
        }.execute(null, null, null);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        System.out.println("inflating!");
        return true;
    }

    @Override
    protected void onStop() {
        webview.loadUrl("about:blank");

        super.onStop();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AuthWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            SharedPreferences settings = context.getSharedPreferences(MainActivity.PREFS_NAME, 0);
            handler.proceed(settings.getString("username", ""), settings.getString("password", ""));
        }
    }
}


