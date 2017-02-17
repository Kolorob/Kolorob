package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class HealthNewDBTablePharma {
    private static final String TAG = HealthNewDBTablePharma.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_PHARMA;
    private static final String KEY_SERVICE_ID = "_servicecenterid";
    private static final String KEY_DOC_AVAIL = "_davailable";
    private static final String KEY_SPECIALITY = "_speciality"; // 1 - text
    private static final String KEY_VACCINE= "_vaccineavailable"; //




    private Context tContext;

    public HealthNewDBTablePharma(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_SERVICE_ID + " INTEGER , "
                + KEY_DOC_AVAIL + "  TEXT  , " // 0 - int "
                + KEY_SPECIALITY + "  TEXT , "
                + KEY_VACCINE + "  TEXT , PRIMARY KEY(" + KEY_SERVICE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(HealthNewDBModelPharmacy healthNewDBModelPharmacy) {
        if (!isFieldExist(healthNewDBModelPharmacy.getServicecenterid())) {
            return insertItem(
                    healthNewDBModelPharmacy.getServicecenterid(), healthNewDBModelPharmacy.getDocavailability(),
                    healthNewDBModelPharmacy.getSpeciality(), healthNewDBModelPharmacy.getVaccineavailability()

            );
        }
        else {
            return updateItem(
                    healthNewDBModelPharmacy.getServicecenterid(), healthNewDBModelPharmacy.getDocavailability(),
                    healthNewDBModelPharmacy.getSpeciality(), healthNewDBModelPharmacy.getVaccineavailability()

            );
        }
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

        rowValue.put(KEY_SERVICE_ID, serviceproviderId);
        rowValue.put(KEY_DOC_AVAIL, docavail);
        rowValue.put(KEY_SPECIALITY, speciality);
        rowValue.put(KEY_VACCINE, vaccine);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int serviceproviderId, String docavail, String speciality,
                            String vaccine) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_SERVICE_ID, serviceproviderId);
        rowValue.put(KEY_DOC_AVAIL, docavail);
        rowValue.put(KEY_SPECIALITY, speciality);
        rowValue.put(KEY_VACCINE, vaccine);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_SERVICE_ID + " = ?  ",
                new String[]{serviceproviderId + ""});
        closeDB();
        return ret;

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

    public ArrayList<HealthNewDBModelPharmacy> getHealthSpecialistData(int node_id) {
        ArrayList<HealthNewDBModelPharmacy> subCatList = new ArrayList<>();
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

    private HealthNewDBModelPharmacy cursorToSubCatList(Cursor cursor) {
        int _servicecenterid = cursor.getInt(0);
        String _davailable = cursor.getString(1);
        String _speciality= cursor.getString(2);
        String _vaccineavailable = cursor.getString(3);






        return new HealthNewDBModelPharmacy(_servicecenterid,_davailable,
                _speciality,_vaccineavailable);
    }


    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
