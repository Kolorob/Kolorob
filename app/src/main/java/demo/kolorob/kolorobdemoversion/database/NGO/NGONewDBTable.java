package demo.kolorob.kolorobdemoversion.database.NGO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;


/**
 * Created by zahid on 2/8/2017.
 */


public class NGONewDBTable extends BaseDBTable <NGONewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.NGONEWDBTABLE;

    private static final String KEY_IDENTIFIER_ID = "_id"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId"; //

    private static final String KEY_NGO_SERVICES = "_services";
    private static final String KEY_NGO_SERVICES_FOR = "_services_for";
    private static final String KEY_NGO_SERVICES_OTHER = "_services_other";
    private static final String KEY_NGO_SERVICE_TYPE = "_service_type";
    private static final String KEY_DROP_TIME = "_drop_time";
    private static final String KEY_NGO_FEE = "_ngo_fee";


    public NGONewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + " INTEGER , "
                + KEY_NGO_SERVICES + " TEXT, "
                + KEY_NGO_SERVICES_FOR + " TEXT, "
                + KEY_NGO_SERVICES_OTHER + " TEXT, "
                + KEY_NGO_SERVICE_TYPE + " TEXT, "
                + KEY_DROP_TIME + " TEXT, "
                + KEY_NGO_FEE + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);

        closeDB();
    }


    public long insertItem(NGONewDBModel ngoNewDBModel) {
        if (!isFieldExist(ngoNewDBModel.getNgoId())) {
            return insertItem(ngoNewDBModel.getNgoId(), ngoNewDBModel.getCommonModel().getId(),
                    ngoNewDBModel.getNgoServices(), ngoNewDBModel.getNgoServicesFor(), ngoNewDBModel.getNgoServicesOther(),
                    ngoNewDBModel.getNgoServiceType(), ngoNewDBModel.getDropTime(), ngoNewDBModel.getNgoFee());
        }
        else {
            return updateItem(ngoNewDBModel.getNgoId(), ngoNewDBModel.getCommonModel().getId(),
                    ngoNewDBModel.getNgoServices(), ngoNewDBModel.getNgoServicesFor(), ngoNewDBModel.getNgoServicesOther(),
                    ngoNewDBModel.getNgoServiceType(), ngoNewDBModel.getDropTime(), ngoNewDBModel.getNgoFee());
        }
    }


    public long insertItem(int ngoId, int commonId, String ngoServices, String ngoServicesFor, String ngoServicesOther, String serviceType, String dropTime, String ngoFee) {
        if (isFieldExist(ngoId)) {
            return updateItem(ngoId, commonId, ngoServices, ngoServicesFor, ngoServicesOther, serviceType, dropTime, ngoFee);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, ngoId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_NGO_SERVICES, ngoServices);
        rowValue.put(KEY_NGO_SERVICES_FOR, ngoServicesFor);
        rowValue.put(KEY_NGO_SERVICES_OTHER, ngoServicesOther);
        rowValue.put(KEY_NGO_SERVICE_TYPE, serviceType);
        rowValue.put(KEY_DROP_TIME, dropTime);
        rowValue.put(KEY_NGO_FEE, ngoFee);


        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return insertedId;
    }
    private long updateItem(int ngoId, int commonId, String ngoServices, String ngoServicesFor, String ngoServicesOther, String serviceType, String dropTime, String ngoFee) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, ngoId);
        rowValue.put(KEY_COMMON_ID, commonId);
        rowValue.put(KEY_NGO_SERVICES, ngoServices);
        rowValue.put(KEY_NGO_SERVICES_FOR, ngoServicesFor);
        rowValue.put(KEY_NGO_SERVICES_OTHER, ngoServicesOther);
        rowValue.put(KEY_NGO_SERVICE_TYPE, serviceType);
        rowValue.put(KEY_DROP_TIME, dropTime);
        rowValue.put(KEY_NGO_FEE, ngoFee);

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{ngoId + ""});
        closeDB();
        return updatedId;
    }

    public NGONewDBModel getNodeInfo(int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        SQLiteDatabase db = openDB();
        NGONewDBModel ngoNewDBModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                ngoNewDBModel = new NGONewDBModel(cursor.getInt(0), commonModel, cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ngoNewDBModel;
    }

    public ArrayList <NGONewDBModel> getDataListFromId(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public NGONewDBModel getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <NGONewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public NGONewDBModel cursorToModel(Cursor cursor) {
        int _ngoId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);
        String _ngoServices = cursor.getString(2);
        String _ngoServicesFor = cursor.getString(3);
        String _ngoServicesOther = cursor.getString(4);
        String _serviceType = cursor.getString(5);
        String _dropTime = cursor.getString(6);
        String _ngoFee = cursor.getString(7);

        CommonModel _commonModel = getCommonModelFromId(_commonId);
        return new NGONewDBModel(_ngoId, _commonModel, _ngoServices, _ngoServicesFor, _ngoServicesOther, _serviceType, _dropTime, _ngoFee);

    }


    public NGONewDBModel getDetailsByCommonId(int commonId) {
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public void dropTable() {
       super.dropTable(TABLE_NAME);
    }


}
