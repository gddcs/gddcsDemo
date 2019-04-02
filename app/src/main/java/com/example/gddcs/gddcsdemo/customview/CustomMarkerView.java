package com.example.gddcs.gddcsdemo.customview;

import android.content.Context;
import android.widget.TextView;

import com.example.gddcs.gddcsdemo.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class CustomMarkerView extends MarkerView {
    private TextView tv1,tv2;
    private MPPointF mOffset;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public CustomMarkerView(Context context) {
        super(context, R.layout.custom_marker_view_layout);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        tv1.setText("Y:"+(int)e.getY());
        tv2.setText("X:"+e.getX());
    }

    @Override
    public MPPointF getOffset() {
        if (null == mOffset){
            mOffset = new MPPointF(getWidth()/10, -(getHeight()/5));
        }
        return mOffset;
    }
}
