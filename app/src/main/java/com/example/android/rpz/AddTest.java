package com.example.android.rpz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by littl on 1/8/2017.
 */

public class AddTest extends AppCompatActivity {
    DatabaseReference tDatabase = FirebaseDatabase.getInstance().getReference();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_view);
        Button submit = (Button) findViewById(R.id.submit_button2);
        Intent intent = getIntent();

        final EncodeDecode newEncode = new EncodeDecode();
        final String newAddress = newEncode.encodeForFirebaseKey(intent.getStringExtra("address"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name = (EditText) findViewById(R.id.name);
                EditText date = (EditText) findViewById(R.id.date);
                EditText cccd = (EditText) findViewById(R.id.cccd);
                EditText cv1 = (EditText) findViewById(R.id.cv1);
                EditText cv2 = (EditText) findViewById(R.id.cv2);
                EditText relief = (EditText) findViewById(R.id.relief);
                EditText comments = (EditText) findViewById(R.id.comments);
                EditText pass = (EditText) findViewById(R.id.pass);


                String newName = newEncode.encodeForFirebaseKey(name.getText().toString().trim());
                String newDate = newEncode.encodeForFirebaseKey(date.getText().toString().trim());
                String newCCCD = newEncode.encodeForFirebaseKey(cccd.getText().toString().trim());
                String newCv1 = newEncode.encodeForFirebaseKey(cv1.getText().toString().trim());
                String newCv2 = newEncode.encodeForFirebaseKey(cv2.getText().toString().trim());
                String newRelief = newEncode.encodeForFirebaseKey(relief.getText().toString().trim());
                String newComments = newEncode.encodeForFirebaseKey(comments.getText().toString().trim());
                String newPass = newEncode.encodeForFirebaseKey(pass.getText().toString().trim());
                if(!newName.equals("")&& !newDate.equals("")&& !newCCCD.equals("")&& !newCv1.equals("") && !newCv2.equals("")&& !newRelief.equals("")&& !newComments.equals("") && !newPass.equals(""))
                {

                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("Name").setValue(newName);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("Date").setValue(newDate);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("CCCD").setValue(newCCCD);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("CV1").setValue(newCv1);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("CV2").setValue(newCv2);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("Relief").setValue(newRelief);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("Comments").setValue(newComments);
                    tDatabase.child("Addresses").child(newAddress).child("Tests").child(newDate).child("Pass").setValue(newPass);
                    Toast.makeText(getBaseContext(), "Test created", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getBaseContext(),"Please do not leave any field blank", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
