package demo.kolorob.kolorobdemoversion.database.Government;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;


/**
 * Created by israt.jahan on 2/9/2017.
 */

public class GovNewDBTable extends CommonDBTable <GovernmentNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.GOV_NEW;
    private static final String KEY_SERVICE_NAME = "service_name";


    public GovNewDBTable(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL +=  KEY_SERVICE_NAME + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(GovernmentNewDBModel governmentNewDBModel) {
        return super.insertItem(governmentNewDBModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, GovernmentNewDBModel government) {
        rowValue.put(KEY_SERVICE_NAME, government.getServiceName());
    }


    public long updateItem(GovernmentNewDBModel governmentNewDBModel) {
        return super.updateItem(governmentNewDBModel, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, GovernmentNewDBModel government){
        rowValue.put(KEY_SERVICE_NAME, government.getServiceName());
    }


    public boolean isFieldExist(int id ) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public GovernmentNewDBModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <GovernmentNewDBModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <GovernmentNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public GovernmentNewDBModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _serviceName = cursor.getString(23);

        return new GovernmentNewDBModel(_commonModel, _serviceName);
    }


    public ArrayList <GovernmentNewDBModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
       super.dropTable(TABLE_NAME);
    }


}
