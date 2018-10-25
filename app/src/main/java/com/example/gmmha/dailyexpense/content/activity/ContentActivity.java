package com.example.gmmha.dailyexpense.content.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gmmha.dailyexpense.R;
import com.example.gmmha.dailyexpense.content.activity.MedicalActivity;

public class ContentActivity extends AppCompatActivity {

    private Toolbar aToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        aToolbar = findViewById(R.id.aboutToolbarId);
        setSupportActionBar(aToolbar);
        getSupportActionBar().setTitle("All Expenses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
