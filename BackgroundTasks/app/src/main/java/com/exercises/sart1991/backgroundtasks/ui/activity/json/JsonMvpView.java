package com.exercises.sart1991.backgroundtasks.ui.activity.json;

import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
import com.exercises.sart1991.backgroundtasks.ui.base.MvpView;
import com.exercises.sart1991.backgroundtasks.ui.subview.usercard.UserCardMvpSubView;

import java.util.List;

/**
 * Created by sart1 on 6/1/2017.
 */

public interface JsonMvpView extends MvpView, UserCardMvpSubView.Callback {

    void setUserCards(List<User> userList);
}
