package com.zql.app_ji.View.BottomSheetDialog;
/**
 * 继承了sheetDialog然后屏蔽上下滑动
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zql.app_ji.R;

public class DateBottomSheetDialog extends BottomSheetDialogFragment {
      private int topoffets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test_bottom_sheet_dialog,container,false);
        return view;
    }
}
