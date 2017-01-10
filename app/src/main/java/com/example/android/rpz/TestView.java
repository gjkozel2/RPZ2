package com.example.android.rpz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by littl on 1/9/2017.
 */

public class TestView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_view_specific);
        EncodeDecode newEncode = new EncodeDecode();
        final EncodeDecode newDecode = new EncodeDecode();
        Intent intent = getIntent();
        final String ndate = intent.getStringExtra("testDate");
        final String naddress = intent.getStringExtra("currAddress");
        DatabaseReference aDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Addresses").child(newEncode.encodeForFirebaseKey(naddress)).child("Tests").child(newEncode.encodeForFirebaseKey(ndate));
        aDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currName = dataSnapshot.child("Name").getValue().toString();
                String currCCCD = dataSnapshot.child("CCCD").getValue().toString();
                String currCV1 = dataSnapshot.child("CV1").getValue().toString();
                String currCV2 = dataSnapshot.child("CV2").getValue().toString();
                String currRelief = dataSnapshot.child("Relief").getValue().toString();
                String currComments = dataSnapshot.child("Comments").getValue().toString();
                String currPass = dataSnapshot.child("Pass").getValue().toString();
                TextView dTextView = (TextView) findViewById(R.id.viewDate);
                TextView naTextView = (TextView) findViewById(R.id.viewName);
                TextView ccTextView = (TextView) findViewById(R.id.viewCCCD);
                TextView cvTextView = (TextView) findViewById(R.id.viewCV1);
                TextView cvvTextView = (TextView) findViewById(R.id.viewCV2);
                TextView reTextView = (TextView) findViewById(R.id.viewRelief);
                TextView comTextView = (TextView) findViewById(R.id.viewComments);
                TextView paTextView = (TextView) findViewById(R.id.viewPass);
                dTextView.setText(newDecode.decodeFromFirebaseKey(ndate));
                naTextView.setText(newDecode.decodeFromFirebaseKey(currName));
                ccTextView.setText(newDecode.decodeFromFirebaseKey(currCCCD));
                cvTextView.setText(newDecode.decodeFromFirebaseKey(currCV1));
                cvvTextView.setText(newDecode.decodeFromFirebaseKey(currCV2));
                reTextView.setText(newDecode.decodeFromFirebaseKey(currRelief));
                comTextView.setText(newDecode.decodeFromFirebaseKey(currComments));
                paTextView.setText(newDecode.decodeFromFirebaseKey(currPass));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
