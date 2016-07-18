package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class EducationResultDetailsTable {
    private static final String TAG = EducationResultDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_PROVIDER_RESULT_TABLE;
    private static final String KEY_NODE_ID = "_eduId";
    private static final String KEY_SERVICE_ID = "_sproviderid";
    private static final String KEY_EXAM_NAME = "_examname"; // 1 - text
    private static final String KEY_STUDENT_NO= "_studentno"; //
    private static final String KEY_PASSED = "_passed"; // 0 -integer
    private static final String KEY_GOLDEN = "_goldena"; // 1 - text
    private static final String KEY_APLUS = "_aplus"; //


    private Context tContext;

    public EducationResultDetailsTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_SERVICE_ID + " INTEGER , "
                + KEY_EXAM_NAME + "  TEXT  , " // 0 - int "
                + KEY_STUDENT_NO + "  TEXT , "
                + KEY_PASSED   + "  TEXT , "
                + KEY_GOLDEN + "  TEXT  , " // 0 - int "
                + KEY_APLUS + "  TEXT , PRIMARY KEY(" + KEY_NODE_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EducationResultItemNew educationResultItemNew) {
        return insertItem(
                educationResultItemNew.getEduId(),educationResultItemNew.getServiceproviderId(),educationResultItemNew.getExamname(),educationResultItemNew.getStudentno(),
                educationResultItemNew.getPassed(),educationResultItemNew.getGoldena(),educationResultItemNew.getAplus()
        );
    }
    public ArrayList<EducationResultItemNew> getAllSubCat() {
        ArrayList<EducationResultItemNew> siList = new ArrayList<>();

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
    public long insertItem(int eduId, int serviceproviderId, String examname, String studentno,
                           String passed, String goldena, String aplus) {
        if (isFieldExist(eduId)) {
            return updateItem(
                    eduId,serviceproviderId,
                    examname,
                    studentno,
                    passed,
                    goldena,aplus);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_EXAM_NAME, examname);
        rowValue.put(KEY_STUDENT_NO, studentno);
        rowValue.put(KEY_PASSED, passed);
        rowValue.put(KEY_GOLDEN, goldena);

        rowValue.put(KEY_APLUS , aplus);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int eduId, int serviceproviderId, String examname, String studentno,
                            String passed, String goldena, String aplus) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_EXAM_NAME, examname);
        rowValue.put(KEY_STUDENT_NO, studentno);
        rowValue.put(KEY_PASSED, passed);
        rowValue.put(KEY_GOLDEN, goldena);

        rowValue.put(KEY_APLUS , aplus);


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

    private EducationResultItemNew cursorToSubCatList(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        int _sproviderId = cursor.getInt(1);
        String _examname= cursor.getString(2);
        String _studentno = cursor.getString(3);
        String _passed = cursor.getString(4);
        String _goldena = cursor.getString(5);
        String _aplus= cursor.getString(6);





        return new EducationResultItemNew(_eduId,_sproviderId,
                _examname,_studentno,_passed,_goldena,_aplus);
    }

    public ArrayList<EducationResultItemNew> getResultInfo(int node_id) {
        ArrayList<EducationResultItemNew> subCatList = new ArrayList<>();
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

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
