package com.example.funwithstatistics2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Arrays;


public class ResultsActivity extends AppCompatActivity implements AddIntegerDialog.AddIntegerDialogListener, DeleteIntegerDialog.DeleteIntegerDialogListener {

    public static final String ARRAY_ID = "array";

    private Statistics statsObj;
    private TextView array, meanLine, txtMean, medianLine, txtMedian, rangeLine, txtRange, standardDeviationLine, txtStandardDeviation;
    private Button btnAddInteger, btnDeleteInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        array = findViewById(R.id.array);
        meanLine = findViewById(R.id.meanLine);
        txtMean = findViewById(R.id.txtMean);
        medianLine = findViewById(R.id.medianLine);
        txtMedian = findViewById(R.id.txtMedian);
        rangeLine = findViewById(R.id.rangeLine);
        txtRange = findViewById(R.id.txtRange);
        standardDeviationLine = findViewById(R.id.standardDeviationLine);
        txtStandardDeviation = findViewById(R.id.txtStandardDeviation);
        btnAddInteger = findViewById(R.id.btnAddInteger);
        btnDeleteInteger = findViewById(R.id.btnDeleteInteger);

        meanLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultsActivity.this);
                builder.setMessage("The mean is the number that all the numbers in a set gravitate towards. To calculate it, first, add up all the numbers in the set."
                        + " Then divide the resulting sum by the number of numbers in the set.");
                builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        medianLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultsActivity.this);
                builder.setMessage("The median is the number that, when the set of numbers it comes from is ordered from least to greatest, is in the middle of that set."
                        + " If there is an even number of numbers in the set, the median is the mean of the 2 numbers that are in the middle of the set when it is ordered from least to greatest.");
                builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        rangeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultsActivity.this);
                builder.setMessage("The range of a set of numbers is the difference between its largest number and its smallest number.");
                builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        standardDeviationLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultsActivity.this);
                builder.setMessage("The standard deviation is a general measure of how far away the numbers in a set are from the set's mean."
                        + " To calculate it, first, for each of the numbers in the set, subtract from it the mean and square the resulting difference."
                        + " Next, calculate the mean of the squared differences you obtained from the previous step."
                        + " Then take the square root of the result you obtain from the previous step.");
                builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        btnAddInteger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIntegerDialog addIntDialog = new AddIntegerDialog();
                addIntDialog.show(getSupportFragmentManager(), "addIntDialog");
            }
        });

        btnDeleteInteger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteIntegerDialog deleteIntDialog = new DeleteIntegerDialog();
                deleteIntDialog.show(getSupportFragmentManager(), "deleteIntDialog");
            }
        });

        Intent previousIntent = getIntent();
        if (previousIntent != null) {
            int[] arrayToUse = previousIntent.getIntArrayExtra(ARRAY_ID);
            if (arrayToUse != null) {
                statsObj = new Statistics(arrayToUse);
                updateActivityContents();
            }
            else {
                Toast.makeText(this, "An error occurred. To try again, click your phone's back button.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "An error occurred. To try again, click your phone's back button.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(ResultsActivity.this);
        alert.setMessage("You're about to go back to the home screen and will lose your integer array and its results. Are you sure you want to go back?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(alert.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show();
    }

    private void updateActivityContents() {
        DecimalFormat formatter = new DecimalFormat("#,##0.###");
        array.setText(Arrays.toString(statsObj.getValuesArray()));
        txtMean.setText(formatter.format(statsObj.mean()));
        txtMedian.setText(formatter.format(statsObj.median()));
        txtRange.setText(Integer.toString(statsObj.range()));
        txtStandardDeviation.setText(formatter.format(statsObj.standardDeviation()));
        btnAddInteger.setEnabled(true);
        if (statsObj.getValuesArray().length > 1) {
            btnDeleteInteger.setEnabled(true);
        }
        else {
            btnDeleteInteger.setEnabled(false);
        }
    }

    @Override
    public void useIntegerToAdd(int integerToAdd) {
        statsObj.addValue(integerToAdd);
        updateActivityContents();
    }

    @Override
    public void useIntegerToDelete(int integerToDelete) {
        if (statsObj.deleteValue(integerToDelete)) {
            updateActivityContents();
            Toast.makeText(this, integerToDelete + " successfully deleted.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Error: " + integerToDelete + " not found.", Toast.LENGTH_SHORT).show();
        }
    }
}