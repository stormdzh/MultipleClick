package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.stormdzh.multipleclick.entity.CircleBean;
import com.stormdzh.multipleclick.entity.PointBean;

/**
 * Created by Administrator on 2018/4/9.
 * 圆形
 */

public class CircleView extends View {

    private CircleBean mCircle;
    private PointBean clickPoint;
    private Paint circlePaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#ff0000"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制控件背景
        canvas.drawColor(Color.parseColor("#dedede"));

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int r = Math.min(cx, cy);
        mCircle = new CircleBean(cx, cy, r);
        canvas.drawCircle(cx, cy, r, circlePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                clickPoint = new PointBean(x, y);
                if (inCircle(clickPoint, mCircle)) {
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

    //判断点在圆内
    private boolean inCircle(PointBean point, CircleBean circle) {
        //到圆心的距离 是否大于半径。半径是R
        //如O(x,y)点圆心，任意一点P（x1,y1） （x-x1）*(x-x1)+(y-y1)*(y-y1)>R*R 那么在圆外 反之在圆内
        int x = circle.cx;
        int y = circle.cy;
        int r = circle.r;
        int px = point.x;
        int py = point.y;

        if (!((x - px) * (x - px) + (y - py) * (y - py) > r * r)) {
            return true;        //当前点在圆内
        } else {
            return false;       //当前点在圆外
        }
    }
}
