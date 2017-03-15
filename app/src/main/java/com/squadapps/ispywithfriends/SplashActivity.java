package com.squadapps.ispywithfriends;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squadapps.ispywithfriends.Utils.Constants;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        //Initialize Global Vars
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mPrefsEditor = mPrefs.edit();

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 4);
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            goToMainActivity();
        }

    }

    private void goToMainActivity(){
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 4: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Thanks for permission!", Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                    mPrefsEditor.putBoolean(Constants.PREF_PERMISSION_CAMERA, true);
                    mPrefsEditor.apply();
                } else {
                    mPrefsEditor.putBoolean(Constants.PREF_PERMISSION_CAMERA, false);
                    mPrefsEditor.apply();
                    Toast.makeText(getApplicationContext(), "You can't use this app without granting camera permissions. Sorry :-(", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
}
