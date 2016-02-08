package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by mity on 2/4/16.
 */
public class HealthSpecialistTable {private static final String TAG = HealthServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_SPECIALIST_TABLE;

    private static final String KEY_NODE_ID = "_nodeId"; // 0 -integer
    private static final String KEY_SPECIALIST_ID = "_specialistId"; // 1 - text
    private static final String KEY_SPECIALIST_TYPE= "_specialisttype"; // 2 - text*/
    private static final String KEY_SPECIALIST_FEES = "_specialistfees";

    private static final String KEY_SPECIALIST_REMARKS= " _specialistremarks";
    private static final String KEY_REF_NUM = "_refNum"; //




    private Context tContext;


    public HealthSpecialistTable(Context context) {
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
                + KEY_REF_NUM + " INTEGER, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_SPECIALIST_ID + "," + KEY_REF_NUM + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public long insertItemHealth(HealthSpecialistItem healthSpecialistItem  ) {
        return insertItemHealth(
               healthSpecialistItem.getNodeId(),
                healthSpecialistItem.getSpecialistId(),
                healthSpecialistItem.getSpecialisttype(),
                healthSpecialistItem.getSpecialistfees(),
                healthSpecialistItem.getSpecialistremarks(),
                healthSpecialistItem.getRefNum()
        );
    }

    public long insertItemHealth(String nodeId, String specialistId, String specialisttype,String specialistfees, String specialistremarks, int refNum) {
        if (isFieldExist(nodeId,specialistId, refNum)) {
            return updateItem(
                    nodeId,
                    specialistId,
                    specialisttype,
                    specialistfees,
                    specialistremarks,
                    refNum
            );
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SPECIALIST_ID,  specialistId);
        rowValue.put(KEY_SPECIALIST_TYPE,  specialisttype );
        rowValue.put(KEY_SPECIALIST_FEES , specialistfees);
        rowValue.put(KEY_SPECIALIST_REMARKS,  specialistremarks );
        rowValue.put(KEY_REF_NUM   , refNum);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
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

    private long updateItem(String nodeId, String specialistId, String specialisttype,String specialistfees, String specialistremarks, int refNum) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SPECIALIST_ID,  specialistId);
        rowValue.put(KEY_SPECIALIST_TYPE,  specialisttype );
        rowValue.put(KEY_SPECIALIST_FEES , specialistfees);
        rowValue.put(KEY_SPECIALIST_REMARKS,  specialistremarks );
        rowValue.put(KEY_REF_NUM   , refNum);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ? AND"+ KEY_SPECIALIST_ID+ " = ? AND "+KEY_REF_NUM + " = ?",
                new String[]{nodeId + "",specialistId + "",refNum+""});
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
    public ArrayList<HealthSpecialistItem> getSpecialistforNode(String nodeId) {
        ArrayList<HealthSpecialistItem> specialistList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+nodeId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                specialistList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return specialistList;
    }

    private HealthSpecialistItem cursorToSubCatList(Cursor cursor) {
        String _nodeId=cursor.getString(0);
        String _specialistId=cursor.getString(1);
        String  _specialisttype=cursor.getString(2);
        String _specialistfees=cursor.getString(3);

        String _specialistremarks=cursor.getString(4);
        int _refNum=cursor.getInt(5);



        return new HealthSpecialistItem(
                _nodeId,
                _specialistId,
                _specialisttype,
                _specialistfees,
                _specialistremarks,
                _refNum
        );
    }
}