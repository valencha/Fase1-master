package com.example.estudiante.fase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edt_name;
    Button btn_add;
    Spinner spinnerImpo;
    DatabaseReference databaseCita;
    ListView listViewCita;
    List<Cita>citaList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseCita = FirebaseDatabase.getInstance().getReference("cita");

        edt_name= findViewById(R.id.edt_name);
        btn_add= findViewById(R.id.btn_add);
        spinnerImpo = findViewById(R.id.spinnerImpo);
        listViewCita= findViewById(R.id.listViewCita);

        citaList = new ArrayList<>();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addCita();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseCita.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                citaList.clear();
                for(DataSnapshot citaSnapshot : dataSnapshot.getChildren()){

                    Cita cita = citaSnapshot.getValue(Cita.class);

                    citaList.add(cita);

                }

                CitaList adapter= new CitaList(MainActivity.this,citaList);
                listViewCita.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addCita(){
        String name = edt_name.getText().toString().trim();
        String importancia = spinnerImpo.getSelectedItem().toString();
        if(!TextUtils.isEmpty(name)){

            String id =databaseCita.push().getKey();
             Cita citas= new Cita(id,name,importancia);

             databaseCita.child(id).setValue(citas);

             Toast.makeText(this,  "Cita Añadida",Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(this,"Ingresa un titulo",Toast.LENGTH_LONG).show();
        }
    }
}
