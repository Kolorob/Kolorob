package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CityCorporation;


/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class CityCorporationTable {

    private static final String TAG = CityCorporationTable.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.CITYCORPORATIONS;

    private static final String KEY_ID = "id"; // 0 -integer
    private static final String KEY_CC_EN = "cc_en";
    private static final String KEY_CC_BN = "cc_bn";
    private static final String KEY_CC_KEYWORD = "cc_keyword";



    private Context tContext;

    public CityCorporationTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_CC_EN + " TEXT, "              // 1 - text
                + KEY_CC_BN + " TEXT, "
                + KEY_CC_KEYWORD + " TEXT "
                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(CityCorporation cityCorporation){
        return insertItem(
                cityCorporation.getId(),
                cityCorporation.getCityCorporation_name(),
                cityCorporation.getCityCorporation_bn(),
                cityCorporation.getCityCorporation_keyword()
        );
    }

    public long insertItem(int id, String cc_name, String cc_bn, String cc_keyword) {
        if (isFieldExist(id)) {
            return updateItem(id, cc_name, cc_bn, cc_keyword);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_CC_EN, cc_name);
        rowValue.put(KEY_CC_BN, cc_bn);
        rowValue.put(KEY_CC_KEYWORD, cc_keyword);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }
    public ArrayList<String> getAllCCNameBN() {
        ArrayList<String> cc_bn_list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String cc_bn = cursor.getString(2);
                cc_bn_list.add(cc_bn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return cc_bn_list;
    }

    public boolean isFieldExist(int id) {
        // Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0)) == id) {
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

    private long updateItem(int id, String cc_en, String cc_bn, String cc_keyword) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_CC_EN, cc_en);
        rowValue.put(KEY_CC_BN, cc_bn);
        rowValue.put(KEY_CC_KEYWORD, cc_keyword);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList<CityCorporation> getAllCCs() {
        ArrayList<CityCorporation> ccList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {

                ccList.add(cursorToCC(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ccList;
    }

    private CityCorporation cursorToCC(Cursor cursor) {
        int id = cursor.getInt(0);
        String cc_en = cursor.getString(1);
        String cc_bn = cursor.getString(2);
        String cc_keyword = cursor.getString(3);

        return new CityCorporation(id, cc_en, cc_bn, cc_keyword);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        //Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
