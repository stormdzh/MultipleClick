package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.stormdzh.multipleclick.entity.PointBean;

/**
 * Created by Administrator on 2018/4/10.
 * 三角形
 */

public class TrigonView extends View {

    private PointBean clickPoint;
    private PointBean a;
    private PointBean b;
    private PointBean c;

    public TrigonView(Context context) {
        this(context, null);
    }

    public TrigonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TrigonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //绘制三角形
        int width = getWidth();
        int height = getHeight();
        Paint p = new Paint();
        p.setColor(Color.parseColor("#ff0000"));
        //实例化路径
        Path path = new Path();
        path.moveTo(0, 0);// 此点为多边形的起点
        a = new PointBean(0, 0);
        path.lineTo(width, 0);
        b = new PointBean(width, 0);
        path.lineTo(0, height);
        c = new PointBean(0, height);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);
    }


    //面积法
    public static boolean isInTriangle(PointBean A, PointBean B, PointBean C, PointBean P) {
        double ABC = triAngleArea(A, B, C);
        double ABp = triAngleArea(A, B, P);
        double ACp = triAngleArea(A, C, P);
        double BCp = triAngleArea(B, C, P);
        if (ABC == ABp + ACp + BCp) {// 若面积之和等于原三角形面积，证明点在三角形内
            return true;
        } else {
            return false;
        }
    }

    private static double triAngleArea(PointBean A, PointBean B, PointBean C) {// 由三个点计算这三个点组成三角形面积
        double result = Math.abs((A.x * B.y + B.x * C.y + C.x * A.y - B.x * A.y - C.x * B.y - A.x * C.y) / 2.0D);
        return result;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                clickPoint = new PointBean(x, y);
                if (isInTriangle(a, b, c, clickPoint)) {
                    Toast.makeText(getContext(), "点击区域在三角形内部=》 x =" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "点击区域在三角形外部=》 x =" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        return true;
    }
}
