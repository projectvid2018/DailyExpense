package com.example.gmmha.dailyexpense.content.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gmmha.dailyexpense.MainActivity;
import com.example.gmmha.dailyexpense.R;
import com.example.gmmha.dailyexpense.listview.Adapter;
import com.example.gmmha.dailyexpense.listview.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ClothesActivity extends AppCompatActivity {


    private DatabaseReference mRef;
    private ArrayList<Item> arrayList;
    private Adapter mAdapter;

    private ListView listView;
    private TextView textViewSum;
    private double sum = 0.0;


    private  DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Clothes");

        //button = findViewById(R.id.medicalAddId);
        listView = findViewById(R.id.listViewClothesActivity);
        textViewSum = findViewById(R.id.sumClothesId);

        arrayList = new ArrayList<Item>();
        mAdapter = new Adapter(this,R.layout.listview_layout,arrayList);

        AddItem();

    }

    public void AddItem(){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                sum = 0.0;
                for (DataSnapshot dSnapshot: dataSnapshot.getChildren()){
                    Item item = dSnapshot.getValue(Item.class);
                    String amount = dSnapshot.child("amount").getValue().toString();
                    sum = sum + Double.parseDouble(amount);
                    String value = Double.toString(sum);
                    textViewSum.setText(value);
                    arrayList.add(item);
                }
                mAdapter.notifyDataSetChanged();
                listView.setAdapter(mAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void OpenDialog(View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ClothesActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        mBuilder.setTitle("Medical Expense");

        final EditText expenseEditText = mView.findViewById(R.id.dialogExpenseId);
        final EditText dateEditText = mView.findViewById(R.id.dialogDateId);
        final EditText descriptionEditText = mView.findViewById(R.id.dialogDescriptionId);

        ImageView calenderView = mView.findViewById(R.id.dialogCalendarId);

        calenderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(
                        ClothesActivity.this,
                        android.R.style.Theme_Holo_Dialog
                        ,mDateSetListener,year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                dateEditText.setText(date);

            }
        };


        mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String mExpense = expenseEditText.getText().toString().trim();
                String mDate = dateEditText.getText().toString().trim();
                String mDescription = descriptionEditText.getText().toString().trim();

                if (mExpense.isEmpty()){
                    expenseEditText.setError("Please enter amount");
                    expenseEditText.requestFocus();
                    return;
                }
                if (mDate.isEmpty()){
                    dateEditText.setError("Please enter date");
                    dateEditText.requestFocus();
                    return;

                }
                Item item = new Item(mExpense, mDate, mDescription);

                String uploadId = mRef.push().getKey();
                mRef.child(uploadId).setValue(item);


/*                arrayList.add(0,item);
                mAdapter.notifyDataSetChanged();
                listView.setAdapter(mAdapter);*/




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
