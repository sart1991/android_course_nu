package com.exercises.sart1991.sqlitedrawer.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sart1 on 5/16/2017.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
            mActivity.onFragmentAttached();
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        mActivity.onError(resId);
    }

    @Override
    public void onError(String message) {
        mActivity.onError(message);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    protected abstract void initializeComponents(View view);

    public interface Callback {
        void onFragmentAttached();
        void onFragmentDetached(String tag);
    }
}
