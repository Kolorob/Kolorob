package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;


/**
 * Created by israt.jahan on 6/27/2016.
 */


public class EduNewDBTableTraining extends BaseDBTable <EduTrainingModel> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_TRAINING;
    private static final String KEY_NODE_ID = "_eduId";
    private static final String KEY_SERVICE_ID = "_sproviderid";
    private static final String KEY_COURSE_DURATION = "_courseduration"; // 1 - text
    private static final String KEY_COST= "_cost"; //
    private static final String KEY_TRAINING_NAME = "_trainingname"; // 0 -integer
    private static final String KEY_COURSE_NAME = "_coursename"; // 1 - text


    public EduNewDBTableTraining(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_SERVICE_ID + " INTEGER , "
                + KEY_COURSE_DURATION + "  TEXT  , " // 0 - int "
                + KEY_COST + "  TEXT , "
                + KEY_TRAINING_NAME   + "  TEXT , "
                + KEY_COURSE_NAME + "  TEXT , PRIMARY KEY(" + KEY_NODE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(EduTrainingModel eduTrainingModel) {
        if (isFieldExist(eduTrainingModel.getServiceproviderid())) {
            return insertItem(
                    eduTrainingModel.getEduid(), eduTrainingModel.getServiceproviderid(), eduTrainingModel.getCourseduration(), eduTrainingModel.getCost(),
                    eduTrainingModel.getTrainingname(), eduTrainingModel.getCoursename()
            );
        }
        else {
            return updateItem(
                    eduTrainingModel.getEduid(), eduTrainingModel.getServiceproviderid(), eduTrainingModel.getCourseduration(), eduTrainingModel.getCost(),
                    eduTrainingModel.getTrainingname(), eduTrainingModel.getCoursename()
            );
        }
    }

    public long insertItem(int eduId, int serviceproviderId, String courseduration, String cost,
                           String trainingname, String coursename ) {
        if (isFieldExist(eduId)) {
            return updateItem(
                    eduId,serviceproviderId,
                    courseduration,
                    cost,
                    trainingname,
                    coursename);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_SERVICE_ID, serviceproviderId);
        rowValue.put(KEY_COURSE_DURATION, courseduration);
        rowValue.put(KEY_COST, cost);
        rowValue.put(KEY_TRAINING_NAME, trainingname);

        rowValue.put(KEY_COURSE_NAME , coursename);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int eduId, int serviceproviderId, String courseduration, String cost,
                            String trainingname, String coursename) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_SERVICE_ID, serviceproviderId);
        rowValue.put(KEY_COURSE_DURATION, courseduration);
        rowValue.put(KEY_COST, cost);
        rowValue.put(KEY_TRAINING_NAME, trainingname);

        rowValue.put(KEY_COURSE_NAME , coursename);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{eduId + ""});
        closeDB();
        return ret;

    }

    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME, KEY_NODE_ID);
    }


    public ArrayList <EduTrainingModel> getDataListFromId (int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_NODE_ID);
    }

    public EduTrainingModel getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_NODE_ID);
    }

    public ArrayList <EduTrainingModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public ArrayList <EduTrainingModel> getDataFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_SERVICE_ID);
    }

    public EduTrainingModel cursorToModel(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        int _sproviderId = cursor.getInt(1);
        String _courseduration= cursor.getString(2);
        String _cost = cursor.getString(3);
        String _trainingname = cursor.getString(4);
        String _coursename = cursor.getString(5);

        return new EduTrainingModel(_eduId,_sproviderId,
                _courseduration,_cost,_trainingname,_coursename);
    }

    public EduTrainingModel cursorToModel(Cursor cursor, CommonModel commonModel){
        return null;
    }

    public EduTrainingModel getNodeInfo(int node){
        SQLiteDatabase db = openDB();
        EduTrainingModel eduTrainingModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_SERVICE_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                eduTrainingModel = new EduTrainingModel(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return eduTrainingModel;
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_NODE_ID);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
