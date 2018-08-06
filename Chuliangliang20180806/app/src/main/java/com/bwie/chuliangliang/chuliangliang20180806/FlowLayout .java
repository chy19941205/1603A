package com.bwie.chuliangliang.chuliangliang20180806;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

class FlowLayout extends ViewGroup {

    public FlowLayout (Context context) {
        this(context,null);
    }

    public FlowLayout (Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width =  0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        int cCount = getChildCount();
        for (int i = 0; i < cCount ; i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp =(MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()){
                width = Math.max(width,lineWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            }else{
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            if (i == cCount -1){
                width = Math.max(lineWidth,width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width+getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );

    }
    private List<List<View>>    mAllViews   = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mAllViews.clear();
        mLineHeight.clear();
        int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        for (int j = 0; j < cCount ; j++) {
            View child = getChildAt(j);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (childWidth + lineWidth + params.leftMargin + params.rightMargin > width - getPaddingLeft() - getPaddingRight()){
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                lineWidth = 0;
                lineHeight = childHeight + params.topMargin + params.bottomMargin;
                // 重置我们的View集合
                lineViews = new ArrayList<View>();
            }
            lineWidth += childWidth + params.leftMargin + params.rightMargin;
            lineHeight += Math.max(lineHeight,childHeight+params.topMargin+params.bottomMargin);
            lineViews.add(child);
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int lineNum = mAllViews.size();
        for (int j = 0; j < lineNum; j++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            for (int k = 0; k < lineViews.size(); k++) {
                View child = lineViews.get(k);
                if (child.getVisibility() == View.GONE){
                    continue;
                }
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + params.leftMargin;
                int tc = top + params.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc,tc,rc,bc);
                left += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
