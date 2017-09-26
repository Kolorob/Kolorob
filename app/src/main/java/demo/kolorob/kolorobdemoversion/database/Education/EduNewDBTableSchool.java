package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class EduNewDBTableSchool extends BaseDBTable <EduNewSchoolModel> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_SCHOOL;
    private static final String KEY_NODE_ID = "_eduId";
    private static final String KEY_SPID = "_sproviderId";
    private static final String KEY_STIPEND = "_stipend";
    private static final String KEY_PRIMARY_FEES = "_primary"; // 1 - text
    private static final String KEY_SECONDARY_FEES= "_secondary"; //
    private static final String KEY_COLLEGE_FEES = "_collage"; // 0 -integer


    public EduNewDBTableSchool(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_SPID + " INTEGER , "
                + KEY_STIPEND + " TEXT , "
                + KEY_PRIMARY_FEES + "  TEXT  , " // 0 - int "
                + KEY_SECONDARY_FEES + "  TEXT , "
                + KEY_COLLEGE_FEES + "  TEXT , PRIMARY KEY(" + KEY_NODE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(EduNewSchoolModel eduNewSchoolModel) {
        if (!isFieldExist(eduNewSchoolModel.getSpid())) {
            return insertItem(
                    eduNewSchoolModel.getId(), eduNewSchoolModel.getSpid(), eduNewSchoolModel.getStipend(),
                    eduNewSchoolModel.getPrimary_fees(), eduNewSchoolModel.getSecondary_fees(),
                    eduNewSchoolModel.getCollege_fees()
            );
        }
        else {
            return updateItem(
                    eduNewSchoolModel.getId(),eduNewSchoolModel.getSpid(),eduNewSchoolModel.getStipend(),
                    eduNewSchoolModel.getPrimary_fees(),eduNewSchoolModel.getSecondary_fees(),
                    eduNewSchoolModel.getCollege_fees()
            );
        }
    }

    public long insertItem(int eduId,int spid, String stipend, String primary, String secondary,
                           String collage ) {
        if (isFieldExist(eduId)) {
            return updateItem(
                    eduId,spid,stipend,
                    primary,
                    secondary,
                    collage);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_SPID , spid);
        rowValue.put(KEY_STIPEND, stipend);
        rowValue.put(KEY_PRIMARY_FEES, primary);
        rowValue.put(KEY_SECONDARY_FEES, secondary);


        rowValue.put(KEY_COLLEGE_FEES , collage);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int eduId, int spid,String stipend, String primary, String secondary,
                            String collage) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_SPID , spid);
        rowValue.put(KEY_STIPEND, stipend);
        rowValue.put(KEY_PRIMARY_FEES, primary);
        rowValue.put(KEY_SECONDARY_FEES, secondary);


        rowValue.put(KEY_COLLEGE_FEES , collage);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{eduId + ""});
        closeDB();
        return ret;

    }

    public EduNewSchoolModel getNodeInfo(int node){
        SQLiteDatabase db = openDB();
        EduNewSchoolModel eduNewSchoolModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_NODE_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                eduNewSchoolModel = new EduNewSchoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return eduNewSchoolModel;
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_NODE_ID);
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public ArrayList <EduNewSchoolModel> getDataFromId(int id) {
        return super.getDataFromId(id, TABLE_NAME, KEY_NODE_ID);
    }

    public ArrayList <EduNewSchoolModel> getDataFromForeignKey(int id) {
        return super.getDataFromId(id, TABLE_NAME, KEY_SPID);
    }



    public EduNewSchoolModel cursorToModel(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        int _spid = cursor.getInt(1);
        String _stipend = cursor.getString(2);
        String _primary= cursor.getString(3);
        String _secondary = cursor.getString(4);
        String _collage = cursor.getString(5);

        return new EduNewSchoolModel(_eduId,_spid,_stipend,
                _primary,_secondary,_collage);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
