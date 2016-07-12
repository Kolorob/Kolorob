package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * Created by touhid on 12/26/15.
 * @author touhid
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "kolorob.db";

    public static  String DB_PATH ;
    public static final int DB_VERSION = 1;

    public static final String SERVICE_CATEGORY = "service_category";
    public static final String SUB_CATEGORY = "sub_category";
    public static final String SUB_CATEGORY_NEW = "sub_category_new";
    public static final String EDU_PROVIDER_TABLE = "edu_provider";
    public static final String EDU_PROVIDER_COURSE_TABLE = "edu_provider_course";
    public static final String EDU_PROVIDER_FEE_TABLE = "edu_provider_fee";
    public static final String EDU_PROVIDER_TABLE_NEW = "edu_provider_new";
    public static final String EDU_PROVIDER_RESULT_TABLE = "edu_provider_result";
    public static final String EDU_TRAINING_TABLE = "edu_training_table";
    public static final String EDU_TUITION_TABLE = "edu_tuition_table";
    public static final String ENT_PROVIDER_TABLE = "ent_provider";
    public static final String ENT_PROVIDER_TABLE_NEW = "ent_provider_new";
    public static final String HEALTH_PROVIDER_TABLE = "hel_provider";
    public static final String HEALTH_PROVIDER_TABLE_NEW = "hel_provider_new";
    public static final String HEALTH_PHARMACY_TABLE = "hel_phermacy";
    public static final String HEALTH_SPECIALIST_TABLE = "hel_specialist";
    public static final String HEALTH_VACCINES_TABLE = "hel_vaccine";
    public static final String HEALTH_VACCINES_TABLE_DETAILS = "hel_vaccine_details";

    public static final String ENT_BOOKSHOP_TABLE = "ent_bookshop";
    public static final String ENT_FIELD = "ent_field";
    public static final String ENT_FIT_BEAUTY = "ent_fit_beauty";
    public static final String ENT_MUSIC_GRP = "ent_music_grp";
    public static final String ENT_NGO = "ent_ngo";
    public static final String ENT_PARK = "ent_park";
    public static final String ENT_SERV_PROVIDER = "ent_service_provider";
    public static final String ENT_SHISHU_PARK = "ent_shishu_park";
    public static final String ENT_SUB_CATEGORY = "ent_sub_category";
    public static final String ENT_THEATRE = "ent_theatre";
    public static final String ENT_DETAILS_INFO = "details_info";
    public static final String Legal_Aid_Details_Table= "legal_details";
    public static final String ENT_TRAINING_CENTER = "ent_training_center";

    public static final String LEGAL_AID_SERVICE_PROVIDER_TABLE="legal_aid_provider";
    public static final String LEGAL_AID_LEGAL_ADVICE_TABLE="legal_aid_legal_advice";
    public static final String LEGAL_AID_SALISHI_TABLE="legal_aid_salishi";
    public Context context;
    public boolean mDataBaseExist;


    // TODO Write table names for all other tables
    public static final String JOB_SERVICE_PROVIDER_TABLE="job_provider";
    public static final String JOB_SERVICE_ADVERTISEMENT_TABLE="job_advertisement_table";
    public static final String JOB_SERVICE_PROVIDER_TYPE="job_provider_type";
    //tables for financial services
    public static final String FINANCIAL_SERVICE_PROVIDER_TABLE="financial_provider";
    public static final String FINANCIAL_SERVICE_BILLS="financial_bills";
    public static final String FINANCIAL_SERVICE_DETAILS="financial_details";
    public static final String FINANCIAL_SERVICE_INSURANCE="financial_insurance";
    public static final String FINANCIAL_SERVICE_LOAN="financial_loan";
    public static final String FINANCIAL_SERVICE_PAYMENT="financial_payment";
    public static final String FINANCIAL_SERVICE_SOCIAL="financial_social";
    public static final String FINANCIAL_SERVICE_TAX="financial_tax";
    public static final String FINANCIAL_SERVICE_TRANSACTION="financial_transaction";
    public static final String FINANCIAL_SERVICE_TUITION="financial_tuition";
    public static final String FINANCIAL_SERVICE_MAP="financial_mapinfo";
    public static final String FINANCIAL_SERVICE_CONTACT="financial_contactinfo";
    public static final String FINANCIAL_SERVICE_REGISTRATION="financial_registrationinfo";
    public static final String FINANCIAL_SERVICE_TIMING="financial_timinginfo";
    public static final String FINANCIAL_SERVICE_NEW="financial_new";
    public static final String GOVERNMENT_SERVICE_DETAILS="gov_details";
    public static final String GOV_MAIN="gov_main";
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + SERVICE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + SUB_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + EDU_PROVIDER_COURSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EDU_PROVIDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LEGAL_AID_SERVICE_PROVIDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LEGAL_AID_LEGAL_ADVICE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LEGAL_AID_SALISHI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + JOB_SERVICE_PROVIDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FINANCIAL_SERVICE_PROVIDER_TABLE);
        // TODO Write drop commands for all the available tables

        onCreate(db);
    }
    private boolean checkDataBase2()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
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

