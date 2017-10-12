package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;


/**
 * Created by israt.jahan on 6/27/2016.
 */


public class EduNewDBTableSchool extends BaseDBTable <EduNewSchoolModel> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_SCHOOL;

    private static final String KEY_EDUCATION_ID = "edu_id";
    private static final String KEY_STIPEND = "stipend";
    private static final String KEY_PRIMARY_FEES = "primary_fees";
    private static final String KEY_SECONDARY_FEES = "secondary_fees";
    private static final String KEY_COLLEGE_FEES = "college_fees";


    public EduNewDBTableSchool(Context context) {
        super(context);
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER , "
                + KEY_EDUCATION_ID + " INTEGER , "
                + KEY_STIPEND + " TEXT , "
                + KEY_PRIMARY_FEES + "  TEXT  , "
                + KEY_SECONDARY_FEES + "  TEXT , "
                + KEY_COLLEGE_FEES + "  TEXT , PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(EduNewSchoolModel school) {
        if (!isFieldExist(school.getId())) {
            return insertItem(
                    school.getId(), school.getEducationId(), school.getStipend(),
                    school.getPrimary_fees(), school.getSecondary_fees(),
                    school.getCollege_fees()
            );
        }
        else {
            return updateItem(school);
        }
    }

    public long insertItem(int id, int educationId, String stipend, String primary, String secondary, String college ) {


        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID , id);
        rowValue.put(KEY_EDUCATION_ID, educationId);
        rowValue.put(KEY_STIPEND, stipend);
        rowValue.put(KEY_PRIMARY_FEES, primary);
        rowValue.put(KEY_SECONDARY_FEES, secondary);
        rowValue.put(KEY_COLLEGE_FEES , college);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;

    }

    public long updateItem(EduNewSchoolModel school){
        return updateItem(school.getId(), school.getEducationId(), school.getStipend(),
                school.getPrimary_fees(), school.getSecondary_fees(), school.getCollege_fees());
    }


    private long updateItem(int id, int educationId, String stipend, String primary, String secondary, String college) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID , id);
        rowValue.put(KEY_EDUCATION_ID, educationId);
        rowValue.put(KEY_STIPEND, stipend);
        rowValue.put(KEY_PRIMARY_FEES, primary);
        rowValue.put(KEY_SECONDARY_FEES, secondary);
        rowValue.put(KEY_COLLEGE_FEES , college);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{id + ""});
        closeDB();
        return ret;

    }



    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public EduNewSchoolModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <EduNewSchoolModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public EduNewSchoolModel getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_EDUCATION_ID);
    }

    public ArrayList <EduNewSchoolModel> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_EDUCATION_ID);
    }


    public ArrayList <EduNewSchoolModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public EduNewSchoolModel cursorToModel(Cursor cursor) {
        int _id = cursor.getInt(0);
        int _educationId = cursor.getInt(1);
        String _stipend = cursor.getString(2);
        String _primary= cursor.getString(3);
        String _secondary = cursor.getString(4);
        String _collage = cursor.getString(5);

        return new EduNewSchoolModel(_id, _educationId, _stipend, _primary, _secondary, _collage);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
