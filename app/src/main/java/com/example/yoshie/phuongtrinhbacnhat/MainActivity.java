package com.example.yoshie.phuongtrinhbacnhat;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String tag_debug = "DEBUG";
    private int REQUEST_CODE = 333;

    public void onClickSolve(View view) {
        if (!checkInputValid()) {
            Log.d(tag_debug, "input not valid");
            displayResult("input not valid");
        } else {
            Double double_A = getDoubleFromEditText(R.id.idEditTextA);
            Double double_B = getDoubleFromEditText(R.id.idEditTextB);
            Log.d(tag_debug, double_A.toString());
            Log.d(tag_debug, double_B.toString());

            // use intent to forward work for another activity
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("A", double_A);
            intent.putExtra("B", double_B);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == 0) {
                displayResult("Vo nghiem");
            } else if (resultCode == 1) {
                Double x = data.getDoubleExtra("x", 0);
                if (x.isNaN() || x.isInfinite())
                    Log.d(tag_debug, "NaN or Infinite");
                else
                    displayResult("x = " + x.toString());
            } else {
                displayResult("Vo so nghiem");
            }
        }
    }

    private Double getDoubleFromEditText(int id) {
        String input_str = ((EditText) findViewById(id)).getText().toString();
        return Double.valueOf(input_str);
    }

    private boolean checkInputValid() {
        return checkInputValidID(R.id.idEditTextA) && checkInputValidID(R.id.idEditTextB);
    }

    private boolean checkInputValidID(int id) {
        String input_str = ((EditText) findViewById(id)).getText().toString();
        if (input_str.isEmpty())
            return false;
        int count_dot = 0; // count .
        for (char ch : input_str.toCharArray()) {
            if (ch == '.')
                ++count_dot;
            else if (ch < '0' || ch > '9')
                return false;
        }
        if (count_dot > 1)
            return false;
        return true;
    }

    private void displayResult(String str) {
        TextView textViewResult = findViewById(R.id.idTextViewResult);
        textViewResult.setText(str);
    }
}
