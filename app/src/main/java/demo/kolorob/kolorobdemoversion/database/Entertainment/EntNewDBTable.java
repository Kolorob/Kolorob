package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;

/**
 * Created by israt.jahan on 2/9/2017.
 */


public class EntNewDBTable extends BaseDBTable <EntertainmentNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.ENT_PROVIDER_TABLE_DBNEW;
    private static final String KEY_IDENTIFIER_ID = "_entid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";

    private static final String KEY_SERVICE_TYPEENT = "_entType";
    private static final String KEY_ENTRY_FEE = "_entryFee"; // 1 - text


    public EntNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
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


    public long insertItem(int entId, int commonId, String entType, String entryFee) {
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



    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public EntertainmentNewDBModel getDetailsByCommonId(int commonId) {
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <EntertainmentNewDBModel> getDataListFromId(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public EntertainmentNewDBModel getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <EntertainmentNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public EntertainmentNewDBModel getNodeInfo(int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        EntertainmentNewDBModel entertainmentNewDBModel = null;
        SQLiteDatabase db = openDB();

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


    public EntertainmentNewDBModel cursorToModel(Cursor cursor) {

        int _entId = cursor.getInt(0);
        int _commonId = cursor. getInt(1);
        String _entType = cursor.getString(2);
        String _entryFee = cursor.getString(3);

        CommonModel _commonModel = getCommonModelFromId(_commonId);
        return new EntertainmentNewDBModel(_entId, _commonModel, _entType, _entryFee);

    }

    public EntertainmentNewDBModel cursorToModel(Cursor cursor, CommonModel _commonModel) {

        int _entId = cursor.getInt(0);
        String _entType = cursor.getString(2);
        String _entryFee = cursor.getString(3);

        return new EntertainmentNewDBModel(_entId, _commonModel, _entType, _entryFee);

    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
