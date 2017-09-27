package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;

import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by shamima.yasmin on 9/22/2017.
 * Methods are originally written by: Israt Mity and Arafat
 */


public abstract class BaseDBTable <ModelType>  {

    protected Context tContext;

    public abstract void createTable();
    public abstract long insertItem(ModelType modelType);
    public abstract ModelType getNodeInfo(int node);
    public abstract ModelType cursorToModel(Cursor cursor);


    protected SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    protected void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    protected boolean isFieldExist(int id, String TABLE_NAME, String KEY_IDENTIFIER_ID) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + id, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cursor.close();
                closeDB();
                return true;
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return false;
    }

    protected void delete(int id, String TABLE_NAME, String KEY_ID){
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_ID + " = " + id, null);

        database.close();
    }


    protected CommonModel getCommonModelFromId(int commonId){
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        return commonDBTable.getNodeInfo(commonId);

    }

    protected ArrayList <ModelType> getDetailsByCommonId(int commonId, String TABLE_NAME, String KEY_COMMON_ID){

        return getDataListFromId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }


    protected ModelType getDataFromId(int nodeId, String TABLE_NAME, String KEY_IDENTIFIER_ID){


        ModelType model = null;
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + nodeId, null);

        if (cursor.moveToFirst()) {
            do {
                model = cursorToModel(cursor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return model;
    }



    protected ArrayList <ModelType> getDataListFromId(int nodeId, String TABLE_NAME, String KEY_IDENTIFIER_ID) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + nodeId, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }

    private ArrayList <ModelType> getAllData(String TABLE_NAME) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }


    protected void dropTable(String TABLE_NAME) {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        closeDB();
    }

}
