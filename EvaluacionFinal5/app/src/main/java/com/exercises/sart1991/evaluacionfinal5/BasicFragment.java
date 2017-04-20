package com.exercises.sart1991.evaluacionfinal5;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicFragment extends Fragment {
    // the fragment initialization parameters
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_DRAWABLE = "drawable";

    private int mDescription;
    private int mDrawable;


    public BasicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param description Parameter 1.
     * @param drawable Parameter 2.
     * @return A new instance of fragment BasicFragment.
     */
    public static BasicFragment newInstance(int description, int drawable) {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DESCRIPTION, description);
        args.putInt(ARG_DRAWABLE, drawable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDescription = getArguments().getInt(ARG_DESCRIPTION);
            mDrawable = getArguments().getInt(ARG_DRAWABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.content_image);
        imageView.setImageResource(mDrawable);
        TextView textView = (TextView) view.findViewById(R.id.content_text);
        textView.setText(mDescription);
        return view;
    }

}
