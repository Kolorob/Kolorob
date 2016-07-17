package demo.kolorob.kolorobdemoversion.database.Health;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItemDetails;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by arafat 12 july 2016.
 */

public class HealthSpecialistTableDetails {
    private static final String TAG = HealthSpecialistTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_SPECIALIST_TABLE;

    private static final String KEY_NODE_ID = "_nodeId"; // 0 -integer
    private static final String KEY_SPECIALIST_ID = "_specialistId"; // 1 - text
    private static final String KEY_SPECIALIST_TYPE= "_specialisttype"; // 2 - text*/
    private static final String KEY_SPECIALIST_FEES = "_specialistfees";

    private static final String KEY_SPECIALIST_REMARKS= " _specialistremarks";
    private static final String KEY_REF_NUM = "_refNum"; //
    private static final String KEY_WEEK_FEE= "_week_fee"; // 2 - text*/
    private static final String KEY_MONTH_FEE = "_month_fee";

    private static final String KEY_REPORT_FEE= " _report_fee";
    private static final String KEY_OTHER_FEE = "_other_fee"; //




    private Context tContext;


    public HealthSpecialistTableDetails(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + "  INTEGER , " // 0 - int
                + KEY_SPECIALIST_ID  + "  TEXT, "              // 1 - text
                + KEY_SPECIALIST_TYPE + " TEXT, "
                + KEY_SPECIALIST_FEES + " TEXT, "
                + KEY_SPECIALIST_REMARKS + " TEXT, "// 2 - text
                + KEY_REF_NUM  + "  TEXT, "              // 1 - text
                + KEY_WEEK_FEE + " TEXT, "
                + KEY_MONTH_FEE + " TEXT, "
                + KEY_REPORT_FEE + " TEXT, "// 2 - text
                + KEY_OTHER_FEE + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public long insertItemHealth(HealthSpecialistItemDetails healthSpecialistItemDetails  ) {
        return insertItemHealth(
                healthSpecialistItemDetails.getNodeId(),
                healthSpecialistItemDetails.getSpecialistId(),
                healthSpecialistItemDetails.getSpecialisttype(),
                healthSpecialistItemDetails.getSpecialistfees(),
                healthSpecialistItemDetails.getSpecialistremarks(),
                healthSpecialistItemDetails.getRefNum(),
                healthSpecialistItemDetails.getWeek_fee(),
                healthSpecialistItemDetails.getMonth_fee(),
                healthSpecialistItemDetails.getReport_fee(),
                healthSpecialistItemDetails.getOther_fee()
                );
    }

    public long insertItemHealth(String nodeId, String specialistId, String specialisttype,String specialistfees, String specialistremarks, int refNum,String week_fee,
                                 String month_fee,
                                 String report_fee,
                                 String other_fee) {
        if (isFieldExist(nodeId,specialistId, refNum)) {
            return updateItem(
                    nodeId,
                    specialistId,
                    specialisttype,
                    specialistfees,
                    specialistremarks,
                    refNum,
                    week_fee,
                    month_fee,
                    report_fee,
                    other_fee
            );
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SPECIALIST_ID,  specialistId);
        rowValue.put(KEY_SPECIALIST_TYPE,  specialisttype );
        rowValue.put(KEY_SPECIALIST_FEES , specialistfees);
        rowValue.put(KEY_SPECIALIST_REMARKS,  specialistremarks );
        rowValue.put(KEY_REF_NUM   , refNum);
        rowValue.put(KEY_WEEK_FEE,  week_fee );
        rowValue.put(KEY_MONTH_FEE , month_fee);
        rowValue.put(KEY_REPORT_FEE,  report_fee );
        rowValue.put(KEY_OTHER_FEE   , other_fee);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
      //  Log.d("health_specialist_d","==="+ret);
        closeDB();
        return ret;

    }

    public boolean isFieldExist(String id,String specialistId,int sub_cat_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&&specialistId.equals(cursor.getString(1))&&Integer.parseInt(cursor.getString(5))==sub_cat_id) {
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

    private long updateItem(String nodeId, String specialistId, String specialisttype,String specialistfees, String specialistremarks, int refNum,String week_fee,
                            String month_fee,
                            String report_fee,
                            String other_fee) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SPECIALIST_ID,  specialistId);
        rowValue.put(KEY_SPECIALIST_TYPE,  specialisttype );
        rowValue.put(KEY_SPECIALIST_FEES , specialistfees);
        rowValue.put(KEY_SPECIALIST_REMARKS,  specialistremarks );
        rowValue.put(KEY_REF_NUM   , refNum);
        rowValue.put(KEY_WEEK_FEE,  week_fee );
        rowValue.put(KEY_MONTH_FEE , month_fee);
        rowValue.put(KEY_REPORT_FEE,  report_fee );
        rowValue.put(KEY_OTHER_FEE   , other_fee);


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
    public ArrayList<HealthSpecialistItemDetails> getSpecialistforNode(String nodeId) {
        ArrayList<HealthSpecialistItemDetails> specialistList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+nodeId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                System.out.println("abc="+cursor.getString(4));
                specialistList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return specialistList;
    }

    public ArrayList<HealthSpecialistItemDetails> getHealthSpecialistData(String node_id) {
        ArrayList<HealthSpecialistItemDetails> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_REF_NUM +" = "+node_id, null);

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

    private HealthSpecialistItemDetails cursorToSubCatList(Cursor cursor) {
        String _nodeId=cursor.getString(0);
        String _specialistId=cursor.getString(1);
        String  _specialisttype=cursor.getString(2);
        String _specialistfees=cursor.getString(3);

        String _specialistremarks=cursor.getString(4);
        int _refNum=cursor.getInt(5);
        String week_fee=cursor.getString(6);
        String  month_fee=cursor.getString(7);
        String report_fee=cursor.getString(8);
        String other_fee=cursor.getString(9);



        return new HealthSpecialistItemDetails(
                _nodeId,
                _specialistId,
                _specialisttype,
                _specialistfees,
                _specialistremarks,
                _refNum,
                week_fee,
                month_fee,
                report_fee,
                other_fee
        );
    }
}