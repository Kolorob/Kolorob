package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.utils.Lg;


/**
 * Created by Mazharul.Islam1 on 1/5/2016.
 */
public class EntertainmentServiceProviderTableNew {

    private static final String TAG = EntertainmentServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_PROVIDER_TABLE_NEW;
    public static final String KEY_NODE_ID = "node_id";
    public static final String KEY_SUB_CATEGORY_ID = "_ent_sub_category_id";
    public static final String KEY_NODE_NAME = "_node_name";
    public static final String KEY_NODE_NAME_BN = "_node_name_bn";

    public static final String KEY_NODE_DESIGNATION = "_node_designation";
    public static final String KEY_NODE_CONTACT = "_node_contact";
    public static final String KEY_NODE_EMAIL = "_node_email";
    public static final String KEY_NODE_ADDITIONAL = "_node_additional";
    public static final String KEY_NODE_WEBSITE = "_node_website";
    public static final String KEY_NODE_FACEBOOK = "_node_facebook";
    public static final String KEY_NODE_REGISTERED_WITH = "_node_registered_with";
    public static final String KEY_NODE_REGISTRATION_NUMBER = "_node_registration_number";

    public static final String KEY_NODE_TYPE = "_node_type";
    public static final String KEY_AREA = "_area";
    public static final String KEY_ADDRESS = "_address";
    public static final String KEY_LATITUDE = "_latitude";
    public static final String KEY_LONGITUDE = "_longitude";
    public static final String KEY_CATEGORY_ID = "_categoryId";
    private static final String KEY_OPENTIME = "_openingtime"; //
    private static final String KEY_BREAKTIME = "_breaktime"; //
    private static final String KEY_CLOSEATIME = "_closingtime";
    private static final String KEY_ROAD = "_road"; //
    private static final String KEY_BLOCK = "_block"; //
    private static final String KEY_LANDMARK = "_landmark"; //
    private static final String KEY_BREAKTIME2 = "_breaktime2"; //
    public static final String KEY_FLOOR = "floor";
    public static final String KEY_HOUSE_NAME = "_house_name";
    private static final String KEY_HOUSE_NUMBER = "_house_number"; //
    private static final String KEY_LINE = "_line"; //
    private static final String KEY_AVENUE = "_avenue";
    private static final String KEY_POST_OFFICE = "_post_office"; //
    private static final String KEY_POLICE_STATION = "_police_station"; //
    private static final String KEY_CITY = "_city"; //
    private static final String KEY_OFF_DAY = "_off_day"; //


    private Context tContext;


    public EntertainmentServiceProviderTableNew(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , " // 0 - int
                + KEY_SUB_CATEGORY_ID + " INTEGER, "              // 1 - text
                + KEY_NODE_NAME + " TEXT, "
                + KEY_NODE_NAME_BN + " TEXT, "// 2 - text
                + KEY_NODE_DESIGNATION + " TEXT, "
                + KEY_NODE_CONTACT + " TEXT, "
                + KEY_NODE_EMAIL + " TEXT, "
                + KEY_NODE_ADDITIONAL + " TEXT, "
                + KEY_NODE_WEBSITE + " TEXT, "
                + KEY_NODE_FACEBOOK + " TEXT, "
                + KEY_NODE_REGISTERED_WITH + " TEXT, "
                + KEY_NODE_REGISTRATION_NUMBER + " TEXT, "
                + KEY_NODE_TYPE + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "
                + KEY_CATEGORY_ID + " TEXT, "
                + KEY_OPENTIME + " TEXT, "
                + KEY_BREAKTIME + " TEXT, "
                + KEY_CLOSEATIME + " TEXT, "
                + KEY_ROAD+ " TEXT, "
                + KEY_BLOCK + " TEXT, "
                + KEY_LANDMARK + " TEXT, "
                + KEY_BREAKTIME2 + " TEXT, "
                + KEY_FLOOR + " TEXT, "
                + KEY_HOUSE_NAME + " TEXT, "
                + KEY_HOUSE_NUMBER + " INTEGER, "
                + KEY_LINE + " TEXT, "
                + KEY_AVENUE + " TEXT, "
                + KEY_POST_OFFICE + " TEXT, "
                + KEY_POLICE_STATION+ " TEXT, "
                + KEY_CITY + " TEXT, "
                + KEY_OFF_DAY + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentServiceProviderItemNew entertainmentServiceProviderItem) {
        return insertItem(
                entertainmentServiceProviderItem.getNodeId(),
                entertainmentServiceProviderItem.getEntSubCategoryId(),
                entertainmentServiceProviderItem.getNodeName(),
                entertainmentServiceProviderItem.getNodeNameBn(),
                entertainmentServiceProviderItem.getNodeDesignation(),
                entertainmentServiceProviderItem.getNodeEmail(),
                entertainmentServiceProviderItem.getNodeAdditional(),
                entertainmentServiceProviderItem.getNodeContact(),
                entertainmentServiceProviderItem.getNodeWebsite(),
                entertainmentServiceProviderItem.getNodeFacebook(),
                entertainmentServiceProviderItem.getNodeRegisteredWith(),
                entertainmentServiceProviderItem.getNodeRegistrationNumber(),
                entertainmentServiceProviderItem.getNodeType(),
                entertainmentServiceProviderItem.getArea(),
                entertainmentServiceProviderItem.getAddress(),
                entertainmentServiceProviderItem.getLatitude(),
                entertainmentServiceProviderItem.getLongitude(),
                entertainmentServiceProviderItem.getCategoryId(),
                entertainmentServiceProviderItem.getOpeningtime(),
                entertainmentServiceProviderItem.getBreaktime(),
                entertainmentServiceProviderItem.getClosingtime(),
                entertainmentServiceProviderItem.getRoad(),
                entertainmentServiceProviderItem.getBlock(),
                entertainmentServiceProviderItem.getLandmark(),
                entertainmentServiceProviderItem.getBreaktime2(),
                entertainmentServiceProviderItem.getFloor(),
                entertainmentServiceProviderItem.getHouse_name(),
                entertainmentServiceProviderItem.getHouse_no(),
                entertainmentServiceProviderItem.getLine(),
                entertainmentServiceProviderItem.getAvenue(),
                entertainmentServiceProviderItem.getPost_office(),
                entertainmentServiceProviderItem.getPolice_station(),
                entertainmentServiceProviderItem.getCity(),
                entertainmentServiceProviderItem.getOff_day()
        );
    }

    public long insertItem(String nodeId,
                           int entSubCategoryId,
                           String nodeName,
                           String nodeNameBn,
                           String nodeDesignation,
                           String nodeContact,
                           String nodeEmail,
                           String nodeAdditional,
                           String nodeWebsite,
                           String nodeFacebook,
                           String nodeRegisteredWith,
                           String nodeRegistrationNumber,
                           String nodeType,
                           String area,
                           String address,
                           String latitude,
                           String longitude,
                           String categoryId,
                           String openingtime,
                           String breaktime,
                           String closingtime,
                           String road,
                           String block,
                           String landmark,
                           String breaktime2,
                           String floor,
                           String house_name,
                           String house_no,
                           String line,
                           String avenue,
                           String post_office,
                           String police_station,
                           String city,
                           String off_day) {
        if (isFieldExist(nodeId)) {
            return updateItem(nodeId,
                    entSubCategoryId,
                    nodeName,
                    nodeNameBn,
                    nodeDesignation,
                    nodeContact,
                    nodeEmail,
                    nodeAdditional,
                    nodeWebsite,
                    nodeFacebook,
                    nodeRegisteredWith,
                    nodeRegistrationNumber,
                    nodeType,
                    area,
                    address,
                    latitude,
                    longitude,
                    categoryId,
                    openingtime,
                    breaktime,
                    closingtime,
                    road,
                    block,
                    landmark,
                    breaktime2,
                    floor,
                    house_name,
                    house_no,
                    line,
                    avenue,
                    post_office,
                    police_station,
                    city,
                    off_day
                   );
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);

        rowValue.put(KEY_SUB_CATEGORY_ID , entSubCategoryId);
        rowValue.put(KEY_NODE_NAME , nodeName);
        rowValue.put(KEY_NODE_NAME_BN ,nodeNameBn);
        rowValue.put(KEY_NODE_DESIGNATION ,nodeDesignation );
        rowValue.put(KEY_NODE_CONTACT  , nodeContact);
        rowValue.put(KEY_NODE_EMAIL , nodeEmail);
        rowValue.put(KEY_NODE_ADDITIONAL,nodeAdditional);
        rowValue.put(KEY_NODE_WEBSITE , nodeWebsite);
        rowValue.put(KEY_NODE_FACEBOOK ,nodeFacebook);
        rowValue.put(KEY_NODE_REGISTERED_WITH, nodeRegisteredWith);
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER  , nodeRegistrationNumber);
        rowValue.put(KEY_NODE_TYPE, nodeType);
        rowValue.put(KEY_AREA  , area);
        rowValue.put(KEY_ADDRESS , address);
        rowValue.put(KEY_LATITUDE,latitude);
        rowValue.put(KEY_LONGITUDE,longitude);
        rowValue.put(KEY_CATEGORY_ID,categoryId);
        rowValue.put(KEY_OPENTIME , openingtime);
        rowValue.put(KEY_BREAKTIME  , breaktime);
        rowValue.put(KEY_CLOSEATIME  , closingtime);
        rowValue.put(KEY_ROAD  , road );
        rowValue.put(KEY_BLOCK   , block );
        rowValue.put(KEY_LANDMARK  , landmark);
        rowValue.put(KEY_BREAKTIME2  , breaktime2 );
        rowValue.put(KEY_FLOOR,floor);
        rowValue.put(KEY_HOUSE_NAME,house_name);
        rowValue.put(KEY_HOUSE_NUMBER , house_no);
        rowValue.put(KEY_LINE  , line);
        rowValue.put(KEY_AVENUE  , avenue);
        rowValue.put(KEY_POST_OFFICE  , post_office );
        rowValue.put(KEY_POLICE_STATION   , police_station );
        rowValue.put(KEY_CITY  , city);
        rowValue.put(KEY_OFF_DAY  , off_day );

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;


    }


    public boolean isFieldExist(String nodeId) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (nodeId.equals(cursor.getString(0))) {
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

    private long updateItem( String nodeId,
                             int entSubCategoryId,
                             String nodeName,
                             String nodeNameBn,
                             String nodeDesignation,
                             String nodeContact,
                             String nodeEmail,
                             String nodeAdditional,
                             String nodeWebsite,
                             String nodeFacebook,
                             String nodeRegisteredWith,
                             String nodeRegistrationNumber,
                             String nodeType,
                             String area,
                             String address,
                             String latitude,
                             String longitude,
                             String categoryId,
                             String openingtime,
                             String breaktime,
                             String closingtime,
                             String road,
                             String block,
                             String landmark,
                             String breaktime2,String floor,
                             String house_name,
                             String house_no,
                             String line,
                             String avenue,
                             String post_office,
                             String police_station,
                             String city,
                             String off_day
    ) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID , entSubCategoryId);
        rowValue.put(KEY_NODE_NAME , nodeName);
        rowValue.put(KEY_NODE_NAME_BN ,nodeNameBn);
        rowValue.put(KEY_NODE_DESIGNATION ,nodeDesignation );
        rowValue.put(KEY_NODE_CONTACT  , nodeContact);
        rowValue.put(KEY_NODE_EMAIL , nodeEmail);
        rowValue.put(KEY_NODE_ADDITIONAL,nodeAdditional);
        rowValue.put(KEY_NODE_WEBSITE , nodeWebsite);
        rowValue.put(KEY_NODE_FACEBOOK ,nodeFacebook);
        rowValue.put(KEY_NODE_REGISTERED_WITH, nodeRegisteredWith);
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER  , nodeRegistrationNumber);
        rowValue.put(KEY_NODE_TYPE, nodeType);
        rowValue.put(KEY_AREA  , area);
        rowValue.put(KEY_ADDRESS , address);
        rowValue.put(KEY_LATITUDE,latitude);
        rowValue.put(KEY_LONGITUDE,longitude);
        rowValue.put(KEY_CATEGORY_ID,categoryId);
        rowValue.put(KEY_OPENTIME , openingtime);
        rowValue.put(KEY_BREAKTIME  , breaktime);
        rowValue.put(KEY_CLOSEATIME  , closingtime);
        rowValue.put(KEY_ROAD  , road );
        rowValue.put(KEY_BLOCK   , block );
        rowValue.put(KEY_LANDMARK  , landmark);
        rowValue.put(KEY_BREAKTIME2  , breaktime2 );
        rowValue.put(KEY_FLOOR,floor);
        rowValue.put(KEY_HOUSE_NAME,house_name);
        rowValue.put(KEY_HOUSE_NUMBER , house_no);
        rowValue.put(KEY_LINE  , line);
        rowValue.put(KEY_AVENUE  , avenue);
        rowValue.put(KEY_POST_OFFICE  , post_office );
        rowValue.put(KEY_POLICE_STATION   , police_station );
        rowValue.put(KEY_CITY  , city);
        rowValue.put(KEY_OFF_DAY  , off_day );
        SQLiteDatabase db = openDB();

//        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND " + KEY_EDU_SUBCATEGORY_ID + " = ? AND " + KEY_CATEGORY_ID + " = ? ",
//                new String[]{identifierId + "", eduSubCategoryId + "", categoryId + ""});


        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?",
                new String[]{nodeId + ""});
        closeDB();
        return ret;
    }





    public ArrayList<EntertainmentServiceProviderItemNew> getAllEntertainmentSubCategoriesInfo() {
        ArrayList<EntertainmentServiceProviderItemNew> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +  " ORDER BY " +KEY_NODE_NAME, null);

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
    public ArrayList<EntertainmentServiceProviderItemNew> getAllEntertainmentSubCategoriesInfoWithHead(int cat_id, String header) {
        ArrayList<EntertainmentServiceProviderItemNew> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        header=","+header+",";
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID + " LIKE '%"+header+"%'", null);

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



    public ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItemNews() {
        ArrayList<EntertainmentServiceProviderItemNew> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

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
    public ArrayList<String> getAllEntertainmentSubCategoriesInfos() {
        ArrayList<String> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        int cat_id=3;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                String  subCatLists = cursor.getString(cursor.getColumnIndex(KEY_NODE_NAME));

                subCatList.add(subCatLists);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }


//    public ArrayList<EntertainmentServiceProviderItem> Entnames(int cat_id,String head,String a,String place) {
//        String subcatnames=null;
//        subcatnames=a;
//
//        ArrayList<EntertainmentServiceProviderItem> nameslist=new ArrayList<>();
//
//        SQLiteDatabase db = openDB();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
//                + " AND "+KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_SUB_CATEGORY_ID + "=" + "(SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_name = '"+subcatnames+"')", null);
//
//
//        if (cursor.moveToFirst()) {
//            do {
//
//
//                nameslist.add(cursorToSubCatList(cursor));
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return  nameslist;
//    }
    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
//    public ArrayList<EntertainmentServiceProviderItem> getAllEntertainmentSubCategoriesInfoWithHead(int cat_id,String header) {
//        ArrayList<EntertainmentServiceProviderItem> subCatList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//
//        SQLiteDatabase db = openDB();
//
//
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_SUB_CATEGORY_ID + " in (SELECT _sub_cat_id from "+ DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '"+header+"')", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                subCatList.add(cursorToSubCatList(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }
//
//    public ArrayList<EntertainmentServiceProviderItem> getAllEntertainmentSubCategoriesInfo(int cat_id,int sub_cat_id) {
//        ArrayList<EntertainmentServiceProviderItem> subCatList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_SUB_CATEGORY_ID+"="+sub_cat_id, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                subCatList.add(cursorToSubCatList(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }








    public ArrayList<EntertainmentServiceProviderItemNew> EntNames(int cat_id,int refId,String a,String place) {
        String subcatnames=null;
        subcatnames=a;
        String places;


        String refids= String.valueOf(refId);

        refids=","+refids+",";
        places="Mirpur-11";




        ArrayList<EntertainmentServiceProviderItemNew> nameslist=new ArrayList<>();

        SQLiteDatabase db = openDB();


        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_AREA+" = '"+places+"'"  + " AND "+ KEY_NODE_EMAIL+ " LIKE '%"+refids+"%'" , null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_AREA+" = '"+place+"'"  , null);
        Log.d("Key Area","------"+KEY_NODE_ADDITIONAL);
         Log.d("Ref Id","------"+"SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_AREA+" = '"+places+"'"  + " AND "+ KEY_NODE_ADDITIONAL+ " LIKE '%"+refids+"%'");
//        Toast.makeText(this, +cursor,
//                Toast.LENGTH_LONG).show();



        if (cursor.moveToFirst()) {
            do {


                nameslist.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return  nameslist;
    }


    public EntertainmentServiceProviderItemNew getentNode2(String Node) {

        SQLiteDatabase db = openDB();
        EntertainmentServiceProviderItemNew entertainmentServiceProviderItem=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+"="+Node, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                entertainmentServiceProviderItem=new EntertainmentServiceProviderItemNew(
                        cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24),
                        cursor.getString(25),
                        cursor.getString(26),
                        cursor.getString(27),
                        cursor.getString(28),
                        cursor.getString(29),
                        cursor.getString(30),
                        cursor.getString(31),
                        cursor.getString(32),
                        cursor.getString(33));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return entertainmentServiceProviderItem;
    }

    private EntertainmentServiceProviderItemNew cursorToSubCatList(Cursor cursor) {
        String _nodeId= cursor.getString(0);
        int _entSubCategoryId= cursor.getInt(1);
        String _nodeName = cursor.getString(2);
        String _nodeNameBn= cursor.getString(3);
        String _nodeDesignation = cursor.getString(4);
        String _nodeContact = cursor.getString(5);
        String _nodeEmail = cursor.getString(6);
        String _nodeAdditional = cursor.getString(7);
        String _nodeWebsite = cursor.getString(8);
        String _nodeFacebook = cursor.getString(9);
        String _nodeRegisteredWith = cursor.getString(10);
        String _nodeRegistrationNumber = cursor.getString(11);
        String _nodeType = cursor.getString(12);
        String _area = cursor.getString(13);
        String _address = cursor.getString(14);
        String _latitude = cursor.getString(15);
        String _longitude= cursor.getString(16);
        String _categoryId= cursor.getString(17);
        String _openingtime=cursor.getString(18);
        String _breaktime=cursor.getString(19);
        String _closingtime=cursor.getString(20);
        String _road=cursor.getString(21);
        String _block=cursor.getString(22);
        String _landmark=cursor.getString(23);
        String _breaktime2=cursor.getString(24);
        String floor= cursor.getString(25);
        String house_name= cursor.getString(26);
        String house_no=cursor.getString(27);
        String line=cursor.getString(28);
        String avenue=cursor.getString(29);
        String post_office=cursor.getString(30);
        String police_station=cursor.getString(31);
        String city=cursor.getString(32);
        String off_day=cursor.getString(33);



        return new EntertainmentServiceProviderItemNew(
                _nodeId,
                _entSubCategoryId,
                _nodeName,
                _nodeNameBn,
                _nodeDesignation,
                _nodeContact,
                _nodeEmail,
                _nodeAdditional,
                _nodeWebsite,
                _nodeFacebook,
                _nodeRegisteredWith,
                _nodeRegistrationNumber,
                _nodeType,
                _area,
                _address,
                _latitude,
                _longitude,
                _categoryId,
                _openingtime,
                _breaktime,
                _closingtime,
                _landmark,
                _road,
                _block,
                _breaktime2,floor,
                house_name,
                house_no,
                line,
                avenue,
                post_office,
                police_station,
                city,
                off_day);
    }



}