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

        recyclerViewElements.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewElements.setLayoutManager(linearLayoutManager);

        insertElements();
        orderElements();

        adapterElements=new AdapterElements(database.getArrayListElements());
        recyclerViewElements.setAdapter(adapterElements);

    }

    public void insertElements(){
        if(database.countElements()==0){
            database.insertElement(new Element("verde",1,1,0,"#4CAF50"));
            database.insertElement(new Element("azul",2,2,0,"#2196F3"));
            database.insertElement(new Element("rojo",3,3,0,"#F44336"));
//            database.insertElement(new Element("amarillo",4,4,0,"#FFEB3B"));
        }
    }

    public void orderElements(){
        if(touchMax()) {
            updateAllOrderCurrent();
            resetContElements();
        }
    }


    public boolean touchMax(){
        ArrayList<Element> elementArrayList= database.getArrayListElements();
        for(int i=0;i<elementArrayList.size();i++){
            if(elementArrayList.get(i).getCont()>=3){
                return true;
            }
        }
        return false;
    }

    public void updateAllOrderCurrent(){
        ArrayList<Element> elementArrayList=database.getArrayListElements();
        for(int i=0;i<elementArrayList.size();i++){
            database.updateOrderCurrent(elementArrayList.get(i));
        }
    }

    public void resetContElements(){
        database.updateResetCont();
    }
}
