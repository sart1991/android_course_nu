package com.exercises.sart1991.evaluacionfinal9.ui.fragments.video;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.exercises.sart1991.evaluacionfinal9.R;
import com.exercises.sart1991.evaluacionfinal9.ui.base.BaseFragment;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment implements VideoMvpView {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = VideoFragment.class.getSimpleName();

    private Button buttonOpen, buttonTake;
    private VideoView videoView;
    private TextView txtPath;
    private Uri videoUri;

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        setUp(view);
        return view;
    }

    @Override
    public void setUp(View view) {
        buttonOpen = (Button) view.findViewById(R.id.button_video_open);
        buttonOpen.setOnClickListener(listenerOpen);
        buttonTake = (Button) view.findViewById(R.id.button_video_take);
        buttonTake.setOnClickListener(listenerTake);
        videoView = (VideoView) view.findViewById(R.id.videoView_video_icon);
        txtPath = (TextView) view.findViewById(R.id.textView_video_routeContainer);
    }

    private View.OnClickListener listenerOpen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent selectIntent = new Intent(
                    Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            );
            startActivityForResult(selectIntent, REQUEST_CODE);
        }
    };

    private View.OnClickListener listenerTake = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File videoFolder = new File(
                    Environment.getExternalStorageDirectory(),
                    "VideoFolder"
            );
            if (videoFolder.mkdirs() || videoFolder.exists()) {
                File video = new File(videoFolder, "video.mp4");
                videoUri = Uri.fromFile(video);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(cameraIntent, REQUEST_CODE);
            } else {
                onNotify("Error al iniciar cámara");
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String pathVideo = "";
        if (requestCode == REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            MediaController mediaController = new MediaController(getViewContext());
            videoView.setMediaController(mediaController);
            if (data != null) {
                videoUri = data.getData();
                String[] path = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(
                        videoUri, path, null, null, null
                );
                cursor.moveToFirst();
                pathVideo = cursor.getString(cursor.getColumnIndex(path[0]));
                cursor.close();
            } else {
                pathVideo = Environment.getExternalStorageDirectory() + "/VideoFolder/video.mp4";
            }
            videoView.setVideoURI(videoUri);
            videoView.start();
            txtPath.setText(pathVideo);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
