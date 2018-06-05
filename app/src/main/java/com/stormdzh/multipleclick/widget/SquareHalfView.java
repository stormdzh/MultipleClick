package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.stormdzh.multipleclick.entity.PointBean;
import com.stormdzh.multipleclick.entity.SquareBean;

/**
 * Created by Administrator on 2018/4/10.
 * 矩形
 */

public class SquareHalfView extends View {

    private Paint squarePaint;
    private SquareBean mSquareBean;
    private PointBean clickPoint;

    public SquareHalfView(Context context) {
        this(context, null);
    }

    public SquareHalfView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareHalfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        squarePaint = new Paint();
        squarePaint.setColor(Color.parseColor("#ff0000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制控件背景
        canvas.drawColor(Color.parseColor("#dedede"));
        //绘制一个矩形
        mSquareBean = new SquareBean(0, 0, getHeight(), getWidth() / 2);
        canvas.drawRect(0, 0, getHeight(), getWidth() / 2, squarePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                clickPoint = new PointBean(x, y);
                if (inSquare(clickPoint, mSquareBean)) {
                    Toast.makeText(getContext(), "点击区域在矩形内部=》 x =" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "点击区域在矩形外部=》 x =" + x + " y=" + y, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        return true;
    }

    private boolean inSquare(PointBean clickPoint, SquareBean mSquareBean) {
        return clickPoint.x >= mSquareBean.left && clickPoint.x <= mSquareBean.right && clickPoint.y >= mSquareBean.top && clickPoint.y <= mSquareBean.bottom;
    }
}
