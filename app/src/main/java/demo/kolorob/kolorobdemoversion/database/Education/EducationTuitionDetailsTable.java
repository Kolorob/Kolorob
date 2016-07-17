package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTuitionDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class EducationTuitionDetailsTable {
    private static final String TAG = EducationTuitionDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_TUITION_TABLE;
    private static final String KEY_NODE_ID = "_eduId";
    private static final String KEY_SERVICE_ID = "_sproviderid";
    private static final String KEY_TUITION_FREE = "_tuitionfree"; // 1 - text
    private static final String KEY_TUITION_LEVEL = "_tuitionlevel"; //
    private static final String KEY_TUITION_STIPEND = "_tuitionstipendfacility"; // 0 -integer
    private static final String KEY_TUITION_STIPEND_TYPE = "_tuitionstipendtype"; // 1 - text
    private static final String KEY_TUITION_DETAILS = "_tuitiondetails"; //
    private static final String KEY_TUITION_MIN_FEE = "_tuitionminfee";
    private static final String KEY_TUITION_MAX_FEE = "_tuitionmaxfee"; // 2 - text*/
    private static final String KEY_TUITION_MIN_COACHING = "_tuitionmincoaching";
    private static final String KEY_TUITION_MAX_COACHING = "_tuitionmaxcoaching";
    private static final String KEY_TUITION_ADDITIONAL = "_tuitionadditional";

    private Context tContext;

    public EducationTuitionDetailsTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_SERVICE_ID + " INTEGER , "
                + KEY_TUITION_FREE + "  TEXT  , " // 0 - int "
                + KEY_TUITION_LEVEL + "  TEXT , "
                + KEY_TUITION_STIPEND   + "  TEXT , "
                + KEY_TUITION_STIPEND_TYPE + "  TEXT  , " // 0 - int "
                + KEY_TUITION_DETAILS + "  TEXT , "
                + KEY_TUITION_MIN_FEE   + "  TEXT , "
                + KEY_TUITION_MAX_FEE + "  TEXT  , " // 0 - int "
                + KEY_TUITION_MIN_COACHING + "  TEXT , "
                + KEY_TUITION_MAX_COACHING   + "  TEXT , "
                + KEY_TUITION_ADDITIONAL + "  TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    public ArrayList<EducationTuitionDetailsItem> gettuitionInfo(int node_id) {
        ArrayList<EducationTuitionDetailsItem> subCatList = new ArrayList<>();
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
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EducationTuitionDetailsItem educationTuitionDetailsItem) {
        return insertItem(
               educationTuitionDetailsItem.getEduId(),educationTuitionDetailsItem.getServiceproviderId(), educationTuitionDetailsItem.getTuitionfree(),educationTuitionDetailsItem.getTuitionlevel(),
                educationTuitionDetailsItem.getTuitionstipendfacility(),educationTuitionDetailsItem.getTuitionstipendtype(),
                educationTuitionDetailsItem.getTuitiondetails(),educationTuitionDetailsItem.getTuitionminfee(),educationTuitionDetailsItem.getTuitionmaxfee(),
                educationTuitionDetailsItem.getTuitionmincoaching(),educationTuitionDetailsItem.getTuitionmaxcoaching(),educationTuitionDetailsItem.getTuitionadditional()
        );
    }
    public long insertItem(int eduId, int serviceproviderId, String tuitionfree, String tuitionlevel,
                           String tuitionstipendfacility, String tuitionstipendtype, String tuitiondetails,
                           String tuitionminfee, String tuitionmaxfee, String tuitionmincoaching,
                           String tuitionmaxcoaching, String tuitionadditional) {
        if (isFieldExist(eduId)) {
            return updateItem(
                    eduId,serviceproviderId,
                    tuitionfree,
                    tuitionlevel,
                    tuitionstipendfacility,
                    tuitionstipendtype,tuitiondetails,tuitionminfee,tuitionmaxfee,tuitionmincoaching,tuitionmaxcoaching,tuitionadditional);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_SERVICE_ID , serviceproviderId);
        rowValue.put(KEY_TUITION_FREE, tuitionfree);
        rowValue.put(KEY_TUITION_LEVEL, tuitionlevel);
        rowValue.put(KEY_TUITION_STIPEND, tuitionstipendfacility);
        rowValue.put(KEY_TUITION_STIPEND_TYPE, tuitionstipendtype);

        rowValue.put(KEY_TUITION_DETAILS , tuitiondetails);
        rowValue.put(KEY_TUITION_MIN_FEE, tuitionminfee);
        rowValue.put(KEY_TUITION_MAX_FEE, tuitionmaxfee);
        rowValue.put(KEY_TUITION_MIN_COACHING, tuitionmincoaching);
        rowValue.put(KEY_TUITION_MAX_COACHING, tuitionmaxcoaching);

        rowValue.put(KEY_TUITION_ADDITIONAL , tuitionadditional);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int eduId,  int serviceproviderId,String tuitionfree, String tuitionlevel, String tuitionstipendfacility,
                            String tuitionstipendtype, String tuitiondetails, String tuitionminfee, String tuitionmaxfee,
                            String tuitionmincoaching, String tuitionmaxcoaching, String tuitionadditional) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_SERVICE_ID , serviceproviderId);
        rowValue.put(KEY_TUITION_FREE, tuitionfree);
        rowValue.put(KEY_TUITION_LEVEL, tuitionlevel);
        rowValue.put(KEY_TUITION_STIPEND, tuitionstipendfacility);
        rowValue.put(KEY_TUITION_STIPEND_TYPE, tuitionstipendtype);

        rowValue.put(KEY_TUITION_DETAILS , tuitiondetails);
        rowValue.put(KEY_TUITION_MIN_FEE, tuitionminfee);
        rowValue.put(KEY_TUITION_MAX_FEE, tuitionmaxfee);
        rowValue.put(KEY_TUITION_MIN_COACHING, tuitionmincoaching);
        rowValue.put(KEY_TUITION_MAX_COACHING, tuitionmaxcoaching);

        rowValue.put(KEY_TUITION_ADDITIONAL , tuitionadditional);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{eduId + ""});
        closeDB();
        return ret;

    }

    public ArrayList<EducationTuitionDetailsItem> getAllSubCat() {
        ArrayList<EducationTuitionDetailsItem> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
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

    private EducationTuitionDetailsItem cursorToSubCatList(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        int _sproviderId = cursor.getInt(1);
        String _tuitionfree= cursor.getString(2);
        String _tuitionlevel = cursor.getString(3);
        String _tuitionstipendfacility = cursor.getString(4);
        String _tuitionstipendtype = cursor.getString(5);
        String _tuitiondetails= cursor.getString(6);
        String _tuitionminfee = cursor.getString(7);
        String _tuitionmaxfee = cursor.getString(8);
        String _tuitionmincoaching = cursor.getString(9);
        String _tuitionmaxcoaching= cursor.getString(10);
        String _tuitionadditional = cursor.getString(11);



        return new EducationTuitionDetailsItem(_eduId,_sproviderId,
                _tuitionfree,_tuitionlevel,_tuitionstipendfacility,_tuitionstipendtype,_tuitiondetails,_tuitionminfee,
                _tuitionmaxfee,_tuitionmincoaching,_tuitionmaxcoaching,_tuitionadditional);
    }



    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
