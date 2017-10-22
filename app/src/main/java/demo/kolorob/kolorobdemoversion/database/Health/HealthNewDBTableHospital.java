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

    private static final String KEY_HEALTH_ID = "health_id";
    private static final String KEY_EMERGENCY_AVAILABLE = "emergency_available";
    private static final String KEY_EMERGENCY_NO = "emergency_number";
    private static final String KEY_AMBULANCE_AVAILABLE = "ambulance_available";
    private static final String KEY_AMBULANCE_NO = "ambulance_number";
    private static final String KEY_MATERNITY_AVAILABLE = "maternity_available";
    private static final String KEY_MATERNITY_SERVICES = "maternity_sevices";
    private static final String KEY_MATERNITY_PRIVACY = "maternity_privacy";


    public HealthNewDBTableHospital(Context context) {
        super(context);
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER , "
                + KEY_HEALTH_ID + " INTEGER , "
                + KEY_EMERGENCY_AVAILABLE + "  TEXT  , "
                + KEY_EMERGENCY_NO + "  TEXT , "
                + KEY_AMBULANCE_AVAILABLE + "  TEXT  , "
                + KEY_AMBULANCE_NO + "  TEXT , "
                + KEY_MATERNITY_AVAILABLE + "  TEXT  , "
                + KEY_MATERNITY_SERVICES + "  TEXT , "
                + KEY_MATERNITY_PRIVACY + "  TEXT , PRIMARY KEY (" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    public long insertItem(HealthNewDBModelHospital hospital) {
        if (!isFieldExist(hospital.getId())) {
            return insertItem(
                    hospital.getId(), hospital.getHealthId(), hospital.getEmergencyavailable(),
                    hospital.getEmergencynumber(), hospital.getAmbulanceavailable(), hospital.getAmbulancenumber(),
                    hospital.getMaternityavailable(), hospital.getMaternitynumber(), hospital.getMaternityprivacy()

            );
        }
        else return updateItem(hospital);
    }

    public long insertItem(int id, int healthId, String eavail, String enumber,String ambavail,String ambnumber,
                           String materavail,String maternumber,String materprivacy) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_HEALTH_ID, healthId);
        rowValue.put(KEY_EMERGENCY_AVAILABLE, eavail);
        rowValue.put(KEY_EMERGENCY_NO, enumber);
        rowValue.put(KEY_AMBULANCE_AVAILABLE, ambavail);
        rowValue.put(KEY_AMBULANCE_NO, ambnumber);
        rowValue.put(KEY_MATERNITY_AVAILABLE, materavail);
        rowValue.put(KEY_MATERNITY_SERVICES, maternumber);
        rowValue.put(KEY_MATERNITY_PRIVACY, materprivacy);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public long updateItem(HealthNewDBModelHospital hospital){
        return updateItem(
                hospital.getId(), hospital.getHealthId(), hospital.getEmergencyavailable(),
                hospital.getEmergencynumber(), hospital.getAmbulanceavailable(), hospital.getAmbulancenumber(),
                hospital.getMaternityavailable(), hospital.getMaternitynumber(), hospital.getMaternityprivacy()
        );
    }


    public long updateItem(int id, int healthId, String eavail, String enumber,String ambavail,String ambnumber,
                            String materavail,String maternumber,String materprivacy) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_HEALTH_ID, healthId);
        rowValue.put(KEY_EMERGENCY_AVAILABLE, eavail);
        rowValue.put(KEY_EMERGENCY_NO, enumber);
        rowValue.put(KEY_AMBULANCE_AVAILABLE, ambavail);
        rowValue.put(KEY_AMBULANCE_NO, ambnumber);
        rowValue.put(KEY_MATERNITY_AVAILABLE, materavail);
        rowValue.put(KEY_MATERNITY_SERVICES, maternumber);
        rowValue.put(KEY_MATERNITY_PRIVACY, materprivacy);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{id + ""});
        closeDB();
        return ret;

    }

    public HealthNewDBModelHospital getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <HealthNewDBModelHospital> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public HealthNewDBModelHospital getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_HEALTH_ID);
    }

    public ArrayList <HealthNewDBModelHospital> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_HEALTH_ID);
    }


    public ArrayList <HealthNewDBModelHospital> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public HealthNewDBModelHospital cursorToModel(Cursor cursor) {
        int _id = cursor.getInt(0);
        int _healthId = cursor.getInt(1);
        String _eavailable = cursor.getString(2);
        String _enumber= cursor.getString(3);
        String _ambavailable = cursor.getString(4);
        String _ambnumber = cursor.getString(5);
        String _mtravailable= cursor.getString(6);
        String _mtrnum = cursor.getString(7);
        String _mtrprivacy = cursor.getString(8);

        return new HealthNewDBModelHospital(_id, _healthId,_eavailable,_enumber,
                _ambavailable,_ambnumber,_mtravailable,_mtrnum,_mtrprivacy);
    }

    public void delete(String ward, String area){
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        database.execSQL(
                "DELETE * FROM " + TABLE_NAME +
                " WHERE " + KEY_HEALTH_ID + " IN (" +
                " SELECT " + HealthNewDBTableMain.KEY_IDENTIFIER_ID + " FROM " + HealthNewDBTableMain.getTableName() +
                " WHERE " + HealthNewDBTableMain.getKeyWard() + " = '" + ward + "' AND ( " +
                        HealthNewDBTableMain.getKeyArea() + " = '" + area + "' OR " + HealthNewDBTableMain.getKeyParentArea() + " = '" + area + "' ))");

        database.close();
    }


    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}