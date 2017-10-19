package com.example.android_prueba_ordenamiento.activity.activity1;

import android.util.Log;

import com.example.android_prueba_ordenamiento.database.Database;
import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

/**
 * Created by bill on 18/10/17.
 */

public class MainAcitivityInteractor implements MainActivityInterface.Interactor {
    private MainActivityInterface.Presenter presenter;

    public MainAcitivityInteractor(MainActivityInterface.Presenter presenter) {
        this.presenter = presenter;
    }

//    inserta los elemntos a la base de datos si no hay data
    @Override
    public void insertElements(Database database) {
        if(database.countElements()==0){
            database.insertElement(new Element("verde",1,1,"#4CAF50"));
            database.insertElement(new Element("azul",2,2,"#2196F3"));
            database.insertElement(new Element("rojo",3,3,"#F44336"));
//            database.insertElement(new Element("amarillo",4,4,"#FFEB3B"));
            presenter.showMessage("Se insertaron los elementos");
        }
    }

//    ordenar los elementos cuando superan los 3 touch en algun elemento y mostrarlo
    @Override
    public void orderElements(Database database){
        ArrayList<Element> elements = database.getArrayListElements();
        if(touchMax(elements)) {
            updateAllOrderCurrent(database,elements);
            resetContElements(database);
            presenter.showMessage("Se ordenaron los elementos");
            Log.i("MainActivityView","se ordenó los elementos");
        }
        presenter.showListElements(database.getArrayListElements());
    }

//    retorna true si algun elemento tiene 3 o mas touch
    private boolean touchMax(ArrayList<Element> elements){
        for(int i=0;i<elements.size();i++){
            if(elements.get(i).getCont()>=3){
                return true;
            }
        }
        return false;
    }


//    actualiza el orden de todos elementos
    private void updateAllOrderCurrent(Database database, ArrayList<Element> elements){
        for(int i=0;i<elements.size();i++){
            database.updateOrderCurrent(elements.get(i),(elements.size()-i));
        }
    }


//    reseta el contador de todos los elementos cuando se ordenan
    private void resetContElements(Database database){
        database.updateResetCont();
    }



}
