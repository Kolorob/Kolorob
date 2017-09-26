package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class AreaTable extends BaseDBTable <Area>{

    private static final String TABLE_NAME = DatabaseHelper.AREAS;

    private static final String KEY_ID = "id"; // 0 -integer
    private static final String KEY_AREA_EN = "area_en"; // 1 - text
    private static final String KEY_AREA_BN = "area_bn"; // 2 - text
    private static final String KEY_AREA_KEYWORD = "area_keyword";
    private static final String KEY_PARENT_AREA = "parent_area";
    private static final String KEY_AREA_WARDID = "ward_id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";


    public AreaTable(Context context) {
        tContext = context;
        createTable();
    }

    public AreaTable() {

    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_AREA_EN + " TEXT, "              // 1 - text
                + KEY_AREA_BN + " TEXT, "
                + KEY_AREA_KEYWORD + " TEXT, "
                + KEY_PARENT_AREA + " TEXT, "
                + KEY_LAT + " TEXT, "
                + KEY_LON + " TEXT, "
                + KEY_AREA_WARDID + " INTEGER "

                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(Area area){
        return insertItem(
                area.getId(),
                area.getArea_name(),
                area.getArea_bn(),
                area.getArea_keyword(),
                area.getParentArea(),
                area.getLat(),
                area.getLon(),
                area.getWard_id()
        );
    }

    public long insertItem(int id, String area_name, String area_bn, String area_keyword, String parent_area, String lat, String lon, int ward_id) {
        if (isFieldExist(id)) {
            return updateItem(id, area_name, area_bn, area_keyword, parent_area, lat, lon, ward_id);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_AREA_EN, area_name);
        rowValue.put(KEY_AREA_BN, area_bn);
        rowValue.put(KEY_AREA_KEYWORD, area_keyword);
        rowValue.put(KEY_PARENT_AREA, parent_area);
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ward_id = " + ward_id, null);

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
        return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, String area_en, String area_bn, String area_keyword, String parent_area, String lat, String lon, int ward_id) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_AREA_EN, area_en);
        rowValue.put(KEY_AREA_BN, area_bn);
        rowValue.put(KEY_AREA_KEYWORD, area_keyword);
        rowValue.put(KEY_PARENT_AREA, parent_area);
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ward_id = " + ward_id, null);

        if (cursor.moveToFirst()) {
            do {
                areaList.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return areaList;
    }

    public Area cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        String area_en = cursor.getString(1);
        String area_bn = cursor.getString(2);
        String area_keyword = cursor.getString(3);
        String parent_area = cursor.getString(4);
        String lat = cursor.getString(5);
        String lon = cursor.getString(6);
        int ward_id = cursor.getInt(7);
        return new Area(id, area_en, area_bn, area_keyword, parent_area, lat, lon, ward_id);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

    public Area getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        Area area = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                area = new Area(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(6));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return area;
    }
}
