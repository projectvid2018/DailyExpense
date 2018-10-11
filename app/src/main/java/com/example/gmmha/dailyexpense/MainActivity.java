package com.example.gmmha.dailyexpense;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gmmha.dailyexpense.content.activity.MedicalActivity;
import com.example.gmmha.dailyexpense.listview.Adapter;
import com.example.gmmha.dailyexpense.listview.Item;
import com.example.gmmha.dailyexpense.nav.activity.AboutActivity;
import com.example.gmmha.dailyexpense.nav.activity.BalanceActivity;
import com.example.gmmha.dailyexpense.nav.activity.ProfileActivity;
import com.example.gmmha.dailyexpense.nav.activity.pieChartActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    // DrawerLayout
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    double i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Toolbar
        mToolbar = findViewById(R.id.mainPageToolbarId);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        //DrawerLayout
        mDrawerLayout = findViewById(R.id.drawerId);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.nav_open,R.string.nav_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //NavigationView
        mNavigationView = findViewById(R.id.navigationId);
        mNavigationView.setNavigationItemSelectedListener(this);



    }


    //DrawerLayout clickable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //NavigationView clickable
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent;
        if(item.getItemId() == R.id.homeMenuId)
        {
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.profileMenuId)
        {
            intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.aboutUsMenuId)
        {
            intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.pieChartMenuId)
        {
            intent = new Intent(this,pieChartActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.balanceMenuId)
        {
            intent = new Intent(this,BalanceActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.shareMenuId)
        {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String sub = "Daily Expense";

            intent.putExtra(Intent.EXTRA_SUBJECT,sub);

            startActivity(Intent.createChooser(intent,"Share with"));
        }
        return false;
    }


    public void medicalActivity(View view) {

        Intent intent ;
        intent = new Intent(this,MedicalActivity.class);
        startActivity(intent);


    }

    /*
// Income Dialog
   public void OpenIncomeDialog(View view) {

        AlertDialog.Builder iBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.income_dialog_layout,null);
        iBuilder.setTitle("Income");

        final EditText editTextIncome = mView.findViewById(R.id.incomeId);
        final Spinner spinner = mView.findViewById(R.id.spinnerID);
        final EditText editTextDate = mView.findViewById(R.id.dateId);
        final EditText editTextDescription = mView.findViewById(R.id.descriptionId);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.incomeList));


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        iBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String income = editTextIncome.getText().toString();
                String date = editTextDate.getText().toString();
                String description = editTextDescription.getText().toString();

                Item item  = new Item(income,date,description);
                arrayList.add(0,item);
                adapter.notifyDataSetChanged();
                listView.setAdapter(mAdapter);




                if(!spinner.getSelectedItem().toString().equalsIgnoreCase("Category")){
                    Toast.makeText(MainActivity.this,spinner.getSelectedItem().toString()+" added",
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
    */


}
