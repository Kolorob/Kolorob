package demo.kolorob.kolorobdemoversion.database.Financial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class FinancialServiceNewTable {
    private static final String TAG = FinancialServiceNewTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_NEW;
    private static final String KEY_NODE_ID = "_finId";
    private static final String KEY_NAME_EN = "_nameen"; // 1 - text
    private static final String KEY_NAME_BN = "_namebn"; //
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
    private static final String KEY_RATING = "_rating"; //
    private static final String KEY_SUBREF = "_sref"; //
    private Context tContext;

    public FinancialServiceNewTable(Context context) {
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
                + KEY_REFNUMS + " TEXT , "
                + KEY_RATING + " TEXT , "
                + KEY_SUBREF + " TEXT ,   PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(FinancialNewItem financialNewItem) {
        return insertItem(
                financialNewItem.getFinId(),
                financialNewItem.getNameen(),
                financialNewItem.getNamebn(),
                financialNewItem.getLat(),
                financialNewItem.getLon(),financialNewItem.getFloor(),
                financialNewItem.getHousename(),financialNewItem.getHouseno(),
                financialNewItem.getRoad(),financialNewItem.getLine(),financialNewItem.getAvenue(),
                financialNewItem.getBlock(),financialNewItem.getArea(),financialNewItem.getLandmark(),
                financialNewItem.getPostoffice(),financialNewItem.getPolicestation(),
                financialNewItem.getCity(),financialNewItem.getCountry(),financialNewItem.getNode_contact(),
                financialNewItem.getNode_contact2(),financialNewItem.getNode_email(),financialNewItem.getNode_website(),
                financialNewItem.getNode_facebook(),financialNewItem.getNode_designation(),financialNewItem.getAddress(),
                financialNewItem.getOpeningtime(),
                financialNewItem.getBreaktime(),
                financialNewItem.getClosetime(),
                financialNewItem.getOffday(), financialNewItem.getRegisteredwith(),
                financialNewItem.getRegisterednumber(),financialNewItem.getCategoryId(),financialNewItem.getRefnumm(),
                financialNewItem.getRating(),financialNewItem.getSubref()
        );
    }
    private long insertItem(int finId, String nameen, String namebn, String lat, String lon,
                            String floor, String housename, String houseno, String road, String line, String avenue,
                            String block, String area, String landmark, String postoffice, String policestation, String city,
                            String country, String node_contact, String node_contact2, String node_email, String node_website,
                            String node_facebook, String node_designation,String address, String openingtime, String closetime, String breaktime,
                            String offday, String registeredwith, String registerednumber, int categoryId, String refnumm,String rating,String subref) {
        if (isFieldExist(finId)) {
            return updateItem(
                    finId,
                    nameen,
                    namebn, lat,
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
                    node_designation, address, openingtime,
                    closetime,breaktime,offday  ,registeredwith,
                    registerednumber,categoryId,refnumm,rating,subref);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , finId);
        rowValue.put(KEY_NAME_EN, nameen);
        rowValue.put(KEY_NAME_BN, namebn);
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
        rowValue.put(KEY_RATING, rating);
        rowValue.put(KEY_SUBREF, subref);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}

    public ArrayList<FinancialNewItem> getAllFinancialSubCategoriesInfo() {
        ArrayList<FinancialNewItem> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_NAME_EN, null);

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
    private long updateItem(int finId, String nameen, String namebn, String lat, String lon,
                            String floor, String housename, String houseno, String road, String line, String avenue,
                            String block, String area, String landmark, String postoffice, String policestation, String city,
                            String country, String node_contact, String node_contact2, String node_email, String node_website,
                            String node_facebook, String node_designation,String address, String openingtime, String closetime, String breaktime,
                            String offday, String registeredwith, String registerednumber, int categoryId, String refnumm,String rating,String subref) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , finId);
        rowValue.put(KEY_NAME_EN, nameen);
        rowValue.put(KEY_NAME_BN, namebn);
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
        rowValue.put(KEY_RATING, rating);
        rowValue.put(KEY_SUBREF, subref);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{finId + ""});
        closeDB();
        return ret;

    }
    public ArrayList<FinancialNewItem> getAllFinancialSubCategoriesInfo(String place) {
        ArrayList<FinancialNewItem> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+ KEY_AREA+" = '"+place+"' ORDER BY " +KEY_NAME_EN,null);

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
    public FinancialNewItem getfinNode2(int Node) {

        SQLiteDatabase db = openDB();
        FinancialNewItem financialNewItem=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+"="+Node, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                financialNewItem=new FinancialNewItem(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),
                        cursor.getString(14),cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),
                        cursor.getString(19),cursor.getString(20),cursor.getString(21),cursor.getString(22),cursor.getString(23),
                        cursor.getString(24),cursor.getString(25), cursor.getString(26),cursor.getString(27),cursor.getString(28),
                        cursor.getString(29),cursor.getString(30),cursor.getInt(31),cursor.getString(32),cursor.getString(33),cursor.getString(34));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return financialNewItem;
    }

    public ArrayList<FinancialNewItem> Finnames(String a,String place) {
        String subcatnames=null;
        subcatnames=a;
        String places;
        int k =0;
        ArrayList<FinancialNewItem> nameslist=new ArrayList<>();
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

                String getter=cursor2.getString(32);
                String delims = "[,]";
                String[] tokens = getter.split(delims);
                for (int ii=0;ii<tokens.length;ii++)
                {
                    if(Integer.parseInt(tokens[ii])==k)
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

    public ArrayList<FinancialNewItem> getAllFinancialSubCategoriesInfoWithHead(String header,String place) {


        int[] k = new int[100];
        ArrayList<FinancialNewItem> nameslist = new ArrayList<>();
        ArrayList<Integer> s = new ArrayList<Integer>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.SUB_CATEGORY_NEW + " WHERE _headbn = '" + header + "'", null);
        if (cursor.moveToFirst()) {
            do {

                s.add(cursor.getInt(5));

            } while (cursor.moveToNext());
        }
        cursor.close();

        Cursor cursor2 = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+ KEY_AREA+" = '"+place+"'", null);


        if (cursor2.moveToFirst()) {
            do {

                String getter = cursor2.getString(32);
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
    private FinancialNewItem cursorToSubCatList(Cursor cursor) {
        int _finId = cursor.getInt(0);
        String _nameen= cursor.getString(1);
        String _namebn = cursor.getString(2);
        String _lat= cursor.getString(3);
        String _lon = cursor.getString(4);
        String _floor = cursor.getString(5);
        String _housename = cursor.getString(6);

        String _houseno = cursor.getString(7);
        String _road = cursor.getString(8);
        String _line = cursor.getString(9);

        String _avenue = cursor.getString(10);
        String _block = cursor.getString(11);
        String _area = cursor.getString(12);

        String _landmark = cursor.getString(13);
        String _postoffice = cursor.getString(14);
        String _policestation = cursor.getString(15);

        String _city = cursor.getString(16);
        String _country = cursor.getString(17);
        String _node_contact = cursor.getString(18);
        String _node_contact2 = cursor.getString(19);

        String _node_email = cursor.getString(20);
        String _node_website = cursor.getString(21);
        String _node_facebook=cursor.getString(22);

        String _node_designation=cursor.getString(23);
        String _address=cursor.getString(24);
        String _opentime= cursor.getString(25);
        String _breaktime = cursor.getString(26);
        String _closetime= cursor.getString(27);
        String _offday = cursor.getString(28);
        String _regwith= cursor.getString(29);
        String _regnum = cursor.getString(30);
        int _catid=cursor.getInt(31);
        String _refnumm=cursor.getString(32);
        String _rating=cursor.getString(33);
        String _subref=cursor.getString(34);
        return new FinancialNewItem(_finId,_nameen,_namebn,_lat, _lon,_floor,_housename,_houseno,_road,_line,_avenue,_block,_area,_landmark,_postoffice,_policestation,
                _city,_country,_node_contact,_node_contact2,_node_email,_node_website,_node_facebook,_node_designation,_address,
                _opentime,
                _breaktime,_closetime,_offday,_regwith,
                _regnum,_catid,_refnumm,_rating,_subref);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}
