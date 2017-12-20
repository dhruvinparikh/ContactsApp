package com.byethost24.dhruvinparikh.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.byethost24.dhruvinparikh.contacts.RVAdapter.CONTACT_NAME;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter adapter;
    Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        contacts = new Contacts();
        LoadData();
        adapter = new RVAdapter(this, contacts);
        rv.setAdapter(adapter);
    }

    private void SaveData() {
        String fileName = "contacts.txt";
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(contacts.getContacts()); //e.g  out.(writeObjectnotesData.getNotesData());
            out.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<Contact> c = new ArrayList<Contact>();
        c = data.getParcelableArrayListExtra(CONTACT_NAME);
        contacts.setContacts(c);
        adapter.notifyDataSetChanged();

        SaveData();
    }

    private void LoadData() {
        String fileName = "contacts.txt";
        ArrayList<Contact> dataArray = null; //dataRow is the data class (not the data list class)
        //if (new File(fileName).exists()) {
            try {

                FileInputStream inputStream = openFileInput(fileName);
                ObjectInputStream in = new ObjectInputStream(inputStream);
                dataArray = (ArrayList<Contact>) in.readObject();
                in.close();
                inputStream.close();
                contacts = new Contacts(dataArray); //DataProvider is the ArrayList class, like NotesData

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        /*} else {
            Log.d("dsp", "No data to load");
            contacts = new Contacts();
        }*/
    }
}
