package com.byethost24.dhruvinparikh.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by imsil on 18/12/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH>{
    public static final String CONTACT_NAME = "contact_name";
    Contacts contacts;
    Context context;

    public RVAdapter(Context context,Contacts contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    public RVAdapter() {
        contacts = new Contacts();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_view,parent,false);
        VH viewHolder = new VH(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        Contact contact = contacts.getContacts().get(position);
        String cName = "";
        if(!contact.getFirstName().isEmpty() || !contact.getLastName().isEmpty()){
            cName = contact.getFirstName()+" "+contact.getLastName();
        }
        else{
            cName = contact.getSerialNo();
        }
        holder.contactTV.setText(cName);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EditContactActivity.class);
                intent.putExtra("position",position);
                ArrayList<Contact> contact = contacts.getContacts();
                intent.putParcelableArrayListExtra(CONTACT_NAME,contact);
                ((Activity)context).startActivityForResult(intent,2017);
            }
        });
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getItemCount(){
        return contacts.getSize();
    }

    public class VH extends RecyclerView.ViewHolder{
        TextView contactTV;
        View v;
        public VH(View itemView) {
            super(itemView);
            contactTV = itemView.findViewById(R.id.contactTV);
            v = itemView;
        }
    }
}
