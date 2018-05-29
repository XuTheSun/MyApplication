package com.example.athis.myapplication.RecyclerInteract;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

public class MyLinearManager extends LinearLayoutManager {
    public MyLinearManager(Context context) {
        super(context);
    }

    public MyLinearManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        CenterScroller linearSmoothScroller =
                new CenterScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    class CenterScroller extends LinearSmoothScroller{
        public CenterScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            Log.d("calculateDtToFit","viewStart: "+viewStart+",viewEnd: "+viewEnd+
                    ",boxStart: "+boxStart + "boxEnd: " + boxEnd + "snapPreference" + snapPreference);
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
    }
}
