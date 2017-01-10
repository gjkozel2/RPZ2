package com.example.android.rpz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import com.google.firebase.FirebaseApp;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        FirebaseApp.initializeApp(context);
        Button list_address = (Button) findViewById(R.id.address_list_view);
        Button new_address = (Button) findViewById(R.id.add_address);

        list_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddressList.class);
                startActivity(intent);
            }
        });
        new_address.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),AddAddress.class);
               startActivity(intent);
           }

        });
    }
}

