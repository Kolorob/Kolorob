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
                + KEY_DOC_AVAILABLE + "  TEXT  , "
                + KEY_SPECIALITY + "  TEXT , "
                + KEY_VACCINE + "  TEXT , PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(HealthNewDBModelPharmacy healthNewDBModelPharmacy) {
        if (!isFieldExist(healthNewDBModelPharmacy.getHealthId())) {
            return insertItem(
                    healthNewDBModelPharmacy.getHealthId(), healthNewDBModelPharmacy.getDocavailability(),
                    healthNewDBModelPharmacy.getSpeciality(), healthNewDBModelPharmacy.getVaccineavailability()

            );
        }
        else return updateItem(healthNewDBModelPharmacy);
    }


    public long updateItem(HealthNewDBModelPharmacy healthNewDBModelPharmacy) {
        return updateItem(
                healthNewDBModelPharmacy.getHealthId(), healthNewDBModelPharmacy.getDocavailability(),
                healthNewDBModelPharmacy.getSpeciality(), healthNewDBModelPharmacy.getVaccineavailability()
        );
    }

    public long insertItem(int serviceproviderId, String docavail, String speciality,
                           String vaccine ) {
        if (isFieldExist(serviceproviderId)) {
            return updateItem(
                   serviceproviderId,
                    docavail,
                    speciality,
                    vaccine);

        }
        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, serviceproviderId);
        rowValue.put(KEY_DOC_AVAILABLE, docavail);
        rowValue.put(KEY_SPECIALITY, speciality);
        rowValue.put(KEY_VACCINE, vaccine);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int serviceproviderId, String docavail, String speciality,
                            String vaccine) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, serviceproviderId);
        rowValue.put(KEY_DOC_AVAILABLE, docavail);
        rowValue.put(KEY_SPECIALITY, speciality);
        rowValue.put(KEY_VACCINE, vaccine);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{serviceproviderId + ""});
        closeDB();
        return ret;

    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public ArrayList <HealthNewDBModelPharmacy> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME);
    }


    public ArrayList <HealthNewDBModelPharmacy> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public HealthNewDBModelPharmacy cursorToModel(Cursor cursor) {
        int _servicecenterid = cursor.getInt(0);
        String _davailable = cursor.getString(1);
        String _speciality = cursor.getString(2);
        String _vaccineavailable = cursor.getString(3);

        return new HealthNewDBModelPharmacy(_servicecenterid,_davailable,
                _speciality,_vaccineavailable);
    }


    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
