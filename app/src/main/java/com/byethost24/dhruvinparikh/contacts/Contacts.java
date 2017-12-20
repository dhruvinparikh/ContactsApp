package com.byethost24.dhruvinparikh.contacts;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by imsil on 18/12/17.
 */

public class Contacts {
    private ArrayList<Contact> contacts;

    public Contacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contacts() {
        contacts = new ArrayList<Contact>();
        for(int i = 0 ; i < 20 ; i++){
            contacts.add(new Contact("","","" ,"<Empty>Contact#"+(i+1),""));
        }
    }

    public int getSize(){
        return contacts.size();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contacts=" + contacts +
                '}';
    }
}
