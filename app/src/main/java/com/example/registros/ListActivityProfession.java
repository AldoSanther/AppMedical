package com.example.registros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListActivityProfession extends AppCompatActivity {


    java.util.List<ProfessionModel> mProfessionnModelList = new ArrayList<>();
    RecyclerView recyclerview;

    //Crear instancia de FirebaseFirestore
    FirebaseFirestore db;

    CustomAdapterProfession customAdapterProfession;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    Context context;
    FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_profession);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ver registros");

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        fabAgregar = findViewById(R.id.fabAgregar);

        // Obtener instancia de Firebase
        db = FirebaseFirestore.getInstance();

        verDatos();

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivityProfession.this, ActivityProfession.class));
                finish();
            }
        });
    }

    public void eliminarRegistro(int index) {
        db.collection("Documents").document(mProfessionnModelList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ListActivityProfession.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                        verDatos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListActivityProfession.this, "No se ha completado la operación" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verDatos() {
        db.collection("Documents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        mProfessionnModelList.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            ProfessionModel professionModel = new ProfessionModel(
                                    doc.getString("id"),
                                    doc.getString("name"),
                                    doc.getString("birthday"),
                                    doc.getString("gender"),
                                    doc.getString("address"),
                                    doc.getString("consultingoom"),
                                    doc.getString("specialty"),
                                    doc.getString("phone"),
                                    doc.getString("professionalid")
                            );
                        }
                       customAdapterProfession = new CustomAdapterProfession(ListActivityProfession.this, mProfessionnModelList);
                        recyclerview.setAdapter(customAdapterProfession);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ListActivityProfession.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}