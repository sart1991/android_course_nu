package com.exercises.sart1991.evaluacionfinal7.ui.subview.donor;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.ui.activity.main.MainMvpPresenter;
import com.exercises.sart1991.evaluacionfinal7.ui.activity.main.MainPresenter;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BaseSubView;
import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpPresenter;
import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpView;

import java.util.List;

/**
 * Created by sart1 on 5/23/2017.
 */

public class CardDonor extends BaseSubView implements CardDonorSubMvpView {

    private static final String TAG = CardDonor.class.getSimpleName();

    private List<Donor> donorList;
    private DonorAdapter adapter = new DonorAdapter();
    private static MainMvpPresenter PRESENTER;

    @Override
    public void onAttach(MvpView mvpView, MvpPresenter mvpPresenter) {
        super.onAttach(mvpView, mvpPresenter);
        PRESENTER = (MainMvpPresenter) mvpPresenter;
    }

    @Override
    public void setDonorList(List<Donor> donorList) {
        this.donorList = donorList;
    }

    @Override
    public void onDonorListChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public DonorAdapter getDonorAdapter() {
        return adapter;
    }

    class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder> {

        @Override
        public DonorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getViewContext()).inflate(R.layout.layout_cardview_donor, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DonorAdapter.ViewHolder holder, int position) {
            Donor donor = donorList.get(position);
            String name = donor.getName() + " " + donor.getLastName();
            String age = donor.getAge() + " " + getViewContext().getString(R.string._years);
            String weight = donor.getWeight() + " " + getViewContext().getString(R.string._kg);
            String height = donor.getHeight() + " " + getViewContext().getString(R.string._cm);
            holder.txtName.setText(name);
            holder.txtId.setText(String.valueOf(donor.getId()));
            holder.txtAge.setText(age);
            holder.txtBloodType.setText(String.valueOf(donor.getBloodType()));
            holder.txtRh.setText(String.valueOf(donor.getRh()));
            holder.txtWeight.setText(weight);
            holder.txtHeight.setText(height);
            holder.buttonDots.setOnClickListener(holder.dotsOnClickListener);
        }

        @Override
        public int getItemCount() {
            return donorList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtName;
            TextView txtId;
            TextView txtAge;
            TextView txtBloodType;
            TextView txtRh;
            TextView txtWeight;
            TextView txtHeight;
            ImageButton buttonDots;
            private MenuPopupHelper popupMenu;

            private ViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.textView_cardViewDonor_name);
                txtId= (TextView) itemView.findViewById(R.id.textView_cardViewDonor_idContainer);
                txtAge = (TextView) itemView.findViewById(R.id.textView_cardViewDonor_ageContainer);
                txtBloodType = (TextView) itemView.findViewById(R.id.textView_cardViewDonor_bloodTypeContainer);
                txtRh = (TextView) itemView.findViewById(R.id.textView_cardViewDonor_rhContainer);
                txtWeight = (TextView) itemView.findViewById(R.id.textView_cardViewDonor_weightContainer);
                txtHeight = (TextView) itemView.findViewById(R.id.textView_cardViewDonor_heightContainer);
                buttonDots = (ImageButton) itemView.findViewById(R.id.imageButton_cardViewDonor_dotsMenu);
                MenuBuilder builder = new MenuBuilder(getViewContext());
                builder.setCallback(callback);
                MenuInflater inflater = new MenuInflater(getViewContext());
                inflater.inflate(R.menu.cardview_donor, builder);
                popupMenu = new MenuPopupHelper(getViewContext(), builder, buttonDots);
                popupMenu.setForceShowIcon(true);
            }

            private MenuBuilder.Callback callback =  new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                    PRESENTER.onDonorMenuClick(donorList.get(getAdapterPosition()), item.getItemId());
                    return true;
                }

                @Override
                public void onMenuModeChange(MenuBuilder menu) {

                }
            };

            private View.OnClickListener dotsOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupMenu.show();
                }
            };
        }
    }


}
