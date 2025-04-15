package com.example.pemrogramanbergerak;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Memo extends AppCompatActivity {
    EditText inputMemo;
    TextView hasilMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        inputMemo = findViewById(R.id.inputMemo);
        hasilMemo = findViewById(R.id.hasilMemo);
        Button simpanMemo = findViewById(R.id.btnSimpanMemo);

        simpanMemo.setOnClickListener(view -> {
            String memo = inputMemo.getText().toString();
            hasilMemo.setText("Memo: " + memo);
        });
    }
}
