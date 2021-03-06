package me.jaxbot.viscendroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onStop() {
        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        String username = ((EditText) findViewById(R.id.username_txt)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_txt)).getText().toString();

        editor.putString("username", username);
        editor.putString("password", password);

        editor.commit();

        super.onStop();
    }
}


