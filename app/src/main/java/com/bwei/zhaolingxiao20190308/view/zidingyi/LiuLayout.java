package com.bwei.zhaolingxiao20190308.view.zidingyi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class LiuLayout extends ViewGroup {
    int maxHeight;
    int marginLeft = 20;
    int marginTop = 20;
    private View childAt;
    private int measuredWidth;

    public LiuLayout(Context context) {
        super(context);
    }

    public LiuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        findMaxHeight();
        int left = 0;
        int top = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int measuredWidth = childAt.getMeasuredWidth();
            if (left != 0) {
                if ((left + measuredWidth) > widthSize) {
                    top = marginTop + maxHeight;
                    left = 0;
                }
            }
            left = marginLeft + measuredWidth + left;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        findMaxHeight();
        int left = 0;
        int top = 0;
        int childCount = getChildCount();
        for (int j = 0; j < childCount; j++) {
            childAt = getChildAt(j);
            measuredWidth = childAt.getMeasuredWidth();
            int width = getWidth();
            if (left != 0) {
                if ((left + measuredWidth) > width) {
                    top = marginTop + maxHeight + top;
                    left = 0;
                }
            }
            childAt.layout(left, top, left + measuredWidth, top + maxHeight);
            left = left + measuredWidth + marginLeft;
        }

    }

    private void findMaxHeight() {
        maxHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int measuredHeight = childAt.getMeasuredHeight();
            if (maxHeight < measuredHeight) {
                maxHeight = measuredHeight;
            }
        }
    }
}
