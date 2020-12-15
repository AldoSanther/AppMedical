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
public class ListActivityUser extends AppCompatActivity {
    java.util.List<UserModel> mUserModelList = new ArrayList<>();
    RecyclerView recyclerView;
    //Crear instancia de FirebaseFirestore
    FirebaseFirestore db;
    CustomAdapterUser customAdapterUser;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    Context context;
    FloatingActionButton fabAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ver registros");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fabAgregar = findViewById(R.id.fabAgregar);
        // Obtener instancia de Firebase
        db = FirebaseFirestore.getInstance();
        verDatos();
        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivityUser.this, ActivityUser.class));
                finish();
            }
        });
    }
    public void eliminarRegistro(int index) {
        db.collection("Documents").document(mUserModelList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ListActivityUser.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                        verDatos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListActivityUser.this, "No se ha completado la operación" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verDatos() {
        db.collection("Documents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        mUserModelList.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            UserModel userModel = new UserModel(
                                    doc.getString("id"),
                                    doc.getString("user"),
                                    doc.getString("password"),
                                    doc.getString("phone"),
                                    doc.getString("mail"),
                                    doc.getString("family"),
                                    doc.getString("proceedings"),
                                    doc.getString("allergies"),
                                    doc.getString("status")
                            );
                            mUserModelList.add(userModel);
                        }
                        customAdapterUser = new CustomAdapterUser(ListActivityUser.this, mUserModelList);
                        recyclerView.setAdapter(customAdapterUser);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ListActivityUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}