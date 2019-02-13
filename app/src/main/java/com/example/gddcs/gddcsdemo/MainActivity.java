package com.example.gddcs.gddcsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gddcs.gddcsdemo.view.HeatMapActivity;

public class MainActivity extends AppCompatActivity {
    private Button mKotlinBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKotlinBt = findViewById(R.id.kotin_button);

        mKotlinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(MainActivity.this, HeatMapActivity.class);
                startActivity(t);
            }
        });
    }


}
