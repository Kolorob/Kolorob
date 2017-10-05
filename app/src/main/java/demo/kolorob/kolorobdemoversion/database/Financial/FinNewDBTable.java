package demo.kolorob.kolorobdemoversion.database.Financial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;


/**
 * Created by israt.jahan on 2/9/2017.
 */


public class FinNewDBTable extends BaseDBTable <FinancialNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_NEWDB;
    private static final String KEY_IDENTIFIER_ID = "_finid"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";
    private static final String KEY_TYPE = "_type";
    private static final String KEY_SERVICE = "_service"; // 1 - text

    public FinNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + "  INTEGER, "              // 1 - text
                + KEY_TYPE + " TEXT, "// 2 - text
                + KEY_SERVICE + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(FinancialNewDBModel financialNewDBModel) {
        if (!isFieldExist(financialNewDBModel.getFinId())) {
            return insertItem(financialNewDBModel.getFinId(), financialNewDBModel.getCommonModel().getId(),
                    financialNewDBModel.getFinType(), financialNewDBModel.getServiceType());
        }
        else {
            return updateItem(financialNewDBModel.getFinId(), financialNewDBModel.getCommonModel().getId(),
                    financialNewDBModel.getFinType(), financialNewDBModel.getServiceType());
        }
    }


    public long insertItem(int finId, int commonId, String type, String service) {
        if (isFieldExist(finId)) {
            return updateItem(finId, commonId, type, service);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, finId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_TYPE, type);
        rowValue.put(KEY_SERVICE, service);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return insertedId;
    }

    private long updateItem(int finId, int commonId, String type, String service) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, finId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_TYPE, type);
        rowValue.put(KEY_SERVICE, service);

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{finId + ""});
        closeDB();
        return updatedId;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public FinancialNewDBModel getDetailsByCommonId(int commonId) {
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }

    public ArrayList <FinancialNewDBModel> getDataListFromId(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public FinancialNewDBModel getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <FinancialNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public FinancialNewDBModel getNodeInfo(int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        SQLiteDatabase db = openDB();
        FinancialNewDBModel financialNewDBModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                financialNewDBModel = new FinancialNewDBModel(cursor.getInt(0), commonModel, cursor.getString(2), cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return financialNewDBModel;
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public FinancialNewDBModel cursorToModel(Cursor cursor) {

        int _finId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);
        String _type = cursor.getString(2);
        String _service = cursor.getString(3);

        CommonModel _commonModel = getCommonModelFromId(_commonId);
        return new FinancialNewDBModel(_finId, _commonModel, _type, _service);
    }

    public FinancialNewDBModel cursorToModel(Cursor cursor, CommonModel _commonModel) {

        int _finId = cursor.getInt(0);
        String _type = cursor.getString(2);
        String _service = cursor.getString(3);

        return new FinancialNewDBModel(_finId, _commonModel, _type, _service);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
