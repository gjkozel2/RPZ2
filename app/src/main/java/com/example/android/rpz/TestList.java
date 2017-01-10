package com.example.android.rpz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by littl on 1/9/2017.
 */

public class TestList extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_list);
        Intent intent = getIntent();
        final EncodeDecode newEncode = new EncodeDecode();
        final String newAddress = intent.getStringExtra("address");
        final ListView listView =(ListView) findViewById(R.id.listViewTest);
        final ArrayList<String> add = new ArrayList<String>();

        FirebaseDatabase aDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference aDatabaseReference = aDatabase.getReference().child("Addresses").child(newEncode.encodeForFirebaseKey(newAddress)).child("Tests");
        aDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot addSnapshot: dataSnapshot.getChildren()) {
                    Log.i("Tests",addSnapshot.getKey());
                    add.add(newEncode.decodeFromFirebaseKey(addSnapshot.getKey()));
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(TestList.this, android.R.layout.simple_list_item_1,add);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id )
            {
                String[] newadd = add.toArray(new String[add.size()]);
                Intent intent = new Intent(getBaseContext(),TestView.class);
                intent.putExtra("testDate", newadd[position]);
                intent.putExtra("currAddress", newAddress);
                startActivity(intent);
            }
        });

    }
}
