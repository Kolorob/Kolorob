package demo.kolorob.kolorobdemoversion.database.Religious;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;


/**
 * Created by zahid on 2/8/2017.
 */


public class ReligiousNewDBTable extends CommonDBTable <ReligiousNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.RELIGIOUSNEWDBTABLE;

    private static final String KEY_RS_RELIGION = "rs_religion";
    private static final String KEY_RS_SERVICES_FOR = "rs_services_for";
    private static final String KEY_RS_SERVICES_FOR_RELIGION = "rs_services_for_religion";
    private static final String KEY_OTHER_RELIGION = "other_religion";
    private static final String KEY_RS_TIME = "rs_time";
    private static final String KEY_RS_FEE = "rs_fee";


    public ReligiousNewDBTable(Context context) {
        super(context);
    }

    public void createTable() {


        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL += KEY_RS_RELIGION + " TEXT, "
                + KEY_RS_SERVICES_FOR + " TEXT, "
                + KEY_RS_SERVICES_FOR_RELIGION + " TEXT, "
                + KEY_OTHER_RELIGION + " TEXT, "
                + KEY_RS_TIME + " TEXT, "
                + KEY_RS_FEE + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    public long insertItem(ReligiousNewDBModel religiousNewDBModel) {
        return super.insertItem(religiousNewDBModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, ReligiousNewDBModel shelter) {
        rowValue.put(KEY_RS_RELIGION, shelter.getRsReligion());
        rowValue.put(KEY_RS_SERVICES_FOR, shelter.getRsServicesFor());
        rowValue.put(KEY_RS_SERVICES_FOR_RELIGION, shelter.getRsServicesForReligion());
        rowValue.put(KEY_OTHER_RELIGION, shelter.getOtherReligion());
        rowValue.put(KEY_RS_TIME, shelter.getRsTime());
        rowValue.put(KEY_RS_FEE, shelter.getRsFee());
    }


    public long updateItem(ReligiousNewDBModel shelter) {
        return super.updateItem(shelter, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, ReligiousNewDBModel shelter){
        insertItem(rowValue, shelter);
    }


    public boolean isFieldExist(int id ) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public ArrayList <ReligiousNewDBModel> getDataListFromId(int node){
        return super.getDataListFromId(node, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <ReligiousNewDBModel> getByArea(String ward, String area){
        return super.getByArea(ward, area, TABLE_NAME);
    }


    public ArrayList <ReligiousNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public ReligiousNewDBModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _rs_religion = cursor.getString(23);
        String _rs_services_for = cursor.getString(24);
        String _rs_services_for_religion = cursor.getString(25);
        String _rs_other_religion = cursor.getString(26);
        String _rs_time = cursor.getString(27);
        String _rs_fee = cursor.getString(28);

        return new ReligiousNewDBModel(_commonModel, _rs_religion, _rs_services_for, _rs_services_for_religion, _rs_other_religion, _rs_time, _rs_fee);
    }

    public ReligiousNewDBModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <ReligiousNewDBModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }


}