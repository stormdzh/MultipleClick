package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/4/11.
 * 路径
 */

public class PathView extends View {

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制控件背景
        canvas.drawColor(Color.parseColor("#dedede"));

        // 创建画笔
        Paint p = new Paint();
        p.setColor(Color.parseColor("#ff0000"));
        //设置空心
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(4);
        Path path = new Path();
        path.moveTo(0, 0);//设置Path的起点
        path.quadTo(getWidth()/2, getHeight()/4*3, getWidth(), getHeight());  //设置路径点和终点
        canvas.drawPath(path, p);
    }
}
