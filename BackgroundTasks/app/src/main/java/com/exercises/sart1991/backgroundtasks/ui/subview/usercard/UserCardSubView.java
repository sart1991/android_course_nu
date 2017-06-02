package com.exercises.sart1991.backgroundtasks.ui.subview.usercard;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/2/2017.
 */

public class UserCardSubView<C extends UserCardMvpSubView.Callback>
        extends BaseSubView<C> implements UserCardMvpSubView<C> {

    private static final String TAG = UserCardSubView.class.getSimpleName();

    private List<User> users;
    private UserCardAdapter userCardAdapter;

    public UserCardSubView() {
        users = new ArrayList<>();
        userCardAdapter = new UserCardAdapter();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        userCardAdapter.notifyDataSetChanged();
    }

    @Override
    public UserCardAdapter getUserCardAdapter() {
        return userCardAdapter;
    }

    class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {

        @Override
        public UserCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(
                    getViewContext()).inflate(R.layout.cardview_user, parent, false
            );
            return new UserCardAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(UserCardAdapter.ViewHolder holder, int position) {
            holder.txtId.setText(String.valueOf(users.get(position).getId()));
            holder.txtUsername.setText(users.get(position).getUsername());
            holder.txtEmail.setText(users.get(position).getEmail());
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtId;
            TextView txtUsername;
            TextView txtEmail;

            ViewHolder(View itemView) {
                super(itemView);
                txtId = (TextView) itemView.findViewById(R.id.textView_cardUser_id);
                txtUsername = (TextView) itemView.findViewById(R.id.textView_cardUser_username);
                txtEmail = (TextView) itemView.findViewById(R.id.textView_cardUser_email);
            }
        }
    }
}
