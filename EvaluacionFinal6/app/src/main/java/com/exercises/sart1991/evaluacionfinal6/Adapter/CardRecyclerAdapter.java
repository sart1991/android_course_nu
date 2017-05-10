package com.exercises.sart1991.evaluacionfinal6.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal6.R;
import com.exercises.sart1991.evaluacionfinal6.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 5/9/2017.
 */

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<Vehicle> vehicleList = new ArrayList<>();

    public CardRecyclerAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }


    @Override
    public CardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardRecyclerAdapter.ViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);

        holder.txtRegistrationNumber.setText(vehicle.getRegistrationNumber());
        holder.txtClientId.setText(vehicle.getClientId());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtRegistrationNumber;
        TextView txtClientId;

        ViewHolder(View itemView) {
            super(itemView);
            txtRegistrationNumber = (TextView) itemView.findViewById(R.id.txt_carId);
            txtClientId = (TextView) itemView.findViewById(R.id.txt_userId);
        }
    }
}
