package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class HealthNewDBTableHospital {
    private static final String TAG = HealthNewDBTableHospital.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_HOS;
    private static final String KEY_SERVICE_ID = "_servicecenterid";
    private static final String KEY_EMERGENCY_AVAIL = "_eavailable";
    private static final String KEY_EMERGENCY_NO = "_enumber";
    private static final String KEY_AMBULANCE_AVAIL = "_ambavailable";
    private static final String KEY_AMBULANCE_NO = "_ambnumber";
    private static final String KEY_MATER_AVAIL = "_mtravailable";
    private static final String KEY_MATER_NO = "_mtrnum";
    private static final String KEY_MATER_PRIVACY= "_mtrprivacy"; //




    private Context tContext;

    public HealthNewDBTableHospital(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
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
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
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

    public ArrayList<HealthNewDBModelHospital> getHealthSpecialistData(int node_id) {
        ArrayList<HealthNewDBModelHospital> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_SERVICE_ID +" = "+node_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                subCatList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }



    public boolean isFieldExist(int nodeid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int s=cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == nodeid ) {
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

    private HealthNewDBModelHospital cursorToSubCatList(Cursor cursor) {
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


    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
