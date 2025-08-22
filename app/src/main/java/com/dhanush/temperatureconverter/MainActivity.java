package com.dhanush.temperatureconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputTemp;
    Spinner spinnerFrom, spinnerTo;
    Button btnConvert;
    TextView resultText;

    String[] units = {"Celsius", "Fahrenheit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTemp = findViewById(R.id.inputTemp);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);
        resultText = findViewById(R.id.resultText);

        // Set adapters for spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = inputTemp.getText().toString();
                if (val.isEmpty()) {
                    resultText.setText("Enter a value");
                    return;
                }

                double input = Double.parseDouble(val);
                String from = spinnerFrom.getSelectedItem().toString();
                String to = spinnerTo.getSelectedItem().toString();

                double result = convertTemp(input, from, to);
                resultText.setText(input + " " + from + " = " + result + " " + to);
            }
        });
    }

    private double convertTemp(double value, String from, String to) {
        if (from.equals(to)) return value;
        if (from.equals("Celsius") && to.equals("Fahrenheit")) return (value * 9/5) + 32;
        if (from.equals("Fahrenheit") && to.equals("Celsius")) return (value - 32) * 5/9;
        return value;
    }
}
