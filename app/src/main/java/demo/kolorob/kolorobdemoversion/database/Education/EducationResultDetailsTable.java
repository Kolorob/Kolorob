package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;


/**
 * Created by israt.jahan on 6/27/2016.
 */

public class EducationResultDetailsTable extends BaseDBTable <EducationResultItemNew> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_PROVIDER_RESULT_TABLE;

    private static final String KEY_EDUCATION_ID = "edu_id";
    private static final String KEY_EXAM_NAME = "exam_name";
    private static final String KEY_STUDENT_NO = "student_no";
    private static final String KEY_PASSED = "passed";
    private static final String KEY_GOLDEN = "golden_a";
    private static final String KEY_APLUS = "a_plus";


    public EducationResultDetailsTable(Context context) {
        super(context);
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER , "
                + KEY_EDUCATION_ID + " INTEGER , "
                + KEY_EXAM_NAME + "  TEXT  , "
                + KEY_STUDENT_NO + "  TEXT , "
                + KEY_PASSED   + "  TEXT , "
                + KEY_GOLDEN + "  TEXT  , "
                + KEY_APLUS + "  TEXT , PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(EducationResultItemNew result) {
        if(!isFieldExist(result.getId())){
            return insertItem(
                    result.getId(), result.getEducationId(), result.getExamname(), result.getStudentno(),
                    result.getPassed(), result.getGoldena(), result.getAplus()
            );
        }
        else return updateItem(result);
    }


    public long insertItem(int id, int educationId, String examname, String studentno,
                           String passed, String goldena, String aplus) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID , id);
        rowValue.put(KEY_EDUCATION_ID, educationId);
        rowValue.put(KEY_EXAM_NAME, examname);
        rowValue.put(KEY_STUDENT_NO, studentno);
        rowValue.put(KEY_PASSED, passed);
        rowValue.put(KEY_GOLDEN, goldena);
        rowValue.put(KEY_APLUS , aplus);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public long updateItem(EducationResultItemNew result){
        return updateItem(result.getId(), result.getEducationId(), result.getExamname(),
                result.getStudentno(), result.getPassed(), result.getGoldena(), result.getAplus());
    }


    private long updateItem(int id, int educationId, String examname, String studentno,
                            String passed, String goldena, String aplus) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID , id);
        rowValue.put(KEY_EDUCATION_ID, educationId);
        rowValue.put(KEY_EXAM_NAME, examname);
        rowValue.put(KEY_STUDENT_NO, studentno);
        rowValue.put(KEY_PASSED, passed);
        rowValue.put(KEY_GOLDEN, goldena);
        rowValue.put(KEY_APLUS , aplus);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{id + ""});
        closeDB();
        return ret;

    }

    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public EducationResultItemNew cursorToModel(Cursor cursor) {
        int _id = cursor.getInt(0);
        int _educationId = cursor.getInt(1);
        String _examname= cursor.getString(2);
        String _studentno = cursor.getString(3);
        String _passed = cursor.getString(4);
        String _goldena = cursor.getString(5);
        String _aplus= cursor.getString(6);


        return new EducationResultItemNew(_id,_educationId,
                _examname,_studentno,_passed,_goldena,_aplus);
    }


    public ArrayList <EducationResultItemNew> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public ArrayList <EducationResultItemNew> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
