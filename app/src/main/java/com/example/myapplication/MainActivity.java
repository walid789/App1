package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.type.LatLng;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.buttonPanel2);
        Button button2 = findViewById(R.id.buttonPanel1);
        Button button4 = findViewById(R.id.buttonPanel4);




       // String address = "1600 Amphitheatre Parkway, Mountain View, CA";
        Geocoder geocoder = new Geocoder(this);



        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.search);
                String value = editText.getText().toString();
                Intent intent =new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("search",value);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.search);
                String value = editText.getText().toString();
                Intent intent =new Intent(MainActivity.this,Users_update.class);
                intent.putExtra("search",value);
                startActivity(intent);
            }
        });



          button4.setOnClickListener(new View.OnClickListener() {
              String valeur;
              double lat ;
              double lng;
              @Override
                public void onClick(View view) {

                    EditText editText = (EditText) findViewById(R.id.search);
                    String adress = editText.getText().toString();
                    Context context = getApplicationContext();
                    try {
                        List<Address> results = geocoder.getFromLocationName(adress, 1);
                        if (results.size() > 0) {
                            Address result = results.get(0);
                            lat = result.getLatitude();
                            lng = result.getLongitude();


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent =new Intent(MainActivity.this,MapActivity2.class);
                  EditText editText1 = (EditText) findViewById(R.id.search);
                  String value = editText.getText().toString();

                    intent.putExtra("lan",lat);
                    intent.putExtra("lng",lng);
                    intent.putExtra("search",value);
                    startActivity(intent);
                }
        });
    }
}
