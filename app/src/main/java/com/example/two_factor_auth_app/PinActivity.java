package com.example.two_factor_auth_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;

public class PinActivity extends AppCompatActivity {

    EditText pin,confirm_pin;
    Button generate_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        String pinPattern = "[0-9]{6}";
        pin = findViewById(R.id.pin_edittext);
        confirm_pin = findViewById(R.id.pin_edittext2);

        generate_pin = findViewById(R.id.generate_pin);

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
                    Toast.makeText(PinActivity.this, "pin generate sucessfully", Toast.LENGTH_SHORT).show();
              }
            }
        });



    }
}