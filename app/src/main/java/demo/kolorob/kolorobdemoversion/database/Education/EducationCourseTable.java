package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/8/2016.
 */
public class EducationCourseTable {
    private static final String TAG = EducationCourseTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_PROVIDER_COURSE_TABLE;
    private static final String KEY_COURSE_ID = "_courseId"; //
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer// 1 - text
    private static final String KEY_EDU_SUBCATEGORY_ID  = "_edu_subcategory_id"; // 2 - text*/
    private static final String KEY_COURSE_NAME = "_educoursename";
    private static final String KEY_COURSE_DURATION = "_educourseduration";
    private static final String KEY_COURSE_ADMISSION = "_educourseadmissiontime"; //
    private static final String KEY_COURSE_COST = "_educoursecost"; //
    private static final String KEY_COURSE_TYPE  = "_educoursetype"; //
    private Context tContext;

    public EducationCourseTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_COURSE_ID     + " TEXT , "
                + KEY_IDENTIFIER_ID + "  TEXT , " // 0 - int// 1 - text
                + KEY_EDU_SUBCATEGORY_ID + " INTEGER, "
                + KEY_COURSE_NAME + " TEXT, "// 2 - text
                + KEY_COURSE_DURATION + " TEXT, "
                + KEY_COURSE_ADMISSION + " TEXT, "
                + KEY_COURSE_COST + " TEXT, "
                + KEY_COURSE_TYPE + " TEXT, PRIMARY KEY(" + KEY_COURSE_ID + ", " + KEY_EDU_SUBCATEGORY_ID + "," + KEY_IDENTIFIER_ID + ", " + KEY_COURSE_TYPE + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }
    public long insertItem(EducationCourseItem educationCourseItem){
        return insertItem(
               educationCourseItem.getCourseId(),
                educationCourseItem.getIdentifierId(),
                educationCourseItem.getEduSubCategoryId(),
                educationCourseItem.getEducoursename(),
                educationCourseItem.getEducourseduration(),
                educationCourseItem.getEducourseadmissiontime(),
                educationCourseItem.getEducoursecost(),
                educationCourseItem.getEducoursetype()
        );
    }

    public long insertItem(String courseId, String identifierId, int eduSubCategoryId, String educoursename,
                           String educourseduration, String educourseadmissiontime,
                           String educoursecost, String educoursetype) {
        if (isFieldExist(identifierId,eduSubCategoryId,educoursetype,courseId)) {
            return updateItem(
                    courseId,
                    identifierId,
                    eduSubCategoryId,
                    educoursename,
                    educourseduration,
                    educourseadmissiontime,
                    educoursecost,
                    educoursetype);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_COURSE_ID , courseId);
        rowValue.put(KEY_IDENTIFIER_ID , identifierId);
        rowValue.put(KEY_EDU_SUBCATEGORY_ID  , eduSubCategoryId);
        rowValue.put(KEY_COURSE_NAME,educoursename);
        rowValue.put(KEY_COURSE_DURATION , educourseduration);
        rowValue.put(KEY_COURSE_ADMISSION ,educourseadmissiontime );
        rowValue.put(KEY_COURSE_COST  , educoursecost);
        rowValue.put(KEY_COURSE_TYPE , educoursetype);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;
    }
    private long updateItem(String courseId, String identifierId, int eduSubCategoryId,
                            String educoursename, String educourseduration, String educourseadmissiontime,
                            String educoursecost, String educoursetype) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_COURSE_ID , courseId);
        rowValue.put(KEY_IDENTIFIER_ID , identifierId);
        rowValue.put(KEY_EDU_SUBCATEGORY_ID  , eduSubCategoryId);
        rowValue.put(KEY_COURSE_NAME,educoursename);
        rowValue.put(KEY_COURSE_DURATION , educourseduration);
        rowValue.put(KEY_COURSE_ADMISSION ,educourseadmissiontime );
        rowValue.put(KEY_COURSE_COST  , educoursecost);
        rowValue.put(KEY_COURSE_TYPE , educoursetype);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND " + KEY_EDU_SUBCATEGORY_ID + " = ? AND " + KEY_COURSE_TYPE + " = ? AND " + KEY_COURSE_ID + " = ? ",
                new String[]{identifierId + "", eduSubCategoryId + "", educoursetype + "", courseId + ""});
        closeDB();
        return ret;
    }
    public boolean isFieldExist(String identifierId,int eduSubCategoryId,String educoursetype,String courseId) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (identifierId.equals(cursor.getString(1))&&Integer.parseInt(cursor.getString(2))==eduSubCategoryId&&educoursetype.equals(cursor.getString(7))&&courseId.equals(cursor.getString(0))) {
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
    public ArrayList<EducationCourseItem> getEduCourse(String idenId) {
        ArrayList<EducationCourseItem> CourseList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = '" + idenId + "'", null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                CourseList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return CourseList;
    }
    public static EducationCourseItem cursorToSubCatList(Cursor cursor) {
        String _courseId = cursor.getString(0);
        String _identifierId = cursor.getString(1);
        int _eduSubCategoryId = cursor.getInt(2);
        String _educoursename = cursor.getString(3);
        String _educourseduration = cursor.getString(4);
        String _educourseadmissiontime = cursor.getString(5);
        String _educoursecost = cursor.getString(6);
        String _educoursetype = cursor.getString(7);


        return new EducationCourseItem(_courseId,
                _identifierId, _eduSubCategoryId, _educoursename,_educourseduration,_educourseadmissiontime,
                _educoursecost,_educoursetype );
    }
    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
