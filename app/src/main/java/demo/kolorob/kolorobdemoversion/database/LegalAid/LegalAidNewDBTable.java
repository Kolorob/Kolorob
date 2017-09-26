package demo.kolorob.kolorobdemoversion.database.LegalAid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;

/**
 * Created by israt.jahan on 2/9/2017.
 */


public class LegalAidNewDBTable extends BaseDBTable <LegalAidNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.LEGALAIDNEWDBTABLE;
    private static final String KEY_IDENTIFIER_ID = "_legid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";
    private static final String KEY_SERVICE_TYPE = "_servicetype"; // 1 - text

    public LegalAidNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + "  TEXT, "              // 1 - text
                + KEY_SERVICE_TYPE + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(LegalAidNewDBModel legalAidNewDBModel) {
        if (!isFieldExist(legalAidNewDBModel.getLegalId())) {
            return insertItem(legalAidNewDBModel.getLegalId(), legalAidNewDBModel.getCommonModel().getId(), legalAidNewDBModel.getService());
        }
        else {
            return updateItem(legalAidNewDBModel.getLegalId(), legalAidNewDBModel.getCommonModel().getId(), legalAidNewDBModel.getService());
        }
    }


    public long insertItem(int legalId, int commonId, String serviceType) {
        if (isFieldExist(legalId)) {
            return updateItem(legalId, commonId, serviceType);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, legalId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_SERVICE_TYPE, serviceType);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return insertedId;
    }

    private long updateItem(int legalId, int commonId, String serviceType) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, legalId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_SERVICE_TYPE, serviceType);

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{legalId + ""});
        closeDB();
        return updatedId;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }


    public LegalAidNewDBModel cursorToModel(Cursor cursor) {
        int _legalId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);
        String _serviceType = cursor.getString(2);

        CommonModel _commonModel = getCommonModelFromId(_commonId);
        return new LegalAidNewDBModel(_legalId, _commonModel, _serviceType);

    }

    public LegalAidNewDBModel getNodeInfo(int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        SQLiteDatabase db = openDB();
        LegalAidNewDBModel legalAidNewDBModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                legalAidNewDBModel = new LegalAidNewDBModel(cursor.getInt(0), commonModel, cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return legalAidNewDBModel;
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <LegalAidNewDBModel> getDetailsByCommonId(int commonId) {
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }


}
