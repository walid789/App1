package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Users_update extends AppCompatActivity {

    DatabaseReference db2;
    private ArrayAdapter<String> listAdapter;
    public static ArrayList<Model> List1 = new ArrayList<Model>();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_update);


        db2= FirebaseDatabase.getInstance().getReference("mydata").child("user_update");

        Intent intent=getIntent();
        String  search=intent.getStringExtra("search");
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int i=0;
                    int j=0;
                    for (DataSnapshot modeldatasnapshot : snapshot.getChildren()) {
                        String indice=Integer.toString(i);
                        String indice_model=Integer.toString(j);
                        String adress=snapshot.child(indice).child("adresse").getValue().toString();
                        if(Objects.equals(adress, search)){
                            String ville=snapshot.child(indice).child("ville").getValue().toString();
                            String prix_nom=snapshot.child(indice).child("prix_nom").getValue().toString();
                            String prix_valeur=snapshot.child(indice).child("prix_valeur").getValue().toString()+"€";
                            String id=indice;
                            //String id=snapshot.child(indice).child("fields").child("id").getValue().toString();
                            Model v3 = new Model(id, adress, ville, prix_valeur,prix_nom,indice_model);
                            List1.add(v3);
                            Log.d(TAG, "onData: "+indice);
                        }
                        i++;
                        Log.d(TAG, "onDataChange: "+adress);
                    }


                    listView = (ListView) findViewById(R.id.shapesListView);
                    ModelAdapter adapter = new ModelAdapter(getApplicationContext(), 0, List1);
                    listView.setAdapter(adapter);
                    setUpOnclickListener();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               //Toast.makeText(MainActivity2.this, "data invalide", Toast.LENGTH_SHORT).show();

            }

        });


    }
    private void setUpOnclickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d(TAG, "onItemClick: postio "+position);
                Model selectShape = (Model) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), UpdateActivity.class);
                showDetail.putExtra("id_activity",1);
                Log.d(TAG, "onItemClick: "+selectShape.getId_model());
                showDetail.putExtra("id", Integer.toString(position));
                startActivity(showDetail);
            }
        });
    }

    }
