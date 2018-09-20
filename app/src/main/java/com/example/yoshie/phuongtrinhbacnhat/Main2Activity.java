package com.example.yoshie.phuongtrinhbacnhat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // receive intent
        Intent rev_intent = getIntent();
        Double double_A = rev_intent.getDoubleExtra("A", 0);
        Double double_B = rev_intent.getDoubleExtra("B", 0);
        solveBacI(double_A, double_B);
    }

    private double DOUBLE_GAP = 1e-6;

    private void solveBacI(Double double_a, Double double_b) {
        Intent send_intent = new Intent();
        if (Math.abs(double_a - 0) < DOUBLE_GAP) {
            if (Math.abs(double_b - 0) < DOUBLE_GAP) {
                setResult(2, send_intent);
            } else {
                setResult(0, send_intent);
            }
        } else {
            Double x = -double_b / double_a;
            send_intent.putExtra("x", x);
            setResult(1, send_intent);
        }
        finish();
    }
}
