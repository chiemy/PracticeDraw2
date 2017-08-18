package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice13ShadowLayerView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice13ShadowLayerView(Context context) {
        super(context);
    }

    public Practice13ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 使用 Paint.setShadowLayer() 设置阴影
        // 阴影半径，水平偏移量，垂直偏移量，阴影颜色
        paint.setShadowLayer(10, 10, 0, Color.BLACK);

        // 在硬件加速开启的情况下，只支持文字绘制
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(120);
        canvas.drawText("Hello HenCoder", 50, 200, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setShadowLayer(10, 10, 10, Color.BLACK);
        canvas.drawCircle(70, 340, 50, paint);
    }
}
