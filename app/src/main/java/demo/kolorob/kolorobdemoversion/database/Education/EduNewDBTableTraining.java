package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class EduNewDBTableTraining {
    private static final String TAG = EduNewDBTableTraining.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_TRAINING;
    private static final String KEY_NODE_ID = "_eduId";
    private static final String KEY_SERVICE_ID = "_sproviderid";
    private static final String KEY_COURSE_DURATION = "_courseduration"; // 1 - text
    private static final String KEY_COST= "_cost"; //
    private static final String KEY_TRAINING_NAME = "_trainingname"; // 0 -integer
    private static final String KEY_COURSE_NAME = "_coursename"; // 1 - text



    private Context tContext;

    public EduNewDBTableTraining(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
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
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EduTrainingModel eduTrainingModel) {
        return insertItem(
                eduTrainingModel.getEduid(),eduTrainingModel.getServiceproviderid(),eduTrainingModel.getCourseduration(),eduTrainingModel.getCost(),
                eduTrainingModel.getTrainingname(),eduTrainingModel.getCoursename()
        );
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



    public boolean isFieldExist(int nodeid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        int s=cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == nodeid ) {
                    cursor.close();
                    closeDB();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return false;
    }
    public ArrayList<EduTrainingModel> gettrainingInfo(int node_id) {
        ArrayList<EduTrainingModel> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_SERVICE_ID +" = "+node_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                subCatList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }
    private EduTrainingModel cursorToSubCatList(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        int _sproviderId = cursor.getInt(1);
        String _courseduration= cursor.getString(2);
        String _cost = cursor.getString(3);
        String _trainingname = cursor.getString(4);
        String _coursename = cursor.getString(5);






        return new EduTrainingModel(_eduId,_sproviderId,
                _courseduration,_cost,_trainingname,_coursename);
    }


    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
