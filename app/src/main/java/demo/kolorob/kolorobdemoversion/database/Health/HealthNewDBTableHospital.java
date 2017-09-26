package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;


/**
 * Created by israt.jahan on 6/27/2016.
 */
public class HealthNewDBTableHospital extends BaseDBTable <HealthNewDBModelHospital> {

    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_HOS;
    private static final String KEY_SERVICE_ID = "_servicecenterid";
    private static final String KEY_EMERGENCY_AVAIL = "_eavailable";
    private static final String KEY_EMERGENCY_NO = "_enumber";
    private static final String KEY_AMBULANCE_AVAIL = "_ambavailable";
    private static final String KEY_AMBULANCE_NO = "_ambnumber";
    private static final String KEY_MATER_AVAIL = "_mtravailable";
    private static final String KEY_MATER_NO = "_mtrnum";
    private static final String KEY_MATER_PRIVACY= "_mtrprivacy"; //


    public HealthNewDBTableHospital(Context context) {
        tContext = context;
        createTable();
    }
    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_SERVICE_ID + " INTEGER , "
                + KEY_EMERGENCY_AVAIL + "  TEXT  , " // 0 - int "
                + KEY_EMERGENCY_NO + "  TEXT , "
                + KEY_AMBULANCE_AVAIL + "  TEXT  , " // 0 - int "
                + KEY_AMBULANCE_NO + "  TEXT , "
                + KEY_MATER_AVAIL + "  TEXT  , " // 0 - int "
                + KEY_MATER_NO + "  TEXT , "
                + KEY_MATER_PRIVACY + "  TEXT , PRIMARY KEY(" + KEY_SERVICE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    public long insertItem(HealthNewDBModelHospital healthNewDBModelHospital) {
        if (!isFieldExist(healthNewDBModelHospital.getServicecenterid())) {
            return insertItem(
                    healthNewDBModelHospital.getServicecenterid(), healthNewDBModelHospital.getEmergencyavailable(),
                    healthNewDBModelHospital.getEmergencynumber(), healthNewDBModelHospital.getAmbulanceavailable(), healthNewDBModelHospital.getAmbulancenumber(),
                    healthNewDBModelHospital.getMaternityavailable(), healthNewDBModelHospital.getMaternitynumber(), healthNewDBModelHospital.getMaternityprivacy()

            );
        }
        else {
            return updateItem(
                    healthNewDBModelHospital.getServicecenterid(), healthNewDBModelHospital.getEmergencyavailable(),
                    healthNewDBModelHospital.getEmergencynumber(), healthNewDBModelHospital.getAmbulanceavailable(), healthNewDBModelHospital.getAmbulancenumber(),
                    healthNewDBModelHospital.getMaternityavailable(), healthNewDBModelHospital.getMaternitynumber(), healthNewDBModelHospital.getMaternityprivacy()

            );
        }
    }

    public long insertItem(int serviceproviderId, String eavail, String enumber,String ambavail,String ambnumber,
                           String materavail,String maternumber,String materprivacy) {
        if (isFieldExist(serviceproviderId)) {
            return updateItem(
                   serviceproviderId,
                    eavail,
                    enumber,
                    ambavail,
                    ambnumber,
                    materavail,
                    maternumber,materprivacy);

        }
        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_SERVICE_ID, serviceproviderId);
        rowValue.put(KEY_EMERGENCY_AVAIL, eavail);
        rowValue.put(KEY_EMERGENCY_NO, enumber);
        rowValue.put(KEY_AMBULANCE_AVAIL, ambavail);
        rowValue.put(KEY_AMBULANCE_NO, ambnumber);
        rowValue.put(KEY_MATER_AVAIL, materavail);
        rowValue.put(KEY_MATER_NO, maternumber);
        rowValue.put(KEY_MATER_PRIVACY, materprivacy);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int serviceproviderId, String eavail, String enumber,String ambavail,String ambnumber,
                            String materavail,String maternumber,String materprivacy) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_SERVICE_ID, serviceproviderId);
        rowValue.put(KEY_EMERGENCY_AVAIL, eavail);
        rowValue.put(KEY_EMERGENCY_NO, enumber);
        rowValue.put(KEY_AMBULANCE_AVAIL, ambavail);
        rowValue.put(KEY_AMBULANCE_NO, ambnumber);
        rowValue.put(KEY_MATER_AVAIL, materavail);
        rowValue.put(KEY_MATER_NO, maternumber);
        rowValue.put(KEY_MATER_PRIVACY, materprivacy);




        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_SERVICE_ID + " = ?  ",
                new String[]{serviceproviderId + ""});
        closeDB();
        return ret;

    }

    public ArrayList <HealthNewDBModelHospital> getDataFromId(int id) {
        return super.getDataFromId(id, TABLE_NAME, KEY_SERVICE_ID);
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public HealthNewDBModelHospital cursorToModel(Cursor cursor) {
        int _servicecenterid = cursor.getInt(0);
        String _eavailable = cursor.getString(1);
        String _enumber= cursor.getString(2);
        String _ambavailable = cursor.getString(3);
        String _ambnumber = cursor.getString(4);
        String _mtravailable= cursor.getString(5);
        String _mtrnum = cursor.getString(6);
        String _mtrprivacy = cursor.getString(7);

        return new HealthNewDBModelHospital(_servicecenterid,_eavailable,_enumber,
                _ambavailable,_ambnumber,_mtravailable,_mtrnum,_mtrprivacy);
    }

    public HealthNewDBModelHospital getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        HealthNewDBModelHospital healthNewDBModelHospital = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_SERVICE_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                healthNewDBModelHospital = new HealthNewDBModelHospital(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return healthNewDBModelHospital;
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_SERVICE_ID);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
