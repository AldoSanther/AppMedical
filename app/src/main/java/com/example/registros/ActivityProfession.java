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

public class ActivityProfession extends AppCompatActivity {

    EditText  etName, etBirthday, etGender, etAddress, etConsultingoom, etSpecialty,etPhone,etProfessionalid;
    FloatingActionButton fabGuardar, fabListar;

    ProgressDialog progressDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateName, updateBirthday, updateGender, updateAddress, updateConsultingoom, updateSpecialty, updatePhone,updateProfessionalid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);

        etName = findViewById(R.id.etName);
        etBirthday = findViewById(R.id.etBirthday);
        etGender = findViewById(R.id.etGender);
        etAddress = findViewById(R.id.etAddress);
        etConsultingoom = findViewById(R.id.etConsultingoom);
        etSpecialty = findViewById(R.id.etSpecialty);
        etPhone = findViewById(R.id.etPhone);
        etProfessionalid = findViewById(R.id.etProfessionalid);

        fabGuardar = findViewById(R.id.fabGuardar);
        fabListar = findViewById(R.id.fabListar);

        progressDialog = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar registro");


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Actualizar Datos");

            updateId = bundle.getString("updateId");
            updateName = bundle.getString("updateName");
            updateBirthday = bundle.getString("updateBirthday");
            updateGender = bundle.getString("updateGender");
            updateAddress = bundle.getString("updateAddress");
            updateConsultingoom = bundle.getString("updateConsultingoom");
            updateSpecialty = bundle.getString(" updateSpecialty");
            updatePhone = bundle.getString("updatePhone");
            updateProfessionalid = bundle.getString("updateProfessionalid");

            etName.setText(updateName);
            etBirthday.setText(updateBirthday);
            etGender.setText(updateGender);
            etAddress.setText(updateAddress);
            etConsultingoom.setText(updateConsultingoom);
            etSpecialty.setText(updateSpecialty);
            etPhone.setText(updatePhone);
            etProfessionalid.setText(updateProfessionalid);

        } else {
            actionBar.setTitle("Agregar");
        }


        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = updateId;
                    String name = etName.getText().toString().trim();
                    String birthday = etBirthday.getText().toString().trim();
                    String gender = etGender.getText().toString().trim();
                    String address = etAddress.getText().toString().trim();
                    String consultingoom = etConsultingoom.getText().toString().trim();
                    String specialty = etSpecialty.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String professionalid = etProfessionalid.getText().toString().trim();

                    actualizarDatos(id, name, birthday, gender, address,consultingoom, specialty, phone, professionalid);

                } else {
                    String name = etName.getText().toString().trim();
                    String birthday = etBirthday.getText().toString().trim();
                    String gender = etGender.getText().toString().trim();
                    String address = etAddress.getText().toString().trim();
                    String  consultingoom = etConsultingoom.getText().toString().trim();
                    String specialty = etSpecialty.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String  professionalid = etProfessionalid.getText().toString().trim();

                    cargarDatos(name, birthday, gender, address, consultingoom, specialty, phone,professionalid);
                }
            }
        });


        fabListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityProfession.this, ListActivityProfession.class));
                finish();
            }
        });

    }


    private void cargarDatos(String name, String birthday, String gender, String address, String consultingoom, String specialty, String phone,String professionalid) {
        progressDialog.setTitle("Agregar datos");
        progressDialog.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("name", name);
        doc.put("birthday", birthday);
        doc.put("gender", gender);
        doc.put("address", address);
        doc.put("consultingoom", consultingoom);
        doc.put("specialty",specialty);
        doc.put("phone",phone);
        doc.put("professionalid",professionalid);


        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(ActivityProfession.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ActivityProfession.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void actualizarDatos(String id, String name, String birthday, String gender, String address, String consultingoom,
                                 String specialty,String phone,String professionalid ) {
        progressDialog.setTitle("Actualizando datos a Firebase");
        progressDialog.show();

        /*
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("nombre", nombre);
        doc.put("apaterno", apaterno);
        doc.put("amaterno", amaterno);
        doc.put("sexo", sexo);
        doc.put("direccion", direccion);
        doc.put("facebook", facebook);
        doc.put("instagram", instagram);

         */


        db.collection("Documents")
                .document(id).update(
                "name", name,
                "birthday", birthday,
                "gender", gender,
                "address", address,
                "consultingoom", consultingoom,
                "specialty", specialty,
                "phone", phone,
                "professionalid", professionalid

        )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(ActivityProfession.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ActivityProfession.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}