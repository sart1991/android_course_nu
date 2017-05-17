package com.exercises.sart1991.sqlitedrawer.ui.insert;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercises.sart1991.sqlitedrawer.R;
import com.exercises.sart1991.sqlitedrawer.ui.base.BaseFragment;

public class InsertFragment extends BaseFragment implements InsertMvpView{

    private static final InsertMvpPresenter<InsertMvpView> PRESENTER = new InsertPresenter<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PRESENTER.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        intializeComponenets(view);
        return view;
    }

    public static InsertFragment newInstance() {
        Bundle args = new Bundle();
        InsertFragment fragment = new InsertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void intializeComponenets(View view) {

    }

    @Override
    public Context getViewContext() {
        return getBaseActivity().getBaseContext();
    }
}
