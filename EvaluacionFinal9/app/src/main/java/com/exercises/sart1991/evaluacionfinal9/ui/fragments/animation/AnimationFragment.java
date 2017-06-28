package com.exercises.sart1991.evaluacionfinal9.ui.fragments.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exercises.sart1991.evaluacionfinal9.R;
import com.exercises.sart1991.evaluacionfinal9.ui.base.BaseFragment;

public class AnimationFragment extends BaseFragment implements AnimationMvpView {

    private FloatingActionButton fab;
    private ImageView imageIcon;
    private AnimatorSet animatorSetForward, animatorSetBackkward;

    public static AnimationFragment newInstance() {
        AnimationFragment fragment = new AnimationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation, container, false);
        setUp(view);
        return view;
    }

    @Override
    public void setUp(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab_animation);
        fab.setOnClickListener(fabListener);
        imageIcon = (ImageView) view.findViewById(R.id.imageView_animation_icon);
        animatorSetForward = (AnimatorSet)
                AnimatorInflater.loadAnimator(getViewContext(), R.animator.animation_star);
        animatorSetForward.setTarget(imageIcon);
        animatorSetForward.addListener(animatorListenerForward);
        animatorSetBackkward = (AnimatorSet)
                AnimatorInflater.loadAnimator(getViewContext(), R.animator.animation_reverse_star);
        animatorSetBackkward.setTarget(imageIcon);
    }

    private FloatingActionButton.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!animatorSetForward.isRunning() || !animatorSetBackkward.isRunning()) {
                animatorSetForward.start();
            }
        }
    };

    private Animator.AnimatorListener animatorListenerForward = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {}

        @Override
        public void onAnimationEnd(Animator animation) {
            animatorSetBackkward.start();
        }

        @Override
        public void onAnimationCancel(Animator animation) {}

        @Override
        public void onAnimationRepeat(Animator animation) {}
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
