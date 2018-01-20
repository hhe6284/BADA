package org.techtown.bada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class First extends AppCompatActivity
{

    static final String TAG = "SplashScreenActivity";
    final int delayMilliSeconds = 1700;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFullScreen);
        setContentView(R.layout.activity_first);

        //SetTextEffect();


        Test();
    }

    void Test() {


    }


    @Override
    protected void onResume() {
        super.onResume();
        StartWaiting();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }


    Handler handler = new Handler();
    Runnable r= new Runnable()
    {
        @Override
        public void run() {
            MoveToNextActivity();
        }
    };
    void StartWaiting() {
        handler.postDelayed(r , delayMilliSeconds);

    }



    void MoveToNextActivity() {


            Intent intent = new Intent(First.this, MainActivity.class);
            startActivity(intent);
            finish();


    }


}