package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
/*import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;*/
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    DatabaseReference db1;
    DatabaseReference db2;
    private ArrayAdapter<String> listAdapter;
    public static   ArrayList<Model> List = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db1= FirebaseDatabase.getInstance().getReference("mydata").child("records");
        db2= FirebaseDatabase.getInstance().getReference("mydata");



        Intent intent=getIntent();
        String  search=intent.getStringExtra("search");
               db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            int i=0;
                            int j=0;
                            for (DataSnapshot modeldatasnapshot : snapshot.getChildren()) {
                                String indice=Integer.toString(i);
                                String indice_model=Integer.toString(j);
                               String adress=snapshot.child(indice).child("fields").child("adresse").getValue().toString();
                                if(Objects.equals(adress, search)){
                                    String ville=snapshot.child(indice).child("fields").child("ville").getValue().toString();
                                    String prix_nom=snapshot.child(indice).child("fields").child("prix_nom").getValue().toString();
                                    String prix_valeur=snapshot.child(indice).child("fields").child("prix_valeur").getValue().toString()+"€";
                                    String id=indice;
                                    //String id=snapshot.child(indice).child("fields").child("id").getValue().toString();
                                    Model v3 = new Model(id, adress, ville, prix_valeur,prix_nom,indice_model);
                                    List.add(v3);
                                    Log.d(TAG, "onData: "+indice);
                                }
                                i++;
                                Log.d(TAG, "onDataChange: "+adress);
                            }


                            listView = (ListView) findViewById(R.id.shapesListView);
                            ModelAdapter adapter = new ModelAdapter(getApplicationContext(), 0, List);
                            listView.setAdapter(adapter);
                            setUpOnclickListener();
                         }
                    }
                            @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity2.this, "data invalide", Toast.LENGTH_SHORT).show();

                    }

                });

    }
    private void setUpOnclickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Model selectShape = (Model) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), UpdateActivity.class);
                showDetail.putExtra("id", selectShape.getId_model());
                startActivity(showDetail);
            }
        });
    }

}