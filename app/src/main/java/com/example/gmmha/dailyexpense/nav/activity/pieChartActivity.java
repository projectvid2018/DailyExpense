package com.example.gmmha.dailyexpense.nav.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.gmmha.dailyexpense.R;


public class pieChartActivity extends AppCompatActivity {

    private Toolbar pToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

       pToolbar = findViewById(R.id.pieChartToolbarId);
       setSupportActionBar(pToolbar);
       getSupportActionBar().setTitle("Pie Chart");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
