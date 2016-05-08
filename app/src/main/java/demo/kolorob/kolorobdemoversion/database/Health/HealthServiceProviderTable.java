package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;

import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by Mazharul.Islam1 on 1/10/2016.
 */
public class HealthServiceProviderTable {

    private static final String TAG = HealthServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_PROVIDER_TABLE;

    private static final String KEY_NODE_ID = "_nodeId"; // 0 -integer
    private static final String KEY_NODE_NAME = "_nodeName"; // 1 - text
    private static final String KEY_DATE_NAME = "_dateName"; // 2 - text*/
    private static final String KEY_DATE_DATE = "_dateDate";
    private static final String KEY_NODE_DESIGNATION = "_nodeDesignation"; //
    private static final String KEY_NODE_CONTACT = "_nodeContact"; //
    private static final String KEY_NODE_EMAIL = "_nodeEmail"; //
    private static final String KEY_NODE_ADDITIONAL = "_nodeAdditional"; //
    private static final String KEY_NODE_WEBSITE = "_nodeWebsite"; //
    private static final String KEY_NODE_FACEBOOK = "_nodeFacebook"; //
    private static final String KEY_NODE_REGISTERED_WITH = "_nodeRegisteredwith"; //
    private static final String KEY_NODE_REGISTRATION_NUMBER = "_nodeRegistationNumber"; //
    private static final String KEY_EDITED_BY = "_editedBy"; //
    private static final String KEY_REF_NUM = "_refNum"; //
    private static final String KEY_NAME_BN = "nameBn";
    private static final String KEY_TIME_STAMP = "_timeStamp"; //
    private static final String KEY_NODE_TYPE = "_nodeType"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_ADDRESS = "_address"; //
    private static final String KEY_LONGITUDE = "_longitude"; //
    private static final String KEY_LATITUDE = "_latitude"; //
    private static final String KEY_CATEGORY_ID = "_categoryId"; //
    private static final String KEY_OPENTIME = "_openingtime"; //
    private static final String KEY_BREAKTIME = "_breaktime"; //
    private static final String KEY_CLOSEATIME = "_closingtime"; //
    private static final String KEY_LANDMARK = "_landmark"; //
    private static final String KEY_ROAD = "_road"; //
    private static final String KEY_BLOCK = "_block"; //
    private static final String KEY_BREAKTIME2 = "_breaktime2"; //
    private static final String KEY_ADTIME = "_additionaltime"; //



    private Context tContext;


    public HealthServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + "  INTEGER , " // 0 - int
                + KEY_NODE_NAME + "  TEXT, "              // 1 - text
                + KEY_DATE_NAME + " TEXT, "
                + KEY_DATE_DATE + " TEXT, "// 2 - text
                + KEY_NODE_DESIGNATION + " TEXT, "
                + KEY_NODE_CONTACT + " TEXT, "
                + KEY_NODE_EMAIL + " TEXT, "
                + KEY_NODE_ADDITIONAL + " TEXT, "
                + KEY_NODE_WEBSITE + " TEXT, "
                + KEY_NODE_FACEBOOK + " TEXT, "
                + KEY_NODE_REGISTERED_WITH + " TEXT, "
                + KEY_NODE_REGISTRATION_NUMBER + " TEXT, "
                + KEY_EDITED_BY + " TEXT, "
                + KEY_REF_NUM + " INTEGER, "
                + KEY_NAME_BN + " TEXT, "
                + KEY_TIME_STAMP + " TEXT, "
                + KEY_NODE_TYPE + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER, "
                + KEY_OPENTIME + " TEXT, "
                + KEY_BREAKTIME + " TEXT, "
                + KEY_CLOSEATIME + " TEXT, "
                + KEY_LANDMARK + " TEXT, "
                + KEY_ROAD+ " TEXT, "
                + KEY_BLOCK + " TEXT, "
                + KEY_BREAKTIME2 + " TEXT, "
                + KEY_ADTIME + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_REF_NUM + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public long insertItemHealth(HealthServiceProviderItem healthServiceProviderItem) {
        return insertItemHealth(
                healthServiceProviderItem.getNodeId(),
                healthServiceProviderItem.getNodeName(),
                healthServiceProviderItem.getDateName(),
                healthServiceProviderItem.getDateDate(),
                healthServiceProviderItem.getNodeDesignation(),
                healthServiceProviderItem.getNodeContact(),
                healthServiceProviderItem.getNodeEmail(),
                healthServiceProviderItem.getNodeAdditional(),
                healthServiceProviderItem.getNodeWebsite(),
                healthServiceProviderItem.getNodeFacebook(),
                healthServiceProviderItem.getNodeRegisteredwith(),
                healthServiceProviderItem.getNodeRegistationNumber(),
                healthServiceProviderItem.getEditedBy(),
                healthServiceProviderItem.getRefNum(),
                healthServiceProviderItem.getNameBn(),
                healthServiceProviderItem.getTimeStamp(),
                healthServiceProviderItem.getNodeType(),
                healthServiceProviderItem.getArea(),
                healthServiceProviderItem.getAddress(),
                healthServiceProviderItem.getLatitude(),
                healthServiceProviderItem.getLongitude(),
                healthServiceProviderItem.getCategoryId(),
                healthServiceProviderItem.getOpeningtime(),
                healthServiceProviderItem.getBreaktime(),
                healthServiceProviderItem.getClosingtime(),
                healthServiceProviderItem.getLandmark(),
                healthServiceProviderItem.getRoad(),
                healthServiceProviderItem.getBlock(),
                healthServiceProviderItem.getBreaktime2(),
                healthServiceProviderItem.getAdditionaltime()

        );
    }

    public long insertItemHealth(String nodeId,
                                 String nodeName,
                                 String dateName,
                                 String dateDate,
                                 String nodeDesignation,
                                 String nodeContact,
                                 String nodeEmail,
                                 String nodeAdditional,
                                 String nodeWebsite,
                                 String nodeFacebook,
                                 String nodeRegisteredwith,
                                 String nodeRegistationNumber,
                                 String editedBy,
                                 int refNum,
                                 String nameBn,
                                 String timeStamp,
                                 String nodeType,
                                 String area,
                                 String address,
                                 String longitude,
                                 String latitude,
                                 int categoryId, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime) {
        if (isFieldExist(nodeId, categoryId, refNum)) {
            return updateItem(
                    nodeId,
                    nodeName,
                    dateName,
                    dateDate,
                    nodeDesignation,
                    nodeContact,
                    nodeEmail,
                    nodeAdditional,
                    nodeWebsite,
                    nodeFacebook,
                    nodeRegisteredwith,
                    nodeRegistationNumber,
                    editedBy,
                    refNum,
                    nameBn,
                    timeStamp,
                    nodeType,
                    area,
                    address,
                    longitude,
                    latitude,
                    categoryId,
                    openingtime,
                    breaktime,
                    closingtime,
                    landmark,
                    road,
                    block,
                    breaktime2,
                    additionaltime);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_NODE_NAME , nodeName);
        rowValue.put(KEY_DATE_NAME  , dateName);
        rowValue.put(KEY_DATE_DATE, dateDate );
        rowValue.put(KEY_NODE_DESIGNATION , nodeDesignation );
        rowValue.put(KEY_NODE_CONTACT ,nodeContact  );
        rowValue.put(KEY_NODE_EMAIL  , nodeEmail );
        rowValue.put(KEY_NODE_ADDITIONAL , nodeAdditional );
        rowValue.put(KEY_NODE_WEBSITE,nodeWebsite );
        rowValue.put(KEY_NODE_FACEBOOK , nodeFacebook );
        rowValue.put(KEY_NODE_REGISTERED_WITH ,nodeRegisteredwith );
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER ,nodeRegistationNumber);
        rowValue.put(KEY_EDITED_BY , editedBy  );
        rowValue.put(KEY_REF_NUM   , refNum);
        rowValue.put(KEY_NAME_BN   , nameBn);
        rowValue.put(KEY_TIME_STAMP  , timeStamp);
        rowValue.put(KEY_NODE_TYPE  , nodeType);
        rowValue.put(KEY_AREA   , area );
        rowValue.put(KEY_ADDRESS   , address );
        rowValue.put(KEY_LATITUDE  , longitude );
        rowValue.put(KEY_LONGITUDE  , latitude );
        rowValue.put(KEY_CATEGORY_ID , categoryId);
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
    public HealthServiceProviderItem gethelNode2(String Node) {

        SQLiteDatabase db = openDB();
        HealthServiceProviderItem healthServiceProviderItem=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+"="+Node, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));

                healthServiceProviderItem=new HealthServiceProviderItem( cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),
                        cursor.getString(11),cursor.getString(12),cursor.getInt(13),cursor.getString(14),cursor.getString(15),
                        cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),
                        cursor.getInt(21),cursor.getString(22),cursor.getString(23),cursor.getString(24),cursor.getString(25)
                        ,cursor.getString(26),cursor.getString(27),cursor.getString(28),cursor.getString(29));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return healthServiceProviderItem;
    }
    public boolean isFieldExist(String id,int cat_id,int sub_cat_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&&Integer.parseInt(cursor.getString(21))==cat_id&&Integer.parseInt(cursor.getString(13))==sub_cat_id) {
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
    public ArrayList<HealthServiceProviderItem> Heanames(int cat_id,String head,String a,String place) {
        String subcatnames=null;
        subcatnames=a;

        ArrayList<HealthServiceProviderItem> nameslist=new ArrayList<>();

        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                + " AND "+KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_REF_NUM + "=" + "(SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_name = '"+subcatnames+"')", null);


        if (cursor.moveToFirst()) {
            do {


                nameslist.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return  nameslist;
    }
    private long updateItem(String nodeId,
                            String nodeName,
                            String dateName,
                            String dateDate,
                            String nodeDesignation,
                            String nodeContact,
                            String nodeEmail,
                            String nodeAdditional,
                            String nodeWebsite,
                            String nodeFacebook,
                            String nodeRegisteredwith,
                            String nodeRegistationNumber,
                            String editedBy,
                            int refNum,
                            String nameBn,
                            String timeStamp,
                            String nodeType,
                            String area,
                            String address,
                            String longitude,
                            String latitude,
                            int categoryId, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_NODE_NAME , nodeName);
        rowValue.put(KEY_DATE_NAME  , dateName);
        rowValue.put(KEY_DATE_DATE, dateDate );
        rowValue.put(KEY_NODE_DESIGNATION , nodeDesignation );
        rowValue.put(KEY_NODE_CONTACT ,nodeContact  );
        rowValue.put(KEY_NODE_EMAIL  , nodeEmail );
        rowValue.put(KEY_NODE_ADDITIONAL , nodeAdditional );
        rowValue.put(KEY_NODE_WEBSITE,nodeWebsite );
        rowValue.put(KEY_NODE_FACEBOOK , nodeFacebook );
        rowValue.put(KEY_NODE_REGISTERED_WITH ,nodeRegisteredwith );
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER ,nodeRegistationNumber);
        rowValue.put(KEY_EDITED_BY , editedBy  );
        rowValue.put(KEY_REF_NUM   , refNum);
        rowValue.put(KEY_NAME_BN   , nameBn);
        rowValue.put(KEY_TIME_STAMP  , timeStamp);
        rowValue.put(KEY_NODE_TYPE  , nodeType);
        rowValue.put(KEY_AREA   , area );
        rowValue.put(KEY_ADDRESS   , address );
        rowValue.put(KEY_LATITUDE  , longitude );
        rowValue.put(KEY_LONGITUDE  , latitude );
        rowValue.put(KEY_CATEGORY_ID , categoryId);
        rowValue.put(KEY_OPENTIME , openingtime);
        rowValue.put(KEY_BREAKTIME  , breaktime);
        rowValue.put(KEY_CLOSEATIME  , closingtime);
        rowValue.put(KEY_LANDMARK  , landmark);
        rowValue.put(KEY_ROAD  , road );
        rowValue.put(KEY_BLOCK   , block );
        rowValue.put(KEY_BREAKTIME2  , breaktime2 );
        rowValue.put(KEY_ADTIME  , additionaltime );


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ? AND "+KEY_REF_NUM + " = ? AND "+KEY_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "",refNum+"",categoryId+""});
        closeDB();
        return ret;
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfo(int cat_id,int sub_cat_id) {
        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_REF_NUM+"="+sub_cat_id, null);

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

    public Vector<String> getAllEntertainmentSubCategoriesInfo() {
        Vector<String> subCatList = new Vector<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        int cat_id=2;

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


    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfo(int cat_id) {
        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+ " ORDER BY " +KEY_NODE_NAME, null);

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

    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfoWithHead(int cat_id,String header) {
        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id
                + " AND " +KEY_REF_NUM + " in (SELECT _sub_cat_id from "+ DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '"+header+"')", null);

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

    private HealthServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _nodeId=cursor.getString(0);
        String _nodeName=cursor.getString(1);
        String _dateName=cursor.getString(2);
        String _dateDate=cursor.getString(3);
        String _nodeDesignation=cursor.getString(4);
        String _nodeContact=cursor.getString(5);
        String _nodeEmail=cursor.getString(6);
        String _nodeAdditional=cursor.getString(7);
        String _nodeWebsite=cursor.getString(8);
        String _nodeFacebook=cursor.getString(9);
        String _nodeRegisteredwith=cursor.getString(10);
        String _nodeRegistationNumber=cursor.getString(11);
        String _editedBy=cursor.getString(12);
        int _refNum=cursor.getInt(13);
        String _nameBn=cursor.getString(14);
        String _timeStamp=cursor.getString(15);
        String _nodeType=cursor.getString(16);
        String _area=cursor.getString(17);
        String _address=cursor.getString(18);
        String _longitude=cursor.getString(19);
        String _latitude=cursor.getString(20);
        int _categoryId=cursor.getInt(21);
        String _openingtime=cursor.getString(22);
        String _breaktime=cursor.getString(23);
        String _closingtime=cursor.getString(24);
        String _landmark=cursor.getString(25);
        String _road=cursor.getString(26);
        String _block=cursor.getString(27);
        String _breaktime2=cursor.getString(28);
        String _additionaltime=cursor.getString(29);


        return new HealthServiceProviderItem(
                _nodeId,
                _nodeName,
                _dateName,
                _dateDate,
                _nodeDesignation,
                _nodeContact,
                _nodeEmail,
                _nodeAdditional,
                _nodeWebsite,
                _nodeFacebook,
                _nodeRegisteredwith,
                _nodeRegistationNumber,
                _editedBy,
                _refNum,
                _nameBn,
                _timeStamp,
                _nodeType,
                _area,
                _address,
                _longitude,
                _latitude,
                _categoryId,
                _openingtime,
                _breaktime,
                _closingtime,
                _landmark,
                _road,
                _block,
                _breaktime2,
                _additionaltime);
    }

}
