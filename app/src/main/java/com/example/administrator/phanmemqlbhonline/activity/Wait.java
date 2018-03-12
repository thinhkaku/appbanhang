package com.example.administrator.phanmemqlbhonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.phanmemqlbhonline.MainActivity;
import com.example.administrator.phanmemqlbhonline.R;

/**
 * Created by Administrator on 10/15/2017.
 */

public class Wait extends AppCompatActivity {
    String userName="";
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wait);
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Intent intent = getIntent();
                userName=intent.getStringExtra("userName");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Wait.this,MainActivity.class);
                intent.putExtra("UserName",userName);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
