package com.stormdzh.multipleclick.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.stormdzh.multipleclick.R;

/**
 * Created by Administrator on 2018/4/11.
 */

public class TextImageView extends View {
    public TextImageView(Context context) {
        this(context, null);
    }

    public TextImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        String text="点击我";
        Paint textPaint=new Paint();
        textPaint.setColor(Color.parseColor("#ff0000"));
        canvas.drawText(text,0,0,textPaint);

        //文本
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //图片
        canvas.drawBitmap(bitmap, 250, 360, textPaint);
    }
}
