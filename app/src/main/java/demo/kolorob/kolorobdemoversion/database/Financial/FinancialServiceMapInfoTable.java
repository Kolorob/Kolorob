package demo.kolorob.kolorobdemoversion.database.Financial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialMapInfoItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class FinancialServiceMapInfoTable {
    private static final String TAG = FinancialServiceDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_DETAILS;
    private static final String KEY_FIN_NODE_ID = "_finId";
    private static final String KEY_FIN_SERVICE_LAT = "_lat"; // 1 - text
    private static final String KEY_FIN_SERVICE_LON = "_lon"; //

    private Context tContext;

    public FinancialServiceMapInfoTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FIN_NODE_ID + " TEXT , "
                + KEY_FIN_SERVICE_LAT + "  TEXT  , " // 0 - int "
                + KEY_FIN_SERVICE_LON + " TEXT,   PRIMARY KEY(" + KEY_FIN_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(FinancialMapInfoItem financialMapInfoItem) {
        return insertItem(
                financialMapInfoItem.getFinId(),
                financialMapInfoItem.getLat(),
                financialMapInfoItem.getLon()

        );
    }
    private long insertItem(String finId, String lat, String lon) {
        if (isFieldExist(finId)) {
            return updateItem(
                    finId,
                    lat,
                    lon);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID , finId);
        rowValue.put(KEY_FIN_SERVICE_LAT, lat);
        rowValue.put(KEY_FIN_SERVICE_LON, lon);




        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(String finId, String lat, String lon) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID , finId);
        rowValue.put(KEY_FIN_SERVICE_LAT, lat);
        rowValue.put(KEY_FIN_SERVICE_LON, lon);



        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_FIN_NODE_ID + " = ?  ",
                new String[]{finId + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(String nodeid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (nodeid.equals(cursor.getString(0) )) {
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
    private FinancialMapInfoItem cursorToSubCatList(Cursor cursor) {
        String _finId = cursor.getString(0);
        String _lat= cursor.getString(1);
        String _lon = cursor.getString(2);




        return new FinancialMapInfoItem(_finId,
                _lat,_lon);


    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
