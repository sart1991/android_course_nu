package com.exercises.sart1991.evaluacionfinal9.ui.fragments.image;


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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal9.R;
import com.exercises.sart1991.evaluacionfinal9.ui.base.BaseFragment;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends BaseFragment implements ImageMvpView {

    private static final int REQUEST_CODE_TAKE = 1;
    private static final int REQUEST_CODE_OPEN = 2;
    private static final String TAG = ImageFragment.class.getSimpleName();

    private Button buttonOpen, buttonTake;
    private ImageView imageIcon;
    private TextView txtPath;

    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        setUp(view);
        return view;
    }

    @Override
    public void setUp(View view) {
        buttonOpen = (Button) view.findViewById(R.id.button_image_open);
        buttonOpen.setOnClickListener(listenerOpen);
        buttonTake = (Button) view.findViewById(R.id.button_image_take);
        buttonTake.setOnClickListener(listenerTake);
        imageIcon = (ImageView) view.findViewById(R.id.imageView_image_icon);
        txtPath = (TextView) view.findViewById(R.id.textView_image_routeContainer);
    }

    private View.OnClickListener listenerOpen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent selectIntent = new Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );
            startActivityForResult(selectIntent, REQUEST_CODE_OPEN);
        }
    };

    private View.OnClickListener listenerTake = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File imageFolder = new File(
                    Environment.getExternalStorageDirectory(),
                    "ImageFolder"
            );
            if (imageFolder.mkdirs() || imageFolder.exists()) {
                File image = new File(imageFolder, "photo.jpg");
                Uri imageUri = Uri.fromFile(image);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, REQUEST_CODE_TAKE);
            } else {
                onNotify("Error al iniciar cámara");
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String pathImage = "";
        if (resultCode == getActivity().RESULT_OK) {
            if (data != null && requestCode == REQUEST_CODE_OPEN) {
                Uri selectedImage = data.getData();
                String[] path = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(
                        selectedImage, path, null, null, null
                );
                cursor.moveToFirst();
                pathImage = cursor.getString(cursor.getColumnIndex(path[0]));
                cursor.close();
            } else if(requestCode == REQUEST_CODE_TAKE) {
                pathImage = Environment.getExternalStorageDirectory() + "/ImageFolder/photo.jpg";
            }
            Bitmap bitmap = BitmapFactory.decodeFile(pathImage);

            Matrix matrix = new Matrix();
            matrix.postScale(0.5f, 0.5f);

            Bitmap newImage = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true
            );
            imageIcon.setImageBitmap(newImage);
            txtPath.setText(pathImage);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
