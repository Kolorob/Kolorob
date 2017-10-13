package demo.kolorob.kolorobdemoversion.database.NGO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;

/**
 * Created by zahid on 2/8/2017.
 */


public class NGONewDBTable extends CommonDBTable<NGONewDBModel> {

    private static final String TABLE_NAME = DatabaseHelper.NGONEWDBTABLE;

    private static final String KEY_NGO_SERVICES = "services";
    private static final String KEY_NGO_SERVICES_FOR = "services_for";
    private static final String KEY_NGO_SERVICES_OTHER = "services_other";
    private static final String KEY_NGO_SERVICE_TYPE = "service_type";
    private static final String KEY_DROP_TIME = "drop_time";
    private static final String KEY_NGO_FEE = "ngo_fee";


    public NGONewDBTable(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL += KEY_NGO_SERVICES + " TEXT, "
                + KEY_NGO_SERVICES_FOR + " TEXT, "
                + KEY_NGO_SERVICES_OTHER + " TEXT, "
                + KEY_NGO_SERVICE_TYPE + " TEXT, "
                + KEY_DROP_TIME + " TEXT, "
                + KEY_NGO_FEE + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    public long insertItem(NGONewDBModel ngoNewDBModel) {
        return super.insertItem(ngoNewDBModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, NGONewDBModel ngoNewDBModel) {
        rowValue.put(KEY_NGO_SERVICES, ngoNewDBModel.getNgoServices());
        rowValue.put(KEY_NGO_SERVICES_FOR, ngoNewDBModel.getNgoServicesFor());
        rowValue.put(KEY_NGO_SERVICES_OTHER, ngoNewDBModel.getNgoServicesOther());
        rowValue.put(KEY_NGO_SERVICE_TYPE, ngoNewDBModel.getNgoServiceType());
        rowValue.put(KEY_DROP_TIME, ngoNewDBModel.getDropTime());
        rowValue.put(KEY_NGO_FEE, ngoNewDBModel.getNgoFee());
    }


    public long updateItem(NGONewDBModel ngoNewDBModel) {
        return super.updateItem(ngoNewDBModel, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, NGONewDBModel ngoNewDBModel){
        insertItem(rowValue, ngoNewDBModel);
    }


    public boolean isFieldExist(int id ) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public NGONewDBModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <NGONewDBModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <NGONewDBModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public NGONewDBModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _services = cursor.getString(23);
        String _services_for = cursor.getString(24);
        String _services_other = cursor.getString(25);
        String _service_type = cursor.getString(26);
        String _drop_time = cursor.getString(27);
        String _ngo_fee = cursor.getString(28);

        return new NGONewDBModel(_commonModel, _services, _services_for, _services_other, _service_type, _drop_time, _ngo_fee);
    }

    public ArrayList <NGONewDBModel> getByArea(String ward, String area){
        return super.getByArea(ward, area, TABLE_NAME);
    }

    public ArrayList <NGONewDBModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
