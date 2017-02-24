package com.snapin.snapins;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;



/**
 * LoginActivity class where user can do signup or signin
 *
 * @author mobinius
 */
public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }

    private void init() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, com.snapin.snapins.ocr.MainActivity.class));
            }
        }, 1000);
    }
}
