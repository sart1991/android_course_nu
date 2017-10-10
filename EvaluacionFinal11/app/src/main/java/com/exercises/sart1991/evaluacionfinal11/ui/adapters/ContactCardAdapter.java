package com.exercises.sart1991.evaluacionfinal11.ui.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal11.R;
import com.exercises.sart1991.evaluacionfinal11.repository.contacts.model.LittleContact;

import java.util.List;

/**
 * Created by sart1 on 9/28/2017.
 */

public class ContactCardAdapter extends RecyclerView.Adapter<ContactCardAdapter.ContactViewHolder> {

    private List<LittleContact> contacts;

    public ContactCardAdapter(List<LittleContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.txtName.setText(contacts.get(position).getName());
        holder.txtNumber.setText(contacts.get(position).getNumber());
        holder.selectSwitch.setChecked(contacts.get(position).isSelected());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtNumber;
        Switch selectSwitch;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.textView_contactCard_name);
            txtNumber = (TextView) itemView.findViewById(R.id.textView_contactCard_number);
            selectSwitch = (Switch) itemView.findViewById(R.id.switch_contactCard_selected);
            selectSwitch.setOnClickListener(selectedClickListener);
        }

        private Switch.OnClickListener selectedClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.get(getAdapterPosition()).setSelected(selectSwitch.isChecked());
                showSnackForSelected(selectSwitch.isChecked(),
                                     contacts.get(getAdapterPosition()).getName());
            }
        };

        private void showSnackForSelected(boolean selected, String name) {
            String message = "El contacto " + name + " será alertado en caso de emergencia.";
            int resource = android.R.color.holo_green_dark;
            if (!selected) {
                message = "El contacto " + name + " ya no será alertado en caso de emergencia.";
                resource = android.R.color.holo_red_dark;
            }
            showSnackBar(message, resource);
        }

        private void showSnackBar(String message, int resColor) {
            Snackbar snack = Snackbar.make(txtName, message, Snackbar.LENGTH_LONG);
            snack.getView().setBackgroundResource(resColor);
            snack.show();
        }
    }
}
