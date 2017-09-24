package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.model.Ward;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredAreaTable extends BaseDBTable <StoredArea> {

    private static final String TABLE_NAME = DatabaseHelper.AREASTORED;

    private static final String KEY_ID = "_id";
    private static final String WARDID = "_ward_id"; // 0 -integer
    private static final String AREANAME = "_area_name"; // 1 - text
    private static final String AREANAMEBN = "_area_nameBn"; // 1 - text
    private static final String PARENTAREA = "_parent_area";
    private static final String LAT = "_lat";
    private static final String LON = "_lon";

    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.

    public StoredAreaTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + WARDID + " TEXT, " // 0 - int
                + AREANAME + " TEXT, "
                + AREANAMEBN + " TEXT, "
                + PARENTAREA + " TEXT, "
                + LAT + " TEXT, " // 1 - tex// 2 - text
                + LON + " TEXT, "
                + " )";


        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(StoredArea storedArea){
        return insertItem(storedArea.getId(), storedArea.getWard(), storedArea.getArea(), storedArea.getAreaBn(), storedArea.getParentArea(), storedArea.getLat(), storedArea.getLon());
    }

    public long insertItem(int id, String ward, String area, String areaBn, String parentArea, String lat, String lon) {
        if (isFieldExist(id)) {
            return updateItem(id, ward, area, areaBn, parentArea, lat, lon);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(WARDID, ward);
        rowValue.put(AREANAME, area);
        rowValue.put(AREANAMEBN, areaBn);
        rowValue.put(PARENTAREA, parentArea);
        rowValue.put(LAT, lat);
        rowValue.put(LON, lon);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public boolean isAreaStored(String ward, String area){
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+ WARDID+ " = '" + ward + "'" + " AND "+ AREANAME+" = '"+area+"'", null);

        if (cursor.moveToFirst()) {
            do {
                if (ward.equals(cursor.getString(0)) && area.equals(cursor.getString(1))) {
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

    private long updateItem(int id, String ward, String area, String areaBn, String parentArea, String lat, String lon) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(WARDID, ward);
        rowValue.put(AREANAME, area);
        rowValue.put(AREANAMEBN, areaBn);
        rowValue.put(PARENTAREA, parentArea);
        rowValue.put(LAT, lat);
        rowValue.put(LON, lon);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }


    public StoredArea getNodeInfo(String ward, String area) {

        SQLiteDatabase db = openDB();
        StoredArea storedArea = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + WARDID + " = '" + ward + "' AND (" + AREANAME + " = '" + area + "' OR " + PARENTAREA + " = '" + area + "' )", null);

        if (cursor.moveToFirst()) {
            do {
                storedArea = new StoredArea(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return storedArea;
    }


    public void delete(int id) {
       super.delete(id, TABLE_NAME, KEY_ID);
    }


   public StoredArea cursorToModel(Cursor cursor) {

       int _id = cursor.getInt(0);
       String _ward = cursor.getString(1);
       String _area = cursor.getString(2);
       String _areaBn = cursor.getString(3);
       String _parentArea = cursor.getString(4);
       String _lat = cursor.getString(5);
       String _lon = cursor.getString(6);

       return new StoredArea(_id, _ward, _area, _areaBn, _parentArea, _lat, _lon);
   }

    public StoredArea getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        StoredArea storedArea = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                storedArea = new StoredArea(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return storedArea;
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
