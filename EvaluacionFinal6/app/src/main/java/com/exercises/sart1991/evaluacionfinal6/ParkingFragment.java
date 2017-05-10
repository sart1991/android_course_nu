package com.exercises.sart1991.evaluacionfinal6;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercises.sart1991.evaluacionfinal6.Adapter.CardRecyclerAdapter;
import com.exercises.sart1991.evaluacionfinal6.model.Vehicle;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParkingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ParkingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParkingFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "VEHICLE_LIST";
    private static final String TAG = ParkingFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private List<Vehicle> mVehicleList;
    private CardRecyclerAdapter recyclerAdapter;
    private OnFragmentInteractionListener mListener;

    public ParkingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param vehicleList Parameter 1.
     * @return A new instance of fragment ParkingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParkingFragment newInstance(List<Vehicle> vehicleList) {
        ParkingFragment fragment = new ParkingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST, ((ArrayList)vehicleList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVehicleList = (ArrayList<Vehicle>) getArguments().getSerializable(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parking, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(
                view.getContext(), 2,
                GridLayoutManager.VERTICAL, false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new CardRecyclerAdapter(view.getContext(), mVehicleList);
        recyclerView.setAdapter(recyclerAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(onFloatClickListener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onClickFloat(CardRecyclerAdapter recyclerAdapter);
    }

    private View.OnClickListener onFloatClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mListener.onClickFloat(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();
        }
    };
}
