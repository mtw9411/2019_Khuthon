package com.smartoc.khuthon2019;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashActivity extends AppCompatActivity {
    private ImageView cloud_left1;
    private ImageView cloud_left2;
    private ImageView cloud_left3;
    private ImageView cloud_right1;
    private ImageView cloud_right2;
    private Animation left;
    private Animation right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startLoading();
        initView();


    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }

    private void initView(){
        cloud_left1 = findViewById(R.id.cloud_left1);
        cloud_left2 = findViewById(R.id.cloud_left2);
        cloud_left3 = findViewById(R.id.cloud_left3);

        cloud_right1 = findViewById(R.id.cloud_right1);
        cloud_right2 = findViewById(R.id.cloud_right2);

        left = AnimationUtils.loadAnimation(this, R.anim.loading_left);
        right = AnimationUtils.loadAnimation(this, R.anim.loading_right);
        cloud_left1.setAnimation(left);
        cloud_left2.setAnimation(left);
        cloud_left3.setAnimation(left);

        cloud_right1.setAnimation(right);
        cloud_right2.setAnimation(right);

    }
}
