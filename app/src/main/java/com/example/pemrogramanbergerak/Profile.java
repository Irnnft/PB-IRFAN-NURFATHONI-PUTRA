package com.example.pemrogramanbergerak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    private TextView txtEmail, txtUID;
    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inisialisasi komponen
        txtEmail = findViewById(R.id.txtEmail);
        txtUID = findViewById(R.id.txtUID);
        btnLogout = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            txtEmail.setText("Email: " + (user.getEmail() != null ? user.getEmail() : "Tidak tersedia"));
            txtUID.setText("User ID: " + user.getUid());
        } else {
            Toast.makeText(this, "Gagal mendapatkan data pengguna!", Toast.LENGTH_SHORT).show();
        }

        btnLogout.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(this, "Logout berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Profile.this, MainActivity.class));
            finish();
        });
    }
}
