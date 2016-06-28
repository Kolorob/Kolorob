package demo.kolorob.kolorobdemoversion.database.Financial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class FinancialServiceNewTable {
    private static final String TAG = FinancialServiceDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_NEW;
    private static final String KEY_FIN_NODE_ID = "_finId";
    private static final String KEY_FIN_NAME_EN = "_nameen"; // 1 - text
    private static final String KEY_FIN_NAME_BN = "_namebn"; //

    private Context tContext;

    public FinancialServiceNewTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FIN_NODE_ID + " INTEGER , "
                + KEY_FIN_NAME_EN + "  TEXT  , " // 0 - int "
                + KEY_FIN_NAME_BN + " TEXT,   PRIMARY KEY(" + KEY_FIN_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(FinancialNewItem financialNewItem) {
        return insertItem(
                financialNewItem.getFinId(),
                financialNewItem.getNameen(),
                financialNewItem.getNamebn()

        );
    }
    private long insertItem(int finId, String nameen, String namebn) {
        if (isFieldExist(finId)) {
            return updateItem(
                    finId,
                    nameen,
                    namebn);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID , finId);
        rowValue.put(KEY_FIN_NAME_EN, nameen);
        rowValue.put(KEY_FIN_NAME_BN, namebn);




        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int finId, String nameen, String namebn) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID , finId);
        rowValue.put(KEY_FIN_NAME_EN, nameen);
        rowValue.put(KEY_FIN_NAME_BN, namebn);



        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_FIN_NODE_ID + " = ?  ",
                new String[]{finId + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(int nodeid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0)==nodeid) {
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
    private FinancialNewItem cursorToSubCatList(Cursor cursor) {
        int _finId = cursor.getInt(0);
        String _nameen= cursor.getString(2);
        String _namebn = cursor.getString(1);




        return new FinancialNewItem(_finId,
                _nameen,_namebn);


    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
