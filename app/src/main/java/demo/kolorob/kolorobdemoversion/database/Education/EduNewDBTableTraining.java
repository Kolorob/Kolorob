package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;


/**
 * Created by israt.jahan on 6/27/2016.
 */


public class EduNewDBTableTraining extends BaseDBTable <EduTrainingModel> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_TRAINING;

    private static final String KEY_EDUCATION_ID = "education_id";
    private static final String KEY_COURSE_DURATION = "course_duration";
    private static final String KEY_COST = "cost";
    private static final String KEY_TRAINING_NAME = "training_name";
    private static final String KEY_COURSE_NAME = "course_name";


    public EduNewDBTableTraining(Context context) {
       super(context);
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER , "
                + KEY_EDUCATION_ID + " INTEGER , "
                + KEY_COURSE_DURATION + "  TEXT  , "
                + KEY_COST + "  TEXT , "
                + KEY_TRAINING_NAME   + "  TEXT , "
                + KEY_COURSE_NAME + "  TEXT , PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(EduTrainingModel training) {
        if (!isFieldExist(training.getId())) {
            return insertItem(
                    training.getId(), training.getEducationId(), training.getCourseduration(), training.getCost(),
                    training.getTrainingname(), training.getCoursename()
            );
        }
        else {
            return updateItem(training);
        }
    }

    public long updateItem(EduTrainingModel training){
        return updateItem(training.getId(), training.getEducationId(), training.getCourseduration(),
                training.getCost(), training.getTrainingname(), training.getCoursename());
    }

    private long insertItem(int id, int educationId, String courseduration, String cost,
                           String trainingname, String coursename ) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID , id);
        rowValue.put(KEY_EDUCATION_ID, educationId);
        rowValue.put(KEY_COURSE_DURATION, courseduration);
        rowValue.put(KEY_COST, cost);
        rowValue.put(KEY_TRAINING_NAME, trainingname);
        rowValue.put(KEY_COURSE_NAME , coursename);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    private long updateItem(int id, int educationId, String courseduration, String cost,
                            String trainingname, String coursename) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID , id);
        rowValue.put(KEY_EDUCATION_ID, educationId);
        rowValue.put(KEY_COURSE_DURATION, courseduration);
        rowValue.put(KEY_COST, cost);
        rowValue.put(KEY_TRAINING_NAME, trainingname);
        rowValue.put(KEY_COURSE_NAME , coursename);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  ",
                new String[]{id + ""});
        closeDB();
        return ret;

    }

    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }


    public EduTrainingModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <EduTrainingModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public EduTrainingModel getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_EDUCATION_ID);
    }

    public ArrayList <EduTrainingModel> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_EDUCATION_ID);
    }

    public ArrayList <EduTrainingModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public EduTrainingModel cursorToModel(Cursor cursor) {
        int _id = cursor.getInt(0);
        int _educationId = cursor.getInt(1);
        String _courseduration = cursor.getString(2);
        String _cost = cursor.getString(3);
        String _trainingname = cursor.getString(4);
        String _coursename = cursor.getString(5);

        return new EduTrainingModel(_id, _educationId, _courseduration, _cost, _trainingname, _coursename);
    }


    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void delete(String ward, String area){
        super.delete(ward, area, TABLE_NAME, KEY_EDUCATION_ID, EduNewDBTableMain.getTableName(), EduNewDBTableMain.KEY_IDENTIFIER_ID);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
