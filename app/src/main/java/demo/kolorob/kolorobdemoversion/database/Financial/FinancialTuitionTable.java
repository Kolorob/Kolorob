package demo.kolorob.kolorobdemoversion.database.Financial;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTuitionItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/7/2016.
 */
public class FinancialTuitionTable {
    private static final String TAG = FinancialTuitionTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_TUITION;
    private static final String KEY_FIN_NODE_ID = "_nodeId";
    private static final String KEY_FIN_SERVICE_NAME = "_servicename"; // 0 -integer

    private static final String KEY_FIN_YN = "_yn"; // 2 - text*/
    private static final String KEY_FIN_SERVICE_COST = "_servicecost"; // 1 - text
    private static final String KEY_FIN_SERVICE_REMARK = "_serviceremark"; //
    private static final String KEY_KEY_FIN_REF_NUM= "_refNum"; //

    private Context tContext;

    public FinancialTuitionTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FIN_NODE_ID + " TEXT , "
                + KEY_FIN_SERVICE_NAME + "  TEXT  , " // 0 - int "
                + KEY_FIN_YN + " TEXT, "
                + KEY_FIN_SERVICE_COST + " TEXT, "
                + KEY_FIN_SERVICE_REMARK + " TEXT, "
                + KEY_KEY_FIN_REF_NUM + " TEXT,   PRIMARY KEY(" + KEY_FIN_NODE_ID + ", " + KEY_FIN_SERVICE_NAME + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(FinancialTuitionItem financialTuitionItem) {
        return insertItem(
                financialTuitionItem.getNodeId(),
                financialTuitionItem.getServicename(),
                financialTuitionItem.getYn(),
                financialTuitionItem.getServicecost(),
                financialTuitionItem.getServiceremark(),
                financialTuitionItem.getRefNum()
        );
    }

    private long insertItem(String nodeId, String servicename, String yn, String servicecost, String serviceremark, String refNum) {
        if (isFieldExist(nodeId,servicename)) {
            return updateItem(
                    nodeId,
                    servicename,
                    yn,
                    servicecost,
                    serviceremark,
                    refNum);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID , nodeId);
        rowValue.put(KEY_FIN_SERVICE_NAME, servicename);
        rowValue.put(KEY_FIN_YN, yn);
        rowValue.put(KEY_FIN_SERVICE_COST, servicecost);
        rowValue.put(KEY_FIN_SERVICE_REMARK, serviceremark);
        rowValue.put(KEY_KEY_FIN_REF_NUM, refNum);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(String nodeId, String servicename, String yn, String servicecost, String serviceremark, String refNum) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID , nodeId);
        rowValue.put(KEY_FIN_SERVICE_NAME, servicename);
        rowValue.put(KEY_FIN_YN, yn);
        rowValue.put(KEY_FIN_SERVICE_COST, servicecost);
        rowValue.put(KEY_FIN_SERVICE_REMARK, serviceremark);
        rowValue.put(KEY_KEY_FIN_REF_NUM, refNum);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_FIN_NODE_ID + " = ?  AND " + KEY_FIN_NODE_ID + " = ? ",
                new String[]{nodeId + "", servicename + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(String nodeid,String sname) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (nodeid.equals(cursor.getString(0) )&& sname.equals(cursor.getString(1))) {
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


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)


    public ArrayList<FinancialTuitionItem> getFinancialTuition(String idenId) {
        ArrayList<FinancialTuitionItem> TuitionList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_FIN_NODE_ID+" = '"+idenId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                TuitionList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return TuitionList;
    }



    private FinancialTuitionItem cursorToSubCatList(Cursor cursor) {
        String _nodeId = cursor.getString(0);
        String _servicename= cursor.getString(1);
        String _yn = cursor.getString(2);
        String _servicecost = cursor.getString(3);
        String _serviceremark = cursor.getString(4);
        String _refNum = cursor.getString(5);


        return new FinancialTuitionItem(_nodeId,
                _servicename, _yn, _servicecost,_serviceremark,_refNum);


    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
