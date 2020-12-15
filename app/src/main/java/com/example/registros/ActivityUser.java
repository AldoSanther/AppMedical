package com.example.registros;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class ActivityUser extends AppCompatActivity {

    EditText etUser, etPassword, etPhone, etMail, etFamily, etProceedings, etAllergies, etStatus;
    FloatingActionButton fabGuardar, fabListar;
    ProgressDialog progressDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateUser, updatePassword, updatePhone, updateMail, updateFamily, updateProceedings, updateAllergies, updateStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);
        etMail = findViewById(R.id.etMail);
        etFamily = findViewById(R.id.etFamily);
        etProceedings = findViewById(R.id.etProceedings);
        etAllergies = findViewById(R.id.etAllergies);
        etStatus = findViewById(R.id.etStatus);
        fabGuardar = findViewById(R.id.fabGuardar);
        fabListar = findViewById(R.id.fabListar);
        progressDialog = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar Usuario");

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Actualizar Datos");
            updateId = bundle.getString("updateId");
            updateUser = bundle.getString("updateUser");
            updatePassword = bundle.getString("updatePassword");
            updatePhone = bundle.getString("updatePhone");
            updateMail = bundle.getString("updateMail");
            updateFamily = bundle.getString("updateFamily");
            updateProceedings = bundle.getString("updateProceedings");
            updateAllergies = bundle.getString("updateAllergies");
            updateStatus = bundle.getString("updateStatus");
            etUser.setText(updateUser);
            etPassword.setText(updatePassword);
            etPhone.setText(updatePhone);
            etMail.setText(updateMail);
            etFamily.setText(updateFamily);
            etProceedings.setText(updateProceedings);
            etAllergies.setText(updateAllergies);
            etStatus.setText(updateStatus);
        } else {
            actionBar.setTitle("Agregar Usuario");
        }
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = updateId;
                    String user = etUser.getText().toString().trim();;
                    String password = etPassword.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String mail = etMail.getText().toString().trim();
                    String family = etFamily.getText().toString().trim();
                    String proceedings = etProceedings.getText().toString().trim();
                    String allergies = etAllergies.getText().toString().trim();
                    String status = etStatus.getText().toString().trim();
                    actualizarDatos(id, user, password, phone, mail, family, proceedings, allergies, status);
                } else {
                    String user = etUser.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String mail = etMail.getText().toString().trim();
                    String family = etFamily.getText().toString().trim();
                    String proceedings = etProceedings.getText().toString().trim();
                    String allergies = etAllergies.getText().toString().trim();
                    String status = etStatus.getText().toString().trim();
                    cargarDatos(user, password, phone, mail, family, proceedings, allergies, status);
                }
            }
        });
        fabListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityUser.this, ListActivityUser.class));
                finish();
            }
        });
    }
    private void cargarDatos(String user, String password, String phone, String mail, String family, String proceedings, String allergies, String status) {
        progressDialog.setTitle("Agregar datos");
        progressDialog.show();
        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();
        doc.put("Usuario", user);
        doc.put("Contraseña", password);
        doc.put("Telefono", phone);
        doc.put("Correo Electronico", mail);
        doc.put("Familiar", family);
        doc.put("Expediente", proceedings);
        doc.put("Alergias", allergies);
        doc.put("Prioridad", status);

        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(ActivityUser.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ActivityUser.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void actualizarDatos(String id, String user, String password, String phone, String mail, String family, String proceedings, String allergies, String status) {
        progressDialog.setTitle("Actualizando datos a Firebase");
        progressDialog.show();
        /*
        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("user", user);
        doc.put("password", password);
         doc.put("name", name);
        doc.put("phone", phone);
        doc.put("mail", mail);
        doc.put("family", family);
        doc.put("proceedings", proceedings);
        doc.put("allergies", allergies);
        doc.put("status", status);
         */

        db.collection("Documents")
                .document(id).update(
                "Usuario", user,
                "Contraseña", password,
                "Telefono", phone,
                "Correo Electronico", mail,
                "Familiar", family,
                "Expediente", proceedings,
                "Alergias", allergies,
                "Prioridad", status
        )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(ActivityUser.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ActivityUser.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}