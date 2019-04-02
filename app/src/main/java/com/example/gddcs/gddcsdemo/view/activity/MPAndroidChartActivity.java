package com.example.gddcs.gddcsdemo.view.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.gddcs.gddcsdemo.R;
import com.example.gddcs.gddcsdemo.customview.CircleProgressBar;
import com.example.gddcs.gddcsdemo.customview.CustomMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MPAndroidChartActivity extends AppBaseActivity {
    private LineChart LineChart1,LineChart2;
    private PieChart PieChart;
    private CircleProgressBar color_progress_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);
        LineChart1 = findViewById(R.id.LineChart1);
        LineChart2 = findViewById(R.id.LineChart2);
        PieChart = findViewById(R.id.PieChart);
        color_progress_view = findViewById(R.id.color_progress_view);

        initDataLineChart1();
        initDataLineChart2();
        initDataPieChart();
        initDataProgress();

    }

    private void initDataLineChart1(){
        // x轴i，y轴变化值
        //随机生成数据
        Random random = new Random();
        List<Entry> entries1 = new ArrayList<Entry>();
        List<Entry> entries2 = new ArrayList<Entry>();
        for (int i = 0; i < 10; i++){
            float value1 = random.nextFloat() * 100;
            float value2 = random.nextFloat() * 100;
            entries1.add(new Entry(i, value1));
            entries2.add(new Entry(i, value2));
        }
        LineChart1.setTouchEnabled(false);
        XAxis xAxis = LineChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置X轴的位置

        YAxis yLeftAxis = LineChart1.getAxisLeft();
        YAxis yRightAxis = LineChart1.getAxisRight();

        xAxis.setEnabled(false);
        //        yRightAxis.setEnabled(false);
        //        yLeftAxis.setEnabled(false);

        yLeftAxis.setAxisMinimum(0);
        yLeftAxis.setAxisMaximum(100);
        yRightAxis.setAxisMinimum(0);
        yRightAxis.setAxisMaximum(100);

        yLeftAxis.setDrawLabels(false);
        yRightAxis.setDrawLabels(false);

        LineDataSet dataSet1 = new LineDataSet(entries1, "Label1"); // add entries to dataset

        dataSet1.setColor(Color.parseColor("#fe9a69"));
        dataSet1.setDrawValues(false); // 启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
        dataSet1.setDrawCircles(false);
        Drawable drawable1 = getResources().getDrawable(R.drawable.color_gradient_red);
        dataSet1.setFillDrawable(drawable1);
        dataSet1.setDrawFilled(true);
        dataSet1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//允许设置平滑曲线

        LineDataSet dataSet2 = new LineDataSet(entries2, "Label2"); // add entries to dataset
        dataSet2.setColor(Color.parseColor("#51b6fd"));
        dataSet2.setDrawValues(false); // 启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
        dataSet2.setDrawCircles(false);
        Drawable drawable2 = getResources().getDrawable(R.drawable.color_gradient_blue);
        dataSet2.setFillDrawable(drawable2);
        dataSet2.setDrawFilled(true);
        dataSet2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//允许设置平滑曲线

        List<ILineDataSet> dataSetList = new ArrayList<>();
        dataSetList.add(dataSet1);
        dataSetList.add(dataSet2);

        LineData lineData = new LineData(dataSetList);
        Description description = new Description();
        description.setText("");
        LineChart1.setDescription(description);
        LineChart1.setData(lineData);
        LineChart1.invalidate(); // refresh
    }

    private void initDataLineChart2(){
        // x轴i，y轴变化值
        //随机生成数据
        Random random = new Random();
        List<Entry> entries1 = new ArrayList<Entry>();
        List<Entry> entries2 = new ArrayList<Entry>();
        for (int i = 0; i < 10; i++){
            float value1 = random.nextFloat() * 100;
            float value2 = random.nextFloat() * 100;
            entries1.add(new Entry(i, value1));
            entries2.add(new Entry(i, value2));
        }
        LineChart2.setBackgroundColor(getResources().getColor(R.color.royalblue));
        LineChart2.setDrawMarkers(true);
        LineChart2.setMarker(new CustomMarkerView(this));
        XAxis xAxis = LineChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置X轴的位置

        YAxis yLeftAxis = LineChart2.getAxisLeft();
        YAxis yRightAxis = LineChart2.getAxisRight();

//        xAxis.setEnabled(false);
                yRightAxis.setEnabled(false);
        //        yLeftAxis.setEnabled(false);

        yLeftAxis.setAxisMinimum(0);
        yLeftAxis.setAxisMaximum(100);
        yRightAxis.setAxisMinimum(0);
        yRightAxis.setAxisMaximum(100);

        yLeftAxis.setDrawLabels(true);
        yRightAxis.setDrawLabels(false);

        yLeftAxis.setAxisLineColor(Color.GREEN);
//        yLeftAxis.setGridColor(Color.RED);

        xAxis.setTextColor(Color.WHITE);
        yLeftAxis.setTextColor(Color.WHITE);

        LineDataSet dataSet1 = new LineDataSet(entries1, "您的活动变化"); // add entries to dataset

        dataSet1.setColor(Color.parseColor("#d9ffffff"));
        dataSet1.setDrawValues(false); // 启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
        dataSet1.setDrawCircles(true);
        dataSet1.setCircleColor(Color.WHITE);
        dataSet1.setCircleRadius(5);
//        dataSet1.setCircleHoleRadius(3);
//        dataSet1.setCircleHoleColor(Color.BLUE);
//        dataSet1.setDrawCircleHole(true);
        dataSet1.setLineWidth(2);
        dataSet1.setHighLightColor(Color.TRANSPARENT);

        LineDataSet dataSet2 = new LineDataSet(entries2, "标准"); // add entries to dataset
        dataSet2.setColor(Color.parseColor("#d900ff00"));
        dataSet2.setDrawValues(false); // 启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
        dataSet2.setDrawCircles(true);
        dataSet2.setCircleColor(Color.GREEN);
        dataSet2.setCircleRadius(5);
//        dataSet2.setDrawCircleHole(true);
        dataSet2.setCircleHoleColor(Color.GREEN);
        dataSet2.setLineWidth(2);
        dataSet2.setHighLightColor(Color.TRANSPARENT);

        List<ILineDataSet> dataSetList = new ArrayList<>();
        dataSetList.add(dataSet1);
        dataSetList.add(dataSet2);

        LineData lineData = new LineData(dataSetList);
        Description description = new Description();
        description.setText("");
        //图例：得到Lengend
        Legend legend = LineChart2.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        LineChart2.setDescription(description);
        LineChart2.setData(lineData);
        LineChart2.invalidate(); // refresh
    }

    private void initDataPieChart(){
        //随机生成数据
        Random random = new Random();
        List<PieEntry> pieEntries = new ArrayList<PieEntry>();
        for (int i = 0; i < 5; i++){
            float value1 = (random.nextFloat() * 100);
            pieEntries.add(new PieEntry(value1, value1));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Label");
        //设置颜色
        final int[] COLORFUL_COLORS = {
                Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
                Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : COLORFUL_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述
        Description description = new Description();
        description.setEnabled(false);
        PieChart.setDescription(description);
        PieChart.setBackgroundColor(getResources().getColor(R.color.royalblue));

        //设置半透明圆环的半径, 0为透明
        PieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        PieChart.setRotationAngle(-15);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);
//        dataSet.setValueLinePart2Length(1);
        dataSet.setValueLinePart1Length(0.8f);
        //设置连接线的颜色
        dataSet.setValueLineColor(Color.WHITE);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        // 设置饼块之间的间隔
        dataSet.setSliceSpace(0f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = PieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        PieChart.setExtraOffsets(25, 5, 25, 5);

        // 设置pieChart图表是否可以手动旋转
        PieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        PieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        PieChart.animateY(1400, Easing.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        PieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        PieChart.setDrawCenterText(false);
        //是否显示内圆
        PieChart.setDrawHoleEnabled(false);
        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.WHITE);

        PieChart.setData(pieData);
        // 更新 piechart 视图
        PieChart.invalidate();
    }

    private void initDataProgress(){
        color_progress_view.setMaxStepNum(100);
        color_progress_view.setmDescription("说明");
        color_progress_view.setmTitle("标题");
        color_progress_view.update(80,1000);
    }
}
