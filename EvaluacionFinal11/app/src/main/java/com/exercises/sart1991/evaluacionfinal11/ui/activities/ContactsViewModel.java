package com.exercises.sart1991.evaluacionfinal11.ui.activities;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import com.exercises.sart1991.evaluacionfinal11.repository.ContactsRepository;
import com.exercises.sart1991.evaluacionfinal11.repository.model.LittleContact;

import java.util.List;

/**
 * Created by sart1 on 9/28/2017.
 */

public class ContactsViewModel extends ViewModel {

    private static final String TAG = ContactsViewModel.class.getSimpleName();

    private ContactsRepository contactsRepository;
    private static MutableLiveData<List<LittleContact>> mutableContacts;

    public LiveData<List<LittleContact>> getContacts(Context context) {
        contactsRepository = ContactsRepository.getInstance(context);
        if (mutableContacts == null) {
            mutableContacts = new MutableLiveData<>();
            loadData();
        }
        return mutableContacts;
    }

    private void loadData() {
        new AsyncTask<Void, Void, List<LittleContact>>() {
            @Override
            protected List<LittleContact> doInBackground(Void... voids) {
                return contactsRepository.getContacts();
            }

            @Override
            protected void onPostExecute(List<LittleContact> contacts) {
                super.onPostExecute(contacts);
                Log.i(TAG, "onPostExecute: contacts: " + contacts);
                mutableContacts.setValue(contacts);
            }
        }.execute();
    }
}
