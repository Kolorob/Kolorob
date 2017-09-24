package demo.kolorob.kolorobdemoversion.database.Religious;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;

/**
 * Created by zahid on 2/8/2017.
 */


public class ReligiousNewDBTable extends BaseDBTable <ReligiousNewDBModel>{

    private static final String TABLE_NAME = DatabaseHelper.RELIGIOUSNEWDBTABLE;

    private static final String KEY_IDENTIFIER_ID = "_religiousid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";

    private static final String KEY_RS_RELIGION = "_rs_religion";
    private static final String KEY_RS_SERVICES_FOR = "_rs_services_for";
    private static final String KEY_RS_SERVICES_FOR_RELIGION = "_rs_services_for_religion";
    private static final String KEY_OTHER_RELIGION = "_other_religion";
    private static final String KEY_RS_TIME = "_rs_time";
    private static final String KEY_RS_FEE = "_rs_fee";


    public ReligiousNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + " INTEGER , "
                + KEY_RS_RELIGION + " TEXT, "
                + KEY_RS_SERVICES_FOR + " TEXT, "
                + KEY_RS_SERVICES_FOR_RELIGION + " TEXT, "
                + KEY_OTHER_RELIGION + " TEXT, "
                + KEY_RS_TIME + " TEXT, "
                + KEY_RS_FEE + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);

        closeDB();
    }


    public long insertItem(ReligiousNewDBModel religiousNewDBModel) {
        if (!isFieldExist(religiousNewDBModel.getShelterId())) {
            return insertItem(religiousNewDBModel.getShelterId(), religiousNewDBModel.getCommonModel().getId(),
                    religiousNewDBModel.getRsReligion(), religiousNewDBModel.getRsServicesFor(), religiousNewDBModel.getRsServicesForReligion(),
                    religiousNewDBModel.getOtherReligion(), religiousNewDBModel.getRsTime(), religiousNewDBModel.getRsFee());
        }
        else {
            return updateItem(religiousNewDBModel.getShelterId(), religiousNewDBModel.getCommonModel().getId(),
                    religiousNewDBModel.getRsReligion(), religiousNewDBModel.getRsServicesFor(), religiousNewDBModel.getRsServicesForReligion(),
                    religiousNewDBModel.getOtherReligion(), religiousNewDBModel.getRsTime(), religiousNewDBModel.getRsFee());
        }
    }




    public long insertItem(int shelterId, int commonId, String rsReligion, String rsServicesFor, String rsServicesForReligion, String otherReligion, String rsTime, String rsFee) {
        if (isFieldExist(shelterId)) {
            return updateItem(shelterId, commonId, rsReligion, rsServicesFor, rsServicesForReligion, otherReligion, rsTime, rsFee);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, shelterId);
        rowValue.put(KEY_COMMON_ID, commonId);

        rowValue.put(KEY_RS_RELIGION, rsReligion);
        rowValue.put(KEY_RS_SERVICES_FOR, rsServicesFor);
        rowValue.put(KEY_RS_SERVICES_FOR_RELIGION, rsServicesForReligion);
        rowValue.put(KEY_OTHER_RELIGION, otherReligion);
        rowValue.put(KEY_RS_TIME, rsTime);
        rowValue.put(KEY_RS_FEE, rsFee);


        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return insertedId;
    }
    private long updateItem(int shelterId, int commonId, String rsReligion, String rsServicesFor, String rsServicesForReligion, String otherReligion, String rsTime, String rsFee) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, shelterId);
        rowValue.put(KEY_COMMON_ID, commonId);

        rowValue.put(KEY_RS_RELIGION, rsReligion);
        rowValue.put(KEY_RS_SERVICES_FOR, rsServicesFor);
        rowValue.put(KEY_RS_SERVICES_FOR_RELIGION, rsServicesForReligion);
        rowValue.put(KEY_OTHER_RELIGION, otherReligion);
        rowValue.put(KEY_RS_TIME, rsTime);
        rowValue.put(KEY_RS_FEE, rsFee);


        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{shelterId + ""});
        closeDB();
        return updatedId;
    }

    public ReligiousNewDBModel cursorToModel(Cursor cursor) {
        int _shelterId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);
        String _rsReligion = cursor.getString(2);
        String _rsServicesFor = cursor.getString(3);
        String _rsServicesForReligion = cursor.getString(4);
        String _otherReligion = cursor.getString(5);
        String _rsTime = cursor.getString(6);
        String _rsFee = cursor.getString(7);

        CommonModel _commonModel = getCommonModelFromId(_commonId);
        return new ReligiousNewDBModel(_shelterId, _commonModel, _rsReligion, _rsServicesFor, _rsServicesForReligion, _otherReligion, _rsTime, _rsFee);

    }

    public ReligiousNewDBModel getNodeInfo(int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        SQLiteDatabase db = openDB();
        ReligiousNewDBModel religiousNewDBModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                religiousNewDBModel = new ReligiousNewDBModel(cursor.getInt(0), commonModel, cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return religiousNewDBModel;
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }


    public ArrayList <ReligiousNewDBModel> getDetailsByCommonId(int commonId) {
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }


    public boolean isFieldExist(int id) {
       return super.isFieldExist(id, TABLE_NAME);
    }



    public void delete(String ward, String area, CommonModel commonModel) {
        super.delete(ward, area, commonModel, TABLE_NAME, KEY_COMMON_ID);
    }
}
