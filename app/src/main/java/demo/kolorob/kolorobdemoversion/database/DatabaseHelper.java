package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by touhid on 12/26/15.
 * @author touhid
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "kolorob.db";
    public static final int DB_VERSION = 1;

    public static final String SERVICE_CATEGORY = "service_category";
    public static final String SUB_CATEGORY = "sub_category";

    public static final String EDU_PROVIDER_TABLE = "edu_provider";
    public static final String EDU_PROVIDER_COURSE_TABLE = "edu_provider_course";
    public static final String EDU_PROVIDER_FEE_TABLE = "edu_provider_fee";
    public static final String EDU_PROVIDER_RESULT_TABLE = "edu_provider_result";
    public static final String EDU_SUB_CAT_TABLE = "edu_sub_category";

    public static final String ENT_PROVIDER_TABLE = "ent_provider";
    public static final String HEALTH_PROVIDER_TABLE = "hel_provider";

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
    public static final String ENT_TRAINING_CENTER = "ent_training_center";

    // TODO Write table names for all other tables
public static final String FINANCIAL_SERVICE_PROVIDER="finance_provider";
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
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

        db.execSQL("DROP TABLE IF EXISTS " + EDU_PROVIDER_TABLE);

        // TODO Write drop commands for all the available tables

        onCreate(db);
    }
}
