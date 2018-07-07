package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;


/**
 * Created by shamima.yasmin on 8/23/2017.
 */


public class CityCorporationTable extends BaseDBTable <CityCorporation> {


    private static final String TABLE_NAME = DatabaseHelper.CITYCORPORATIONS;

    private static final String KEY_CC_EN = "cc_en";
    private static final String KEY_CC_BN = "cc_bn";
    private static final String KEY_CC_KEYWORD = "cc_keyword";
    private static final String KEY_DISTRICT_ID = "district_id";


    public CityCorporationTable(Context context) {
       super(context);
    }


    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER PRIMARY KEY, "
                + KEY_CC_EN + " TEXT, "
                + KEY_CC_BN + " TEXT, "
                + KEY_CC_KEYWORD + " TEXT, "
                + KEY_DISTRICT_ID + " INTEGER "
                + " )";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(CityCorporation cityCorporation){
        if(isFieldExist(cityCorporation.getId())){
            return updateItem(cityCorporation);
        }

        return insertItem(
                    cityCorporation.getId(),
                    cityCorporation.getCityCorporation_name(),
                    cityCorporation.getCityCorporation_bn(),
                    cityCorporation.getCityCorporation_keyword(),
                    cityCorporation.getDistrictId()
        );

    }

    public long updateItem(CityCorporation cityCorporation){
        return updateItem(cityCorporation.getId(),
                cityCorporation.getCityCorporation_name(),
                cityCorporation.getCityCorporation_bn(),
                cityCorporation.getCityCorporation_keyword(),
                cityCorporation.getDistrictId());
    }

    private long insertItem(int id, String cc_name, String cc_bn, String cc_keyword, int district_id) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_CC_EN, cc_name);
        rowValue.put(KEY_CC_BN, cc_bn);
        rowValue.put(KEY_CC_KEYWORD, cc_keyword);
        rowValue.put(KEY_DISTRICT_ID, district_id);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, String cc_en, String cc_bn, String cc_keyword, int district_id) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_CC_EN, cc_en);
        rowValue.put(KEY_CC_BN, cc_bn);
        rowValue.put(KEY_CC_KEYWORD, cc_keyword);
        rowValue.put(KEY_DISTRICT_ID, district_id);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public CityCorporation getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <CityCorporation> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <CityCorporation> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_DISTRICT_ID);
    }


    public ArrayList <CityCorporation> getAllData() {
        return super.getAllData(TABLE_NAME);
    }


    public CityCorporation cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        String cc_en = cursor.getString(1);
        String cc_bn = cursor.getString(2);
        String cc_keyword = cursor.getString(3);
        int district_id = cursor.getInt(4);

        return new CityCorporation(id, cc_en, cc_bn, cc_keyword, district_id);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
