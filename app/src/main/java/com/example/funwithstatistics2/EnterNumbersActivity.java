package com.example.funwithstatistics2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class EnterNumbersActivity extends AppCompatActivity {

    private TextView array, errorMsg;
    private EditText edtTxtEnterNumbers;
    private Button btnInsert, btnNext;
    private int[] userIntArray = new int[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_numbers);
        array = findViewById(R.id.array);
        errorMsg = findViewById(R.id.errorMsg);
        edtTxtEnterNumbers = findViewById(R.id.edtTxtEnterNumbers);
        btnInsert = findViewById(R.id.btnInsert);
        btnNext = findViewById(R.id.btnNext);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int intToAdd = Integer.parseInt(edtTxtEnterNumbers.getText().toString());
                    userIntArray = Arrays.copyOf(userIntArray, userIntArray.length + 1);
                    userIntArray[userIntArray.length - 1] = intToAdd;
                    array.setText(Arrays.toString(userIntArray));
                    edtTxtEnterNumbers.setText("");
                    errorMsg.setVisibility(View.GONE);
                    if (userIntArray.length >= 1) {
                        btnNext.setEnabled(true);
                    }
                }
                catch (NumberFormatException e) {
                    errorMsg.setVisibility(View.VISIBLE);
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterNumbersActivity.this, ResultsActivity.class);
                intent.putExtra(ResultsActivity.ARRAY_ID, userIntArray);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(EnterNumbersActivity.this);
        dialog.setMessage("Are you sure you want to go back? If you do, you will lose all the integers you have entered so far.");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EnterNumbersActivity.super.onBackPressed();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.create().show();
    }
}