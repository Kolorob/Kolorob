package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

    private static final String KEY_NODE_ID = "_node_id"; // 0 -integer
    private static final String KEY_NODE_NAME = "_node_name"; // 1 - text
    private static final String KEY_DATE_NAME = "_date_name"; // 2 - text*/
    private static final String KEY_DATE_DATE = "_date_date";
    private static final String KEY_NODE_DESIGNATION = "_node_designation"; //
    private static final String KEY_NODE_CONTACT = "_node_contact"; //
    private static final String KEY_NODE_EMAIL = "_node_email"; //
    private static final String KEY_NODE_ADDITIONAL = "_node_additional"; //
    private static final String KEY_NODE_WEBSITE = "_node_website"; //
    private static final String KEY_NODE_FACEBOOK = "_node_facebook"; //
    private static final String KEY_NODE_REGISTERED_WITH = "_node_registeredwith"; //
    private static final String KEY_NODE_REGISTRATION_NUMBER = "_node_registationNumber"; //
    private static final String KEY_EDITED_BY = "_edited_by"; //
    private static final String KEY_REF_NUM = "_ref_num"; //
    private static final String KEY_NAME_BN = "name_bn";
    private static final String KEY_TIME_STAMP = "_time_stamp"; //
    private static final String KEY_NODE_TYPE = "_node_type"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_ADDRESS = "_address"; //
    private static final String KEY_LONGITUDE = "_longitude"; //
    private static final String KEY_LATITUDE = "_latitude"; //
    private static final String KEY_CATEGORY_ID = "_categoryId"; //


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
                + KEY_CATEGORY_ID + " INTEGER, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_CATEGORY_ID + "))";
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
                healthServiceProviderItem.getCategoryId()
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
                                 int categoryId) {
        if (isFieldExist(nodeId, categoryId)) {
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
                    categoryId);
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


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    public boolean isFieldExist(String id,int cat_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&&Integer.parseInt(cursor.getString(21))==cat_id) {
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
                            int categoryId) {
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


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ? AND "+KEY_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "",categoryId+""});
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

    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfo(int cat_id) {
        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);

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
                _categoryId);
    }

}

