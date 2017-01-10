package com.example.android.rpz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.content.Context;
/**
 * Created by littl on 1/6/2017.
 */

public class AddressList extends AppCompatActivity {
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_list);
        final ListView listView =(ListView) findViewById(R.id.listView);
        final ArrayList<String> add = new ArrayList<String>();
        FirebaseDatabase aDatabase = FirebaseDatabase.getInstance();
        final EncodeDecode newDecode = new EncodeDecode();
        final DatabaseReference aDatabaseReference = aDatabase.getReference("Addresses");
        aDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot addSnapshot: dataSnapshot.getChildren()) {
                    Log.i("Addresses",addSnapshot.getKey());
                    add.add(newDecode.decodeFromFirebaseKey(addSnapshot.getKey()));
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(AddressList.this, android.R.layout.simple_list_item_1,add);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id )
            {
                String[] newadd = add.toArray(new String[add.size()]);
                Intent intent = new Intent(getBaseContext(),AddressView.class);
                intent.putExtra("address", newadd[position]);
                startActivity(intent);
            }
        });

    }
}
