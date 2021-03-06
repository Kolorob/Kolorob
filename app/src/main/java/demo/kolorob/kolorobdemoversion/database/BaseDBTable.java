package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.model.CommonModel;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by shamima.yasmin on 9/22/2017.
 * Methods are originally written by: Israt Mity and Arafat
 */


public abstract class BaseDBTable <ModelType>  {


    protected static final String KEY_IDENTIFIER_ID = "id";
    protected Context tContext;

    public abstract void createTable();
    public abstract ModelType cursorToModel(Cursor cursor);
    public abstract boolean isFieldExist(int id);
    public abstract long insertItem(ModelType model);
    public abstract long updateItem(ModelType model);
    public abstract ModelType getNodeInfo(int id);
    public abstract ArrayList <ModelType> getDataListFromId(int id);
    public abstract void delete(int id);
    public abstract void dropTable();


    protected BaseDBTable(Context context) {
        tContext = context;
        createTable();
    }


    protected SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    protected void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    protected boolean isFieldExist(int id, String TABLE_NAME) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + id, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        closeDB();

        return exists;
    }


    protected void delete(int id, String TABLE_NAME){
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_IDENTIFIER_ID + " = " + id, null);

        database.close();
    }

    protected <Table extends CommonDBTable> void delete(String ward, String area, String TABLE_NAME, String foreignKey, String foreignTable, String foreignTableKey){
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        database.delete(TABLE_NAME, foreignKey + " IN (" +
                " SELECT " + foreignTableKey + " FROM " + foreignTable +
                " WHERE " + Table.getKeyWard() + " = '" + ward + "' AND ( " +
                Table.getKeyArea() + " = '" + area + "' OR " + Table.getKeyParentArea() + " = '" + area + "' ))" , null);

        database.close();
    }


    protected ModelType getNodeInfo(int nodeId, String TABLE_NAME, String KEY){

        ModelType model = null;
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = " + nodeId, null);

        if (cursor.moveToFirst()) {
            do {
                model = cursorToModel(cursor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return model;
    }



    protected ArrayList <ModelType> getDataListFromId(int nodeId, String TABLE_NAME, String KEY) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = " + nodeId, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }



    protected ArrayList <ModelType> getAllData(String TABLE_NAME) {

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
