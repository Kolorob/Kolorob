package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccineItemDetails;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccinesItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by arafat 12 July 2016
 */
public class HealthVaccineTableDetails {

    private static final String TAG = HealthVaccinesTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_VACCINES_TABLE_DETAILS;

    private static final String KEY_NODE_ID = "_nodeId"; // 0 -integer
    private static final String KEY_VACCINE_NAME = "_vaccinename"; // 1 - text
    private static final String KEY_VACCINE_FEE = "_vaccinefee"; // 2 - text*/
    private static final String KEY_VACCINE_REMARKS = "_vaccineremarks";
    private static final String KEY_FOREIGN_KEY = "_refNum"; //




    private Context tContext;


    public HealthVaccineTableDetails(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + "  INTEGER , " // 0 - int
                + KEY_VACCINE_NAME + "  TEXT, "              // 1 - text
                + KEY_VACCINE_FEE + " TEXT, "
                + KEY_VACCINE_REMARKS + " TEXT, "// 2 - text
                + KEY_FOREIGN_KEY + " INTEGER, PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public long insertItemHealth(HealthVaccineItemDetails healthVaccineItemDetails ) {
        return insertItemHealth(
                healthVaccineItemDetails.getNodeId(),
                healthVaccineItemDetails.getVaccinename(),
                healthVaccineItemDetails.getVaccinefee(),
                healthVaccineItemDetails.getVaccineremarks(),
                healthVaccineItemDetails.getForeign_key()
        );
    }

    public long insertItemHealth(String nodeId,
                                 String vaccinename,
                                 String vaccinefee,
                                 String vaccineremarks,
                                 int refNum) {
        if (isFieldExist(nodeId)) {
            return updateItem(
                    nodeId,
                    vaccinename,
                    vaccinefee,
                    vaccineremarks,
                    refNum
            );
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_VACCINE_NAME ,  vaccinename);
        rowValue.put(KEY_VACCINE_FEE , vaccinefee);
        rowValue.put(KEY_VACCINE_REMARKS,  vaccineremarks );
        rowValue.put(KEY_FOREIGN_KEY   , refNum);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        //Log.d("saveInVaccine","==="+ret);
        closeDB();
        return ret;
    }

    public boolean isFieldExist(String id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))) {
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

    private long updateItem(String nodeId,
                            String vaccinename,
                            String vaccinefee,
                            String vaccineremarks,
                            int refNum) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_VACCINE_NAME ,  vaccinename);
        rowValue.put(KEY_VACCINE_FEE , vaccinefee);
        rowValue.put(KEY_VACCINE_REMARKS,  vaccineremarks );
        rowValue.put(KEY_FOREIGN_KEY   , refNum);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?",
                new String[]{nodeId + ""});


        closeDB();
        return ret;
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

    public ArrayList<HealthVaccineItemDetails> getHealthVaccineData(String node_id) {
        ArrayList<HealthVaccineItemDetails> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_FOREIGN_KEY +" = "+node_id, null);

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
//    public ArrayList<HealthVaccinesItem> getVaccinesforNode(String nodeId) {
//        ArrayList<HealthVaccinesItem> vaccineList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+nodeId+"'" , null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                vaccineList.add(cursorToSubCatList(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return vaccineList;
//    }

    private HealthVaccineItemDetails cursorToSubCatList(Cursor cursor) {
        String _nodeId=cursor.getString(0);
        String _vaccinename=cursor.getString(1);
        String _vaccinefee=cursor.getString(2);
        String _vaccineremarks=cursor.getString(3);
        int _refNum=cursor.getInt(4);



        return new HealthVaccineItemDetails(
                _nodeId,
                _vaccinename,
                _vaccinefee,
                _vaccineremarks,
                _refNum
        );
    }
}
