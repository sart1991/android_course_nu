package com.exercises.sart1991.evaluacionfinal9.ui.activitys.main;

import android.Manifest;
import android.os.StrictMode;
import android.support.v4.app.Fragment;

import com.exercises.sart1991.evaluacionfinal9.R;
import com.exercises.sart1991.evaluacionfinal9.ui.base.BasePresenter;
import com.exercises.sart1991.evaluacionfinal9.ui.fragments.animation.AnimationFragment;
import com.exercises.sart1991.evaluacionfinal9.ui.fragments.audio.AudioFragment;
import com.exercises.sart1991.evaluacionfinal9.ui.fragments.graphics.GraphicsFragment;
import com.exercises.sart1991.evaluacionfinal9.ui.fragments.image.ImageFragment;
import com.exercises.sart1991.evaluacionfinal9.ui.fragments.video.VideoFragment;

/**
 * Created by sart1 on 6/27/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {

    private Fragment animationFragment = AnimationFragment.newInstance();
    private Fragment graphicsFragment = GraphicsFragment.newInstance();
    private Fragment imageFragment = ImageFragment.newInstance();
    private Fragment audioFragment = AudioFragment.newInstance();
    private Fragment videoFragment = VideoFragment.newInstance();

    @Override
    public void welcome() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        getMvpView().showFragment(animationFragment);

        if (!getMvpView().hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            getMvpView().requestPermissionsSafely(
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    1
            );
        }

        if (!getMvpView().hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getMvpView().requestPermissionsSafely(
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1
            );
        }

        if (!getMvpView().hasPermission(Manifest.permission.RECORD_AUDIO)) {
            getMvpView().requestPermissionsSafely(
                    new String[]{
                            Manifest.permission.RECORD_AUDIO
                    },
                    1
            );
        }
    }

    @Override
    public void clickDrawerOption(int itemId) {
        switch (itemId) {
            case R.id.nav_animation:
                getMvpView().showFragment(animationFragment);
                return;
            case R.id.nav_graphics:
                getMvpView().showFragment(graphicsFragment);
                return;
            case R.id.nav_image:
                getMvpView().showFragment(imageFragment);
                return;
            case R.id.nav_audio:
                getMvpView().showFragment(audioFragment);
                return;
            case R.id.nav_video:
                getMvpView().showFragment(videoFragment);
                break;
        }
    }
}
