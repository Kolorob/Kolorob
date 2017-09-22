package demo.kolorob.kolorobdemoversion.database.Government;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2017.
 */

public class GovNewDBTable {
    private static final String TAG = GovNewDBTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.GOV_NEW;
    private static final String KEY_IDENTIFIER_ID = "_govid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";
    private static final String KEY_SERVICE_NAME = "_service_name"; // 1 - text

    private Context tContext;

    public GovNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + "  INTEGER, "              // 1 - text
                + KEY_SERVICE_NAME + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(GovernmentNewDBModel governmentNewDBModel) {
        if (!isFieldExist(governmentNewDBModel.getGovId())) {
            return insertItem(governmentNewDBModel.getGovId(), governmentNewDBModel.getCommonModel().getId(), governmentNewDBModel.getServiceName());
        }
        else {
            return updateItem(governmentNewDBModel.getGovId(), governmentNewDBModel.getCommonModel().getId(), governmentNewDBModel.getServiceName());
        }
    }


    public long insertItem(int govId, int commonId, String serviceName) {
        if (isFieldExist(govId)) {
            return updateItem(govId, commonId, serviceName);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, govId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_SERVICE_NAME, serviceName);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return insertedId;
    }

    private long updateItem(int govId, int commonId, String serviceName) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, govId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_SERVICE_NAME, serviceName);

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{govId + ""});
        closeDB();
        return updatedId;
    }


    public boolean isFieldExist(int id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
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



    public GovernmentNewDBModel getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        GovernmentNewDBModel governmentNewDBModel = null;
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel commonModel = commonDBTable.getNodeInfo(node);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                governmentNewDBModel = new GovernmentNewDBModel(cursor.getInt(0), commonModel, cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return governmentNewDBModel;
    }

    private GovernmentNewDBModel cursorToSubCatList(Cursor cursor) {

        int _govId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);
        String _serviceName = cursor.getString(2);

        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel _commonModel = commonDBTable.getNodeInfo(_commonId);


        return new GovernmentNewDBModel(_govId, _commonModel, _serviceName);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }


    public ArrayList <GovernmentNewDBModel> getGovByCommonId(int commonId) {

        ArrayList <GovernmentNewDBModel> govList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + commonId, null);

        if (cursor.moveToFirst()) {
            do {

                govList.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return govList;
    }

    public void delete(String ward,String area, CommonModel commonModel)
    {
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        commonDBTable.delete(ward, area);
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_COMMON_ID + " = " + commonModel.getId(), null);

        database.close();
    }
}
