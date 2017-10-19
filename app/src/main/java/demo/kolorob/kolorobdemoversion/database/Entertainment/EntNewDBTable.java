package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;

/**
 * Created by israt.jahan on 2/9/2017.
 */


public class EntNewDBTable extends CommonDBTable <EntertainmentNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.ENT_PROVIDER_TABLE_DBNEW;

    private static final String KEY_SERVICE_TYPEENT = "ent_type";
    private static final String KEY_ENTRY_FEE = "entry_fee";


    public EntNewDBTable(Context context) {
       super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL +=  KEY_SERVICE_TYPEENT + " TEXT, " +
                KEY_ENTRY_FEE + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    public long insertItem(EntertainmentNewDBModel entertainmentNewDBModel) {
        return super.insertItem(entertainmentNewDBModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, EntertainmentNewDBModel entertainmentNewDBModel) {
        rowValue.put(KEY_SERVICE_TYPEENT, entertainmentNewDBModel.getEntType());
        rowValue.put(KEY_ENTRY_FEE, entertainmentNewDBModel.getEntryFee());
    }


    public long updateItem(EntertainmentNewDBModel entertainmentNewDBModel) {
        return super.updateItem(entertainmentNewDBModel, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, EntertainmentNewDBModel entertainmentNewDBModel){
        rowValue.put(KEY_SERVICE_TYPEENT, entertainmentNewDBModel.getEntType());
        rowValue.put(KEY_ENTRY_FEE, entertainmentNewDBModel.getEntryFee());
    }

    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }


    public EntertainmentNewDBModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <EntertainmentNewDBModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <EntertainmentNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public EntertainmentNewDBModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _service_type = cursor.getString(23);
        String _entry_fee = cursor.getString(24);

        return new EntertainmentNewDBModel(_commonModel, _service_type, _entry_fee);
    }

    public ArrayList <EntertainmentNewDBModel> getByArea(String ward, String area){
        return super.getByArea(ward, area, TABLE_NAME);
    }

    public ArrayList <EntertainmentNewDBModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void delete(String ward, String area){
        super.delete(ward, area, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}