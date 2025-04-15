package com.example.pemrogramanbergerak;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Switch switchNotifikasi = findViewById(R.id.switchNotifikasi);
        switchNotifikasi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Notifikasi diaktifkan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notifikasi dimatikan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
