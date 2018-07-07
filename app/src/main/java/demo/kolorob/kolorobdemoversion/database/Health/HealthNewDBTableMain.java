package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;


/**
 * Created by israt.jahan on 2/9/2017.
 */

public class HealthNewDBTableMain extends CommonDBTable <HealthNewDBModelMain> {

    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_MAIN;

    private static final String KEY_INSTITUTE_TYPE = "institute_type";

    public static String getTableName() {
        return TABLE_NAME;
    }

    public HealthNewDBTableMain(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL +=  KEY_INSTITUTE_TYPE + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(HealthNewDBModelMain health) {
        return super.insertItem(health, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, HealthNewDBModelMain health) {
        rowValue.put(KEY_INSTITUTE_TYPE, health.getInstituteType());
    }

    public long updateItem(HealthNewDBModelMain health) {
        return super.updateItem(health, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, HealthNewDBModelMain health){
        insertItem(rowValue, health);
    }


    public boolean isFieldExist(int id ) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public HealthNewDBModelMain getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <HealthNewDBModelMain> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <HealthNewDBModelMain> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public HealthNewDBModelMain cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _institute_type = cursor.getString(24);

        return new HealthNewDBModelMain(_commonModel, _institute_type);
    }

    public ArrayList <HealthNewDBModelMain> getByArea(String ward, String area){
        return super.getByArea(ward, area, TABLE_NAME);
    }

    public ArrayList <HealthNewDBModelMain> getByAreaCategory(String ward, String area, int category){
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