package com.example.android_prueba_ordenamiento.activity.activity1;

import android.content.Context;
import android.provider.ContactsContract;

import com.example.android_prueba_ordenamiento.database.Database;
import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

/**
 * Created by bill on 18/10/17.
 */

public interface MainActivityInterface {

    interface View{
        void showListElements(ArrayList<Element> elements);
        void showMessage(String message);
    }

    interface Presenter{
        void showListElements(ArrayList<Element> elements);
        void showMessage(String message);
        void insertElements(Database database);
        void orderElements(Database database);
    }

    interface Interactor{
        void insertElements(Database database);
        void orderElements(Database database);
    }

}
