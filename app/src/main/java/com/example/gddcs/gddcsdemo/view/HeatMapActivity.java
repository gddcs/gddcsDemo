package com.example.gddcs.gddcsdemo.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gddcs.gddcsdemo.R;
import com.example.gddcs.gddcsdemo.customview.FootView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 热力图
 */
public class HeatMapActivity extends AppCompatActivity {
    private FootView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heat_map);
        map = findViewById(R.id.example_map);
        Map<Float, Integer> colors = new ArrayMap<>();
        for (int i = 0; i < 21; i++) {
            float stop = ((float)i) / 20.0f;
            int color = doGradient(i * 5, 0, 100, 0xff00ff00, 0xffff0000);
            colors.put(stop, color);
        }
        map.setMinValue(0);
        map.setMaxValue(100);
        map.setColorStops(colors);
        map.setTransparentBackground(true);
        drawNewMap();

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawNewMap();
                map.invalidate();
            }
        });
    }

    private static int doGradient(double value, double min, double max, int min_color, int max_color) {
        if (value >= max) {
            return max_color;
        }
        if (value <= min) {
            return min_color;
        }
        float[] hsvmin = new float[3];
        float[] hsvmax = new float[3];
        float frac = (float) ((value - min) / (max - min));
        Color.RGBToHSV(Color.red(min_color), Color.green(min_color), Color.blue(min_color), hsvmin);
        Color.RGBToHSV(Color.red(max_color), Color.green(max_color), Color.blue(max_color), hsvmax);
        float[] retval = new float[3];
        for (int i = 0; i < 3; i++) {
            retval[i] = interpolate(hsvmin[i], hsvmax[i], frac);
        }
        return Color.HSVToColor(retval);
    }

    private static float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
    }

    private void drawNewMap() {
        map.clearData();
        Random rand = new Random();
        getData(rand);
    }

    private float clamp(double value, double min, double max) {
        return (float)(value * (max - min) + min);
    }

    private void getData(Random rand) {
        List<FootView.FootPoint> list = new ArrayList<>();
        FootView.FootPoint f0 = new FootView.FootPoint((55 - 37) / (float) 55, 17 / (float) 178, clamp(rand.nextFloat(), 0.0, 100.0));
        FootView.FootPoint f1 = new FootView.FootPoint((55 - 26) / (float) 55, 17 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f2 = new FootView.FootPoint((55 - 16) / (float) 55, 17 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f3 = new FootView.FootPoint((55 - 5) / (float) 55, 17 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f4 = new FootView.FootPoint((55 - 48) / (float) 55, 47 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f5 = new FootView.FootPoint((55 - 35) / (float) 55, 55 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f6 = new FootView.FootPoint((55 - 22) / (float) 55, 55 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f7 = new FootView.FootPoint((55 - 8) / (float) 55, 47 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f8 = new FootView.FootPoint((55 - 48) / (float) 55, 74 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f9 = new FootView.FootPoint((55 - 48) / (float) 55, 103 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f10 = new FootView.FootPoint((55 - 35) / (float) 55, 97 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f11 = new FootView.FootPoint((55 - 22) / (float) 55, 97 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f12 = new FootView.FootPoint((55 - 44) / (float) 55, 131 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f13 = new FootView.FootPoint((55 - 26) / (float) 55, 131 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f14 = new FootView.FootPoint((55 - 44) / (float) 55, 160 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));
        FootView.FootPoint f15 = new FootView.FootPoint((55 - 26) / (float) 55, 160 / (float) 178, clamp(rand.nextDouble(), 0.0, 100.0));

//        map.addData(f0);
//        map.addData(f1);
//        map.addData(f2);
//        map.addData(f3);
//        map.addData(f4);
//        map.addData(f5);
//        map.addData(f6);
//        map.addData(f7);
//        map.addData(f8);
//        map.addData(f9);
//        map.addData(f10);
//        map.addData(f11);
//        map.addData(f12);
//        map.addData(f13);
//        map.addData(f14);
//        map.addData(f15);

        list.add(f0);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(f8);
        list.add(f9);
        list.add(f10);
        list.add(f11);
        list.add(f12);
        list.add(f13);
        list.add(f14);
        list.add(f15);

        map.setData(list);

    }

}
