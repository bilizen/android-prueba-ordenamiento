package com.example.android_prueba_ordenamiento.activity.activity1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android_prueba_ordenamiento.R;
import com.example.android_prueba_ordenamiento.adapter.AdapterElements;
import com.example.android_prueba_ordenamiento.database.Database;
import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Database database;
    private RecyclerView recyclerViewElements;
    private AdapterElements adapterElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewElements= (RecyclerView) findViewById(R.id.recyclerViewElements);
        database=new Database(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ordenamiento");

        recyclerViewElements.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewElements.setLayoutManager(linearLayoutManager);

        insertElements();
        orderElements(database.getArrayListElements());
        showListElements(database.getArrayListElements());


    }

//    muestra la lista de elementos
    public void showListElements(ArrayList<Element> elements){
        adapterElements=new AdapterElements(elements);
        recyclerViewElements.setAdapter(adapterElements);
    }

//    inserta los elemntos a la base de datos si no hay data
    public void insertElements(){
        if(database.countElements()==0){
            database.insertElement(new Element("verde",1,1,0,"#4CAF50"));
            database.insertElement(new Element("azul",2,2,0,"#2196F3"));
            database.insertElement(new Element("rojo",3,3,0,"#F44336"));
//            database.insertElement(new Element("amarillo",4,4,0,"#FFEB3B"));
        }
    }

//    ordenar los elementos cuando superan los 3 touch en algun elemento
    public void orderElements(ArrayList<Element> arrayList){
        if(touchMax(arrayList)) {
            updateAllOrderCurrent(arrayList);
            resetContElements();
        }
    }

//    retorna true si algun elemento tiene 3 o mas touch
    public boolean touchMax(ArrayList<Element> elements){
//        ArrayList<Element> elementArrayList= database.getArrayListElements();
        for(int i=0;i<elements.size();i++){
            if(elements.get(i).getCont()>=3){
                return true;
            }
        }
        return false;
    }

//    actualiza el orden de todos elementos
    public void updateAllOrderCurrent(ArrayList<Element> elements){
//        ArrayList<Element> elementArrayList=database.getArrayListElements();
        for(int i=0;i<elements.size();i++){
            database.updateOrderCurrent(elements.get(i));
        }
    }

//    reseta el contador de todos los elementos cuando se ordenan
    public void resetContElements(){
        database.updateResetCont();
    }
}
