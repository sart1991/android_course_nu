package com.exercises.sart1991.sqlitedrawer.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercises.sart1991.sqlitedrawer.R;
import com.exercises.sart1991.sqlitedrawer.data.db.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 5/17/2017.
 */

public class TableRecyclerAdapter extends RecyclerView.Adapter<TableRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Vehicle> vehicleList;

    public TableRecyclerAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_insert_tablerow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.txtIdVisualizer.setText(String.valueOf(vehicle.getId()));
        holder.txtBrandVisualizer.setText(String.valueOf(vehicle.getBrand()));
        holder.txtQuantityVisualizer.setText(String.valueOf(vehicle.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtIdVisualizer;
        private TextView txtBrandVisualizer;
        private TextView txtQuantityVisualizer;

        public ViewHolder(View itemView) {
            super(itemView);
            txtIdVisualizer = (TextView) itemView.findViewById(R.id.textView_tableRow_idVisualizer);
            txtBrandVisualizer = (TextView) itemView.findViewById(R.id.textView_tableRow_brandVisualizer);
            txtQuantityVisualizer = (TextView) itemView.findViewById(R.id.textView_tableRow_quantityVisualizer);
        }
    }
}
