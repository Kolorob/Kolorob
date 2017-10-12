package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.model.StoredArea;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredAreaTable extends BaseDBTable <StoredArea> {

    private static final String TABLE_NAME = DatabaseHelper.AREASTORED;

    private static final String WARDID = "_ward_id";
    private static final String AREANAME = "name_en";
    private static final String AREANAMEBN = "name_bn";
    private static final String PARENTAREA = "parent_area";
    private static final String LAT = "lat";
    private static final String LON = "lon";


    public StoredAreaTable(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER PRIMARY KEY, "
                + WARDID + " TEXT, "
                + AREANAME + " TEXT, "
                + AREANAMEBN + " TEXT, "
                + PARENTAREA + " TEXT, "
                + LAT + " TEXT, "
                + LON + " TEXT "
                + " )";


        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(StoredArea storedArea){
        if(isFieldExist(storedArea.getId())){
            return updateItem(storedArea);
        }
        return insertItem(storedArea.getId(), storedArea.getWard(), storedArea.getArea(), storedArea.getAreaBn(), storedArea.getParentArea(), storedArea.getLat(), storedArea.getLon());
    }

    public long updateItem(StoredArea storedArea){
        return updateItem(storedArea.getId(), storedArea.getWard(), storedArea.getArea(), storedArea.getAreaBn(), storedArea.getParentArea(), storedArea.getLat(), storedArea.getLon());
    }

    private long insertItem(int id, String ward, String area, String areaBn, String parentArea, String lat, String lon) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
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

    public ArrayList <StoredArea> getStoredLocation(String ward, String area) {
        ArrayList<StoredArea> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + WARDID + " = '" + ward + "' AND " + AREANAME + " = '" + area + "'", null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public boolean isAreaStored(String ward, String area){
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + WARDID + " = '" + ward + "'" + " AND " + AREANAME + " = '" + area + "'", null);

        boolean stored = cursor.moveToFirst();

        cursor.close();
        closeDB();
        return stored;
    }

    private long updateItem(int id, String ward, String area, String areaBn, String parentArea, String lat, String lon) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(WARDID, ward);
        rowValue.put(AREANAME, area);
        rowValue.put(AREANAMEBN, areaBn);
        rowValue.put(PARENTAREA, parentArea);
        rowValue.put(LAT, lat);
        rowValue.put(LON, lon);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
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
       super.delete(id, TABLE_NAME);
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



   public ArrayList <StoredArea> getAllData() {
        return super.getAllData(TABLE_NAME);
   }

   public ArrayList <StoredArea> getDataListFromId(int id){
        return super.getDataListFromId(id, TABLE_NAME);
   }

   public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
