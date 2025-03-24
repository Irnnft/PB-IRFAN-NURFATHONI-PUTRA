package com.example.pemrogramanbergerak;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pemrogramanbergerak.Models.UserDetails;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    Button signUpBtn;
    TextInputEditText usernameSignUp, passwordSignUp, nimPengguna, emailPengguna;
    FirebaseAuth mAuth;
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance(); // Inisialisasi FirebaseAuth

        signUpBtn = findViewById(R.id.signUpBtn);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        emailPengguna = findViewById(R.id.emailPengguna);
        passwordSignUp = findViewById(R.id.passwordSingUp);
        nimPengguna = findViewById(R.id.nimPengguna);

        signUpBtn.setOnClickListener(view -> {
            String username = usernameSignUp.getText().toString().trim();
            String email = emailPengguna.getText().toString().trim();
            String password = passwordSignUp.getText().toString().trim();
            String NIM = nimPengguna.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                usernameSignUp.setError("Enter Username");
                usernameSignUp.requestFocus();
            } else if (TextUtils.isEmpty(email)) {
                emailPengguna.setError("Enter Email");
                emailPengguna.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                passwordSignUp.setError("Enter Password");
                passwordSignUp.requestFocus();
            } else if (TextUtils.isEmpty(NIM)) {
                nimPengguna.setError("Enter Your NIM");
                nimPengguna.requestFocus();
            } else {
                registerUser(username, email, password, NIM);
            }
        });
    }

    private void registerUser(String username, String email, String password, String NIM) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity2.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser fUser = mAuth.getCurrentUser();
                        if (fUser != null) {
                            String uid = fUser.getUid();

                            // Simpan data pengguna ke Firebase Realtime Database
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                            UserDetails userDetails = new UserDetails(uid, username, email, password, NIM);
                            reference.child(uid).setValue(userDetails).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    fUser.sendEmailVerification();
                                    Toast.makeText(MainActivity2.this, "Account Created! Check Email for Verification", Toast.LENGTH_LONG).show();

                                    // Pindah ke halaman utama setelah registrasi berhasil
                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity2.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "Database Error: " + task1.getException());
                                }
                            });
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Auth Error: " + task.getException());
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity2.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Failure: " + e.getMessage());
                });
    }
}
