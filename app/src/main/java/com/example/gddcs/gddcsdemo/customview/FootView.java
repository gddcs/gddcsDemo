package com.example.gddcs.gddcsdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.gddcs.gddcsdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FootView extends View {
    private List<FootPoint> data;     // 数据点
    private int[] colors = new int[]{0xff00ff00, 0xffff0000};     // 默认渐变颜色
    private float[] colorPositions = new float[]{0.0f, 1.0f};     // 默认渐变颜色占比
    private int[] colorPixels = new int[256];     // 渐变颜色数组
    private int maxDrawWidth;     // bitmap宽度
    private int maxDrawHeight;     // bitmap高度
    private float[] rectangle = new float[4];     // 绘制所有透明渐变圆的范围
    private Paint transparentPaint;     // 透明渐变圆的画笔
    private Paint backgroundPaint;     // 背景画笔
    private float transparentRadius;     // 透明渐变圆的半径
    private float maxValue;     // 最小数据值
    private float minValue;     // 最大数据值

    private Bitmap mBitmap = null;     // 绘制的bitmap
    private boolean isChangeColor = true;      // 颜色是否改变
    private boolean isTransparentBackground;     // 背景是否透明

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public boolean isTransparentBackground() {
        return isTransparentBackground;
    }

    public void setTransparentBackground(boolean transparentBackground) {
        isTransparentBackground = transparentBackground;
    }

    /**
     * 传感器点（x、y为百分比）
     */
    public static class FootPoint {
        public float x;
        public float y;
        public float value;

        public FootPoint(float x, float y, float value) {
            if (x > 1 && y > 1)
                throw new IllegalArgumentException("footpoint参数设置错误，x和y的范围为[0,1]");
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }


    public FootView(Context context) {
        super(context);
        initView();
    }

    public FootView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.FootView);
        try {
            maxDrawHeight = (int) t.getDimension(R.styleable.FootView_maxDrawHeight, 300);
            maxDrawWidth = (int) t.getDimension(R.styleable.FootView_maxDrawWidth, 300);
            transparentRadius = t.getFloat(R.styleable.FootView_transparentRadius, 50);
            maxValue = t.getFloat(R.styleable.FootView_maxValue, 100f);
            minValue = t.getFloat(R.styleable.FootView_minValue, 0.0f);
        } finally {
            t.recycle();
        }

    }

    public FootView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化数据、画笔
     */
    private void initView() {
        transparentPaint = new Paint();
        backgroundPaint = new Paint();
        this.data = new ArrayList<>();
    }


    private int getDrawingWidth() {
        if (maxDrawWidth == 0)
            return getWidth();
        return Math.min(calcMaxWidth(), getWidth());
    }

    private int getDrawingHeight() {
        if (maxDrawHeight == 0)
            return getHeight();
        return Math.min(calcMaxHeight(), getHeight());
    }

    private int calcMaxHeight() {
        return (int) (getHeight() / getScale());
    }

    private int calcMaxWidth() {
        return (int) (getWidth() / getScale());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.data != null && this.data.size() != 0) {
            if (isChangeColor)
                setColorPixel();
            drawTransparentPoint(getDrawingWidth(), getDrawingHeight());
            drawColour(canvas);
        } else {
            Log.e("tag", "没有数据");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制透明渐变的圆
     */
    public void drawTransparentPoint(int width, int height) {
        rectangle[0] = 10000;
        rectangle[1] = 10000;
        rectangle[2] = 0;
        rectangle[3] = 0;
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);
        if (this.data == null || this.data.size() == 0)
            return;
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (FootPoint footPoint : this.data) {
            float x = footPoint.x * width;
            float y = footPoint.y * height;
            float value = Math.max(minValue, Math.min(footPoint.value, maxValue));
            float alpha = (value - minValue) / (maxValue - minValue);

            float rectX = x - transparentRadius;
            float rectY = y - transparentRadius;

            RadialGradient gradient = new RadialGradient(x, y, transparentRadius,
                    new int[]{Color.argb((int) (alpha * 255), 0, 0, 0), Color.argb(0, 0, 0, 0)},
                    null, Shader.TileMode.CLAMP);
            transparentPaint.setShader(gradient);
            mCanvas.drawCircle(x, y, 2 * transparentRadius, transparentPaint);

            if (rectX < rectangle[0])
                rectangle[0] = rectX;
            if (rectY < rectangle[1])
                rectangle[1] = rectY;
            if ((rectX + (2 * transparentRadius)) > rectangle[2])
                rectangle[2] = rectX + (2 * transparentRadius);
            if ((rectY + (2 * transparentRadius)) > rectangle[3])
                rectangle[3] = rectY + (2 * transparentRadius);

        }
    }

    /**
     * 根据透明度绘制渐变颜色
     * @param canvas
     */
    private void drawColour(Canvas canvas) {
        if (this.data == null || this.data.size() == 0)
            return;
        int x = (int) rectangle[0];
        int y = (int) rectangle[1];
        int width = (int) rectangle[2];
        int height = (int) rectangle[3];
        int maxWidth = getDrawingWidth();
        int maxHeight = getDrawingHeight();

        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        if (x + width > maxWidth)
            width = maxWidth - x;
        if (y + height > maxHeight)
            height = maxHeight - y;

        int pixels[] = new int[width];

        for (int j = 0; j < height; j++) {
            mBitmap.getPixels(pixels, 0, width, x, y + j, width, 1);

            for (int i = 0; i < width; i++) {
                int pixel = pixels[i];
                int alpha = 0xff & (pixel >> 24);

                int clampAlpha;
                if (alpha < 256) {
                    if (alpha < 0) {
                        clampAlpha = 0;
                    } else {
                        clampAlpha = alpha;
                    }
                } else {
                    clampAlpha = 256;
                }

                pixels[i] = ((0xff & clampAlpha) << 24) | (0xffffff & colorPixels[alpha]);

            }
            mBitmap.setPixels(pixels, 0, width, x, y + j, width, 1);
        }
        // 绘制背景
        if (!isTransparentBackground){
            canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()),backgroundPaint);
        }
        canvas.drawBitmap(mBitmap, new Rect(0, 0, getDrawingWidth(), getDrawingHeight()), new Rect(0, 0, getWidth(), getHeight()), null);
        // 绘制点
        for (FootPoint point : data) {
            float rx = (point.x * getWidth());
            float ry = (point.y * getHeight());
            drawPoint(canvas, rx, ry);
        }
    }

    /**
     * 绘制数据点
     * @param canvas
     * @param x
     * @param y
     */
    private void drawPoint(Canvas canvas, float x, float y) {
        Paint myPaint = new Paint();
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setColor(Color.RED);
        canvas.drawCircle(x, y, 10, myPaint);
    }

    /**
     * 设置渐变颜色
     * @param stops
     */
    public void setColorStops(Map<Float, Integer> stops) {
        if (stops.size() < 2)
            return;
        colors = new int[stops.size()];
        colorPositions = new float[stops.size()];
        int i = 0;
        for (Float key : stops.keySet()) {
            colors[i] = stops.get(key);
            colorPositions[i] = key;
            i++;
        }
        isChangeColor = true;
    }

    /**
     * 生成渐变颜色数组
     */
    public void setColorPixel() {
        Bitmap bitmap = Bitmap.createBitmap(256, 1, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);
        Paint colorPaint = new Paint();
        colorPaint.setStyle(Paint.Style.FILL);
        LinearGradient lg = new LinearGradient(0, 0, 256, 1, colors, colorPositions, Shader.TileMode.CLAMP);
        colorPaint.setShader(lg);
        bitmapCanvas.drawLine(0, 0, 256, 1, colorPaint);
        bitmap.getPixels(colorPixels, 0, 256, 0, 0, 256, 1);
        backgroundPaint.setStyle(Paint.Style.FILL);
        if (!isTransparentBackground)
            backgroundPaint.setColor(colorPixels[0]);
        isChangeColor = false;
    }

    /**
     * 根据长宽获取缩放
     * @return
     */
    private float getScale() {
        if (maxDrawWidth == 0 || maxDrawHeight == 0)
            return 1.0f;
        float sourceRatio = getWidth() / getHeight();
        float targetRatio = maxDrawWidth / maxDrawHeight;
        float scale;
        if (sourceRatio < targetRatio) {
            scale = getWidth() / ((float) maxDrawWidth);
        } else {
            scale = getHeight() / ((float) maxDrawHeight);
        }
        return scale;
    }

    public void clearData() {
        this.data.clear();
    }

    public void addData(FootView.FootPoint point) {
        this.data.add(point);
    }

    public void setData(List<FootPoint> list) {
        this.data = list;
    }

}
