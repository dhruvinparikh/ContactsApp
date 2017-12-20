package com.byethost24.dhruvinparikh.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.byethost24.dhruvinparikh.contacts.RVAdapter.CONTACT_NAME;

public class EditContactActivity extends AppCompatActivity {
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText contactNumberEdit;
    ArrayList<Contact> contacts;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        position =  getIntent().getIntExtra("position", 0);

        contacts = getIntent().getParcelableArrayListExtra(CONTACT_NAME);


        firstNameEdit = findViewById(R.id.firstNameEdit);
        lastNameEdit = findViewById(R.id.lastNameEdit);
        contactNumberEdit = findViewById(R.id.contactNumberEdit);

        firstNameEdit.setText(contacts.get(position).getFirstName());
        lastNameEdit.setText(contacts.get(position).getLastName());
        contactNumberEdit.setText(contacts.get(position).getPhoneNumber());

        //Toast.makeText(this, "received position: "+position, Toast.LENGTH_SHORT).show();

    }

    public void saveClicked(View view) {
        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String contactNumber = contactNumberEdit.getText().toString();

        String contactName = "";
        if(firstName != "" || lastName != "") {
            contactName = firstName + " " + lastName;
        }
       /* else{
            contactName = serialNo.getText().toString();
        }*/

        contacts.get(position).setFirstName(firstName);
        contacts.get(position).setLastName(lastName);
        contacts.get(position).setPhoneNumber(contactNumber);
        contacts.get(position).setContactName(contactName);
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(CONTACT_NAME , contacts);
        setResult(RESULT_OK, intent);
        finish();
    }
}
