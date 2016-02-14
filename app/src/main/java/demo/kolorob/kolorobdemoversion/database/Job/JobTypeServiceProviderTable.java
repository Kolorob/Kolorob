package demo.kolorob.kolorobdemoversion.database.Job;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobTypeServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by mity on 2/14/16.
 */
public class JobTypeServiceProviderTable {
    private static final String TAG = JobTypeServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.JOB_SERVICE_PROVIDER_TYPE;
    private static final String KEY_JOB_ID = "_job_id";
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer
    private static final String KEY_JOB_SUBCATEGORY_ID = "_job_subcategory_id"; // 2 - text*/
  ;

    private static final String KEY_JOB_NAME = "_job_name"; //
    private static final String KEY_JOB_SOURCE = "_job_news_source"; //
    private static final String KEY_JOB_REMARK = "_job_remark"; //
    private static final String KEY_JOB_TYPE= "_job_type"; ///

    private Context tContext;

    public JobTypeServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_JOB_ID + "  TEXT, "
                + KEY_IDENTIFIER_ID + "  TEXT , " // 0 - int// 1 - text
                + KEY_JOB_SUBCATEGORY_ID + " INTEGER, "
                + KEY_JOB_NAME + " TEXT, "
                + KEY_JOB_SOURCE + " TEXT, "
                + KEY_JOB_REMARK  + " TEXT, "
                + KEY_JOB_TYPE + " TEXT, PRIMARY KEY( " + KEY_JOB_ID + ", " + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(JobTypeServiceProviderItem jobTypeServiceProviderItem) {
        return insertItem(jobTypeServiceProviderItem.getJobId(),
                jobTypeServiceProviderItem.getIdentifierId(),
                jobTypeServiceProviderItem.getJobSubCategoryId(),
                jobTypeServiceProviderItem.getJobname(),
                jobTypeServiceProviderItem.getJobnewssource(),
                jobTypeServiceProviderItem.getJobremark(),
                jobTypeServiceProviderItem.getJobtype());

    }


    public long insertItem(String jobId, String identifierId, int jobSubCategoryId,
                           String jobname, String jobnewssource, String jobremark, String jobtype) {
        if (isFieldExist(identifierId, jobId)) {
            return updateItem(
                    jobId,
                    identifierId,
                    jobSubCategoryId,
                    jobname,
                    jobnewssource,
                    jobremark,
                    jobtype);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_JOB_ID, jobId);
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_JOB_SUBCATEGORY_ID, jobSubCategoryId);
        rowValue.put(KEY_JOB_NAME,jobname);
        rowValue.put(KEY_JOB_SOURCE, jobnewssource);
        rowValue.put(KEY_JOB_REMARK, jobremark);
        rowValue.put(KEY_JOB_TYPE, jobtype);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    public boolean isFieldExist(String id, String job_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (job_id.equals(cursor.getString(0)) &&id.equals(cursor.getString(1))) {
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

    private long updateItem(
            String jobId, String identifierId, int jobSubCategoryId,
            String jobname, String jobnewssource, String jobremark, String jobtype) {
;
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_JOB_ID, jobId);
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_JOB_SUBCATEGORY_ID, jobSubCategoryId);
        rowValue.put(KEY_JOB_NAME,jobname);
        rowValue.put(KEY_JOB_SOURCE, jobnewssource);
        rowValue.put(KEY_JOB_REMARK, jobremark);
        rowValue.put(KEY_JOB_TYPE, jobtype);
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND " + KEY_JOB_ID + " = ?  ",
                new String[]{identifierId + "", jobId + ""});
        closeDB();
        return ret;
    }



    private JobTypeServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _job_id = cursor.getString(0);
        String _identifier_id = cursor.getString(1);
        int _jobSubCategoryId = cursor.getInt(2);

        String _job_name = cursor.getString(3);
        String _job_news_source = cursor.getString(4);
        String _job_remark = cursor.getString(5);
        String _job_type = cursor.getString(6);


        return new JobTypeServiceProviderItem(_job_id, _identifier_id, _jobSubCategoryId
                , _job_name, _job_news_source, _job_remark, _job_type);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
