package com.exercises.sart1991.evaluacionfinal7.ui.subview.donor;

import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.ui.base.SubMvpView;

import java.util.List;

/**
 * Created by sart1 on 5/23/2017.
 */

public interface CardDonorSubMvpView extends SubMvpView {
    void setDonorList(List<Donor> donorList);
    void onDonorListChanged();
    CardDonor.DonorAdapter getDonorAdapter();
}
