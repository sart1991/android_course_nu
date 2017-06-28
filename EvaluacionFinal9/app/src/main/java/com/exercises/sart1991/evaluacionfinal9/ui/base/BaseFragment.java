package com.exercises.sart1991.evaluacionfinal9.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by sart1 on 6/27/2017.
 */

abstract public class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onNotify(@StringRes int resId) {
        mActivity.onNotify(resId);
    }

    @Override
    public void onNotify(String message) {
        mActivity.onNotify(message);
    }

    @Override
    public Context getViewContext() {
        return mActivity;
    }

    @Override
    public void onDestroyView() {
        mActivity = null;
        super.onDestroyView();
    }

    abstract public void setUp(View view);
}
