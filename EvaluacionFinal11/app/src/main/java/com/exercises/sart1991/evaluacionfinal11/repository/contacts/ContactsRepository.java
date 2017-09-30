package com.exercises.sart1991.evaluacionfinal11.repository.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.exercises.sart1991.evaluacionfinal11.repository.contacts.model.LittleContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 9/27/2017.
 */

public class ContactsRepository {

    private final Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
    private final String CONTACT_ID = ContactsContract.Contacts._ID;
    private final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    private final Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private final String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    private final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    //private final Uri NAME_CONTENT_URI = ContactsContract.CommonDataKinds.StructuredName.URI;
    private final String NAME_CONTACT_ID = ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID;
    private final String NAME = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME;

    private Context context;
    private Cursor cursor;
    private ContentResolver contentResolver;

    private static ContactsRepository mInstance;

    private ContactsRepository(Context context) {
        this.context = context;
        contentResolver = context.getContentResolver();
    }

    public static ContactsRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ContactsRepository(context);
        }
        return mInstance;
    }

    public List<LittleContact> getContacts() {
        List<LittleContact> contacts = new ArrayList<>();

        cursor = contentResolver.query(CONTACTS_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                LittleContact contact = new LittleContact();

                contact.setContactId(cursor.getString(cursor.getColumnIndex(CONTACT_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
                int hasNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasNumber > 0) {
                    Cursor c = contentResolver.query(
                            PHONE_CONTENT_URI, null,
                            PHONE_CONTACT_ID + " = ?",
                            new String[] {contact.getContactId()}, null);
                    if (c != null){
                        if (c.moveToFirst()) {
                            do {
                                contact.setNumber(c.getString(c.getColumnIndex(NUMBER)));
                            } while (c.moveToNext());
                        }
                        c.close();
                    }
                }
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        return contacts;
    }

}
