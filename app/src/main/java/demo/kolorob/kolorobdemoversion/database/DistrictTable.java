package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.model.District;

/**
 * Created by shamima.brishti on 2/9/18.
 */

public class DistrictTable extends BaseDBTable <District> {


    private static final String TABLE_NAME = DatabaseHelper.DISTRICTS;

    private static final String KEY_DISTRICT_EN = "district_en";
    private static final String KEY_DISTRICT_BN = "district_bn";
    private static final String KEY_DISTRICT_KEYWORD = "district_keyword";


    public DistrictTable(Context context) {
        super(context);
    }


    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER PRIMARY KEY, "
                + KEY_DISTRICT_EN + " TEXT, "
                + KEY_DISTRICT_BN + " TEXT, "
                + KEY_DISTRICT_KEYWORD + " TEXT "
                + " )";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(District district){
        if(isFieldExist(district.getId())){
            return updateItem(district);
        }

        return insertItem(
                district.getId(),
                district.getDistrict_en(),
                district.getDistrict_bn(),
                district.getDistrict_keyword()
        );

    }

    public long updateItem(District district){
        return updateItem(district.getId(),
                district.getDistrict_en(),
                district.getDistrict_bn(),
                district.getDistrict_keyword()
        );
    }

    private long insertItem(int id, String district_en, String district_bn, String district_keyword) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_DISTRICT_EN, district_en);
        rowValue.put(KEY_DISTRICT_BN, district_bn);
        rowValue.put(KEY_DISTRICT_KEYWORD, district_keyword);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, String district_en, String district_bn, String district_keyword) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_DISTRICT_EN, district_en);
        rowValue.put(KEY_DISTRICT_BN, district_bn);
        rowValue.put(KEY_DISTRICT_KEYWORD, district_keyword);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public District getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <District> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <District> getAllData() {
        return super.getAllData(TABLE_NAME);
    }


    public District cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        String district_en = cursor.getString(1);
        String district_bn = cursor.getString(2);
        String district_keyword = cursor.getString(3);

        return new District(id, district_en, district_bn, district_keyword);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}