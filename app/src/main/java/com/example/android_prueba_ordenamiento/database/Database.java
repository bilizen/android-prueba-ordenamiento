package com.example.android_prueba_ordenamiento.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android_prueba_ordenamiento.entity.Element;

import java.util.ArrayList;

/**
 * Created by Bill on 15/10/2017.
 */

public class Database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="PRUEBA";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME_ELEMENT="ELEMENT";
    private static final String COLUMN_ELEMENT_ID="elementId";
    private static final String COLUMN_ELEMENT_NAME="elementName";
    private static final String COLUMN_ELEMENT_ORDER_CURRENT="elementOrderCurrent";
    private static final String COLUMN_ELEMENT_ORDER_LAST="elementOrderLast";
    private static final String COLUMN_ELEMENT_COUNT="elementCount";
    private static final String COLUMN_ELEMENT_COLOR="elementColor";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TABLE_ELEMENT = "CREATE TABLE "+TABLE_NAME_ELEMENT + " ( "+
                COLUMN_ELEMENT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COLUMN_ELEMENT_NAME +" TEXT , "+
                COLUMN_ELEMENT_ORDER_CURRENT +" INTEGER , "+
                COLUMN_ELEMENT_ORDER_LAST +" INTEGER , "+
                COLUMN_ELEMENT_COUNT +" INTEGER , " +
                COLUMN_ELEMENT_COLOR + " TEXT ) ";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ELEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String SQL_DROP_TABLE_ELEMENT="DROP TABLE IF EXISTS "+TABLE_NAME_ELEMENT;
        sqLiteDatabase.execSQL(SQL_DROP_TABLE_ELEMENT);
        this.onCreate(sqLiteDatabase);
    }

    public int countElements(){
        SQLiteDatabase db=this.getWritableDatabase();
        String SQL_SELECT = "SELECT COUNT(*) FROM "+TABLE_NAME_ELEMENT;
        Cursor cursor = db.rawQuery(SQL_SELECT, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                i = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return i;
    }
    public void insertElement(Element element){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ELEMENT_NAME, element.getName());
        contentValues.put(COLUMN_ELEMENT_ORDER_CURRENT, element.getOrderCurrent());
        contentValues.put(COLUMN_ELEMENT_ORDER_LAST, element.getOrderLast());
        contentValues.put(COLUMN_ELEMENT_COUNT, element.getCont());
        contentValues.put(COLUMN_ELEMENT_COLOR, element.getColor());
        db.insert(TABLE_NAME_ELEMENT, null, contentValues);
        db.close();
    }

    public ArrayList<Element> getArrayListElements(){
        String SQL_SELECT = "SELECT "+
                COLUMN_ELEMENT_ID+" , "+
                COLUMN_ELEMENT_NAME+" , "+
                COLUMN_ELEMENT_ORDER_CURRENT+" , "+
                COLUMN_ELEMENT_ORDER_LAST+" , "+
                COLUMN_ELEMENT_COUNT+" , "+
                COLUMN_ELEMENT_COLOR+
                " FROM "+TABLE_NAME_ELEMENT +
                " ORDER BY "+COLUMN_ELEMENT_ORDER_CURRENT+" DESC ";
        ArrayList<Element> elementArrayList= new ArrayList<>();
        Element element;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery(SQL_SELECT,null);
        if(cursor.moveToFirst()){
            do {
                element= new Element();
                element.setId(cursor.getInt(0));
                element.setName(cursor.getString(1));
                element.setOrderCurrent(cursor.getInt(2));
                element.setOrderLast(cursor.getInt(3));
                element.setCont(cursor.getInt(4));
                element.setColor(cursor.getString(5));
                elementArrayList.add(element);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return elementArrayList;
    }

    public Element getElement(int id){
        String SQL_SELECT = "SELECT "+
                COLUMN_ELEMENT_ID+" , "+
                COLUMN_ELEMENT_NAME+" , "+
                COLUMN_ELEMENT_ORDER_CURRENT+" , "+
                COLUMN_ELEMENT_ORDER_LAST+" , "+
                COLUMN_ELEMENT_COUNT+" , "+
                COLUMN_ELEMENT_COLOR+
                " FROM "+TABLE_NAME_ELEMENT+
                " WHERE "+COLUMN_ELEMENT_ID+" = "+id;
        Element element= new Element();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery(SQL_SELECT,null);
        if(cursor.moveToFirst()){
            do {
                element.setId(cursor.getInt(0));
                element.setName(cursor.getString(1));
                element.setOrderCurrent(cursor.getInt(2));
                element.setOrderLast(cursor.getInt(3));
                element.setCont(cursor.getInt(4));
                element.setColor(cursor.getString(5));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return element;
    }

//  dismuye un(1) orden de los elementos
    public void updateOrderLatestByOrder(int orderLatest){
        String SQL_UPDATE=" UPDATE "+ TABLE_NAME_ELEMENT +
                " SET "+COLUMN_ELEMENT_ORDER_LAST+" =  "+(orderLatest-1)+
                " WHERE "+COLUMN_ELEMENT_ORDER_LAST+" = "+orderLatest;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL(SQL_UPDATE);
        sqLiteDatabase.close();
    }

//    actualizar el ultimo elemento tocado
    public void updateOrderLatest(int id,int order){
        String SQL_UPDATE=" UPDATE "+ TABLE_NAME_ELEMENT +
                " SET "+COLUMN_ELEMENT_ORDER_LAST+" = "+order+
                " WHERE "+COLUMN_ELEMENT_ID+" = "+id;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL(SQL_UPDATE);
        sqLiteDatabase.close();
    }

//    actualizar el ultimo orden de cada elemento
    public void updateContLatest(int id){
        Element element=getElement(id);
        String SQL_UPDATE=" UPDATE "+ TABLE_NAME_ELEMENT +
                " SET "+COLUMN_ELEMENT_COUNT+" =  "+(element.getCont()+1)+
                " WHERE "+COLUMN_ELEMENT_ID+" = "+id;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL(SQL_UPDATE);
        sqLiteDatabase.close();
    }

//    actualiza el orden actual de los elementos
    public void updateOrderCurrent(Element element){
        String SQL_UPDATE=" UPDATE "+ TABLE_NAME_ELEMENT +
                " SET "+COLUMN_ELEMENT_ORDER_CURRENT+" = "+element.getOrderLast()+
                " WHERE "+COLUMN_ELEMENT_ID+" = "+element.getId();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL(SQL_UPDATE);
        sqLiteDatabase.close();
    }

//    resetea todos los contadoras de los elementos
    public void updateResetCont(){
        String SQL_UPDATE=" UPDATE "+ TABLE_NAME_ELEMENT +
                " SET "+COLUMN_ELEMENT_COUNT+" = 0";
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL(SQL_UPDATE);
        sqLiteDatabase.close();
    }

}
