package com.example.pemrogramanbergerak;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Keuangan extends AppCompatActivity {
    EditText inputKeuangan;
    TextView hasilKeuangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan);

        inputKeuangan = findViewById(R.id.inputKeuangan);
        hasilKeuangan = findViewById(R.id.hasilKeuangan);
        Button simpanBtn = findViewById(R.id.btnSimpanKeuangan);

        simpanBtn.setOnClickListener(view -> {
            String data = inputKeuangan.getText().toString();
            hasilKeuangan.setText("Data Keuangan: " + data);
        });
    }
}
