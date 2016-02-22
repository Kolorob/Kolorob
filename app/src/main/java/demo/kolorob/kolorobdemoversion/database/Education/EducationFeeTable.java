package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationFeeItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by mity on 2/19/16.
 */
public class EducationFeeTable {
    private static final String TAG = EducationFeeTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_PROVIDER_FEE_TABLE;
    private static final String KEY_FEE_ID = "_fee_id"; //
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 1 - text
    private static final String KEY_PRE_FREE  = "_pre_school_free"; // 2 - text*/
    private static final String KEY_PRE_STIPEND_SPCL = "pre_school_stipend_speciality";
    private static final String KEY_PRE_STIPEND_TYPE= "pre_school_stipend_type"; //
    private static final String KEY_PRE_STIPEND_DET = "pre_school_stipend_details"; //
    private static final String KEY_PRE_MAX_FEE  = "pre_school_max_fee"; //
    private static final String KEY_PRE_MIN_FEE = "pre_school_min_fee"; //
    private static final String KEY_PRE_COACHING= "pre_school_coaching_fee"; //
    private static final String KEY_PRE_ADDITIONAL = "pre_school_additional_fee"; //

    private static final String KEY_I_V_FREE  = "i_v_free"; // 2 - text*/
    private static final String KEY_I_V_STIPEND_SPCL = "i_v_stipend_speciality";
    private static final String KEY_I_V_STIPEND_TYPE= "i_v_stipend_type"; //
    private static final String KEY_I_V_STIPEND_DET = "i_v_stipend_details"; //
    private static final String KEY_I_V_MAX_FEE  = "i_v_max_fee"; //
    private static final String KEY_I_V_MIN_FEE = "i_v_min_fee"; //
    private static final String KEY_I_V_COACHING= "i_v_coaching_fee"; //
    private static final String KEY_I_V_ADDITIONAL = "i_v_additional_fee"; //

    private static final String KEY_VI_X_FREE  = "vi_x_free"; // 2 - text*/
    private static final String KEY_VI_X_STIPEND_SPCL = "vi_x_stipend_speciality";
    private static final String KEY_VI_X_STIPEND_TYPE= "vi_x_stipend_type"; //
    private static final String KEY_VI_X_STIPEND_DET = "vi_x_stipend_details"; //
    private static final String KEY_VI_X_MAX_FEE  = "vi_x_max_fee"; //
    private static final String KEY_VI_X_MIN_FEE = "vi_x_min_fee"; //
    private static final String KEY_VI_X_COACHING= "vi_x_coaching_fee"; //
    private static final String KEY_VI_X_ADDITIONAL = "vi_x_additional_fee"; //

    private static final String KEY_XI_XII_FREE  = "xi_xii_free"; // 2 - text*/
    private static final String KEY_XI_XII_STIPEND_SPCL = "xi_xii_stipend_speciality";
    private static final String KEY_XI_XII_STIPEND_TYPE= "xi_xii_stipend_type"; //
    private static final String KEY_XI_XII_STIPEND_DET = "xi_xii_stipend_details"; //
    private static final String KEY_XI_XII_MAX_FEE  = "xi_xii_max_fee"; //
    private static final String KEY_XI_XII_MIN_FEE = "xi_xii_min_fee"; //
    private static final String KEY_XI_XII_COACHING= "xi_xii_coaching_fee"; //
    private static final String KEY_XI_XII_ADDITIONAL = "xi_xii_additional_fee"; //

    private static final String KEY_UNI_FREE  = "uni_free"; // 2 - text*/
    private static final String KEY_UNI_STIPEND_SPCL = "uni_stipend_speciality";
    private static final String KEY_UNI_STIPEND_TYPE= "uni_stipend_type"; //
    private static final String KEY_UNI_STIPEND_DET = "uni_stipend_details"; //
    private static final String KEY_UNI_MAX_FEE  = "uni_max_fee"; //
    private static final String KEY_UNI_MIN_FEE = "uni_min_fee"; //
    private static final String KEY_UNI_COACHING= "uni_coaching_fee"; //
    private static final String KEY_UNI_ADDITIONAL = "uni_additional_fee"; //
    private Context tContext;

    public EducationFeeTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FEE_ID + "  TEXT , "
                + KEY_IDENTIFIER_ID + "  TEXT , " // 0 - int
                + KEY_PRE_FREE + "  TEXT, "              // 1 - text
                + KEY_PRE_STIPEND_SPCL + " TEXT, "
                + KEY_PRE_STIPEND_TYPE + " TEXT, "// 2 - text
                + KEY_PRE_STIPEND_DET + " TEXT, "
                + KEY_PRE_MAX_FEE + " TEXT, "
                + KEY_PRE_MIN_FEE + " TEXT, "
                + KEY_PRE_COACHING + " TEXT, "
                + KEY_PRE_ADDITIONAL + " TEXT, "
                + KEY_I_V_FREE + " TEXT, "
                + KEY_I_V_STIPEND_SPCL + " TEXT, "
                + KEY_I_V_STIPEND_TYPE + " TEXT, "
                + KEY_I_V_STIPEND_DET + " TEXT, "
                + KEY_I_V_MAX_FEE+ " TEXT, "
                + KEY_I_V_MIN_FEE + " TEXT, "
                + KEY_I_V_COACHING + " TEXT, "
                + KEY_I_V_ADDITIONAL  + " TEXT, "
                + KEY_VI_X_FREE + " TEXT, "
                + KEY_VI_X_STIPEND_SPCL + " TEXT, "
                + KEY_VI_X_STIPEND_TYPE + " TEXT, "
                + KEY_VI_X_STIPEND_DET + " TEXT, "
                + KEY_VI_X_MAX_FEE + " TEXT, "
                + KEY_VI_X_MIN_FEE + " TEXT, "
                + KEY_VI_X_COACHING + " TEXT, "
                + KEY_VI_X_ADDITIONAL + " TEXT, "
                + KEY_XI_XII_FREE + " TEXT, "
                + KEY_XI_XII_STIPEND_SPCL + " TEXT, "
                + KEY_XI_XII_STIPEND_TYPE + " TEXT, "
                + KEY_XI_XII_STIPEND_DET + " TEXT, "
                + KEY_XI_XII_MAX_FEE + " TEXT, "
                + KEY_XI_XII_MIN_FEE + " TEXT, "
                + KEY_XI_XII_COACHING+ " TEXT, "
                + KEY_XI_XII_ADDITIONAL + " TEXT, "
                + KEY_UNI_FREE + " TEXT, "
                + KEY_UNI_STIPEND_SPCL + " TEXT, "
                + KEY_UNI_STIPEND_TYPE + " TEXT, "
                + KEY_UNI_STIPEND_DET + " TEXT, "
                + KEY_UNI_MAX_FEE + " TEXT, "
                + KEY_UNI_MIN_FEE+ " TEXT, "
                + KEY_UNI_COACHING + " TEXT, "
                + KEY_UNI_ADDITIONAL + " TEXT, PRIMARY KEY(" + KEY_FEE_ID + ", " + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }
    public long insertItem(EducationFeeItem educationFeeItem) {
        return insertItem(
                educationFeeItem.getFee_id(),educationFeeItem.getIdentifier_id(),educationFeeItem.getPre_school_free(),
                educationFeeItem.getPre_school_stipend_speciality(),educationFeeItem.getPre_school_stipend_type(),educationFeeItem.getPre_school_stipend_details(), educationFeeItem.getPre_school_max_fee(),educationFeeItem.getPre_school_min_fee(),educationFeeItem.getPre_school_coaching_fee(),educationFeeItem.getPre_school_additional_fee(),
                educationFeeItem.getI_v_free(),educationFeeItem.getI_v_stipend_speciality(),educationFeeItem.getI_v_stipend_type(),educationFeeItem.getI_v_stipend_details(),
                educationFeeItem.getI_v_max_fee(),educationFeeItem.getI_v_min_fee(),educationFeeItem.getI_v_coaching_fee(),educationFeeItem.getI_v_additional_fee(),
                educationFeeItem.getVi_x_free(),educationFeeItem.getVi_x_stipend_speciality(),educationFeeItem.getVi_x_stipend_type(),educationFeeItem.getVi_x_stipend_details(),
                educationFeeItem.getVi_x_max_fee(),educationFeeItem.getVi_x_min_fee(),educationFeeItem.getVi_x_coaching_fee(),educationFeeItem.getVi_x_additional_fee(),
                educationFeeItem.getXi_xii_free(),educationFeeItem.getXi_xii_stipend_speciality(),educationFeeItem.getXi_xii_stipend_type(),educationFeeItem.getXi_xii_stipend_details(),
                educationFeeItem.getXi_xii_max_fee(),educationFeeItem.getXi_xii_min_fee(),educationFeeItem.getXi_xii_coaching_fee(),educationFeeItem.getXi_xii_additional_fee(),
                educationFeeItem.getUni_free(),educationFeeItem.getUni_stipend_speciality(),educationFeeItem.getUni_stipend_type(),educationFeeItem.getUni_stipend_details(),
                educationFeeItem.getUni_max_fee(),educationFeeItem.getUni_min_fee(),educationFeeItem.getUni_coaching_fee(),educationFeeItem.getUni_additional_fee()
        );
    }

    private long insertItem(String fee_id, String identifier_id, String pre_school_free, String pre_school_stipend_speciality,
                            String pre_school_stipend_type, String pre_school_stipend_details, String pre_school_max_fee,
                            String pre_school_min_fee, String pre_school_coaching_fee, String pre_school_additional_fee, String i_v_free,
                            String i_v_stipend_speciality, String i_v_stipend_type, String i_v_stipend_details, String i_v_max_fee,
                            String i_v_min_fee, String i_v_coaching_fee, String i_v_additional_fee, String vi_x_free,
                            String vi_x_stipend_speciality, String vi_x_stipend_type, String vi_x_stipend_details, String vi_x_max_fee,
                            String vi_x_min_fee, String vi_x_coaching_fee, String vi_x_additional_fee, String xi_xii_free,
                            String xi_xii_stipend_speciality, String xi_xii_stipend_type, String xi_xii_stipend_details, String xi_xii_max_fee,
                            String xi_xii_min_fee, String xi_xii_coaching_fee, String xi_xii_additional_fee, String uni_free,
                            String uni_stipend_speciality, String uni_stipend_type, String uni_stipend_details, String uni_max_fee,
                            String uni_min_fee, String uni_coaching_fee, String uni_additional_fee){
        if (isFieldExist(fee_id,identifier_id)) {
            return updateItem(
                    fee_id, identifier_id, pre_school_free, pre_school_stipend_speciality, pre_school_stipend_type, pre_school_stipend_details,
                    pre_school_max_fee, pre_school_min_fee,pre_school_coaching_fee,pre_school_additional_fee,
                    i_v_free, i_v_stipend_speciality,i_v_stipend_type,i_v_stipend_details,i_v_max_fee,
                    i_v_min_fee,i_v_coaching_fee,i_v_additional_fee,vi_x_free,vi_x_stipend_speciality,vi_x_stipend_type,vi_x_stipend_details,
                    vi_x_max_fee,vi_x_min_fee,vi_x_coaching_fee,vi_x_additional_fee,xi_xii_free,xi_xii_stipend_speciality,xi_xii_stipend_type,
                    xi_xii_stipend_details,xi_xii_max_fee,xi_xii_min_fee,xi_xii_coaching_fee,xi_xii_additional_fee,uni_free,
                    uni_stipend_speciality,uni_stipend_type,uni_stipend_details,uni_max_fee,uni_min_fee,uni_coaching_fee,uni_additional_fee);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FEE_ID , fee_id);
        rowValue.put(KEY_IDENTIFIER_ID , identifier_id);
        rowValue.put(KEY_PRE_FREE , pre_school_free);
        rowValue.put(KEY_PRE_STIPEND_SPCL  , pre_school_stipend_speciality);
        rowValue.put(KEY_PRE_STIPEND_TYPE,pre_school_stipend_type);
        rowValue.put(KEY_PRE_STIPEND_DET , pre_school_stipend_details);
        rowValue.put(KEY_PRE_MAX_FEE ,pre_school_max_fee );
        rowValue.put(KEY_PRE_MIN_FEE  , pre_school_min_fee);
        rowValue.put(KEY_PRE_COACHING , pre_school_coaching_fee);
        rowValue.put(KEY_PRE_ADDITIONAL,pre_school_additional_fee);

        rowValue.put(KEY_I_V_FREE , i_v_free);
        rowValue.put(KEY_I_V_STIPEND_SPCL ,i_v_stipend_speciality);
        rowValue.put(KEY_I_V_STIPEND_TYPE, i_v_stipend_type);
        rowValue.put(KEY_I_V_STIPEND_DET  , i_v_stipend_details);
        rowValue.put(KEY_I_V_MAX_FEE , i_v_max_fee);
        rowValue.put(KEY_I_V_MIN_FEE , i_v_min_fee);
        rowValue.put(KEY_I_V_COACHING  , i_v_coaching_fee);
        rowValue.put(KEY_I_V_ADDITIONAL  , i_v_additional_fee);

        rowValue.put(KEY_VI_X_FREE , vi_x_free);
        rowValue.put(KEY_VI_X_STIPEND_SPCL , vi_x_stipend_speciality);
        rowValue.put(KEY_VI_X_STIPEND_TYPE , vi_x_stipend_type);
        rowValue.put(KEY_VI_X_STIPEND_DET  , vi_x_stipend_details);
        rowValue.put(KEY_VI_X_MAX_FEE , vi_x_max_fee);
        rowValue.put(KEY_VI_X_MIN_FEE , vi_x_min_fee);
        rowValue.put(KEY_VI_X_COACHING , vi_x_coaching_fee);
        rowValue.put(KEY_VI_X_ADDITIONAL,vi_x_additional_fee);

        rowValue.put(KEY_XI_XII_FREE,xi_xii_free);
        rowValue.put(KEY_XI_XII_STIPEND_SPCL,xi_xii_stipend_speciality);
        rowValue.put(KEY_XI_XII_STIPEND_TYPE,xi_xii_stipend_type);
        rowValue.put(KEY_XI_XII_STIPEND_DET , xi_xii_stipend_details);
        rowValue.put(KEY_XI_XII_MAX_FEE  , xi_xii_max_fee);
        rowValue.put(KEY_XI_XII_MIN_FEE  , xi_xii_min_fee);
        rowValue.put(KEY_XI_XII_COACHING  , xi_xii_coaching_fee );
        rowValue.put(KEY_XI_XII_ADDITIONAL   , xi_xii_additional_fee );

        rowValue.put(KEY_UNI_FREE  , uni_free);
        rowValue.put(KEY_UNI_STIPEND_SPCL  , uni_stipend_speciality );
        rowValue.put(KEY_UNI_STIPEND_TYPE  , uni_stipend_type );
        rowValue.put(KEY_UNI_STIPEND_DET,uni_stipend_details);
        rowValue.put(KEY_UNI_MAX_FEE , uni_max_fee);
        rowValue.put(KEY_UNI_MIN_FEE  , uni_min_fee);
        rowValue.put(KEY_UNI_COACHING  , uni_coaching_fee);
        rowValue.put(KEY_UNI_ADDITIONAL  , uni_additional_fee );
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;


    }

    private long updateItem(String fee_id, String identifier_id, String pre_school_free, String pre_school_stipend_speciality,
                            String pre_school_stipend_type, String pre_school_stipend_details, String pre_school_max_fee,
                            String pre_school_min_fee, String pre_school_coaching_fee, String pre_school_additional_fee, String i_v_free,
                            String i_v_stipend_speciality, String i_v_stipend_type, String i_v_stipend_details, String i_v_max_fee,
                            String i_v_min_fee, String i_v_coaching_fee, String i_v_additional_fee, String vi_x_free,
                            String vi_x_stipend_speciality, String vi_x_stipend_type, String vi_x_stipend_details, String vi_x_max_fee,
                            String vi_x_min_fee, String vi_x_coaching_fee, String vi_x_additional_fee, String xi_xii_free,
                            String xi_xii_stipend_speciality, String xi_xii_stipend_type, String xi_xii_stipend_details, String xi_xii_max_fee,
                            String xi_xii_min_fee, String xi_xii_coaching_fee, String xi_xii_additional_fee, String uni_free,
                            String uni_stipend_speciality, String uni_stipend_type, String uni_stipend_details, String uni_max_fee,
                            String uni_min_fee, String uni_coaching_fee, String uni_additional_fee) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FEE_ID , fee_id);
        rowValue.put(KEY_IDENTIFIER_ID , identifier_id);
        rowValue.put(KEY_PRE_FREE , pre_school_free);
        rowValue.put(KEY_PRE_STIPEND_SPCL  , pre_school_stipend_speciality);
        rowValue.put(KEY_PRE_STIPEND_TYPE,pre_school_stipend_type);
        rowValue.put(KEY_PRE_STIPEND_DET , pre_school_stipend_details);
        rowValue.put(KEY_PRE_MAX_FEE ,pre_school_max_fee );
        rowValue.put(KEY_PRE_MIN_FEE  , pre_school_min_fee);
        rowValue.put(KEY_PRE_COACHING , pre_school_coaching_fee);
        rowValue.put(KEY_PRE_ADDITIONAL,pre_school_additional_fee);

        rowValue.put(KEY_I_V_FREE , i_v_free);
        rowValue.put(KEY_I_V_STIPEND_SPCL ,i_v_stipend_speciality);
        rowValue.put(KEY_I_V_STIPEND_TYPE, i_v_stipend_type);
        rowValue.put(KEY_I_V_STIPEND_DET  , i_v_stipend_details);
        rowValue.put(KEY_I_V_MAX_FEE , i_v_max_fee);
        rowValue.put(KEY_I_V_MIN_FEE , i_v_min_fee);
        rowValue.put(KEY_I_V_COACHING  , i_v_coaching_fee);
        rowValue.put(KEY_I_V_ADDITIONAL  , i_v_additional_fee);

        rowValue.put(KEY_VI_X_FREE , vi_x_free);
        rowValue.put(KEY_VI_X_STIPEND_SPCL , vi_x_stipend_speciality);
        rowValue.put(KEY_VI_X_STIPEND_TYPE , vi_x_stipend_type);
        rowValue.put(KEY_VI_X_STIPEND_DET  , vi_x_stipend_details);
        rowValue.put(KEY_VI_X_MAX_FEE , vi_x_max_fee);
        rowValue.put(KEY_VI_X_MIN_FEE , vi_x_min_fee);
        rowValue.put(KEY_VI_X_COACHING , vi_x_coaching_fee);
        rowValue.put(KEY_VI_X_ADDITIONAL,vi_x_additional_fee);

        rowValue.put(KEY_XI_XII_FREE,xi_xii_free);
        rowValue.put(KEY_XI_XII_STIPEND_SPCL,xi_xii_stipend_speciality);
        rowValue.put(KEY_XI_XII_STIPEND_TYPE,xi_xii_stipend_type);
        rowValue.put(KEY_XI_XII_STIPEND_DET , xi_xii_stipend_details);
        rowValue.put(KEY_XI_XII_MAX_FEE  , xi_xii_max_fee);
        rowValue.put(KEY_XI_XII_MIN_FEE  , xi_xii_min_fee);
        rowValue.put(KEY_XI_XII_COACHING  , xi_xii_coaching_fee );
        rowValue.put(KEY_XI_XII_ADDITIONAL   , xi_xii_additional_fee );

        rowValue.put(KEY_UNI_FREE  , uni_free);
        rowValue.put(KEY_UNI_STIPEND_SPCL  , uni_stipend_speciality );
        rowValue.put(KEY_UNI_STIPEND_TYPE  , uni_stipend_type );
        rowValue.put(KEY_UNI_STIPEND_DET,uni_stipend_details);
        rowValue.put(KEY_UNI_MAX_FEE , uni_max_fee);
        rowValue.put(KEY_UNI_MIN_FEE  , uni_min_fee);
        rowValue.put(KEY_UNI_COACHING  , uni_coaching_fee);
        rowValue.put(KEY_UNI_ADDITIONAL  , uni_additional_fee );
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND " + KEY_FEE_ID + " = ? ",
                new String[]{identifier_id + "", fee_id + ""});
        closeDB();
        return ret;
    }
    public boolean isFieldExist(String feeid,String idenid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (feeid.equals(cursor.getString(0))&&idenid.equals(cursor.getString(1))) {
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
    public ArrayList<EducationFeeItem> getEduFee(String idenId) {
        ArrayList<EducationFeeItem> FeeList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = '" + idenId + "'", null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                FeeList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return FeeList;
    }
    public static EducationFeeItem cursorToSubCatList(Cursor cursor) {
        String _fee_id=cursor.getString(0);
        String _identifier_id=cursor.getString(1);
        String _pre_school_free=cursor.getString(2);
        String _pre_school_stipend_speciality=cursor.getString(3);
        String _pre_school_stipend_type=cursor.getString(4);
        String _pre_school_stipend_details=cursor.getString(5);
        String _pre_school_max_fee=cursor.getString(6);
        String _pre_school_min_fee=cursor.getString(7);
        String _pre_school_coaching_fee=cursor.getString(8);
        String _pre_school_additional_fee=cursor.getString(9);

        String _i_v_free=cursor.getString(10);
        String _i_v_stipend_speciality=cursor.getString(11);
        String _i_v_stipend_type=cursor.getString(12);
        String _i_v_stipend_details=cursor.getString(13);
        String _i_v_max_fee=cursor.getString(14);
        String _i_v_min_fee=cursor.getString(15);
        String _i_v_coaching_fee=cursor.getString(16);
        String _i_v_additional_fee=cursor.getString(17);

        String _vi_x_free=cursor.getString(18);
        String _vi_x_stipend_speciality=cursor.getString(19);
        String _vi_x_stipend_type=cursor.getString(20);
        String _vi_x_stipend_details=cursor.getString(21);
        String _vi_x_max_fee=cursor.getString(22);
        String _vi_x_min_fee=cursor.getString(23);
        String _vi_x_coaching_fee=cursor.getString(24);
        String _vi_x_additional_fee=cursor.getString(25);

        String _xi_xii_free = cursor.getString(26);
        String _xi_xii_stipend_speciality = cursor.getString(27);
        String _xi_xii_stipend_type=cursor.getString(28);
        String _xi_xii_stipend_details=cursor.getString(29);
        String _xi_xii_max_fee=cursor.getString(30);
        String _xi_xii_min_fee=cursor.getString(31);
        String _xi_xii_coaching_fee=cursor.getString(32);
        String _xi_xii_additional_fee = cursor.getString(33);

        String _uni_free = cursor.getString(34);
        String _uni_stipend_speciality= cursor.getString(35);
        String _uni_stipend_type=cursor.getString(36);
        String _uni_stipend_details=cursor.getString(37);
        String _uni_max_fee=cursor.getString(38);
        String _uni_min_fee = cursor.getString(39);
        String _uni_coaching_fee = cursor.getString(40);
        String _uni_additional_fee= cursor.getString(41);



        return new EducationFeeItem( _fee_id,
                _identifier_id,
                _pre_school_free,
                _pre_school_stipend_speciality,
                _pre_school_stipend_type,
                _pre_school_stipend_details,
                _pre_school_max_fee,
                _pre_school_min_fee,
                _pre_school_coaching_fee,
                _pre_school_additional_fee,
                _i_v_free,
                _i_v_stipend_speciality,
                _i_v_stipend_type,
                _i_v_stipend_details,
                _i_v_max_fee,
                _i_v_min_fee,
                _i_v_coaching_fee,
                _i_v_additional_fee,
                _vi_x_free,
                _vi_x_stipend_speciality,
                _vi_x_stipend_type,
                _vi_x_stipend_details,
                _vi_x_max_fee,
                _vi_x_min_fee,_vi_x_coaching_fee,_vi_x_additional_fee,
                _xi_xii_free,
                _xi_xii_stipend_speciality,_xi_xii_stipend_type,
                _xi_xii_stipend_details,_xi_xii_max_fee,_xi_xii_min_fee,
                _xi_xii_coaching_fee,_xi_xii_additional_fee,
                _uni_free,_uni_stipend_speciality,
                _uni_stipend_type,_uni_stipend_details,_uni_max_fee,_uni_min_fee,
                _uni_coaching_fee,_uni_additional_fee);

    }
    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
