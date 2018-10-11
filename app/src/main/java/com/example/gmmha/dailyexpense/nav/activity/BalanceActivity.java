package com.example.gmmha.dailyexpense.nav.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gmmha.dailyexpense.MainActivity;
import com.example.gmmha.dailyexpense.R;
import com.example.gmmha.dailyexpense.listview.Adapter;
import com.example.gmmha.dailyexpense.listview.Item;

import java.util.ArrayList;

public class BalanceActivity extends AppCompatActivity {


    private Toolbar mToolbar;

    TextView balanceView;

    double balance = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        // Toolbar
        mToolbar = findViewById(R.id.balancePageToolbarId);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Balance");

        balanceView = findViewById(R.id.balanceViewId);
    }


    // Income Dialog
    public void OpenIncomeDialog(View view) {

        AlertDialog.Builder iBuilder = new AlertDialog.Builder(BalanceActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.in_dialog_layout, null);

        final EditText editTextIncome = mView.findViewById(R.id.incomeId);
        final Spinner spinner = mView.findViewById(R.id.spinnerID);
        final EditText editTextDate = mView.findViewById(R.id.dateId);
        final EditText editTextDescription = mView.findViewById(R.id.descriptionId);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(BalanceActivity.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.incomeList));


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        iBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String income = editTextIncome.getText().toString();
                if(income.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please insert income",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    double i = Double.valueOf(income);
                    balance = balance + i;
                    String j = Double.toString(balance);
                    balanceView.setText(j);

                }




                if (!spinner.getSelectedItem().toString().equalsIgnoreCase("Category")) {
                    Toast.makeText(BalanceActivity.this, spinner.getSelectedItem().toString() + " added",
                            Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }


            }
        });

        iBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        iBuilder.setView(mView);
        AlertDialog dialog = iBuilder.create();
        dialog.show();

    }

    public void OpenExpenseDialog(View view) {
    }

}


