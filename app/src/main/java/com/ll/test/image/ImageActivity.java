package com.ll.test.image;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ll.test.R;
import com.ll.test.base.BActivity;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ll on 16-10-24.
 */

public class ImageActivity extends BActivity {
    @BindView(R.id.image_test)
    ImageView imageView;
    @BindView(R.id.image_test_1)
    ImageView imageView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);
        ButterKnife.bind(ImageActivity.this);
        imageView = (ImageView) findViewById(R.id.image_test);
        imageView1 = (ImageView) findViewById(R.id.image_test_1);
        tint();
    }

    private EditText editText2;

    private void tint() {
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.icon_photo);
//        Drawable.ConstantState state = drawable.getConstantState();
//        Drawable drawable1 = DrawableCompat.wrap(state == null ? drawable : state.newDrawable()).mutate();
//        drawable1.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.horizon_sky_to));
        imageView.setImageDrawable(drawable);
//        imageView1.setImageDrawable(drawable1);

    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
//        你可能会有这样的担心，调用 mutate() 是不是在内存中把 Bitmap 拷贝了一份？
//        其实不是这样的，还是公用的 Bitmap，
//        只是拷贝了一份状态值，这个数据量很小，所以不用担心。
//        wrap版本控制
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public static void tintCursorDrawable(EditText editText, int color) {
//        原理比较简单，就是直接获得到 EditText 的 mCursorDrawableRes，然后通过这个 id 获取到对应的 Drawable，调用我们的着色函数 tintDrawable，然后设置进去。效果如下
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            if (mCursorDrawableRes <= 0) {
                return;
            }

            Drawable cursorDrawable = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            if (cursorDrawable == null) {
                return;
            }

            Drawable tintDrawable = tintDrawable(cursorDrawable, ColorStateList.valueOf(color));
            Drawable[] drawables = new Drawable[]{tintDrawable, tintDrawable};
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {
        }
    }
}

