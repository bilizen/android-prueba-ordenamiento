package com.example.android_prueba_ordenamiento.activity.activity2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.android_prueba_ordenamiento.R;

public class Main2Activity extends AppCompatActivity {
    private LinearLayout linearLayoutSecondary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("color fondo");
        linearLayoutSecondary=(LinearLayout) findViewById(R.id.linearLayoutSecondary);
        Bundle bundle=getIntent().getExtras();
        linearLayoutSecondary.setBackgroundColor(Color.parseColor(bundle.getString("color")));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
