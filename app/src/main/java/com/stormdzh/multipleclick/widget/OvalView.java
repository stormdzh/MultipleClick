package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.stormdzh.multipleclick.entity.PointBean;

/**
 * Created by Administrator on 2018/4/11.
 * 椭圆
 */

public class OvalView extends View {

    private int a;
    private int b;
    private PointBean clickPoint;
    private PointBean centerPoint;

    public OvalView(Context context) {
        this(context, null);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        //绘制椭圆
        int width = getWidth();
        int height = (int) (getHeight() * 0.7);
        int ptop = (getHeight() - height) / 2;

        a = width / 2;
        b = height / 2;

        centerPoint=new PointBean(a,b+ptop);
        // 创建画笔
        Paint p = new Paint();
        p.setColor(Color.parseColor("#ff0000"));
        RectF rectF = new RectF(0, ptop, width, height + ptop);
        canvas.drawOval(rectF, p);

    }


    //判断点在圆内
    private boolean inOval(PointBean point,PointBean centerPoint) {
        //到圆心的距离 是否大于半径。半径是R
        double v = Math.pow(centerPoint.x-point.x, 2) / Math.pow(a, 2) + Math.pow(centerPoint.y-point.y, 2) / Math.pow(b, 2);
        return v < 1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                clickPoint = new PointBean(x, y);
                if (inOval(clickPoint,centerPoint)) {
                    Toast.makeText(getContext(), "点击区域在圆内部=》 x =" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                    Log.i("test", "点击区域在圆内部=》 x =" + x + " y=" + y);
                } else {
                    Log.i("test", "点击区域在圆外部=》 x =" + x + " y=" + y);
                    Toast.makeText(getContext(), "点击区域在圆外部=》 x =" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        return true;
    }

}
