package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.stormdzh.multipleclick.entity.PointBean;

/**
 * Created by Administrator on 2018/4/10.
 * 扇形
 */

public class SectorView extends View {

    private PointBean clickPoint;
    private int r;
    private int sAngle = 90;

    public SectorView(Context context) {
        this(context, null);
    }

    public SectorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        int width = getWidth();
        int height = getHeight();
        Paint p = new Paint();
        p.setColor(Color.parseColor("#ff0000"));
        RectF rectF = new RectF(0, 0, width, height);
        r = Math.min(width / 2, height / 2);
        canvas.drawArc(rectF, 0, sAngle, true, p);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                clickPoint = new PointBean(x, y);
                if (isInSector(r, clickPoint)) {
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

    private boolean isInSector(int r, PointBean clickPoint) {
        //中心点位置
        PointBean centerPoint = new PointBean(getWidth() / 2, getHeight() / 2);

        //点离中心点位置
        double sqrt = getDistance(clickPoint, centerPoint);
        boolean lessThanR = r > sqrt;

        //构建一个新的三角形  求出扇形的角度，第一个点事 ：中心点 第二个点是，右边的点 第三个点是点击的点
        //一个顶点
        PointBean dotA = new PointBean(getWidth(), 0);

        double distance1 = getDistance(centerPoint, dotA);
        double distance2 = getDistance(centerPoint, clickPoint);
        double distance3 = getDistance(dotA, clickPoint);

        double cosa = (Math.pow(distance1, 2) + Math.pow(distance2, 2) - Math.pow(distance3, 2)) / (2 * distance1 * distance2);
        double acos = Math.acos(cosa);
        double b = Math.toDegrees(acos);
        if (clickPoint.y<centerPoint.y)
            b = 180 + (180 - b);
        return lessThanR && (b > 0 && b < sAngle);

    }

    private double getDistance(PointBean pa, PointBean pb) {
        double sqrt = Math.sqrt(Math.pow(pa.x - pb.x, 2) + Math.pow(pa.y - pb.y, 2));
        return sqrt;
    }


}
