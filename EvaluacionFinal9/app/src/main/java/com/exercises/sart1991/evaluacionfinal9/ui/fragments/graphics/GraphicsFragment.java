package com.exercises.sart1991.evaluacionfinal9.ui.fragments.graphics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercises.sart1991.evaluacionfinal9.R;
import com.exercises.sart1991.evaluacionfinal9.ui.base.BaseFragment;

public class GraphicsFragment extends BaseFragment implements GraphicsMvpView {

    public GraphicsFragment() {
        // Required empty public constructor
    }
    public static GraphicsFragment newInstance() {
        GraphicsFragment fragment = new GraphicsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graphics, container, false);
    }

    @Override
    public void setUp(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
