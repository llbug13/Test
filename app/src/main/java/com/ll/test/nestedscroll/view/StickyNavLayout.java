package com.ll.test.nestedscroll.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.ll.test.R;


public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {
    private static final String TAG = "StickyNavLayout";

    /**
     * 回调开始滑动
     *
     * @param child            该父VIew 的子View
     * @param target           支持嵌套滑动的 VIew
     * @param nestedScrollAxes 滑动方向
     * @return 是否支持 嵌套滑动
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
//        onStartNestedScroll该方法，一定要按照自己的需求返回true，
//        该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
//        假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断。
        Log.e(TAG, "onStartNestedScroll");
//        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "onNestedScrollAccepted");
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.e(TAG, "onStopNestedScroll");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "onNestedScroll");
    }

    /**
     * 这里 传来了 x y 方向上的滑动距离
     * 并且 先与 子VIew  处理滑动,  并且 consumed  中可以设置相应的 除了的距离
     * 然后 子View  需要更具这感觉, 来处理自己滑动
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
//        onNestedPreScroll该方法的会传入内部View移动的dx,dy，
//        如果你需要消耗一定的dx,dy，
//        就通过最后一个参数consumed进行指定
//        例如我要消耗一半的dy，就可以写consumed[1]=dy/2
//        就是NestedScrollingParent内部的View，在滑动到时候，会首先将dx、dy传入给NestedScrollingParent，NestedScrollingParent可以决定是否对其进行消耗，
//        一般会根据需求消耗部分或者全部(不过这里并没有实际的约束，你可以随便写消耗多少，可能会对内部View造成一定的影响）。
        Log.e(TAG, dx + "onNestedPreScroll" + dy);
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
//        onNestedFling你可以捕获对内部View的fling事件，如果return true则表示拦截掉内部View的事件。
        Log.e(TAG, "onNestedFling");
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
//        当顶部控件显示时，fling可以让顶部控件隐藏或者显示。
        Log.e(TAG, "onNestedPreFling");
        //down - //up+
        if (getScrollY() >= mTopViewHeight) return false;
        Log.e(TAG, ";;;onNestedPreFling");
        fling((int) velocityY);
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        Log.e(TAG, "getNestedScrollAxes");
        return 0;
    }

    private View mTop;
    private View mNav;
    private ViewPager mViewPager;

    private int mTopViewHeight;

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity, mMinimumVelocity;

    private float mLastY;
    private boolean mDragging;

    public StickyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);

        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context)
                .getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context)
                .getScaledMinimumFlingVelocity();

    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        initVelocityTrackerIfNotExists();
//        mVelocityTracker.addMovement(event);
//        int action = event.getAction();
//        float y = event.getY();
//
//        switch (action)
//        {
//            case MotionEvent.ACTION_DOWN:
//                if (!mScroller.isFinished())
//                    mScroller.abortAnimation();
//                mLastY = y;
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float dy = y - mLastY;
//
//                if (!mDragging && Math.abs(dy) > mTouchSlop)
//                {
//                    mDragging = true;
//                }
//                if (mDragging)
//                {
//                    scrollBy(0, (int) -dy);
//                }
//
//                mLastY = y;
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                mDragging = false;
//                recycleVelocityTracker();
//                if (!mScroller.isFinished())
//                {
//                    mScroller.abortAnimation();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                mDragging = false;
//                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
//                int velocityY = (int) mVelocityTracker.getYVelocity();
//                if (Math.abs(velocityY) > mMinimumVelocity)
//                {
//                    fling(-velocityY);
//                }
//                recycleVelocityTracker();
//                break;
//        }
//
//        return super.onTouchEvent(event);
//    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.id_stickynavlayout_topview);
        mNav = findViewById(R.id.id_stickynavlayout_indicator);
        View view = findViewById(R.id.id_stickynavlayout_viewpager);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException(
                    "id_stickynavlayout_viewpager show used by ViewPager !");
        }
        mViewPager = (ViewPager) view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //不限制顶部的高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), mTop.getMeasuredHeight() + mNav.getMeasuredHeight() + mViewPager.getMeasuredHeight());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTop.getMeasuredHeight();
    }


    public void fling(int velocityY) {
//        ☞ fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY)
//        这个方法也很重要，如果你想实现滑动之后，布局能够根据移动速度，慢慢减速的话，
//        就需要用这个来实现，这里需要加速度的参数，我们可以通过VelocityTracker这个类来获取，
//        然后使用，具体参数函数，在下面的实例中进行说明。
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            Log.e(TAG, mScroller.getCurrY() + "[[[onNestedPreFling");
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }


}
