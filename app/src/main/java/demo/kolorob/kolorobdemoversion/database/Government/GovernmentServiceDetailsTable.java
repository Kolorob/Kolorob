package demo.kolorob.kolorobdemoversion.database.Government;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentServiceDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class GovernmentServiceDetailsTable {
    private static final String TAG = GovernmentServiceDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.GOVERNMENT_SERVICE_DETAILS;
    private static final String KEY_NODE_ID = "_finId";
    private static final String KEY_SERVICE_ID = "_sproviderid";
    private static final String KEY_SERVICE_COST = "_servicecost"; // 1 - text
    private static final String KEY_SERVICE_PERSON = "_person"; //
    private static final String KEY_SERVICE_DETAIL = "_detail"; //
    private static final String KEY_SERVICE_TYPE = "_servicetype"; // 0 -integer

    private static final String KEY_SERVICE_SUBTYPE = "_servicesubtype"; // 2 - text*/
    private Context tContext;

    public GovernmentServiceDetailsTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_SERVICE_ID + " INTEGER , "
                + KEY_SERVICE_COST + "  TEXT  , " // 0 - int "
                + KEY_SERVICE_PERSON + " TEXT , "
                + KEY_SERVICE_DETAIL + " TEXT , "
                + KEY_SERVICE_TYPE   + " TEXT , "
                + KEY_SERVICE_SUBTYPE + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(GovernmentServiceDetailsItem governmentServiceDetailsItem) {
        return insertItem(
                governmentServiceDetailsItem.getFinId(),governmentServiceDetailsItem.getServiceproviderId(),
                governmentServiceDetailsItem.getServicecost(),
                governmentServiceDetailsItem.getResponsibleperson(),
                governmentServiceDetailsItem.getDetailstep(),
                governmentServiceDetailsItem.getServicetype(),
                governmentServiceDetailsItem.getServicesubtype()
        );
    }
    public long insertItem(int finId, int serviceproviderId, String servicecost, String responsibleperson,
                           String detailstep, String servicetype, String servicesubtype) {
        if (isFieldExist(finId)) {
            return updateItem(
                    finId,serviceproviderId,
                    servicecost,
                    responsibleperson,detailstep,
                    servicetype,
                    servicesubtype);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , finId);
        rowValue.put(KEY_SERVICE_ID , serviceproviderId);
        rowValue.put(KEY_SERVICE_COST, servicecost);
        rowValue.put(KEY_SERVICE_PERSON, responsibleperson);
        rowValue.put(KEY_SERVICE_DETAIL, detailstep);
        rowValue.put(KEY_SERVICE_TYPE, servicetype);
        rowValue.put(KEY_SERVICE_SUBTYPE, servicesubtype);



        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int finId,int serviceproviderId, String servicecost, String responsibleperson, String detailstep, String servicetype, String servicesubtype) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , finId);
        rowValue.put(KEY_SERVICE_ID , serviceproviderId);
        rowValue.put(KEY_SERVICE_COST, servicecost);
        rowValue.put(KEY_SERVICE_PERSON, responsibleperson);
        rowValue.put(KEY_SERVICE_DETAIL, detailstep);
        rowValue.put(KEY_SERVICE_TYPE, servicetype);
        rowValue.put(KEY_SERVICE_SUBTYPE, servicesubtype);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{finId + ""});
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
    public ArrayList<GovernmentServiceDetailsItem> getgovinfo(int node_id) {
        ArrayList<GovernmentServiceDetailsItem> subCatList = new ArrayList<>();
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
    private GovernmentServiceDetailsItem cursorToSubCatList(Cursor cursor) {
        int _finId = cursor.getInt(0);
        int _sproviderId = cursor.getInt(1);
        String _servicecost= cursor.getString(2);
        String _person = cursor.getString(3);
        String _detail = cursor.getString(4);
        String _servicetype = cursor.getString(5);
        String _servicesubtype = cursor.getString(6);



        return new GovernmentServiceDetailsItem(_finId,_sproviderId,
                _servicecost,_person,_detail,_servicetype,_servicesubtype);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
