package demo.kolorob.kolorobdemoversion.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.Ward;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class WardTable {

    private static final String TAG = WardTable.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.WARDS;

    private static final String KEY_ID = "id"; // 0 -integer
    private static final String KEY_WARD_EN = "ward_en";
    private static final String KEY_WARD_BN = "ward_bn";
    private static final String KEY_WARD_KEYWORD = "ward_keyword";
    private static final String KEY_WARD_CCID = "cc_id";



    private Context tContext;

    public WardTable(Context context) {
        tContext = context;
        createTable();
    }

    public WardTable() {

    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_WARD_EN + " TEXT, "              // 1 - text
                + KEY_WARD_BN + " TEXT, "
                + KEY_WARD_KEYWORD + " TEXT, "
                + KEY_WARD_CCID + " INTEGER "
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

    public long insertItem(Ward ward){
        return insertItem(
                ward.getId(),
                ward.getWard_name(),
                ward.getWard_bn(),
                ward.getWard_keyword(),
                ward.getCc_id()
        );
    }

    public long insertItem(int id, String ward_name, String ward_bn, String ward_keyword, int cc_id) {
        if (isFieldExist(id)) {
            return updateItem(id, ward_name, ward_bn, ward_keyword, cc_id);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_WARD_EN, ward_name);
        rowValue.put(KEY_WARD_BN, ward_bn);
        rowValue.put(KEY_WARD_KEYWORD, ward_keyword);
        rowValue.put(KEY_WARD_CCID, cc_id);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }
    public ArrayList<String> getAllWardNameBN() {
        ArrayList<String> ward_bn_list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String ward_bn = cursor.getString(2);
                ward_bn_list.add(ward_bn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ward_bn_list;
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

    private long updateItem(int id, String ward_en, String ward_bn, String ward_keyword, int cc_id) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_WARD_EN, ward_en);
        rowValue.put(KEY_WARD_BN, ward_bn);
        rowValue.put(KEY_WARD_KEYWORD, ward_keyword);
        rowValue.put(KEY_WARD_CCID, cc_id);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList<Ward> getAllWards(int cc_id) {
        ArrayList<Ward> wardList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE cc_id = " + cc_id, null);

        if (cursor.moveToFirst()) {
            do {

                wardList.add(cursorToWard(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return wardList;
    }

    private Ward cursorToWard(Cursor cursor) {
        int id = cursor.getInt(0);
        String ward_en = cursor.getString(1);
        String ward_bn = cursor.getString(2);
        String ward_keyword = cursor.getString(3);
        int cc_id = cursor.getInt(4);
        return new Ward(id, ward_en, ward_bn, ward_keyword, cc_id);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        //Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
