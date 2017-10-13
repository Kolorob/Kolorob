package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;


/**
 * Created by israt.jahan on 2/9/2017.
 */


public class EduNewDBTableMain extends CommonDBTable<EduNewModel> {

    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_MAIN;

    private static final String KEY_EDU_TYPE = "edu_type";
    private static final String KEY_SHIFT = "shift";
    private static final String KEY_STUDENT_NO = "student_No";
    private static final String KEY_TEACHER_NO = "teachers_no";
    private static final String KEY_AVG_STD = "avg_std_per_class";
    private static final String KEY_FACILITY = "facility";


    public EduNewDBTableMain(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = createTableQuery(TABLE_NAME);
        CREATE_TABLE_SQL += KEY_EDU_TYPE + " TEXT, "
                + KEY_SHIFT + " TEXT, "
                + KEY_STUDENT_NO + " TEXT, "
                + KEY_TEACHER_NO + " TEXT, "
                + KEY_AVG_STD + " TEXT, "
                + KEY_FACILITY + " TEXT )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(EduNewModel eduNewModel) {
        return super.insertItem(eduNewModel, TABLE_NAME);
    }


    public void insertItem(ContentValues rowValue, EduNewModel eduNewModel) {
        rowValue.put(KEY_EDU_TYPE, eduNewModel.getEducationType());
        rowValue.put(KEY_SHIFT, eduNewModel.getShift());
        rowValue.put(KEY_STUDENT_NO, eduNewModel.getStudentNo());
        rowValue.put(KEY_TEACHER_NO, eduNewModel.getTeachersNo());
        rowValue.put(KEY_AVG_STD, eduNewModel.getAverageStudentPerClass());
        rowValue.put(KEY_FACILITY, eduNewModel.getFacility());
    }


    public long updateItem(EduNewModel eduNewModel) {
        return super.updateItem(eduNewModel, TABLE_NAME);
    }


    public void updateItem(ContentValues rowValue, EduNewModel eduNewModel){
        insertItem(rowValue, eduNewModel);
    }


    public boolean isFieldExist(int id ) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    public EduNewModel getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <EduNewModel> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public ArrayList <EduNewModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }


    public EduNewModel cursorToModel(Cursor cursor) {

        CommonModel _commonModel = super.cursorToModel(cursor);
        String _edu_type = cursor.getString(23);
        String _shift = cursor.getString(24);
        String _student_no = cursor.getString(25);
        String _teachers_no = cursor.getString(26);
        String _avg_std_per_class = cursor.getString(27);
        String _facility = cursor.getString(28);

        return new EduNewModel(_commonModel, _edu_type, _shift, _student_no, _teachers_no, _avg_std_per_class, _facility);
    }

    public ArrayList <EduNewModel> getByArea(String ward, String area){
        return super.getByArea(ward, area, TABLE_NAME);
    }

    public ArrayList <EduNewModel> getByAreaCategory(String ward, String area, int category){
        return super.getByAreaCategory(ward, area, category, TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }


}