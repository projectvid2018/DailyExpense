package com.example.gmmha.dailyexpense.content.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gmmha.dailyexpense.R;
import com.example.gmmha.dailyexpense.listview.Adapter;
import com.example.gmmha.dailyexpense.listview.Item;

import java.util.ArrayList;

public class MedicalActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ArrayList<Item> arrayList;
    private Adapter mAdapter;
    //private Button button;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);

        mToolbar = findViewById(R.id.medicalToolbarId);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Medical Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //button = findViewById(R.id.medicalAddId);
        listView = findViewById(R.id.listViewMedicalActivity);

        arrayList = new ArrayList<Item>();
        mAdapter = new Adapter(this,R.layout.listview_layout,arrayList);

    }


    public void OpenDialog(View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MedicalActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        mBuilder.setTitle("Medical Expense");

        final EditText expenseEditText = mView.findViewById(R.id.dialogExpenseId);
        final EditText dateEditText = mView.findViewById(R.id.dialogDateId);
        final EditText descriptionEditText = mView.findViewById(R.id.dialogDescriptionId);

        mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String mExpense = expenseEditText.getText().toString().trim();
                String mDate = dateEditText.getText().toString().trim();
                String mDescription = descriptionEditText.getText().toString().trim();


                Item item = new Item(mExpense, mDate, mDescription);
                arrayList.add(0,item);
                mAdapter.notifyDataSetChanged();
                listView.setAdapter(mAdapter);
            }
        });

        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();




    }
}
