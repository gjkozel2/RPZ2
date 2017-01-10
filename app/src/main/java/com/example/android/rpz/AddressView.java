package com.example.android.rpz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.Button;
/**
 * Created by littl on 1/7/2017.
 */

public class AddressView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_view);
        Intent intent = getIntent();
        final String naddress = intent.getStringExtra("address");
        EncodeDecode newEncode = new EncodeDecode();
        final EncodeDecode newDecode = new EncodeDecode();
        DatabaseReference aDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Addresses").child(newEncode.encodeForFirebaseKey(naddress));
        aDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currContact = dataSnapshot.child("Contact").getValue().toString();
                String currPhone = dataSnapshot.child("Phone").getValue().toString();
                String currMake = dataSnapshot.child("Make").getValue().toString();
                String currSerial = dataSnapshot.child("Serial").getValue().toString();
                String currSize = dataSnapshot.child("Size").getValue().toString();
                String currService = dataSnapshot.child("Service").getValue().toString();
                String currLocation = dataSnapshot.child("Location").getValue().toString();
                String currReport = dataSnapshot.child("Report").getValue().toString();
                String currDue = dataSnapshot.child("Due").getValue().toString();
                TextView mTextView = (TextView) findViewById(R.id.viewAddress);
                TextView adTextView = (TextView) findViewById(R.id.viewContact);
                TextView phTextView = (TextView) findViewById(R.id.viewPhone);
                TextView maTextView = (TextView) findViewById(R.id.viewMake);
                TextView seriTextView = (TextView) findViewById(R.id.viewSerial);
                TextView siTextView = (TextView) findViewById(R.id.viewSize);
                TextView servTextView = (TextView) findViewById(R.id.viewService);
                TextView locTextView = (TextView) findViewById(R.id.viewLocation);
                TextView reTextView = (TextView) findViewById(R.id.viewReport);
                TextView dueTextView = (TextView) findViewById(R.id.viewDue);
                mTextView.setText(newDecode.decodeFromFirebaseKey(naddress));
                adTextView.setText(newDecode.decodeFromFirebaseKey(currContact));
                phTextView.setText(newDecode.decodeFromFirebaseKey(currPhone));
                maTextView.setText(newDecode.decodeFromFirebaseKey(currMake));
                seriTextView.setText(newDecode.decodeFromFirebaseKey(currSerial));
                siTextView.setText(newDecode.decodeFromFirebaseKey(currSize));
                servTextView.setText(newDecode.decodeFromFirebaseKey(currService));
                locTextView.setText(newDecode.decodeFromFirebaseKey(currLocation));
                reTextView.setText(newDecode.decodeFromFirebaseKey(currReport));
                dueTextView.setText(newDecode.decodeFromFirebaseKey(currDue));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button addTest = (Button) findViewById(R.id.addTest);
        Button viewTest = (Button) findViewById(R.id.viewTest);

        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AddTest.class);
                intent.putExtra("address", naddress);
                startActivity(intent);
            }
        });
        viewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),TestList.class);
                intent.putExtra("address", naddress);
                startActivity(intent);
            }
        });
    }
}
