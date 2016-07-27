package demo.kolorob.kolorobdemoversion.database.Education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceDetailsTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class EducationNewTable {
    private static final String TAG = FinancialServiceDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.EDU_PROVIDER_TABLE_NEW;
    private static final String KEY_NODE_ID = "_finId";
    private static final String KEY_NAME_EN = "_nameen"; // 1 - text
    private static final String KEY_NAME_BN = "_namebn"; //

    private static final String KEY_EDU_TYPE = "_edtype"; // 1 - text
    private static final String KEY_SHIFT = "_shift"; //
    private static final String KEY_STUDENT_NO = "_studentno"; // 1 - text
    private static final String KEY_TEACHER_NO = "_teachersno"; //
    private static final String KEY_CLASS_NO = "_classno"; //
    private static final String KEY_ADDITIONAL = "_additional"; //
    private static final String KEY_STUDENT_MALE  = "_malestudent"; //
    private static final String KEY_STUDENT_FEMALE = "_femalestudent"; //
    private static final String KEY_SPNEEDS = "_specialneeds"; //
    private static final String KEY_WASHROOM_NO = "_washroom_no"; //
    private static final String KEY_WASHMALE_NO = "_washroom_male"; //
    private static final String KEY_WASH_CONDITION = "_washroomcleanliness"; //
    private static final String KEY_WATER_CONDITION = "_watercondition"; //
    private static final String KEY_WATER_SOURCE = "_watersource"; //
    private static final String KEY_STUDENT_AVG = "_averagestudent"; //
    private static final String KEY_WASHFEMALE_NO = "_washroomfemale"; //

    private static final String KEY_SERVICE_LAT = "_lat"; // 1 - text
    private static final String KEY_SERVICE_LON = "_lon"; //
    private static final String KEY_NODE_FLOOR = "_floor"; //
    private static final String KEY_NODE_HOUSENAME = "_housename"; //
    private static final String KEY_NODE_HOUSENO  = "_houseno"; //
    private static final String KEY_NODE_ROAD = "_road"; //
    private static final String KEY_NODE_LINE = "_line"; //
    private static final String KEY_NODE_AVENUE = "_avenue"; //
    private static final String KEY_NODE_BLOCK = "_block"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_NODE_LANDMARK = "_landmark"; //
    private static final String KEY_NODE_POSTOFFICE = "_postoffice"; //
    private static final String KEY_NODE_POLICESTATION = "_policestation"; //
    private static final String KEY_NODE_CITY = "_city"; //
    private static final String KEY_NODE_COUNTRY = "_country"; //
    private static final String KEY_NODE_CONTACT = "_node_contact"; //
    private static final String KEY_NODE_CONTACT2 = "_node_contact2"; //
    private static final String KEY_NODE_EMAIL= "_node_email"; //
    private static final String KEY_NODE_WEBSITE = "_node_website"; //
    private static final String KEY_NODE_FACEBOOK= "_node_facebook"; //
    private static final String KEY_NODE_DESIGNATION = "_node_designation"; //
    private static final String KEY_NODE_ADDRESS = "_node_address"; //
    private static final String KEY_SERVICE_OPENTIME = "_opentime"; // 1 - text
    private static final String KEY_SERVICE_BREAKTIME = "_breaktime"; //
    private static final String KEY_SERVICE_CLOSETIME = "_closetime"; //
    private static final String KEY_SERVICE_OFFDAY = "_offday"; //
    private static final String KEY_SERVICE_REGWITH = "_regwith"; // 1 - text
    private static final String KEY_SERVICE_REGNO = "_regnum"; //

    private static final String KEY_CATID = "_catid"; // 1 - text
    private static final String KEY_REFNUMS = "_refnumm"; //


    private Context tContext;

    public EducationNewTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " INTEGER , "
                + KEY_NAME_EN + "  TEXT  , " // 0 - int "
                + KEY_NAME_BN + " TEXT,   "

                + KEY_EDU_TYPE + "  TEXT  , "
                + KEY_SHIFT + "  TEXT  , "
                + KEY_STUDENT_NO + "  TEXT, "              // 1 - text
                + KEY_TEACHER_NO + " TEXT, "
                + KEY_CLASS_NO + " TEXT, "
                + KEY_ADDITIONAL + " TEXT, "
                + KEY_STUDENT_MALE + " TEXT, "
                + KEY_STUDENT_FEMALE + " TEXT, "
                + KEY_SPNEEDS + "  TEXT  , "
                + KEY_WASHROOM_NO + "  TEXT  , "
                + KEY_WASHMALE_NO + "  TEXT, "              // 1 - text
                + KEY_WASH_CONDITION + " TEXT, "
                + KEY_WATER_CONDITION + " TEXT, "
                + KEY_WATER_SOURCE + " TEXT, "
                + KEY_STUDENT_AVG + " TEXT, "
                + KEY_WASHFEMALE_NO + " TEXT, "


                + KEY_SERVICE_LAT + "  TEXT  , "
                + KEY_SERVICE_LON + "  TEXT  , "
                + KEY_NODE_FLOOR + "  TEXT, "              // 1 - text
                + KEY_NODE_HOUSENAME + " TEXT, "
                + KEY_NODE_HOUSENO + " TEXT, "
                + KEY_NODE_ROAD + " TEXT, "
                + KEY_NODE_LINE + " TEXT, "
                + KEY_NODE_AVENUE + " TEXT, "
                + KEY_NODE_BLOCK + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_NODE_LANDMARK + " TEXT, "
                + KEY_NODE_POSTOFFICE+ " TEXT, "
                + KEY_NODE_POLICESTATION + " TEXT, "
                + KEY_NODE_CITY + " TEXT, "
                + KEY_NODE_COUNTRY + " TEXT, "
                + KEY_NODE_CONTACT + " TEXT, "
                + KEY_NODE_CONTACT2 + " TEXT, "
                + KEY_NODE_EMAIL + " TEXT,"
                + KEY_NODE_WEBSITE + " TEXT, "
                + KEY_NODE_FACEBOOK + " TEXT, "
                + KEY_NODE_DESIGNATION + " TEXT, "
                + KEY_NODE_ADDRESS + " TEXT, "
                + KEY_SERVICE_OPENTIME + "  TEXT  , "
                + KEY_SERVICE_BREAKTIME + "  TEXT  , "
                + KEY_SERVICE_CLOSETIME + "  TEXT  , "
                + KEY_SERVICE_OFFDAY + "  TEXT  , "
                + KEY_SERVICE_REGWITH + "  TEXT  , " // 0 - int "
                + KEY_SERVICE_REGNO + "  TEXT  , " // 0 - int "
                + KEY_CATID + " INTEGER , "
                + KEY_REFNUMS + " TEXT ,   PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EducationNewItem educationNewItem) {
        return insertItem(educationNewItem.getEduId(),educationNewItem.getNameen(),educationNewItem.getNamebn(),educationNewItem.getEdtype(),
                educationNewItem.getShift(),educationNewItem.getStudentno(),educationNewItem.getTeachersno(),educationNewItem.getClassno(),
                educationNewItem.getAdditional(),educationNewItem.getMalestudent(),educationNewItem.getFemalestudent(),
                educationNewItem.getSpecialneeds(),educationNewItem.getWashroom_no(),educationNewItem.getWashroom_male(),
                educationNewItem.getWashroomcleanliness(),educationNewItem.getWatercondition(),educationNewItem.getWatersource(),
                educationNewItem.getAveragestudent(),educationNewItem.getWashroomfemale(), educationNewItem.getLat(),
                educationNewItem.getLon(),educationNewItem.getFloor(),
                educationNewItem.getHousename(),educationNewItem.getHouseno(),
                educationNewItem.getRoad(),educationNewItem.getLine(),educationNewItem.getAvenue(),
                educationNewItem.getBlock(),educationNewItem.getArea(),educationNewItem.getLandmark(),
                educationNewItem.getPostoffice(),educationNewItem.getPolicestation(),
                educationNewItem.getCity(),educationNewItem.getCountry(),educationNewItem.getNode_contact(),
                educationNewItem.getNode_contact2(),educationNewItem.getNode_email(),educationNewItem.getNode_website(),
                educationNewItem.getNode_facebook(),educationNewItem.getNode_designation(),educationNewItem.getAddress(),
                educationNewItem.getOpeningtime(),
                educationNewItem.getBreaktime(),
                educationNewItem.getClosetime(),
                educationNewItem.getOffday(), educationNewItem.getRegisteredwith(),
                educationNewItem.getRegisterednumber(),educationNewItem.getCategoryId(),educationNewItem.getRefnumm()

        );
    }
    private long insertItem(int eduId, String nameen, String namebn, String edtype, String shift, String studentno,
                            String teachersno, String classno, String additional, String malestudent, String femalestudent,
                            String specialneeds, String washroom_no, String washroom_male, String washroomcleanliness,
                            String watercondition, String watersource, String averagestudent, String washroomfemale, String lat,
                            String lon, String floor, String housename, String houseno, String road, String line, String avenue,
                            String block, String area, String landmark, String postoffice, String policestation, String city,
                            String country, String node_contact, String node_contact2, String node_email, String node_website,
                            String node_facebook, String node_designation,String address, String openingtime, String closetime, String breaktime,
                            String offday, String registeredwith, String registerednumber, int categoryId, String refnumm) {
        if (isFieldExist(eduId)) {
            return updateItem(
                    eduId,
                    nameen,
                    namebn,
                    edtype,shift,studentno,teachersno,classno,additional,malestudent,femalestudent,specialneeds,washroom_no,
                    washroom_male,washroomcleanliness,watercondition,watersource,averagestudent,washroomfemale,



                    lat,
                    lon, floor,
                    housename,
                    houseno,
                    road,
                    line,
                    avenue,
                    block,
                    area,
                    landmark,
                    postoffice,
                    policestation,
                    city,
                    country,
                    node_contact,
                    node_contact2,
                    node_email,node_website,
                    node_facebook,
                    node_designation,address,  openingtime,
                    closetime,breaktime,offday  ,registeredwith,
                    registerednumber,categoryId,refnumm);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_NAME_EN, nameen);
        rowValue.put(KEY_NAME_BN, namebn);

        rowValue.put(KEY_EDU_TYPE, edtype);
        rowValue.put(KEY_SHIFT, shift);
        rowValue.put(KEY_STUDENT_NO,  studentno);
        rowValue.put(KEY_TEACHER_NO, teachersno);
        rowValue.put(KEY_CLASS_NO, classno);
        rowValue.put(KEY_ADDITIONAL,  additional);
        rowValue.put(KEY_STUDENT_MALE ,  malestudent);
        rowValue.put(KEY_STUDENT_FEMALE ,femalestudent);
        rowValue.put(KEY_SPNEEDS, specialneeds);
        rowValue.put(KEY_WASHROOM_NO, washroom_no);
        rowValue.put(KEY_WASHMALE_NO,  washroom_male);
        rowValue.put(KEY_WASH_CONDITION, washroomcleanliness);
        rowValue.put(KEY_WATER_CONDITION, watercondition);
        rowValue.put(KEY_WATER_SOURCE,  watersource);
        rowValue.put(KEY_STUDENT_AVG ,  averagestudent);
        rowValue.put(KEY_WASHFEMALE_NO ,washroomfemale);

        rowValue.put(KEY_SERVICE_LAT, lat);
        rowValue.put(KEY_SERVICE_LON, lon);
        rowValue.put(KEY_NODE_FLOOR,  floor);
        rowValue.put(KEY_NODE_HOUSENAME, housename);
        rowValue.put(KEY_NODE_HOUSENO, houseno);
        rowValue.put(KEY_NODE_ROAD,  road);
        rowValue.put(KEY_NODE_LINE ,  line);
        rowValue.put(KEY_NODE_AVENUE ,avenue);
        rowValue.put(KEY_NODE_BLOCK , block);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_NODE_LANDMARK,landmark);
        rowValue.put(KEY_NODE_POSTOFFICE , postoffice);
        rowValue.put(KEY_NODE_POLICESTATION, policestation);
        rowValue.put(KEY_NODE_CITY, city);
        rowValue.put(KEY_NODE_COUNTRY, country);
        rowValue.put(KEY_NODE_CONTACT, node_contact);
        rowValue.put(KEY_NODE_CONTACT2, node_contact2);
        rowValue.put(KEY_NODE_EMAIL, node_email);
        rowValue.put(KEY_NODE_WEBSITE , node_website);
        rowValue.put(KEY_NODE_FACEBOOK  , node_facebook);
        rowValue.put(KEY_NODE_DESIGNATION  , node_designation);
        rowValue.put(KEY_NODE_ADDRESS  , address);
        rowValue.put(KEY_SERVICE_OPENTIME, openingtime);
        rowValue.put(KEY_SERVICE_BREAKTIME, closetime);
        rowValue.put(KEY_SERVICE_CLOSETIME , breaktime);
        rowValue.put(KEY_SERVICE_OFFDAY, offday);
        rowValue.put(KEY_SERVICE_REGWITH , registeredwith);
        rowValue.put(KEY_SERVICE_REGNO, registerednumber);
        rowValue.put(KEY_CATID , categoryId);
        rowValue.put(KEY_REFNUMS, refnumm);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(int eduId, String nameen, String namebn, String edtype, String shift, String studentno,
                            String teachersno, String classno, String additional, String malestudent, String femalestudent,
                            String specialneeds, String washroom_no, String washroom_male, String washroomcleanliness,
                            String watercondition, String watersource, String averagestudent, String washroomfemale, String lat,
                            String lon, String floor, String housename, String houseno, String road, String line, String avenue,
                            String block, String area, String landmark, String postoffice, String policestation, String city,
                            String country, String node_contact, String node_contact2, String node_email, String node_website,
                            String node_facebook, String node_designation,String address, String openingtime, String closetime, String breaktime,
                            String offday, String registeredwith, String registerednumber, int categoryId, String refnumm) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , eduId);
        rowValue.put(KEY_NAME_EN, nameen);
        rowValue.put(KEY_NAME_BN, namebn);

        rowValue.put(KEY_EDU_TYPE, edtype);
        rowValue.put(KEY_SHIFT, shift);
        rowValue.put(KEY_STUDENT_NO,  studentno);
        rowValue.put(KEY_TEACHER_NO, teachersno);
        rowValue.put(KEY_CLASS_NO, classno);
        rowValue.put(KEY_ADDITIONAL,  additional);
        rowValue.put(KEY_STUDENT_MALE ,  malestudent);
        rowValue.put(KEY_STUDENT_FEMALE ,femalestudent);
        rowValue.put(KEY_SPNEEDS, specialneeds);
        rowValue.put(KEY_WASHROOM_NO, washroom_no);
        rowValue.put(KEY_WASHMALE_NO,  washroom_male);
        rowValue.put(KEY_WASH_CONDITION, washroomcleanliness);
        rowValue.put(KEY_WATER_CONDITION, watercondition);
        rowValue.put(KEY_WATER_SOURCE,  watersource);
        rowValue.put(KEY_STUDENT_AVG ,  averagestudent);
        rowValue.put(KEY_WASHFEMALE_NO ,washroomfemale);

        rowValue.put(KEY_SERVICE_LAT, lat);
        rowValue.put(KEY_SERVICE_LON, lon);
        rowValue.put(KEY_NODE_FLOOR,  floor);
        rowValue.put(KEY_NODE_HOUSENAME, housename);
        rowValue.put(KEY_NODE_HOUSENO, houseno);
        rowValue.put(KEY_NODE_ROAD,  road);
        rowValue.put(KEY_NODE_LINE ,  line);
        rowValue.put(KEY_NODE_AVENUE ,avenue);
        rowValue.put(KEY_NODE_BLOCK , block);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_NODE_LANDMARK,landmark);
        rowValue.put(KEY_NODE_POSTOFFICE , postoffice);
        rowValue.put(KEY_NODE_POLICESTATION, policestation);
        rowValue.put(KEY_NODE_CITY, city);
        rowValue.put(KEY_NODE_COUNTRY, country);
        rowValue.put(KEY_NODE_CONTACT, node_contact);
        rowValue.put(KEY_NODE_CONTACT2, node_contact2);
        rowValue.put(KEY_NODE_EMAIL, node_email);
        rowValue.put(KEY_NODE_WEBSITE , node_website);
        rowValue.put(KEY_NODE_FACEBOOK  , node_facebook);
        rowValue.put(KEY_NODE_DESIGNATION  , node_designation);
        rowValue.put(KEY_NODE_ADDRESS  , address);
        rowValue.put(KEY_SERVICE_OPENTIME, openingtime);
        rowValue.put(KEY_SERVICE_BREAKTIME, closetime);
        rowValue.put(KEY_SERVICE_CLOSETIME , breaktime);
        rowValue.put(KEY_SERVICE_OFFDAY, offday);
        rowValue.put(KEY_SERVICE_REGWITH , registeredwith);
        rowValue.put(KEY_SERVICE_REGNO, registerednumber);
        rowValue.put(KEY_CATID , categoryId);
        rowValue.put(KEY_REFNUMS, refnumm);



        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{eduId + ""});
        closeDB();
        return ret;

    }

    public ArrayList<EducationNewItem> getAllEducationSubCategoriesInfo() {
        ArrayList<EducationNewItem> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +  " ORDER BY " +KEY_NAME_EN, null);

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
    public ArrayList<EducationNewItem> getAllEducationSubCategoriesInfoWithHead(String header) {


       // Log.d("cat_id","####"+cat_id);
        Log.d("header/Query****","####"+header);
        int[] k = new int[100];
        ArrayList<EducationNewItem> nameslist = new ArrayList<>();
        ArrayList<Integer> s = new ArrayList<Integer>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.SUB_CATEGORY_NEW + " WHERE _headbn = '" + header + "'", null);
        if (cursor.moveToFirst()) {
            do {

                s.add(cursor.getInt(5));

            } while (cursor.moveToNext());
        }
        cursor.close();

            Cursor cursor2 = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);


            if (cursor2.moveToFirst()) {
                do {

                    String getter = cursor2.getString(48);
                    String delims = "[,]";
                    String[] tokens = getter.split(delims);
                    for (int j = 0; j < s.size(); j++) {
                        for (int ii = 0; ii < tokens.length; ii++) {
                            if (Integer.parseInt(tokens[ii]) == s.get(j)) {
                                nameslist.add(cursorToSubCatList(cursor2));
                            }
                        }
                    }

                } while (cursor2.moveToNext());
            }
            cursor2.close();

        closeDB();
return nameslist;

    }
    public ArrayList<EducationNewItem> Edunames(String a,String place) {
        String subcatnames=null;
        subcatnames=a;
        String places;
        int k =0;
        ArrayList<EducationNewItem> nameslist=new ArrayList<>();
        ArrayList<Integer>s=new ArrayList<Integer>();
        places=place;
        SQLiteDatabase db = openDB();
        int i=0;
        Cursor cursor =db.rawQuery("SELECT * FROM " + DatabaseHelper.SUB_CATEGORY_NEW +  " WHERE _subcatnamebn = '"+subcatnames+"'" ,null);
        if (cursor.moveToFirst()) {
            do {

                k=cursor.getInt(5);



            } while (cursor.moveToNext());
        }
        cursor.close();

        Cursor cursor2 = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "
                +KEY_AREA+" = '"+places+"'"  , null);


        if (cursor2.moveToFirst()) {
            do {

              String getter=cursor2.getString(48);
               String delims = "[,]";
               String[] tokens = getter.split(delims);
                for (int ii=0;ii<tokens.length;ii++)
                {
                    if(Integer.parseInt(tokens[i])==k)
                    {
                        nameslist.add(cursorToSubCatList(cursor2));
                    }
                }


            } while (cursor2.moveToNext());
        }
        cursor2.close();
        closeDB();
        return  nameslist;
    }
    public ArrayList<EducationNewItem> getEducationData(String node_id) {
        ArrayList<EducationNewItem> subCatList = new ArrayList<>();
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
    public ArrayList<EducationNewItem> getAllSubCat() {
        ArrayList<EducationNewItem> siList = new ArrayList<>();

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

    public EducationNewItem geteduNode2(int Node) {

        SQLiteDatabase db = openDB();
        EducationNewItem educationNewItem=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+"="+Node , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                educationNewItem=new EducationNewItem(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),
                        cursor.getString(8),cursor.getString(9),
                        cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),
                        cursor.getString(16), cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),cursor.getString(21),cursor.getString(22),
                        cursor.getString(23),cursor.getString(24),cursor.getString(25),cursor.getString(26),cursor.getString(27),
                        cursor.getString(28),cursor.getString(29),cursor.getString(30),cursor.getString(31),cursor.getString(32),
                        cursor.getString(33),cursor.getString(34),cursor.getString(35),cursor.getString(36),
                cursor.getString(37),
               cursor.getString(38),

                cursor.getString(39),
                cursor.getString(40),
                        cursor.getString(41),
               cursor.getString(42),
                cursor.getString(43),
               cursor.getString(44),
               cursor.getString(45),
               cursor.getString(46),
                cursor.getInt(47),
               cursor.getString(48));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return educationNewItem;
    }
    public boolean isFieldExist(int nodeid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0)==nodeid) {
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

    private EducationNewItem cursorToSubCatList(Cursor cursor) {
        int _eduId = cursor.getInt(0);
        String _nameen= cursor.getString(1);
        String _namebn = cursor.getString(2);
        String _edtype = cursor.getString(3);
        String _shift = cursor.getString(4);
        String _studentno = cursor.getString(5);
        String _teachersno = cursor.getString(6);
        String _classno =cursor.getString(7);
        String _additional = cursor.getString(8);
        String _malestudent = cursor.getString(9);
        String _femalestudent = cursor.getString(10);
        String _specialneeds = cursor.getString(11);
        String _washroom_no = cursor.getString(12);
        String _washroom_male = cursor.getString(13);
        String _washroomcleanliness = cursor.getString(14);
        String _watercondition = cursor.getString(15);
        String _watersource = cursor.getString(16);
        String _averagestudent = cursor.getString(17);
        String _washroomfemale = cursor.getString(18);

        String _lat= cursor.getString(19);
        String _lon = cursor.getString(20);
        String _floor = cursor.getString(21);
        String _housename = cursor.getString(22);

        String _houseno = cursor.getString(23);
        String _road = cursor.getString(24);
        String _line = cursor.getString(25);

        String _avenue = cursor.getString(26);
        String _block = cursor.getString(27);
        String _area = cursor.getString(28);

        String _landmark = cursor.getString(29);
        String _postoffice = cursor.getString(30);
        String _policestation = cursor.getString(31);

        String _city = cursor.getString(32);
        String _country = cursor.getString(33);
        String _node_contact = cursor.getString(34);
        String _node_contact2 = cursor.getString(35);

        String _node_email = cursor.getString(36);
        String _node_website = cursor.getString(37);
        String _node_facebook=cursor.getString(38);

        String _node_designation=cursor.getString(39);
        String _Address=cursor.getString(40);
        String _opentime= cursor.getString(41);
        String _breaktime = cursor.getString(42);
        String _closetime= cursor.getString(43);
        String _offday = cursor.getString(44);
        String _regwith= cursor.getString(45);
        String _regnum = cursor.getString(46);
        int _catid=cursor.getInt(47);
        String _refnumm=cursor.getString(48);
        return new EducationNewItem(_eduId,_nameen,_namebn,_edtype,_shift,_studentno,_teachersno,_classno,_additional,_malestudent,
                _femalestudent,_specialneeds,_washroom_no,_washroom_male,_washroomcleanliness,_watercondition,_watersource,_averagestudent,_washroomfemale,_lat, _lon,_floor,_housename,_houseno,_road,_line,_avenue,_block,_area,_landmark,_postoffice,_policestation,
                _city,_country,_node_contact,_node_contact2,_node_email,_node_website,_node_facebook,_node_designation,_Address,
                _opentime,
                _breaktime,_closetime,_offday,_regwith,
                _regnum,_catid,_refnumm);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
