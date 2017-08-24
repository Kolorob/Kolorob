package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.Area;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class AreaTable {

    private static final String TAG = AreaTable.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.AREAS;

    private static final String KEY_ID = "id"; // 0 -integer
    private static final String KEY_AREA_EN = "area_en"; // 1 - text
    private static final String KEY_AREA_BN = "area_bn"; // 2 - text
    private static final String KEY_AREA_KEYWORD = "area_keyword";
    private static final String KEY_AREA_WARDID = "ward_id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";




    private Context tContext;

    public AreaTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_AREA_EN + " TEXT, "              // 1 - text
                + KEY_AREA_BN + " TEXT, "
                + KEY_AREA_KEYWORD + " TEXT, "
                + KEY_LAT + " TEXT, "
                + KEY_LON + " TEXT, "
                + KEY_AREA_WARDID + " INTEGER "

                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(Area area){
        return insertItem(
                area.getId(),
                area.getArea_name(),
                area.getArea_bn(),
                area.getArea_keyword(),
                area.getLat(),
                area.getLon(),
                area.getWard_id()
        );
    }

    public long insertItem(int id, String area_name, String area_bn, String area_keyword, String lat, String lon, int ward_id) {
        if (isFieldExist(id)) {
            return updateItem(id, area_name, area_bn, area_keyword, lat, lon, ward_id);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_AREA_EN, area_name);
        rowValue.put(KEY_AREA_BN, area_bn);
        rowValue.put(KEY_AREA_KEYWORD, area_keyword);
        rowValue.put(KEY_LAT, lat);
        rowValue.put(KEY_LON, lon);
        rowValue.put(KEY_AREA_WARDID, ward_id);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }
    public ArrayList<String> getAllAreaNameBN(int ward_id) {
        ArrayList<String> area_bn_list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE ward_id = " + ward_id, null);

        if (cursor.moveToFirst()) {
            do {
                String area_bn = cursor.getString(2);
                area_bn_list.add(area_bn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return area_bn_list;
    }

    public boolean isFieldExist(int id) {
        // Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0)) == id) {
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

    private long updateItem(int id, String area_en, String area_bn, String area_keyword, String lat, String lon, int ward_id) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_AREA_EN, area_en);
        rowValue.put(KEY_AREA_BN, area_bn);
        rowValue.put(KEY_AREA_KEYWORD, area_keyword);
        rowValue.put(KEY_LAT, lat);
        rowValue.put(KEY_LON, lon);
        rowValue.put(KEY_AREA_WARDID, ward_id);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList<Area> getAllAreas(int ward_id) {
        ArrayList<Area> areaList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE ward_id = " + ward_id, null);

        if (cursor.moveToFirst()) {
            do {

                areaList.add(cursorToArea(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return areaList;
    }

    private Area cursorToArea(Cursor cursor) {
        int id = cursor.getInt(0);
        String area_en = cursor.getString(1);
        String area_bn = cursor.getString(2);
        String area_keyword = cursor.getString(3);
        String lat = cursor.getString(4);
        String lon = cursor.getString(5);
        int ward_id = cursor.getInt(6);
        return new Area(id, area_en, area_bn, area_keyword, lat, lon, ward_id);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        //Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
