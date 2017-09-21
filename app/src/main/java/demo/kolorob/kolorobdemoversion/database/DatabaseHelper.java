package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by touhid on 12/26/15.
 * @author touhid
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "kolorobupdatedv2.db";

    public static  String DB_PATH ;
    public static final int DB_VERSION = 4;   // changed here

    public static final String AREASTORED= "areastored";

    public static final String COMMON_DB = "common";
    public static final String EDU_NEW_DB_MAIN= "edu_ndb_main";
    public static final String EDU_NEW_DB_SCHOOL= "edu_ndb_school";
    public static final String EDU_NEW_DB_TRAINING= "edu_ndb_train";
    public static final String EDU_PROVIDER_RESULT_TABLE = "edu_provider_result";  // NO DB IS CREATED

    public static final String ENT_PROVIDER_TABLE_DBNEW= "ent_provider_newdb";
    public static final String FINANCIAL_NEWDB="financial_providerr";

    public static final String GOV_NEW="gov_main_new";

    public static final String HEALTH_NEW_DB_MAIN= "hel_ndb_main";
    public static final String HEALTH_NEW_DB_PHARMA= "hel_ndb_pharma";
    public static final String HEALTH_NEW_DB_HOS= "hel_ndb_hos";


    public static final String LEGALAIDNEWDBTABLE="legal_aid_main";
    public static final String NGONEWDBTABLE="ngo_main";
    public static final String RELIGIOUSNEWDBTABLE="religious_main";

    public static final String SERVICE_CATEGORY = "service_category";
    public static final String SUB_CATEGORY_NEW = "sub_category_new";
    public static final String AREAS = "areas";
    public static final String WARDS = "wards";
    public static final String CITYCORPORATIONS = "city_corporations";




    // =============   NOT IN USE ===================

    public static final String RATING = "rating_table";
    public static final String COMMENT = "comment_table";
    public static final String SUB_CATEGORY = "sub_category";

    public static final String EDU_PROVIDER_TABLE = "edu_provider";
    public static final String EDU_PROVIDER_COURSE_TABLE = "edu_provider_course";







    //sqls
    public static String sql=null;

    public Context context;


    // TODO Write table names for all other tables
    public static final String JOB_SERVICE_PROVIDER_TABLE="job_provider";
    public static final String JOB_SERVICE_ADVERTISEMENT_TABLE="job_advertisement_table";
    public static final String JOB_SERVICE_PROVIDER_TYPE="job_provider_type";
    //tables for financial services




    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            Log.d("tasks", "Tasks remaining: " + DB_PATH);
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    public static int getDbVersion() {
        return DB_VERSION;
    }

    public static String getAlterQuery(String tableName, String columnName){
        return "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " TEXT";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        //if(newVersion > 3) {

            Log.e("", "NEW version: " + newVersion + "old: " +oldVersion);



            db.execSQL("DROP TABLE IF EXISTS " + EDU_NEW_DB_SCHOOL);
            db.execSQL("DROP TABLE IF EXISTS " + EDU_NEW_DB_TRAINING);
            db.execSQL("DROP TABLE IF EXISTS " + EDU_PROVIDER_RESULT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + ENT_PROVIDER_TABLE_DBNEW);
            db.execSQL("DROP TABLE IF EXISTS " + FINANCIAL_NEWDB);
            db.execSQL("DROP TABLE IF EXISTS " + GOV_NEW);
            db.execSQL("DROP TABLE IF EXISTS " + HEALTH_NEW_DB_HOS);
            db.execSQL("DROP TABLE IF EXISTS " + HEALTH_NEW_DB_MAIN);
            db.execSQL("DROP TABLE IF EXISTS " + HEALTH_NEW_DB_PHARMA);
            //db.execSQL("DROP TABLE IF EXISTS " + JOB_SERVICE_ADVERTISEMENT_TABLE);
            //db.execSQL("DROP TABLE IF EXISTS " + JOB_SERVICE_PROVIDER_TABLE);
            //db.execSQL("DROP TABLE IF EXISTS " + JOB_SERVICE_PROVIDER_TYPE);
            db.execSQL("DROP TABLE IF EXISTS " + LEGALAIDNEWDBTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + NGONEWDBTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + RELIGIOUSNEWDBTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + AREASTORED);
            db.execSQL("DROP TABLE IF EXISTS " + SERVICE_CATEGORY);
            db.execSQL("DROP TABLE IF EXISTS " + AREAS);
            db.execSQL("DROP TABLE IF EXISTS " + WARDS);
            db.execSQL("DROP TABLE IF EXISTS " + CITYCORPORATIONS);
            db.execSQL("DROP TABLE IF EXISTS " + SUB_CATEGORY_NEW);
            db.execSQL("DROP TABLE IF EXISTS " + EDU_NEW_DB_MAIN);


            //  db.execSQL("DROP TABLE IF EXISTS " + FINANCIAL_SERVICE_PROVIDER_TABLE);
            // }
            /*db.execSQL(getAlterQuery(EDU_NEW_DB_MAIN, "__parentarea"));
            db.execSQL(getAlterQuery(HEALTH_NEW_DB_MAIN, "__parentarea"));
            db.execSQL(getAlterQuery(FINANCIAL_NEWDB, "__parentarea"));
            db.execSQL(getAlterQuery(GOV_NEW, "__parentarea"));
            db.execSQL(getAlterQuery(LEGALAIDNEWDBTABLE, "__parentarea"));
            db.execSQL(getAlterQuery(ENT_PROVIDER_TABLE_DBNEW, "__parentarea"));*/


       // }





        // TODO Write drop commands for all the available tables

        onCreate(db);
    }

    public static boolean checkDataBase(Context c) {
        File f = new File("/data/data/" + c.getPackageName() + "/databases/"
                + DB_NAME);
        if (!f.exists())
            return false;
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase
                    .openDatabase("/data/data/" + c.getPackageName()
                                    + "/databases/" + DB_NAME, null,
                            SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return checkDB != null ? true : false;
    }
    public boolean isTableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+DatabaseHelper.EDU_PROVIDER_TABLE ,null);
        if (!cursor.moveToFirst())
        {
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

}

