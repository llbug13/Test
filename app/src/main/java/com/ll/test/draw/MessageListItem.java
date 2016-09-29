package com.ll.test.draw;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/29.
 * <p>
 * draw状态测试
 */
public class MessageListItem extends RelativeLayout {

    private static final int[] STATE_MESSAGE_READED = {R.attr.state_message_readed};
    private boolean mMessgeReaded = false;

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMessageReaded(boolean readed) {
        if (this.mMessgeReaded != readed) {
            mMessgeReaded = readed;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
//        添加状态
        if (mMessgeReaded) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);
            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }
//    大家可以看看CompoundButton（CheckBox父类）的源码，它有个checked状态：
//    @Override
//    protected int[] onCreateDrawableState(int extraSpace) {
//        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
//        if (isChecked()) {
//            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
//        }
//        return drawableState;
//    }
}
