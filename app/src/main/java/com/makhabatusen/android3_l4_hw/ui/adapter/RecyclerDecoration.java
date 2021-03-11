package com.makhabatusen.android3_l4_hw.ui.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerDecoration extends RecyclerView.ItemDecoration {
    int sidePAdding;
    int topPadding;

    public RecyclerDecoration(int sidePAdding, int topPadding) {
        this.sidePAdding = sidePAdding;
        this.topPadding = topPadding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = topPadding;
        outRect.top = topPadding;
        outRect.left = sidePAdding;
        outRect.right = sidePAdding;
    }
}
