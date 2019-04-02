package com.example.gddcs.gddcsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gddcs.gddcsdemo.aidltest.BookMangerActivity;
import com.example.gddcs.gddcsdemo.view.activity.AppBaseActivity;
import com.example.gddcs.gddcsdemo.view.activity.HeatMapActivity;
import com.example.gddcs.gddcsdemo.view.activity.KotlinTestActivity;
import com.example.gddcs.gddcsdemo.view.activity.MPAndroidChartActivity;

public class MainActivity extends AppBaseActivity {
    private Button mKotlinBt, mJniBt, mHeatMapBt, mMpAndroidBt, mAIDLBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKotlinBt = findViewById(R.id.kotin_button);
        mJniBt = findViewById(R.id.jni_button);
        mHeatMapBt = findViewById(R.id.heat_map_button);
        mMpAndroidBt = findViewById(R.id.mp_android_chart_button);
        mAIDLBt = findViewById(R.id.aidl_button);

        mKotlinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(MainActivity.this, KotlinTestActivity.class);
                startActivity(t);
            }
        });
        mJniBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "...", Toast.LENGTH_LONG).show();
            }
        });
        mHeatMapBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, HeatMapActivity.class);
                startActivity(t);
            }
        });
        mMpAndroidBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, MPAndroidChartActivity.class);
                startActivity(t);
            }
        });
        mAIDLBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, BookMangerActivity.class);
                startActivity(t);
            }
        });

    }


}
