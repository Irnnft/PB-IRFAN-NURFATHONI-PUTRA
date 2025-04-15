package com.example.pemrogramanbergerak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Atur padding untuk sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tombol menuju Profile
        Button btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(view -> {
            Intent intent = new Intent(home.this, Profile.class);
            startActivity(intent);
        });

        // Tombol menu utama: Keuangan
        Button btnKeuangan = findViewById(R.id.btnKeuangan);
        btnKeuangan.setOnClickListener(view -> {
            Intent intent = new Intent(home.this, Keuangan.class);
            startActivity(intent);
        });

        // Tombol menu utama: Memo
        Button btnMemo = findViewById(R.id.btnMemo);
        btnMemo.setOnClickListener(view -> {
            Intent intent = new Intent(home.this, Memo.class);
            startActivity(intent);
        });

        // Bottom Navigation handler
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true; // Sudah di home
            } else if (itemId == R.id.nav_graph) {
                startActivity(new Intent(home.this, Profile.class));
                return true;
            } else if (itemId == R.id.nav_settings) {
                startActivity(new Intent(home.this, Settings.class));
                return true;
            }
            return false;
        });
    }
}
