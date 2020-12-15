package com.example.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Button btnProfession = findViewById(R.id.btnProfession);

        btnProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irProfession = new Intent(MainActivity.this, ActivityProfession.class);
                startActivity(irProfession);
            }
        });

        final Button btnUser = findViewById(R.id.btnUser);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irUser = new Intent(MainActivity.this, ActivityUser.class);
                startActivity(irUser);
            }
        });

    }
}