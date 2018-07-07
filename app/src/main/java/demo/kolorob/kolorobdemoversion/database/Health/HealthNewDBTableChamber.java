package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.Health.HealthModelChamber;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;

/**
 * Created by shamima.brishti on 2/10/18.
 */

public class HealthNewDBTableChamber extends BaseDBTable<HealthModelChamber> {

    private static final String TABLE_NAME = DatabaseHelper.HEALTH_NEW_DB_CHAMBER;
    private static final String KEY_HEALTH_ID = "health_id";
    private static final String KEY_SPECIALITY = "speciality";


    public HealthNewDBTableChamber(Context context) {
        super(context);
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER , "
                + KEY_HEALTH_ID + " INTEGER , "
                + KEY_SPECIALITY + "  TEXT , PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(HealthModelChamber chamber) {
        if (!isFieldExist(chamber.getId())) {
            return insertItem(
                    chamber.getId(), chamber.getHealthId(),
                    chamber.getSpeciality()

            );
        }
        else return updateItem(chamber);
    }


    public long updateItem(HealthModelChamber chamber) {
        return updateItem(
                chamber.getId(), chamber.getHealthId(),
                chamber.getSpeciality()
        );
    }

    public long insertItem(int id, int healthId, String speciality) {
        if (isFieldExist(id)) {
            return updateItem(id, healthId, speciality);

        }
        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_HEALTH_ID, healthId);
        rowValue.put(KEY_SPECIALITY, speciality);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int id, int healthId, String speciality) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_HEALTH_ID, healthId);
        rowValue.put(KEY_SPECIALITY, speciality);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{id + ""});
        closeDB();
        return ret;

    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public HealthModelChamber getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList<HealthModelChamber> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public HealthModelChamber getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_HEALTH_ID);
    }

    public ArrayList <HealthModelChamber> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_HEALTH_ID);
    }


    public ArrayList <HealthModelChamber> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public HealthModelChamber cursorToModel(Cursor cursor) {
        int _id = cursor.getInt(0);
        int _healthId = cursor.getInt(1);
        String _speciality = cursor.getString(2);

        return new HealthModelChamber(_id, _healthId, _speciality);
    }


    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void delete(String ward, String area){
        super.delete(ward, area, TABLE_NAME, KEY_HEALTH_ID, HealthNewDBTableMain.getTableName(), HealthNewDBTableMain.KEY_IDENTIFIER_ID);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}

