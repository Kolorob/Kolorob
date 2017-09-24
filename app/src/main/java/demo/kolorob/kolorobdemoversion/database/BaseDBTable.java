package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CategoryItem;
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


    public SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    public void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public boolean isFieldExist(int id, String TABLE_NAME) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id == cursor.getInt(0)) {
                    cursor.close();
                    closeDB();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return false;
    }

    public void delete(int id, String TABLE_NAME, String KEY_ID){
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_ID + " = " + id, null);

        database.close();
    }



    public void delete(String ward, String area, CommonModel commonModel, String TABLE_NAME, String KEY_COMMON_ID){
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        commonDBTable.delete(ward, area);
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_COMMON_ID + " = " + commonModel.getId(), null);

        database.close();
    }

    public CommonModel getCommonModelFromId(int commonId){
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel commonModel = commonDBTable.getNodeInfo(commonId);

        return commonModel;
    }

    public ArrayList <ModelType> getDetailsByCommonId(int commonId, String TABLE_NAME, String KEY_COMMON_ID){

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + commonId, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToModel(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }

    public ArrayList <ModelType> getDataFromId(int nodeId, String TABLE_NAME, String KEY_IDENTIFIER_ID) {

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

    public ArrayList <ModelType> getAllData(String TABLE_NAME) {

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



    public void dropTable(String TABLE_NAME) {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        closeDB();
    }

}
