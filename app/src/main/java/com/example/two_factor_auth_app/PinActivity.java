package com.example.two_factor_auth_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PinActivity extends AppCompatActivity {

    EditText pin,confirm_pin;
    Button generate_pin;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        String pinPattern = "[0-9]{6}";
        pin = findViewById(R.id.pin_edittext);
        confirm_pin = findViewById(R.id.pin_edittext2);

        generate_pin = findViewById(R.id.generate_pin);
        db = FirebaseFirestore.getInstance();

        String phoneno = getIntent().getStringExtra("phone_no");

        generate_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int pin1 = Integer.parseInt(pin.getText().toString());
               int pin2 = Integer.parseInt(confirm_pin.getText().toString());

              if (pin.getText().toString().equals(null)){
                  pin.setError("Enter Pin");

              }
              else if (confirm_pin.getText().toString().equals(null)){
                  confirm_pin.setError("Enter Pin");
              }
              else if(!(pin1 == pin2)){
                  confirm_pin.setError("Enter same pin");
                  pin.setError("Enter smae pin");
              }

              else if (!pin.getText().toString().matches(pinPattern)){
                  pin.setError("Enter 6 Digit Number");
              }

                else if (!confirm_pin.getText().toString().matches(pinPattern)){
                    confirm_pin.setError("Enter 6 Digit Number");
                }

                else {
                    addDatatoFirestore(phoneno,pin1);
              }
            }
        });



    }

    private void addDatatoFirestore(String phoneno, int pin1) {
        CollectionReference dbPin = db.collection("Pin");

        //DocumentReference documentReference = dbPin.document(phoneno);

        Pin pinn = new Pin(phoneno,pin1);

        dbPin.document("SF").set(pinn);
        Toast.makeText(PinActivity.this, "Pin Generated Sucessfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(PinActivity.this,Pin_Check.class);
        intent.putExtra("p",phoneno);
        startActivity(intent);


    }
}