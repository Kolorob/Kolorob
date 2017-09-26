package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;

/**
 * Created by israt.jahan on 2/9/2017.
 */


public class EduNewDBTableMain extends BaseDBTable <EduNewModel> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_MAIN;
    private static final String KEY_IDENTIFIER_ID = "_eduId"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";
    private static final String KEY_EDU_TYPE = "_eduType"; // 1 - text
    private static final String KEY_SHIFT = "_shift"; // 1 - text
    private static final String KEY_STUDENT_NO = "_studentNo"; // 1 - text
    private static final String KEY_TEACHER_NO = "_teachersNo"; // 1 - text
    private static final String KEY_AVG_STD = "_avgStdPerClass"; // 1 - text
    private static final String KEY_FACILITY = "_facility"; // 1 - text


    public EduNewDBTableMain(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
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


    public long insertItem(EduNewModel eduNewModel) {
        if (!isFieldExist(eduNewModel.getEduId()))
        {
            return insertItem(eduNewModel.getEduId(), eduNewModel.getCommonModel().getId(),
                    eduNewModel.getEducationType(), eduNewModel.getShift(),
                    eduNewModel.getStudentNo(), eduNewModel.getTeachersNo(),
                    eduNewModel.getAverageStudentPerClass(), eduNewModel.getFacility());
        }
        else return updateItem(eduNewModel.getEduId(), eduNewModel.getCommonModel().getId(),
                eduNewModel.getEducationType(), eduNewModel.getShift(),
                eduNewModel.getStudentNo(), eduNewModel.getTeachersNo(),
                eduNewModel.getAverageStudentPerClass(), eduNewModel.getFacility());

    }


    public long insertItem(int eduId, int commonId, String educationType, String shift,
                           String studentNo, String teacherNo, String avgStudentPerClass, String facility
    ) {
        if (isFieldExist(eduId)) {
            return updateItem(eduId, commonId, educationType, shift, studentNo, teacherNo, avgStudentPerClass, facility);
        }

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, eduId);
        rowValue.put(KEY_COMMON_ID, commonId);
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
            int eduId, int commonId, String educationType, String shift,
            String studentNo, String teacherNo, String avgStudentPerClass, String facility
    ) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, eduId);
        rowValue.put(KEY_COMMON_ID, commonId);
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


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }


    public ArrayList <EduNewModel> getDetailsByCommonId(int commonId) {     // getAllEducationSubCategoriesInfo
        return super.getDetailsByCommonId(commonId, TABLE_NAME, KEY_COMMON_ID);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public EduNewModel getNodeInfo(int node) {

        CommonModel commonModel = getCommonModelFromId(node);
        EduNewModel eduNewModel = null;
        SQLiteDatabase db = openDB();

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

    public ArrayList <EduNewModel> getDataFromId(int nodeId) {
        return super.getDataFromId(nodeId, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public EduNewModel cursorToModel(Cursor cursor) {

        int _eduId = cursor.getInt(0);
        int _commonId = cursor.getInt(1);

        String _eduType = cursor.getString(2);
        String _shift = cursor.getString(3);
        String _studentNo = cursor.getString(4);
        String _teachersNo = cursor.getString(5);
        String _avgStdPerClass = cursor.getString(6);
        String _facility = cursor.getString(7);

        CommonModel _commonModel = getCommonModelFromId(_commonId);

       return new EduNewModel(_eduId, _commonModel, _eduType, _shift, _studentNo, _teachersNo, _avgStdPerClass, _facility);

    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
