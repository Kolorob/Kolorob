package demo.kolorob.kolorobdemoversion.database.LegalAid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;


/**
 * Created by israt.jahan on 2/9/2017.
 */


public class LegalAidNewDBTable extends CommonDBTable<LegalAidNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.LEGALAIDNEWDBTABLE;
    private static final String KEY_SERVICE_TYPE = "service_type";

    public LegalAidNewDBTable(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL +=  KEY_SERVICE_TYPE + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(LegalAidNewDBModel legalAidNewDBModel) {
        return super.insertItem(legalAidNewDBModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, LegalAidNewDBModel legalAidNewDBModel) {
        rowValue.put(KEY_SERVICE_TYPE, legalAidNewDBModel.getService());
    }


    public long updateItem(LegalAidNewDBModel legalAidNewDBModel) {
        return super.updateItem(legalAidNewDBModel, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, LegalAidNewDBModel legalAidNewDBModel){
        rowValue.put(KEY_SERVICE_TYPE, legalAidNewDBModel.getService());
    }


    public boolean isFieldExist(int id ) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public ArrayList <LegalAidNewDBModel> getDataListFromId(int node){
        return super.getDataListFromId(node, TABLE_NAME);
    }


    public ArrayList <LegalAidNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public LegalAidNewDBModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _service = cursor.getString(23);

        return new LegalAidNewDBModel(_commonModel, _service);
    }


    public ArrayList <LegalAidNewDBModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}