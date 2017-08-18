package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        // 拐角圆角
        CornerPathEffect cornerPathEffect = new CornerPathEffect(10);
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        // 将路径分隔成一段段小线段，然后在原路径上做随机的偏离
        // 第一个参数：线段长，第二个参数：偏离量
        DiscretePathEffect discretePathEffect = new DiscretePathEffect(10, 5);
        paint.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        // 虚线
        // 第一个参数：数组必须为偶数，最少2个。偶数位为画线长度，奇数位为空白长度，即[画线长度，空白长度，画线长度，...]
        // 第二个参数：虚线偏移量，向线段起点方向偏移？
        float[] intervals = new float[]{20f, 10f, 5f, 10f};
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, 10);
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        // 使用 Path 绘制虚线
        Path dashPath = new Path();
        dashPath.moveTo(0, 20);
        dashPath.lineTo(10, 0);
        dashPath.lineTo(20, 20);
        dashPath.close();
        // 第一个参数：用于绘制虚线的 Path
        // 第二个参数：Path 间距，两个 Path 起点的间距
        // 第三个参数：第一个 Path 偏移量
        // 第四个参数：线段拐弯处，shape 的转换方式:
        // TRANSLATE（位移）- Path 方向保持不变，只是沿着线段绘制
        // ROTATE（旋转），随线段的走势而发生旋转
        // MORPH（变体），拐角处形变
        PathDashPathEffect pathDashPathEffect = new PathDashPathEffect(dashPath, 30, 0,
                PathDashPathEffect.Style.TRANSLATE);
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        // 组合效果
        SumPathEffect sumPathEffect = new SumPathEffect(discretePathEffect, dashPathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        // 叠加效果
        // 第一个参数：最后的效果
        // 第二个参数：第一次的效果
        // 先偏移，后虚线
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect,
                discretePathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
