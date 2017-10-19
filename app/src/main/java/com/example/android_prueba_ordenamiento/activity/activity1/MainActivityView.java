package com.example.android_prueba_ordenamiento.activity.activity1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android_prueba_ordenamiento.R;
import com.example.android_prueba_ordenamiento.adapter.AdapterElements;
import com.example.android_prueba_ordenamiento.database.Database;
import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

public class MainActivityView extends AppCompatActivity implements MainActivityInterface.View{
    private Database database;
    private RecyclerView recyclerViewElements;
    private AdapterElements adapterElements;
    private MainActivityInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewElements= (RecyclerView) findViewById(R.id.recyclerViewElements);
        database=new Database(this);

        getSupportActionBar().setTitle("Ordenamiento");

        presenter= new MainActivityPresenter(this);

        recyclerViewElements.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewElements.setLayoutManager(linearLayoutManager);

        presenter.insertElements(database);
        presenter.orderElements(database);

    }


//    muestra la lista de elementos
    @Override
    public void showListElements(ArrayList<Element> elements){
        adapterElements=new AdapterElements(elements);
        recyclerViewElements.setAdapter(adapterElements);
    }

//    muestra un mensaje
    @Override
    public void showMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }




}
