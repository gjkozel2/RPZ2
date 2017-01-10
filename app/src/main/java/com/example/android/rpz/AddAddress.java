package com.example.android.rpz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;



public class AddAddress extends AppCompatActivity {


    DatabaseReference aDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_address);
        Button submit = (Button) findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText address = (EditText) findViewById(R.id.address);
                EditText contact = (EditText) findViewById(R.id.contact);
                EditText phone = (EditText) findViewById(R.id.phone);
                EditText make = (EditText) findViewById(R.id.make);
                EditText serial = (EditText) findViewById(R.id.serial);
                EditText size = (EditText) findViewById(R.id.size);
                EditText service = (EditText) findViewById(R.id.service);
                EditText location = (EditText) findViewById(R.id.location);
                EditText report = (EditText) findViewById(R.id.report);
                EditText due = (EditText) findViewById(R.id.due);
                EncodeDecode newEncode = new EncodeDecode();
                String newAddress = newEncode.encodeForFirebaseKey(address.getText().toString().trim());
                String newContact = newEncode.encodeForFirebaseKey(contact.getText().toString().trim());
                String newPhone = newEncode.encodeForFirebaseKey(phone.getText().toString().trim());
                String newMake = newEncode.encodeForFirebaseKey(make.getText().toString().trim());
                String newSerial = newEncode.encodeForFirebaseKey(serial.getText().toString().trim());
                String newSize = newEncode.encodeForFirebaseKey(size.getText().toString().trim());
                String newService = newEncode.encodeForFirebaseKey(service.getText().toString().trim());
                String newLocation = newEncode.encodeForFirebaseKey(location.getText().toString().trim());
                String newReport = newEncode.encodeForFirebaseKey(report.getText().toString().trim());
                String newDue = newEncode.encodeForFirebaseKey(due.getText().toString().trim());

                if(!newAddress.equals("")&& !newContact.equals("")&& !newPhone.equals("")&& !newMake.equals("") && !newSerial.equals("")&& !newSize.equals("")&& !newService.equals("")&& !newLocation.equals("")&& !newReport.equals("") && !newDue.equals(""))
                {
                    aDatabase.child("Addresses").child(newAddress).child("Contact").setValue(newContact);
                    aDatabase.child("Addresses").child(newAddress).child("Phone").setValue(newPhone);
                    aDatabase.child("Addresses").child(newAddress).child("Make").setValue(newMake);
                    aDatabase.child("Addresses").child(newAddress).child("Serial").setValue(newSerial);
                    aDatabase.child("Addresses").child(newAddress).child("Size").setValue(newSize);
                    aDatabase.child("Addresses").child(newAddress).child("Service").setValue(newService);
                    aDatabase.child("Addresses").child(newAddress).child("Location").setValue(newLocation);
                    aDatabase.child("Addresses").child(newAddress).child("Report").setValue(newReport);
                    aDatabase.child("Addresses").child(newAddress).child("Due").setValue(newDue);
                    Toast.makeText(getBaseContext(), "Address created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(),AddressList.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getBaseContext(),"Please do not leave any field blank", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}

