package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {
    Model selectedShape;
    DatabaseReference db1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSelectedShape();
        setValues();
        db1= FirebaseDatabase.getInstance().getReference("mydata").child("user_update");
        Button b1=findViewById(R.id.update);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Boolean[] verife = {true};
                TextView adress = (TextView) findViewById(R.id.adress);
                TextView prix_nom = (TextView) findViewById(R.id.prix_nom);
                TextView prix_valeur = (TextView) findViewById(R.id.prix_valeur);
                TextView ville = (TextView) findViewById(R.id.ville);
                String adres= adress.getText().toString();
                String nom_prix = prix_nom.getText().toString();
                String prix = prix_valeur.getText().toString();
                String vile = ville.getText().toString();
                String id=selectedShape.getId().toString();


                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i=0;
                        if (snapshot.exists()) {
                            for (DataSnapshot modeldatasnapshot : snapshot.getChildren()) {
                                    i++;
                            }
                            if(verife[0] ==true){
                                String indice=Integer.toString(i);
                                Model m=new Model(id,adres,vile,prix,nom_prix,indice);
                                Log.d(TAG, "onClick: "+i+1);
                                db1.child(String.valueOf(i)).setValue(m);
                                verife[0] =false;
                            }

                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                       // Toast.makeText(MainActivity2.this, "data invalide", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }

    private void getSelectedShape()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        int id_activity=previousIntent.getIntExtra("id_activity",0);
        if(id_activity==1){
            selectedShape = Users_update.List1.get(Integer.valueOf(parsedStringID));
        }
        else{
            selectedShape = MainActivity2.List.get(Integer.valueOf(parsedStringID));
        }

    }

    private void setValues()
    {
        TextView adress = (TextView) findViewById(R.id.adress);
        TextView prix_nom = (TextView) findViewById(R.id.prix_nom);
        TextView prix_valeur = (TextView) findViewById(R.id.prix_valeur);
        TextView ville = (TextView) findViewById(R.id.ville);

        adress.setText(selectedShape.getAdresse());
        prix_nom.setText(selectedShape.getPrix_nom());
        prix_valeur.setText(selectedShape.getPrix_valeur());
        ville.setText(selectedShape.getVille());

        //tv.setText(selectedShape.getName());
        //iv.setImageResource(selectedShape.getImage());
    }
    }
