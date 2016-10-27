package com.example.sergioalejandro.evfinal2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sergioalejandro.evfinal2.R;

/**
 * Created by Admin on 10/27/2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;

    private Integer[] images = {
            R.drawable.winter1, R.drawable.winter2, R.drawable.winter3, R.drawable.winter4,
            R.drawable.winter5, R.drawable.winter6, R.drawable.winter7, R.drawable.winter8
    };

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return images.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // new ImageView
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        int imageSize = parent.getWidth()/2;
        imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(imageSize, imageSize));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        imageView.setImageResource(images[position]);
        return imageView;
    }
}
