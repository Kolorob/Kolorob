package demo.kolorob.kolorobdemoversion.database.Financial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;


/**
 * Created by israt.jahan on 2/9/2017.
 */


public class FinNewDBTable extends CommonDBTable <FinancialNewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_NEWDB;

    private static final String KEY_TYPE = "type";
    private static final String KEY_SERVICE = "service"; // 1 - text

    public FinNewDBTable(Context context) {
       super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL +=  KEY_TYPE + " TEXT, " +
                KEY_SERVICE + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(FinancialNewDBModel financialNewDBModel) {
        return super.insertItem(financialNewDBModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, FinancialNewDBModel financialNewDBModel) {
        rowValue.put(KEY_TYPE, financialNewDBModel.getFinType());
        rowValue.put(KEY_SERVICE, financialNewDBModel.getServiceType());
    }


    public long updateItem(FinancialNewDBModel financialNewDBModel) {
        return super.updateItem(financialNewDBModel, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, FinancialNewDBModel financialNewDBModel){
        rowValue.put(KEY_TYPE, financialNewDBModel.getFinType());
        rowValue.put(KEY_SERVICE, financialNewDBModel.getServiceType());
    }

    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }


    public FinancialNewDBModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <FinancialNewDBModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <FinancialNewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public FinancialNewDBModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _type = cursor.getString(23);
        String _service = cursor.getString(24);

        return new FinancialNewDBModel(_commonModel, _type, _service);
    }

    public ArrayList <FinancialNewDBModel> getByArea(String ward, String area){
        return super.getByArea(ward, area, TABLE_NAME);
    }

    public ArrayList <FinancialNewDBModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
