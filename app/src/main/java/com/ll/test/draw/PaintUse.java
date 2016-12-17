package com.ll.test.draw;

import android.content.Context;
//import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/12.
 * paint 相当于画笔和调色板
 */
public class PaintUse extends View {
    private Paint paint;
    private Paint mPaint;

    public PaintUse(Context context, AttributeSet attrs) {
        super(context, attrs);
//        硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        Paint.ANTI_ALIAS_FLAG在绘制斜线是会使用抗锯齿效果来平滑该斜线外观（会降低性能）
//        Paint.SUBPIXEL_TEXT_FLAG更加平滑，一般文字，会应用子像素抗锯齿效果
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
//        手动设置
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setAlpha(127);
//        填充
        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void shader() {
//        ComposeShader使用多个shader
//        BitmapShader
//        线性渐变
        LinearGradient linearGradient = new LinearGradient(0.1f, 0.1f, 0.1f, 0.1f, Color.RED, Color.CYAN, Shader.TileMode.CLAMP);
        int[] gradientColors = new int[3];
        gradientColors[0] = Color.RED;
        gradientColors[1] = Color.CYAN;
        gradientColors[2] = Color.GRAY;
        float[] gradentPostions = new float[3];
        gradentPostions[0] = 0.0f;
        gradentPostions[1] = 0.5f;
//        gradentPostions[2] = 1.0f;
//        辐射，还有扫描
        RadialGradient radialGradient = new RadialGradient(20.f, 20f, 20f, gradientColors, gradentPostions, Shader.TileMode.CLAMP);

        paint.setShader(radialGradient);
//        shader的边界填充
//        Shader.TileMode.CLAMP
//        定义了平铺的3种模式：
//        static final Shader.TileMode CLAMP: 边缘拉伸.
//        static final Shader.TileMode MIRROR：在水平方向和垂直方向交替景象, 两个相邻图像间没有缝隙.
//        Static final Shader.TillMode REPETA：在水平方向和垂直方向重复摆放,两个相邻图像间有缝隙缝隙.
    }

    private MaskFilter mEmboss;
    private MaskFilter mBlur;

    //   maskFilter对 Paint的边缘效果，硬件加速时无效，对alpha通道转换
//    colorfilter对每个rgb通道，
    private void maskFilter(Canvas canvas) {
//        BlurMaskFilter制定模糊样式和半径；
//        EmbossMaskFilter指定光源的方向和环境光强度来添加浮雕
//        光源方向
        float[] direction = new float[]{1, 1, 1};
//        设置要应用的环境光亮度
        float ligjt = 0.4f;
//        应用的反射等级
        float specular = 6;
//        向蒙版应用一定级别的迷糊
        float blur = 3.5f;
        EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(direction, ligjt, specular, blur);
//        应用蒙版
        if (canvas.isHardwareAccelerated()) {
//            硬件加速
            paint.setMaskFilter(embossMaskFilter);
        } else {
//            不加速
        }
        mEmboss = new EmbossMaskFilter(new float[]{1, 1, 1},
                0.4f, 6, 3.5f);

        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
        paint.setColorFilter(new LightingColorFilter(Color.RED, Color.BLACK));

    }

    //    patheffect控制paint轮廓线
    private void patheffect() {
//        CornerPathEffect对边角进行平滑
//        DashPathEffect虚线
//        DiscretePathEffect跟上面的相似，有随机性，需要指定每段的长度和原始的偏离程度
//        PathDashPathEffect新的形状，并把原先的标记
//        SumPathEffect顺序的添加两个效果，同时作用
//        ComposePathEffect先使用一种效果在，在此基础上再使用第二种效果
        paint.setPathEffect(new CornerPathEffect(5));
    }

    //    改变转换模式
    private void xfermode() {
//        颜色和容差
//        AvoidXfermode
//        覆盖已有。xor
//        PixelXorXfermode
//        已有16种
//        PorterDuffXfermode
//        AvoidXfermode avoidXfermode = new AvoidXfermode(Color.RED, 10, AvoidXfermode.Mode.AVOID);
//        paint.setXfermode(avoidXfermode);
    }

    private void bitmap(View view, Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bitmap);
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        bd.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bd.setDither(true);
        view.setBackgroundDrawable(bd);
    }
}
