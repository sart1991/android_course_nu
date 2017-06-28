package com.exercises.sart1991.evaluacionfinal9.ui.fragments.audio;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal9.R;
import com.exercises.sart1991.evaluacionfinal9.ui.base.BaseFragment;

import java.io.File;
import java.io.IOException;

public class AudioFragment extends BaseFragment implements AudioMvpView {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = AudioFragment.class.getSimpleName();

    private Button buttonOpen, buttonTake, buttonControl;
    private TextView txtPath;
    private String pathAudio;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private boolean recording = false, playing = false;

    public static AudioFragment newInstance() {
        AudioFragment fragment = new AudioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        setUp(view);
        return view;
    }

    @Override
    public void setUp(View view) {
        buttonOpen = (Button) view.findViewById(R.id.button_audio_open);
        buttonOpen.setOnClickListener(listenerOpen);
        buttonTake = (Button) view.findViewById(R.id.button_audio_take);
        buttonTake.setOnClickListener(listenerTake);
        buttonControl = (Button) view.findViewById(R.id.button_audio_play);
        buttonControl.setOnClickListener(listenerPlay);
        buttonControl.setEnabled(false);
        txtPath = (TextView) view.findViewById(R.id.textView_audio_routeContainer);
        pathAudio = Environment.getExternalStorageDirectory() + "/AudioFolder/audio.3gp";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(pathAudio);
        mediaPlayer = new MediaPlayer();
    }

    private View.OnClickListener listenerOpen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent selectIntent = new Intent(
                    Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            );
            startActivityForResult(selectIntent, REQUEST_CODE);
        }
    };

    private View.OnClickListener listenerTake = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!recording) {
                startRecordingAudio();
            } else {
                stopRecordingAudio();
            }
        }
    };

    private View.OnClickListener listenerPlay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!playing) {
                startPlayingAudio();
            } else {
                stopPlayingAudio();
            }
        }
    };

    private void startRecordingAudio() {
        recording = true;
        buttonTake.setText(R.string.audio_buttonTake_stop);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        buttonControl.setEnabled(false);
    }

    private void stopRecordingAudio() {
        recording = false;
        buttonTake.setText(R.string.audio_buttonTake);
        mediaRecorder.stop();
        mediaRecorder.release();
        buttonControl.setEnabled(true);
    }

    private void startPlayingAudio() {
        playing = true;
        buttonControl.setText(R.string.audio_buttonPlay_stop);
        try {
            mediaPlayer.setDataSource(pathAudio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPlayingAudio() {
        playing = false;
        buttonControl.setText(R.string.audio_buttonPlay);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            if (data != null) {
                buttonControl.setEnabled(true);
                Uri selectedAudio = data.getData();
                String[] path = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(
                        selectedAudio, path, null, null, null
                );
                cursor.moveToFirst();
                pathAudio = cursor.getString(cursor.getColumnIndex(path[0]));
                cursor.close();
            }
            txtPath.setText(pathAudio);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mediaPlayer = null;
        mediaRecorder = null;
    }
}
