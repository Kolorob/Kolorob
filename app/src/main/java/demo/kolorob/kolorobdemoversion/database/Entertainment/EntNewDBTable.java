package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2017.
 */



public class EntNewDBTable {
    private static final String TAG = EntNewDBTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_PROVIDER_TABLE_DBNEW;
    private static final String KEY_IDENTIFIER_ID = "_entid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";

    private static final String KEY_SERVICE_TYPEENT = "_entType";
    private static final String KEY_ENTRY_FEE = "_entryFee"; // 1 - text


    private Context tContext;

    public EntNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + " INTEGER , "
                + KEY_ENTRY_FEE + " TEXT , "
                + KEY_SERVICE_TYPEENT + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentNewDBModel entertainmentNewDBModel) {
        if (!isFieldExist(entertainmentNewDBModel.getEntid())) {

            return insertItem(entertainmentNewDBModel.getEntid(), entertainmentNewDBModel.getCommonModel().getId(),
                    entertainmentNewDBModel.getEntType(), entertainmentNewDBModel.getEntryFee()
            );
        }
        else {
            return updateItem(entertainmentNewDBModel.getEntid(), entertainmentNewDBModel.getCommonModel().getId(),
                    entertainmentNewDBModel.getEntType(), entertainmentNewDBModel.getEntryFee()
            );
        }
    }


    public long insertItem(int entId, int commonId, String entType, String entryFee
    ) {
        if (isFieldExist(entId)) {
            return updateItem(entId, commonId, entType, entryFee);
        }


        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, entId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_SERVICE_TYPEENT, entType);
        rowValue.put(KEY_ENTRY_FEE, entryFee);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return insertedId;
    }

    private long updateItem(int entId, int commonId, String entType, String entryFee) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, entId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_SERVICE_TYPEENT, entType);
        rowValue.put(KEY_ENTRY_FEE, entryFee);


        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{entId + ""});
        closeDB();
        return updatedId;
    }

    public void delete(String ward, String area, CommonModel commonModel)
    {
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        commonDBTable.delete(ward, area);
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_COMMON_ID + " = " + commonModel.getId(), null);

        database.close();
    }


    public boolean isFieldExist(int id) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id == cursor.getInt(0)) {
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


    public ArrayList <EntertainmentNewDBModel> getAllEntertainmentByCommonId(int commonId) {

        ArrayList <EntertainmentNewDBModel> entertainmentList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + commonId, null);

        if (cursor.moveToFirst()) {
            do {

                entertainmentList.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return entertainmentList;
    }



    public EntertainmentNewDBModel getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        EntertainmentNewDBModel entertainmentNewDBModel = null;
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel commonModel = commonDBTable.getNodeInfo(node);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                entertainmentNewDBModel = new EntertainmentNewDBModel(cursor.getInt(0), commonModel, cursor.getString(2), cursor.getString(3));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return entertainmentNewDBModel;
    }





    private EntertainmentNewDBModel cursorToSubCatList(Cursor cursor) {

        int _entId = cursor.getInt(0);
        int _commonId = cursor. getInt(1);
        String _entType = cursor.getString(2);
        String _entryFee = cursor.getString(3);

        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel _commonModel = commonDBTable.getNodeInfo(_commonId);

        return new EntertainmentNewDBModel(_entId, _commonModel, _entType, _entryFee);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
