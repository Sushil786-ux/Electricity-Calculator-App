package com.example.electricitycalculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etUnits;
    private Button btnCalculate;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etUnits = findViewById(R.id.etUnits);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unitsStr = etUnits.getText().toString().trim();

                // Validation check for empty input
                if (unitsStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter units consumed", Toast.LENGTH_SHORT).show();
                    return;
                }

                double units = Double.parseDouble(unitsStr);

                if (units < 0) {
                    Toast.makeText(MainActivity.this, "Units cannot be negative", Toast.LENGTH_SHORT).show();
                    return;
                }

                double billAmount = calculateBill(units);

                // Display the calculated bill formatted to 2 decimal places
                tvResult.setText(String.format("Total Bill: $%.2f", billAmount));

            }
        });

    }

    private double calculateBill(double units) {
        double bill = 0.0;

        if (units <= 100) {
            bill = units * 1.50;
        } else if (units <= 200) {
            bill = (100 * 1.50) + ((units - 100) * 2.50);
        } else {
            bill = (100 * 1.50) + (100 * 2.50) + ((units - 200) * 3.50);
        }

        return bill;
    }
}