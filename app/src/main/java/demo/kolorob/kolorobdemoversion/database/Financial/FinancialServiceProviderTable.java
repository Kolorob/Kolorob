package demo.kolorob.kolorobdemoversion.database.Financial;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 1/11/2016.
 */
public class FinancialServiceProviderTable {


    private static final String TAG = FinancialServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_PROVIDER_TABLE;

    private static final String KEY_FIN_NODE_ID = "_nodeId"; // 0 -integer
    private static final String KEY_FIN_NODE_NAME = "_nodeName"; // 1 - text
    private static final String KEY_FIN_NODE_DESIGNATION = "_nodeDesignation"; //
    private static final String KEY_FIN_NODE_CONTACT = "_nodeContact"; //
    private static final String KEY_FIN_NODE_EMAIL  = "_nodeEmail"; //
    private static final String KEY_FIN_NODE_ADDITIONAL = "_nodeAdditional"; //
    private static final String KEY_FIN_NODE_WEBSITE = "_nodeWebsite"; //
    private static final String KEY_FIN_NODE_FACEBOOK = "_nodeFacebook"; //
    private static final String KEY_FIN_NODE_REGISTERED_WITH = "_nodeRegisteredwith"; //
    private static final String KEY_FIN_NODE_REGISTRATION_NUMBER = "_nodeRegistationNumber"; //
    private static final String KEY_FIN_REF_NUM = "_refNum"; //
    private static final String KEY_FIN_NAME_BN= "_namebn"; //
    private static final String KEY_FIN_AREA = "_area"; //
    private static final String KEY_FIN_ADDRESS = "_address"; //
    private static final String KEY_FIN_LATITUDE  = "_latitude"; //
    private static final String KEY_FIN_LONGITUDE = "_longitude"; //

    private static final String KEY_CATEGORY_ID = "_categoryId"; //
    private static final String KEY_OPENTIME = "_openingtime"; //
    private static final String KEY_BREAKTIME = "_breaktime"; //
    private static final String KEY_CLOSEATIME = "_closingtime"; //
    private static final String KEY_LANDMARK = "_landmark"; //
    private static final String KEY_ROAD = "_road"; //
    private static final String KEY_BLOCK = "_block"; //
    private static final String KEY_BREAKTIME2 = "_breaktime2"; //
    private static final String KEY_ADTIME = "_additionaltime";

private  FinancialServiceProviderItem financialServiceProviderItem;
    private Context tContext;


    public FinancialServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FIN_NODE_ID + "  INTEGER , " // 0 - int
                + KEY_FIN_NODE_NAME + "  TEXT, "              // 1 - text
                + KEY_FIN_NODE_DESIGNATION + " TEXT, "
                + KEY_FIN_NODE_CONTACT + " TEXT, "
                + KEY_FIN_NODE_EMAIL + " TEXT, "
                + KEY_FIN_NODE_ADDITIONAL + " TEXT, "
                + KEY_FIN_NODE_WEBSITE + " TEXT, "
                + KEY_FIN_NODE_FACEBOOK + " TEXT, "
                + KEY_FIN_NODE_REGISTERED_WITH + " TEXT, "
                + KEY_FIN_NODE_REGISTRATION_NUMBER + " TEXT, "
                + KEY_FIN_REF_NUM+ " INTEGER, "
                + KEY_FIN_NAME_BN + " TEXT, "
                + KEY_FIN_AREA + " TEXT, "
                + KEY_FIN_ADDRESS + " TEXT, "
                + KEY_FIN_LATITUDE + " TEXT, "
                + KEY_FIN_LONGITUDE + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER,"
                + KEY_OPENTIME + " TEXT, "
                + KEY_BREAKTIME + " TEXT, "
                + KEY_CLOSEATIME + " TEXT, "
                + KEY_LANDMARK + " TEXT, "
                + KEY_ROAD+ " TEXT, "
                + KEY_BLOCK + " TEXT, "
                + KEY_BREAKTIME2 + " TEXT, "
                + KEY_ADTIME + " TEXT, PRIMARY KEY(" + KEY_FIN_NODE_ID+ ", " + KEY_FIN_REF_NUM + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }
    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }
    public long insertItem(FinancialServiceProviderItem financialServiceProviderItem) {
        return insertItem(financialServiceProviderItem.getNodeId(), financialServiceProviderItem.getNodeName(),
                financialServiceProviderItem.getNodeDesignation(), financialServiceProviderItem.getNodeContact(),
                financialServiceProviderItem.getNodeEmail(),financialServiceProviderItem.getNodeAdditional(),financialServiceProviderItem.getNodeWebsite(),
                financialServiceProviderItem.getNodeFacebook(),financialServiceProviderItem.getNodeRegisteredwith(),
                financialServiceProviderItem.getNodeRegistrationnumber(),financialServiceProviderItem.getRefNum(),financialServiceProviderItem.getNamebn(), financialServiceProviderItem.getArea(), financialServiceProviderItem.getAddress(),
                financialServiceProviderItem.getLatitude(),financialServiceProviderItem.getLongitude(),financialServiceProviderItem.getCategoryId(),
                financialServiceProviderItem.getOpeningtime(),
                financialServiceProviderItem.getBreaktime(),
                financialServiceProviderItem.getClosingtime(),
                financialServiceProviderItem.getLandmark(),
                financialServiceProviderItem.getRoad(),
                financialServiceProviderItem.getBlock(),
                financialServiceProviderItem.getBreaktime2(),
                financialServiceProviderItem.getAdditionaltime());
    }

    private long insertItem(String nodeId, String nodeName, String nodeDesignation,
                            String nodeContact, String nodeEmail, String nodeAdditional,
                            String nodeWebsite, String nodeFacebook, String nodeRegisteredwith,
                            String nodeRegistrationnumber, int refNum,String namebn, String area,
                            String address, String latitude, String longitude, int categoryId, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime) {
        if (isFieldExist(nodeId, categoryId, refNum)) {
            return updateItem(
                    nodeId,
                   nodeName,
                    nodeDesignation,
                    nodeContact,
                    nodeEmail,
                    nodeAdditional,
                    nodeWebsite,
                    nodeFacebook,
                    nodeRegisteredwith,
                    nodeRegistrationnumber,
                    refNum,
                    namebn,
                    area,
                    address,
                    latitude,
                    longitude,
                    categoryId,openingtime,
                    breaktime,
                    closingtime,
                    landmark,
                    road,
                    block,
                    breaktime2,
                    additionaltime);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID, nodeId);
        rowValue.put(KEY_FIN_NODE_NAME,  nodeName);
        rowValue.put(KEY_FIN_NODE_DESIGNATION, nodeDesignation);
        rowValue.put(KEY_FIN_NODE_CONTACT, nodeContact);
        rowValue.put(KEY_FIN_NODE_EMAIL,  nodeEmail);
        rowValue.put(KEY_FIN_NODE_ADDITIONAL ,  nodeAdditional);
        rowValue.put(KEY_FIN_NODE_WEBSITE ,nodeWebsite);
        rowValue.put(KEY_FIN_NODE_FACEBOOK , nodeFacebook);
        rowValue.put(KEY_FIN_NODE_REGISTERED_WITH, nodeRegisteredwith);
        rowValue.put(KEY_FIN_NODE_REGISTRATION_NUMBER,nodeRegistrationnumber);
        rowValue.put(KEY_FIN_REF_NUM , refNum);
        rowValue.put(KEY_FIN_NAME_BN, namebn);
        rowValue.put(KEY_FIN_AREA, area);
        rowValue.put(KEY_FIN_ADDRESS, address);
        rowValue.put(KEY_FIN_LATITUDE, latitude);
        rowValue.put(KEY_FIN_LONGITUDE, longitude);
        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_OPENTIME , openingtime);
        rowValue.put(KEY_BREAKTIME  , breaktime);
        rowValue.put(KEY_CLOSEATIME  , closingtime);
        rowValue.put(KEY_LANDMARK  , landmark);
        rowValue.put(KEY_ROAD  , road );
        rowValue.put(KEY_BLOCK   , block );
        rowValue.put(KEY_BREAKTIME2  , breaktime2 );
        rowValue.put(KEY_ADTIME  , additionaltime );
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;


    }

    private long  updateItem(String nodeId, String nodeName, String nodeDesignation, String nodeContact,
                             String nodeEmail, String nodeAdditional, String nodeWebsite, String nodeFacebook,
                             String nodeRegisteredwith, String nodeRegistrationnumber, int refNum,String namebn, String area, String address,
                             String latitude, String longitude, int categoryId, String openingtime, String breaktime,
                             String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID, nodeId);
        rowValue.put(KEY_FIN_NODE_NAME,  nodeName);
        rowValue.put(KEY_FIN_NODE_DESIGNATION, nodeDesignation);
        rowValue.put(KEY_FIN_NODE_CONTACT, nodeContact);
        rowValue.put(KEY_FIN_NODE_EMAIL,  nodeEmail);
        rowValue.put(KEY_FIN_NODE_ADDITIONAL ,  nodeAdditional);
        rowValue.put(KEY_FIN_NODE_WEBSITE ,nodeWebsite);
        rowValue.put(KEY_FIN_NODE_FACEBOOK , nodeFacebook);
        rowValue.put(KEY_FIN_NODE_REGISTERED_WITH, nodeRegisteredwith);
        rowValue.put(KEY_FIN_NODE_REGISTRATION_NUMBER,nodeRegistrationnumber);
        rowValue.put(KEY_FIN_REF_NUM , refNum);
        rowValue.put(KEY_FIN_NAME_BN, namebn);
        rowValue.put(KEY_FIN_AREA, area);
        rowValue.put(KEY_FIN_ADDRESS, address);
        rowValue.put(KEY_FIN_LATITUDE, latitude);
        rowValue.put(KEY_FIN_LONGITUDE, longitude);
        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_OPENTIME , openingtime);
        rowValue.put(KEY_BREAKTIME  , breaktime);
        rowValue.put(KEY_CLOSEATIME  , closingtime);
        rowValue.put(KEY_LANDMARK  , landmark);
        rowValue.put(KEY_ROAD  , road );
        rowValue.put(KEY_BLOCK   , block );
        rowValue.put(KEY_BREAKTIME2  , breaktime2 );
        rowValue.put(KEY_ADTIME  , additionaltime );
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;

    }

    public boolean isFieldExist(String id, int cat_id, int sub_cat_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0)) && Integer.parseInt(cursor.getString(16)) == cat_id && Integer.parseInt(cursor.getString(10)) == sub_cat_id) {
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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<FinancialServiceProviderItem> getAllFinancialSubCategoriesInfo(int cat_id, int sub_cat_id) {
        ArrayList<FinancialServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id + " AND " + KEY_FIN_REF_NUM + "=" + sub_cat_id, null, null);

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
    public ArrayList<FinancialServiceProviderItem> Finnames(int cat_id,String head,String a,String place) {
        String subcatnames=null;
        subcatnames=a;

        ArrayList<FinancialServiceProviderItem> nameslist=new ArrayList<>();

        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                + " AND "+KEY_FIN_AREA+" = '"+place+"'"  + " AND "+ KEY_FIN_REF_NUM + "=" + "(SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_name = '"+subcatnames+"')", null);


        if (cursor.moveToFirst()) {
            do {


                nameslist.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return  nameslist;
    }
    public FinancialServiceProviderItem getfinNode2(String Node) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_FIN_NODE_ID+"="+Node, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                financialServiceProviderItem=new FinancialServiceProviderItem(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),
                        cursor.getString(8),cursor.getString(9),
                        cursor.getInt(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),
                        cursor.getInt(16), cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),cursor.getString(21),cursor.getString(22),
                        cursor.getString(23),cursor.getString(24));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return financialServiceProviderItem;
    }
    public ArrayList<FinancialServiceProviderItem> getfinNode(String Node) {
        ArrayList<FinancialServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_FIN_NODE_ID+"="+Node, null);

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

    public ArrayList<FinancialServiceProviderItem> getAllFinancialSubCategoriesInfo(int cat_id) {
        ArrayList<FinancialServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id +" ORDER BY " +KEY_FIN_NODE_NAME, null);

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

    public ArrayList<FinancialServiceProviderItem> getAllFinancialSubCategoriesInfoWithHead(int cat_id, String header) {
        ArrayList<FinancialServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                + " AND " + KEY_FIN_REF_NUM + " in (SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '" + header + "')", null);

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

    private FinancialServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _FinnodeId = cursor.getString(0);
        String _FinnodeName = cursor.getString(1);
        String _FinnodeDesignation = cursor.getString(2);
        String _FinnodeContact = cursor.getString(3);
        String _FinnodeEmail = cursor.getString(4);
        String _FinnodeAdditional = cursor.getString(5);
        String  _FinnodeWebsite = cursor.getString(6);
        String _FinnodeFacebook = cursor.getString(7);
        String _FinnodeRegisteredwith = cursor.getString(8);
        String _FinnodeRegistrationNumber = cursor.getString(9);
        int  _FinrefNum = cursor.getInt(10);
        String _Finnamebn=cursor.getString(11);
        String _Finarea = cursor.getString(12);
        String _Finaddress = cursor.getString(13);
        String _Finlatitude = cursor.getString(14);
        String _Finlongitude = cursor.getString(15);
        int    _categoryId   = cursor.getInt(16);
        String _openingtime=cursor.getString(17);
        String _breaktime=cursor.getString(18);
        String _closingtime=cursor.getString(19);
        String _landmark=cursor.getString(20);
        String _road=cursor.getString(21);
        String _block=cursor.getString(22);
        String _breaktime2=cursor.getString(23);
        String _additionaltime=cursor.getString(24);

        return new FinancialServiceProviderItem(_FinnodeId, _FinnodeName,_FinnodeDesignation,
                _FinnodeContact,_FinnodeEmail, _FinnodeAdditional, _FinnodeWebsite,_FinnodeFacebook,
                _FinnodeRegisteredwith, _FinnodeRegistrationNumber, _FinrefNum, _Finnamebn,_Finarea, _Finaddress, _Finlatitude, _Finlongitude,_categoryId,_openingtime,
                _breaktime,
                _closingtime,
                _landmark,
                _road,
                _block,
                _breaktime2,
                _additionaltime);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
