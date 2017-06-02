package com.exercises.sart1991.backgroundtasks.ui.subview.usercard;

import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
import com.exercises.sart1991.backgroundtasks.ui.base.MvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/2/2017.
 */

public interface UserCardMvpSubView<C extends UserCardMvpSubView.Callback> extends MvpSubView<C> {

    void setUsers(List<User> users);
    UserCardSubView.UserCardAdapter getUserCardAdapter();

    interface Callback extends MvpSubView.Callback {

    }
}
