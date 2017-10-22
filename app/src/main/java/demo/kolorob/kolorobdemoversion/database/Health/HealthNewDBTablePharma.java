package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;


/**
 * Created by israt.jahan on 6/27/2016.
 */

public class HealthNewDBTablePharma extends BaseDBTable <HealthNewDBModelPharmacy> {

    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_PHARMA;
    private static final String KEY_HEALTH_ID = "health_id";
    private static final String KEY_DOC_AVAILABLE = "doctor_available";
    private static final String KEY_SPECIALITY = "speciality";
    private static final String KEY_VACCINE = "vaccine_available";


    public HealthNewDBTablePharma(Context context) {
        super(context);
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER , "
                + KEY_HEALTH_ID + " INTEGER , "
                + KEY_DOC_AVAILABLE + "  TEXT  , "
                + KEY_SPECIALITY + "  TEXT , "
                + KEY_VACCINE + "  TEXT , PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(HealthNewDBModelPharmacy pharmacy) {
        if (!isFieldExist(pharmacy.getId())) {
            return insertItem(
                    pharmacy.getId(), pharmacy.getHealthId(), pharmacy.getDocavailability(),
                    pharmacy.getSpeciality(), pharmacy.getVaccineavailability()

            );
        }
        else return updateItem(pharmacy);
    }


    public long updateItem(HealthNewDBModelPharmacy pharmacy) {
        return updateItem(
                pharmacy.getId(), pharmacy.getHealthId(), pharmacy.getDocavailability(),
                pharmacy.getSpeciality(), pharmacy.getVaccineavailability()
        );
    }

    public long insertItem(int id, int healthId, String docavail, String speciality,
                           String vaccine ) {
        if (isFieldExist(id)) {
            return updateItem(id, healthId, docavail, speciality, vaccine);

        }
        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_HEALTH_ID, healthId);
        rowValue.put(KEY_DOC_AVAILABLE, docavail);
        rowValue.put(KEY_SPECIALITY, speciality);
        rowValue.put(KEY_VACCINE, vaccine);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int id, int healthId, String docavail, String speciality,
                            String vaccine) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_HEALTH_ID, healthId);
        rowValue.put(KEY_DOC_AVAILABLE, docavail);
        rowValue.put(KEY_SPECIALITY, speciality);
        rowValue.put(KEY_VACCINE, vaccine);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{id + ""});
        closeDB();
        return ret;

    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public HealthNewDBModelPharmacy getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <HealthNewDBModelPharmacy> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public HealthNewDBModelPharmacy getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_HEALTH_ID);
    }

    public ArrayList <HealthNewDBModelPharmacy> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_HEALTH_ID);
    }


    public ArrayList <HealthNewDBModelPharmacy> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public HealthNewDBModelPharmacy cursorToModel(Cursor cursor) {
        int _id = cursor.getInt(0);
        int _healthId = cursor.getInt(1);
        String _davailable = cursor.getString(2);
        String _speciality = cursor.getString(3);
        String _vaccineavailable = cursor.getString(4);

        return new HealthNewDBModelPharmacy(_id, _healthId, _davailable,
                _speciality,_vaccineavailable);
    }


    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void delete(String ward, String area){
        super.delete(ward, area, TABLE_NAME, KEY_HEALTH_ID, HealthNewDBTableMain.getTableName(), HealthNewDBTableMain.KEY_IDENTIFIER_ID);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
