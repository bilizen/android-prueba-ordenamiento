package com.example.android_prueba_ordenamiento.activity.activity1;

import android.content.Context;

import com.example.android_prueba_ordenamiento.database.Database;
import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

/**
 * Created by bill on 18/10/17.
 */

public class MainActivityPresenter implements MainActivityInterface.Presenter {

    private MainActivityInterface.View view;
    private MainActivityInterface.Interactor interactor;

    public MainActivityPresenter(MainActivityInterface.View view) {
        this.view = view;
        interactor= new MainAcitivityInteractor(this);
    }

    @Override
    public void insertElements(Database database) {
        if(view!=null){
            interactor.insertElements(database);
        }
    }

    @Override
    public void showListElements(ArrayList<Element> elements) {
        if(view!=null){
            view.showListElements(elements);
        }
    }

    @Override
    public void orderElements(Database database) {
        if(view!=null){
            interactor.orderElements(database);
        }
    }

    @Override
    public void showMessage(String message) {
        if(view!=null) {
            view.showMessage(message);
        }
    }
}
