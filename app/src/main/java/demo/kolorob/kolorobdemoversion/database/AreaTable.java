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


public class AreaTable extends BaseDBTable <Area>{

    private static final String TABLE_NAME = DatabaseHelper.AREAS;

    private static final String KEY_AREA_EN = "area_en"; // 1 - text
    private static final String KEY_AREA_BN = "area_bn"; // 2 - text
    private static final String KEY_AREA_KEYWORD = "area_keyword";
    private static final String KEY_PARENT_AREA = "parent_area";
    private static final String KEY_AREA_WARDID = "ward_id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";


    public AreaTable(Context context) {
        super(context);
    }


    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER PRIMARY KEY, "
                + KEY_AREA_EN + " TEXT, "
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
        if(!isFieldExist(area.getId())){
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
        else{
            return updateItem(area);
        }
    }

    public long updateItem(Area area){
        return updateItem(area.getId(),
                area.getArea_name(),
                area.getArea_bn(),
                area.getArea_keyword(),
                area.getParentArea(),
                area.getLat(),
                area.getLon(),
                area.getWard_id());
    }

    private long insertItem(int id, String area_name, String area_bn, String area_keyword, String parent_area, String lat, String lon, int ward_id) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
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



    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, String area_en, String area_bn, String area_keyword, String parent_area, String lat, String lon, int ward_id) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_AREA_EN, area_en);
        rowValue.put(KEY_AREA_BN, area_bn);
        rowValue.put(KEY_AREA_KEYWORD, area_keyword);
        rowValue.put(KEY_PARENT_AREA, parent_area);
        rowValue.put(KEY_LAT, lat);
        rowValue.put(KEY_LON, lon);
        rowValue.put(KEY_AREA_WARDID, ward_id);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
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


    public Area getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <Area> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public Area getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_AREA_WARDID);
    }

    public ArrayList <Area> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_AREA_WARDID);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

    public ArrayList <Area> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }


}
