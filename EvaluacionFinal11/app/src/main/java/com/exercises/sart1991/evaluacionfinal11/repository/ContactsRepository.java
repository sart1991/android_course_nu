package com.exercises.sart1991.evaluacionfinal11.repository;

/**
 * Created by sart1 on 9/27/2017.
 */

public class ContactsRepository {

    private static ContactsRepository mInstance;

    private ContactsRepository() {}

    public ContactsRepository getInstance() {
        if (mInstance == null) {
            mInstance = new ContactsRepository();
        }
        return mInstance;
    }



}
