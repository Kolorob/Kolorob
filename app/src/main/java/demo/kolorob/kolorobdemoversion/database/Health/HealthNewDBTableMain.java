package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;


/**
 * Created by israt.jahan on 2/9/2017.
 */

public class HealthNewDBTableMain extends BaseDBTable <HealthNewDBModelMain>{

    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_MAIN;
    private static final String KEY_IDENTIFIER_ID = "_healthid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";
    private static final String KEY_INSTITUTE_TYPE = "_institutetype"; // 1 - text

    public HealthNewDBTableMain(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {

        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + " INTEGER , "
                + KEY_INSTITUTE_TYPE + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(HealthNewDBModelMain healthNewDBModelMain) {
        if (!isFieldExist(healthNewDBModelMain.getHealthId())) {
            return insertItem(healthNewDBModelMain.getHealthId(), healthNewDBModelMain.getCommonModel().getId(), healthNewDBModelMain.getInstituteType());
        }
        else {
            return updateItem(healthNewDBModelMain.getHealthId(), healthNewDBModelMain.getCommonModel().getId(), healthNewDBModelMain.getInstituteType());
        }
    }


    public long insertItem(int healthId, int commonId, String instituteType) {
        if (isFieldExist(healthId)) {
            return updateItem(healthId, commonId, instituteType);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, healthId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_INSTITUTE_TYPE, instituteType);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return insertedId;
    }

    private long updateItem(int healthId, int commonId, String instituteType) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, healthId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_INSTITUTE_TYPE, instituteType);

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{healthId + ""});
        closeDB();
        return updatedId;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public HealthNewDBModelMain getNodeInfo (int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + node, null);

        HealthNewDBModelMain healthNewDBModelMain=null;
        if (cursor.moveToFirst()) {
            do {
                healthNewDBModelMain = new HealthNewDBModelMain(cursor.getInt(0), commonModel, cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return healthNewDBModelMain;
    }


    public HealthNewDBModelMain cursorToModel(Cursor cursor) {
        int _healthId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);
        String _instituteType = cursor.getString(2);

        CommonModel _commonModel = getCommonModelFromId(_commonId);
        return new HealthNewDBModelMain(_healthId, _commonModel, _instituteType);

    }


    public HealthNewDBModelMain getDetailsByCommonId(int commonId) {
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }

    public ArrayList <HealthNewDBModelMain> getDataListFromId(int nodeId){
        return super.getDataListFromId(nodeId, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public HealthNewDBModelMain getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public void dropTable(){
        super.dropTable(TABLE_NAME);
    }
}