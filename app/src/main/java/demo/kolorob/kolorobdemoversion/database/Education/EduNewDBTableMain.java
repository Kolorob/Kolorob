package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2017.
 */
public class EduNewDBTableMain {
    private static final String TAG = EduNewDBTableMain.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_NEW_DB_MAIN;

    private static final String KEY_IDENTIFIER_ID = "_eduId"; // 0 -integer
    private static final String KEY_COMMON_ID = "_commonId";

    private static final String KEY_EDU_TYPE = "_edtype"; // 1 - text
    private static final String KEY_SHIFT = "_shift"; // 1 - text
    private static final String KEY_STUDENT_NO = "_studentno"; // 1 - text
    private static final String KEY_TEACHER_NO = "_teachersno"; // 1 - text
    private static final String KEY_AVG_STD = "_avgstdperclass"; // 1 - text
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
            return insertItem(eduNewModel.getEduId(),eduNewModel.getNameen(),eduNewModel.getNamebn(),
                    eduNewModel.getEdtype(),eduNewModel.getShift(),eduNewModel.getStudentno(),eduNewModel.getTeachersno(),eduNewModel.getAveragestdperclass(),
                    eduNewModel.getFacility(),eduNewModel.getLat(),eduNewModel.getLon(),eduNewModel.getHouseno(),
                    eduNewModel.getBlock(),eduNewModel.getArea(),eduNewModel.getPolicestation(),eduNewModel.getNode_email(),
                    eduNewModel.getWard(),eduNewModel.getRoad(),eduNewModel.getNode_contact(),eduNewModel.getOtherinfo(),eduNewModel.getArea_bn(),  eduNewModel.getParent_area(),
                    eduNewModel.getOpeningtime(),eduNewModel.getClosetime(),eduNewModel.getOffday(),eduNewModel.getCategoryId(),
                    eduNewModel.getRefnumm(),eduNewModel.getRatings(),eduNewModel.getSubcat()
            );
        }
        else return updateItem(eduNewModel.getEduId(),eduNewModel.getNameen(),eduNewModel.getNamebn(),
                eduNewModel.getEdtype(),eduNewModel.getShift(),eduNewModel.getStudentno(),eduNewModel.getTeachersno(),eduNewModel.getAveragestdperclass(),
                eduNewModel.getFacility(),eduNewModel.getLat(),eduNewModel.getLon(),eduNewModel.getHouseno(),
                eduNewModel.getBlock(),eduNewModel.getArea(),eduNewModel.getPolicestation(),eduNewModel.getNode_email(),
                eduNewModel.getWard(),eduNewModel.getRoad(),eduNewModel.getNode_contact(),eduNewModel.getOtherinfo(),eduNewModel.getArea_bn(),  eduNewModel.getParent_area(),
                eduNewModel.getOpeningtime(),eduNewModel.getClosetime(),eduNewModel.getOffday(),eduNewModel.getCategoryId(),
                eduNewModel.getRefnumm(),eduNewModel.getRatings(),eduNewModel.getSubcat());

    }




    public long insertItem(int eduid, String nameen, String namebn, String edutype,String shift,String stdno,String teacherno,String avgstd,
                           String facility,String lat,
                           String lon, String houseno, String block, String area, String policestation,
                           String node_email, String ward, String road, String node_contact, String otherinfo,String areabn, String parent_area,
                           String openingtime, String closetime, String offday, int categoryId, String refnumm, String ratings,
                           String subcat
    ) {
        if (isFieldExist(eduid)) {
            return updateItem(
                    eduid,
                    nameen,
                    namebn,
                     edutype, shift, stdno, teacherno, avgstd,
                     facility,
                    lat,
                    lon,
                    houseno,
                    block,
                    area,
                    policestation,
                    node_email,
                    ward,
                    road,
                    node_contact,
                    otherinfo,areabn, parent_area,
                    openingtime,
                    closetime,
                    offday,categoryId,
                    refnumm,
                    ratings,
                    subcat
                    );
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, eduid);
        rowValue.put(KEY_NAME_ENG, nameen);
        rowValue.put(KEY_NAME_BAN, namebn);
        rowValue.put(KEY_EDU_TYPE, edutype);
        rowValue.put(KEY_SHIFT, shift);
        rowValue.put(KEY_STUDENT_NO, stdno);
        rowValue.put(KEY_TEACHER_NO, teacherno);
        rowValue.put(KEY_AVG_STD, avgstd);
        rowValue.put(KEY_FACILITY, facility);

        rowValue.put(KEY_LATITUDE, lat);
        rowValue.put(KEY_LONGITUDE, lon);

        rowValue.put(KEY_HOUSE_NO, houseno);
        rowValue.put(KEY_BLOCK, block);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_POLICE_STATION, policestation);
        rowValue.put(KEY_EMAIL_ADDRESS, node_email);
        rowValue.put(KEY_WARD, ward);
        rowValue.put(KEY_ROAD, road);
        rowValue.put(KEY_CONTACT_NO, node_contact);
        rowValue.put(KEY_OTHER_INFO, otherinfo);
        rowValue.put(KEY_AREABN, areabn);
        rowValue.put(KEY_PARENT_AREA, parent_area);
        rowValue.put(KEY_OPENTIME, openingtime);
        rowValue.put(KEY_CLOSEATIME, closetime);
        rowValue.put(KEY_OFF_DAY, offday);
        rowValue.put(KEY_CATEGORY_ID  , categoryId);
        rowValue.put(KEY_REFERENCE  , refnumm);
        rowValue.put(KEY_RATINGS  , ratings);
        rowValue.put(KEY_SUBCATEGORY_ID  , subcat );

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return ret;
    }
    private long updateItem(
            int govid, String nameen, String namebn, String edutype,String shift,String stdno,String teacherno,String avgstd,
            String facility,String lat,
            String lon, String houseno, String block, String area, String policestation,
            String node_email, String ward, String road, String node_contact, String otherinfo,String areabn, String parent_area,
            String openingtime, String closetime, String offday, int categoryId, String refnumm, String ratings,
            String subcat
    ) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, govid);
        rowValue.put(KEY_NAME_ENG, nameen);
        rowValue.put(KEY_NAME_BAN, namebn);
        rowValue.put(KEY_EDU_TYPE, edutype);
        rowValue.put(KEY_SHIFT, shift);
        rowValue.put(KEY_STUDENT_NO, stdno);
        rowValue.put(KEY_TEACHER_NO, teacherno);
        rowValue.put(KEY_AVG_STD, avgstd);
        rowValue.put(KEY_FACILITY, facility);

        rowValue.put(KEY_LATITUDE, lat);
        rowValue.put(KEY_LONGITUDE, lon);

        rowValue.put(KEY_HOUSE_NO, houseno);
        rowValue.put(KEY_BLOCK, block);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_POLICE_STATION, policestation);
        rowValue.put(KEY_EMAIL_ADDRESS, node_email);
        rowValue.put(KEY_WARD, ward);
        rowValue.put(KEY_ROAD, road);
        rowValue.put(KEY_CONTACT_NO, node_contact);
        rowValue.put(KEY_OTHER_INFO, otherinfo);
        rowValue.put(KEY_AREABN, areabn);
        rowValue.put(KEY_PARENT_AREA, parent_area);
        rowValue.put(KEY_OPENTIME, openingtime);
        rowValue.put(KEY_CLOSEATIME, closetime);
        rowValue.put(KEY_OFF_DAY, offday);
        rowValue.put(KEY_CATEGORY_ID  , categoryId);
        rowValue.put(KEY_REFERENCE  , refnumm);
        rowValue.put(KEY_RATINGS  , ratings);
        rowValue.put(KEY_SUBCATEGORY_ID  , subcat );

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{govid + ""});
        closeDB();
        return ret;
    }

    public void delete(String ward,String area)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(EduNewDBTableMain.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_WARD + " = '" + ward + "' AND "+"("+KEY_AREA +"  = '"+ area + "')"+" OR "+"("+KEY_PARENT_AREA +"  =  '"+ area + "')", null);

        database.close();
    }
    public boolean isFieldExist(int id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id==cursor.getInt(0)) {
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
    public ArrayList<EduNewModel> getAllEducationSubCategoriesInfo(String ward, String place) {
        ArrayList<EduNewModel> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM "+  TABLE_NAME + " WHERE "+KEY_WARD + " = '"+ ward + "' AND "+"("+KEY_AREA +"  = '"+ place + "')"+" OR "+"("+KEY_PARENT_AREA +"  =  '"+ place + "')", null);

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

   public ArrayList<EduNewModel> geteduNode2(int Node) {
        ArrayList<EduNewModel> subCatList = new ArrayList<>();
        SQLiteDatabase db = openDB();
        EduNewModel eduNewModel=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_IDENTIFIER_ID+"="+Node , null);

        if (cursor.moveToFirst()) {
            do {
                subCatList.add(cursorToSubCatList(cursor));
                //System.out.println("abc="+cursor.getString(4));
         /*       eduNewModel=new EduNewModel(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),
                        cursor.getString(8),cursor.getString(9),
                        cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),
                        cursor.getString(16), cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),cursor.getString(21),cursor.getString(22),
                        cursor.getInt(23),cursor.getString(24),cursor.getString(25),cursor.getString(26));
           */ } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }
    public EduNewModel geteduNode2v2(int Node) {
        ArrayList<EduNewModel> subCatList = new ArrayList<>();
        SQLiteDatabase db = openDB();
        EduNewModel eduNewModel=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_IDENTIFIER_ID+"="+Node , null);

        if (cursor.moveToFirst()) {
            do {
               // subCatList.add(cursorToSubCatList(cursor));
                //System.out.println("abc="+cursor.getString(4));
                eduNewModel = new EduNewModel(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9),
                        cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15),
                        cursor.getString(16), cursor.getString(17), cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21),cursor.getString(22), cursor.getString(23),
                        cursor.getString(24), cursor.getInt(25), cursor.getString(25), cursor.getString(27), cursor.getString(28));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return eduNewModel;
    }


    private EduNewModel cursorToSubCatList(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        String _nameen = cursor.getString(1);
        String _namebn = cursor.getString(2);
        String _edtype = cursor.getString(3);
        String _shift = cursor.getString(4);
        String _studentno = cursor.getString(5);
        String _teachersno = cursor.getString(6);
        String _avgstdperclass = cursor.getString(7);
        String _facility = cursor.getString(8);

        String _lat = cursor.getString(9);
        String _lon = cursor.getString(10);
        String _houseno = cursor.getString(11);
        String _block = cursor.getString(12);
        String _area = cursor.getString(13);
        String _policestation = cursor.getString(14);
        String _node_email = cursor.getString(15);
        String _ward = cursor.getString(16);
        String _road = cursor.getString(17);
        String _node_contact = cursor.getString(18);
        String _other = cursor.getString(19);
        String _parentarea = cursor.getString(20);
        String _areabn = cursor.getString(21);
        String _opentime = cursor.getString(22);
        String _closetime = cursor.getString(23);
        String _offday = cursor.getString(24);
        int _catid = cursor.getInt(25);
        String _refnumm = cursor.getString(26);
        String _rating = cursor.getString(27);
        String _sref = cursor.getString(28);


       return new EduNewModel(_eduId,_nameen,_namebn,_edtype,_shift,_studentno,_teachersno,_avgstdperclass,_facility,
                _lat, _lon,_houseno,_block,_area,_policestation,_node_email,_ward,_road,_node_contact,_other,_areabn, _parentarea,
               _opentime,  _closetime,_offday,_catid,_refnumm,_rating, _sref);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
