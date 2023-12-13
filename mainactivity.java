package com.healthyme.BMIcalculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private TextView mytext;
    private RadioButton malebutton;
    private RadioButton femalebutton;
    private EditText myage;
    private EditText myweight;
    private EditText feetheight;
    private EditText inchheight;
    private Button mybutton;
    private TextView mytext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findview();

        setuponclick_listener(button);


    }


    private Button findview() {
        mytext = findViewById(R.id.maintext);
        mybutton = findViewById(R.id.calbutton);
        malebutton = findViewById(R.id.male);
        femalebutton = findViewById(R.id.female);
        myage = findViewById(R.id.ageinput);
        myweight = findViewById(R.id.weightinput);
        feetheight = findViewById(R.id.hrightinput);
        inchheight = findViewById(R.id.heightinput2);
        mytext2 = findViewById(R.id.maintext2);
        return mybutton;
    }

    private void setuponclick_listener(Button mybutton) {
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                String ageText = myage.getText().toString();
                int age = Integer.parseInt(ageText);

                double BMIresult = calculateBmi();

                if (age >= 18) {
                    displayresult(BMIresult);
                } else {
                    displayguidence(BMIresult);
                }


            }
        });

    }


    private double calculateBmi() {


        String iheight = inchheight.getText().toString();
        String fheight = feetheight.getText().toString();
        String weight = myweight.getText().toString();


        int totalinch = (Integer.parseInt(fheight) * 12) + Integer.parseInt(iheight);
        double mheight = totalinch * 0.0254;

        return Integer.parseInt(weight) / (mheight * mheight);

    }

    private void displayresult(double BMI) {
        String fullresultstring;
        String value;

        DecimalFormat df = new DecimalFormat("#.##");
        String formatBMI = df.format(BMI);

        if (BMI < 18.5) {

            value = "BMI - " + formatBMI + " ";
            fullresultstring = "Your BMI is in underweight segment";
        } else if (BMI > 25) {

            value = "BMI - " + formatBMI;
            fullresultstring = "Your BMI is in overweight segment";
        } else {

            value = "BMI - " + formatBMI;
            fullresultstring = "Your BMI is in healthy weight segment";

        }
        mytext.setText(value);
        mytext2.setText(fullresultstring);

    }

    private void displayguidence(double BMI) {

        String fullresultstring;
        String value;

        DecimalFormat df = new DecimalFormat("#.##");
        String formatBMI = df.format(BMI);

        if (malebutton.isChecked()) {

            value = "BMI - " + formatBMI;
            fullresultstring = "You can check with your docter what is the healthy weight for boys of your age";
        } else if (femalebutton.isChecked()) {

            value = "BMI - " + formatBMI;
            fullresultstring = "You can check with your docter what is the healthy weight for girls of your age";

        } else {
            value = "BMI - " + formatBMI;
            fullresultstring = "You can check with your docter what is the healthy weight for you";

        }
        mytext.setText(value);
        mytext2.setText(fullresultstring);
    }


}
