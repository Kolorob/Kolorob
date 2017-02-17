package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class EduNewDBTableSchool {
    private static final String TAG = EduNewDBTableSchool.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_SCHOOL;
    private static final String KEY_NODE_ID = "_eduId";
    private static final String KEY_STIPEND = "_stipend";
    private static final String KEY_PRIMARY_FEES = "_primary"; // 1 - text
    private static final String KEY_SECONDARY_FEES= "_secondary"; //
    private static final String KEY_COLLEGE_FEES = "_collage"; // 0 -integer




    private Context tContext;

    public EduNewDBTableSchool(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_STIPEND + " TEXT , "
                + KEY_PRIMARY_FEES + "  TEXT  , " // 0 - int "
                + KEY_SECONDARY_FEES + "  TEXT , "
                + KEY_COLLEGE_FEES + "  TEXT , PRIMARY KEY(" + KEY_NODE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EduNewSchoolModel eduNewSchoolModel) {
        return insertItem(
                eduNewSchoolModel.getId(),eduNewSchoolModel.getStipend(),
                eduNewSchoolModel.getPrimary_fees(),eduNewSchoolModel.getSecondary_fees(),
                eduNewSchoolModel.getCollage_fees()
        );
    }

    public long insertItem(int eduId, String stipend, String primary, String secondary,
                           String collage ) {
        if (isFieldExist(eduId)) {
            return updateItem(
                    eduId,stipend,
                    primary,
                    secondary,
                    collage);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_STIPEND, stipend);
        rowValue.put(KEY_PRIMARY_FEES, primary);
        rowValue.put(KEY_SECONDARY_FEES, secondary);


        rowValue.put(KEY_COLLEGE_FEES , collage);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int eduId, String stipend, String primary, String secondary,
                            String collage) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_STIPEND, stipend);
        rowValue.put(KEY_PRIMARY_FEES, primary);
        rowValue.put(KEY_SECONDARY_FEES, secondary);


        rowValue.put(KEY_COLLEGE_FEES , collage);


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
    public ArrayList<EduNewSchoolModel> getschoolInfo(int node_id) {
        ArrayList<EduNewSchoolModel> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_NODE_ID +" = "+node_id, null);

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
    private EduNewSchoolModel cursorToSubCatList(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        String _stipend = cursor.getString(1);
        String _primary= cursor.getString(2);
        String _secondary = cursor.getString(3);
        String _collage = cursor.getString(4);







        return new EduNewSchoolModel(_eduId,_stipend,
                _primary,_secondary,_collage);
    }


    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
