package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2017.
 */
public class EduNewDBTableMain {
    private static final String TAG = EduNewDBTableMain.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_MAIN;

    private static final String KEY_IDENTIFIER_ID = "_eduId"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";

    private static final String KEY_EDU_TYPE = "_eduType"; // 1 - text
    private static final String KEY_SHIFT = "_shift"; // 1 - text
    private static final String KEY_STUDENT_NO = "_studentNo"; // 1 - text
    private static final String KEY_TEACHER_NO = "_teachersNo"; // 1 - text
    private static final String KEY_AVG_STD = "_avgStdPerClass"; // 1 - text
    private static final String KEY_FACILITY = "_facility"; // 1 - text



    private Context tContext;

    public EduNewDBTableMain(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_COMMON_ID + "  INTEGER, "              // 1 - text

                + KEY_EDU_TYPE + " TEXT, "// 2 -
                + KEY_SHIFT + " TEXT, "// 2 - text
                + KEY_STUDENT_NO + " TEXT, "// 2 - text
                + KEY_TEACHER_NO + " TEXT, "// 2 - text
                + KEY_AVG_STD + " TEXT, "// 2 - text
                + KEY_FACILITY + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EduNewModel eduNewModel) {
        if (!isFieldExist(eduNewModel.getEduId()))
        {
            return insertItem(eduNewModel.getEduId(), eduNewModel.getCommonModel(),
                    eduNewModel.getEducationType(), eduNewModel.getShift(),
                    eduNewModel.getStudentNo(), eduNewModel.getTeachersNo(),
                    eduNewModel.getAverageStudentPerClass(), eduNewModel.getFacility());
        }
        else return updateItem(eduNewModel.getEduId(), eduNewModel.getCommonModel(),
                eduNewModel.getEducationType(), eduNewModel.getShift(),
                eduNewModel.getStudentNo(), eduNewModel.getTeachersNo(),
                eduNewModel.getAverageStudentPerClass(), eduNewModel.getFacility());

    }


    public long insertItem(int eduId, CommonModel commonId, String educationType, String shift,
                           String studentNo, String teacherNo, String avgStudentPerClass, String facility
    ) {
        if (isFieldExist(eduId)) {
            return updateItem(eduId, commonId, educationType, shift, studentNo, teacherNo, avgStudentPerClass, facility);
        }

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, eduId);
        rowValue.put(KEY_COMMON_ID, commonId.getId());
        rowValue.put(KEY_EDU_TYPE, educationType);
        rowValue.put(KEY_SHIFT, shift);
        rowValue.put(KEY_STUDENT_NO, studentNo);
        rowValue.put(KEY_TEACHER_NO, teacherNo);
        rowValue.put(KEY_AVG_STD, avgStudentPerClass);
        rowValue.put(KEY_FACILITY, facility);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return insertedId;
    }
    private long updateItem(
            int eduId, CommonModel commonId, String educationType, String shift,
            String studentNo, String teacherNo, String avgStudentPerClass, String facility
    ) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, eduId);
        rowValue.put(KEY_COMMON_ID, commonId.getId());
        rowValue.put(KEY_EDU_TYPE, educationType);
        rowValue.put(KEY_SHIFT, shift);
        rowValue.put(KEY_STUDENT_NO, studentNo);
        rowValue.put(KEY_TEACHER_NO, teacherNo);
        rowValue.put(KEY_AVG_STD, avgStudentPerClass);
        rowValue.put(KEY_FACILITY, facility);

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{eduId + ""});
        closeDB();
        return updatedId;
    }

    public void delete(String ward, String area, CommonModel commonModel)
    {
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        commonDBTable.delete(ward, area);
        DatabaseHelper databaseHelper = new DatabaseHelper(EduNewDBTableMain.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_COMMON_ID + " = " + commonModel.getId(), null);

        database.close();
    }
    public boolean isFieldExist(int id) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id == cursor.getInt(0)) {
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
    public ArrayList<EduNewModel> getAllEducationByArea(int commonId) {     // getAllEducationSubCategoriesInfo

        ArrayList<EduNewModel> educationList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + commonId, null);

        if (cursor.moveToFirst()) {
            do {

               educationList.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return educationList;
    }



    public EduNewModel getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        EduNewModel eduNewModel = null;
        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel commonModel = commonDBTable.getNodeInfo(node);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COMMON_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                eduNewModel = new EduNewModel(cursor.getInt(0), commonModel, cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return eduNewModel;
    }


    private EduNewModel cursorToSubCatList(Cursor cursor) {

        int _eduId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);

        String _eduType = cursor.getString(2);
        String _shift = cursor.getString(3);
        String _studentNo = cursor.getString(4);
        String _teachersNo = cursor.getString(5);
        String _avgStdPerClass = cursor.getString(6);
        String _facility = cursor.getString(7);

        CommonDBTable commonDBTable = new CommonDBTable(tContext);
        CommonModel _commonModel = commonDBTable.getNodeInfo(_commonId);

       return new EduNewModel(_eduId, _commonModel, _eduType, _shift, _studentNo, _teachersNo, _avgStdPerClass, _facility);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
