package com.example.two_factor_auth_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class Pin_Check extends AppCompatActivity {

    EditText edt_pin;
    Button verify_btn;
    private FirebaseFirestore db;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_check);

        edt_pin = findViewById(R.id.verify_pin_edittext);
        verify_btn = findViewById(R.id.verify_pin);

        db = FirebaseFirestore.getInstance();

        String phonenoo = getIntent().getStringExtra("p");

        String pin = edt_pin.getText().toString();
        dbHelper = new DBHelper(Pin_Check.this);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pin = Integer.parseInt(edt_pin.getText().toString());
                int pin_check = dbHelper.readdata(phonenoo);

                if (pin == pin_check){
                    Toast.makeText(Pin_Check.this, "Welcome...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Pin_Check.this,Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Pin_Check.this, "Wrong Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

