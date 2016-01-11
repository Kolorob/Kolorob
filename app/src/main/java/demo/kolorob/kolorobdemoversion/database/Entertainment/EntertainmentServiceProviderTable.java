package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;

import demo.kolorob.kolorobdemoversion.model.Education.Entertainment;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;


/**
 * Created by Mazharul.Islam1 on 1/5/2016.
 */
public class EntertainmentServiceProviderTable {

    private static final String TAG = EntertainmentServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_PROVIDER_TABLE;

    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_SUB_CATEGORY_ID = "_ent_sub_category_id";
    public static final String KEY_NODE_NAME = "_node_name";
    public static final String KEY_NODE_NAME_BN = "_node_name_bn";
    public static final String KEY_DATE_NAME = "_data_name";
    public static final String KEY_DATE_DATE = "_data_date";
    public static final String KEY_NODE_DESIGNATION = "_node_designation";
    public static final String KEY_NODE_CONTACT = "_node_contact";
    public static final String KEY_NODE_EMAIL = "_node_email";
    public static final String KEY_NODE_ADDITIONAL = "_node_additional";
    public static final String KEY_NODE_WEBSITE = "_node_website";
    public static final String KEY_NODE_FACEBOOK = "_node_facebook";
    public static final String KEY_NODE_REGISTERED_WITH = "_node_registered_with";
    public static final String KEY_NODE_REGISTRATION_NUMBER = "_node_registration_number";
    public static final String KEY_EDITED_BY = "_edited_by";
    public static final String KEY_UPLOADING_TIME = "_uploading_time";
    public static final String KEY_NODE_TYPE = "_node_type";
    public static final String KEY_AREA = "_area";
    public static final String KEY_ADDRESS = "_address";
    public static final String KEY_LATITUDE = "_latitude";
    public static final String KEY_LONGITUDE = "_longitude";
    public static final String KEY_CATEGORY_ID = "_categoryId";

    private Context tContext;


    public EntertainmentServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + "  INTEGER , " // 0 - int
                + KEY_SUB_CATEGORY_ID + "  INTEGER, "              // 1 - text
                + KEY_NODE_NAME + " TEXT, "
                + KEY_NODE_NAME_BN + " TEXT, "// 2 - text
                + KEY_DATE_NAME + " TEXT, "
                + KEY_DATE_DATE + " TEXT, "
                + KEY_NODE_DESIGNATION + " TEXT, "
                + KEY_NODE_CONTACT + " TEXT, "
                + KEY_NODE_EMAIL + " TEXT, "
                + KEY_NODE_ADDITIONAL + " TEXT, "
                + KEY_NODE_WEBSITE + " TEXT, "
                + KEY_NODE_FACEBOOK + " TEXT, "
                + KEY_NODE_REGISTERED_WITH + " TEXT, "
                + KEY_NODE_REGISTRATION_NUMBER + " TEXT, "
                + KEY_EDITED_BY + " TEXT, "
                + KEY_UPLOADING_TIME + " TEXT, "
                + KEY_NODE_TYPE + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_SUB_CATEGORY_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItemEntertainment(EntertainmentServiceProviderItem entertainmentServiceProviderItem) {
        return insertItemEntertainment(
                entertainmentServiceProviderItem.getNodeId(),
                entertainmentServiceProviderItem.getEntSubCategoryId(),
                entertainmentServiceProviderItem.getNodeName(),
                entertainmentServiceProviderItem.getNodeNameBn(),
                entertainmentServiceProviderItem.getDataName(),
                entertainmentServiceProviderItem.getDataDate(),
                entertainmentServiceProviderItem.getNodeDesignation(),
                entertainmentServiceProviderItem.getNodeEmail(),
                entertainmentServiceProviderItem.getNodeAdditional(),
                entertainmentServiceProviderItem.getNodeContact(),
                entertainmentServiceProviderItem.getNodeWebsite(),
                entertainmentServiceProviderItem.getNodeFacebook(),
                entertainmentServiceProviderItem.getNodeRegisteredWith(),
                entertainmentServiceProviderItem.getNodeRegistrationNumber(),
                entertainmentServiceProviderItem.getEditedBy(),
                entertainmentServiceProviderItem.getUploadingTime(),
                entertainmentServiceProviderItem.getNodeType(),
                entertainmentServiceProviderItem.getArea(),
                entertainmentServiceProviderItem.getAddress(),
                entertainmentServiceProviderItem.getLatitude(),
                entertainmentServiceProviderItem.getLongitude(),
                entertainmentServiceProviderItem.getCategoryId()

                );
    }

    public long insertItemEntertainment(String nodeId,
                                        int entSubCategoryId,
                                        String nodeName,
                                        String nodeNameBn,
                                        String dataName,
                                        String dataDate,
                                        String nodeDesignation,
                                        String nodeContact,
                                        String nodeEmail,
                                        String nodeAdditional,
                                        String nodeWebsite,
                                        String nodeFacebook,
                                        String nodeRegisteredWith,
                                        String nodeRegistrationNumber,
                                        String editedBy,
                                        String uploadingTime,
                                        String nodeType,
                                        String area,
                                        String address,
                                        String latitude,
                                        String longitude,
                                        int categoryId) {
        if (isFieldExist(nodeId, entSubCategoryId)) {
            return updateItem(nodeId,
                    entSubCategoryId,
                    nodeName,
                    nodeNameBn,
                    dataName,
                    dataDate,
                    nodeDesignation,
                    nodeContact,
                    nodeEmail,
                    nodeAdditional,
                    nodeWebsite,
                    nodeFacebook,
                    nodeRegisteredWith,
                    nodeRegistrationNumber,
                    editedBy,
                    uploadingTime,
                    nodeType,
                    area,
                    address,
                    latitude,
                    longitude,
                    categoryId);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID , entSubCategoryId);
        rowValue.put(KEY_NODE_NAME  , nodeName);
        rowValue.put(KEY_NODE_NAME_BN,nodeNameBn);
        rowValue.put(KEY_DATE_NAME , dataName);
        rowValue.put(KEY_DATE_DATE , dataDate);
        rowValue.put(KEY_NODE_DESIGNATION ,nodeDesignation );
        rowValue.put(KEY_NODE_CONTACT  , nodeContact);
        rowValue.put(KEY_NODE_EMAIL , nodeEmail);
        rowValue.put(KEY_NODE_ADDITIONAL,nodeAdditional);
        rowValue.put(KEY_NODE_WEBSITE , nodeWebsite);
        rowValue.put(KEY_NODE_FACEBOOK ,nodeFacebook);
        rowValue.put(KEY_NODE_REGISTERED_WITH, nodeRegisteredWith);
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER  , nodeRegistrationNumber);
        rowValue.put(KEY_EDITED_BY , editedBy);
        rowValue.put(KEY_UPLOADING_TIME , uploadingTime);
        rowValue.put(KEY_NODE_TYPE  , nodeType);
        rowValue.put(KEY_AREA  , area);
        rowValue.put(KEY_ADDRESS , address);
        rowValue.put(KEY_LATITUDE,latitude);
        rowValue.put(KEY_LONGITUDE,longitude);
        rowValue.put(KEY_CATEGORY_ID,categoryId);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;


    }


    public boolean isFieldExist(String nodeId,int entSubCategoryId) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (nodeId.equals(cursor.getString(0))&&Integer.parseInt(cursor.getString(1))==entSubCategoryId) {
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
                            String dataName,
                            String dateDate,
                            String nodeDesignation,
                            String nodeContact,
                            String nodeEmail,
                            String nodeAdditional,
                            String nodeWebsite,
                            String nodeFacebook,
                            String nodeRegisteredWith,
                            String nodeRegistrationNumber,
                            String editedBy,
                            String uploadingTime,
                            String nodeType,
                            String area,
                            String address,
                            String latitude,
                            String longitude,
    int categoryId) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID , entSubCategoryId);
        rowValue.put(KEY_NODE_NAME  , nodeName);
        rowValue.put(KEY_NODE_NAME_BN,nodeNameBn);
        rowValue.put(KEY_DATE_NAME , dataName);
        rowValue.put(KEY_DATE_DATE , dateDate);
        rowValue.put(KEY_NODE_DESIGNATION ,nodeDesignation );
        rowValue.put(KEY_NODE_CONTACT  , nodeContact);
        rowValue.put(KEY_NODE_EMAIL , nodeEmail);
        rowValue.put(KEY_NODE_ADDITIONAL,nodeAdditional);
        rowValue.put(KEY_NODE_WEBSITE , nodeWebsite);
        rowValue.put(KEY_NODE_FACEBOOK ,nodeFacebook);
        rowValue.put(KEY_NODE_REGISTERED_WITH, nodeRegisteredWith);
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER  , nodeRegistrationNumber);
        rowValue.put(KEY_EDITED_BY , editedBy);
        rowValue.put(KEY_UPLOADING_TIME , uploadingTime);
        rowValue.put(KEY_NODE_TYPE  , nodeType);
        rowValue.put(KEY_AREA  , area);
        rowValue.put(KEY_ADDRESS , address);
        rowValue.put(KEY_LATITUDE,latitude);
        rowValue.put(KEY_LONGITUDE,longitude);
        rowValue.put(KEY_CATEGORY_ID,categoryId);

        SQLiteDatabase db = openDB();




        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ? AND "+KEY_SUB_CATEGORY_ID + " = ? AND "+KEY_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "",entSubCategoryId+"",categoryId+""});
        closeDB();
        return ret;
    }

    public ArrayList<EntertainmentServiceProviderItem> getAllEntertainmentSubCategoriesInfoWithHead(int cat_id,String header) {
        ArrayList<EntertainmentServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        cat_id=1;
        SQLiteDatabase db = openDB();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_SUB_CATEGORY_ID + " in (SELECT _sub_cat_id from "+ DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '"+header+"')", null);

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

    public ArrayList<EntertainmentServiceProviderItem> getAllEntertainmentSubCategoriesInfo(int cat_id,int sub_cat_id) {
        ArrayList<EntertainmentServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_SUB_CATEGORY_ID+"="+sub_cat_id, null);

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


    public ArrayList<EntertainmentServiceProviderItem> getAllEntertainmentSubCategoriesInfo(int cat_id) {
        ArrayList<EntertainmentServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id, null);



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



    private EntertainmentServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _nodeId= cursor.getString(0);
        Integer _entSubCategoryId= cursor.getInt(1);
        String _nodeName = cursor.getString(2);
        String _nodeNameBn= cursor.getString(3);
        String _dataName = cursor.getString(4);
        String _dataDate = cursor.getString(5);
        String _nodeDesignation = cursor.getString(6);
        String _nodeContact = cursor.getString(7);
        String _nodeEmail = cursor.getString(8);
        String _nodeAdditional = cursor.getString(9);
        String _nodeWebsite = cursor.getString(10);
        String _nodeFacebook = cursor.getString(11);
        String _nodeRegisteredWith = cursor.getString(12);
        String _nodeRegistrationNumber = cursor.getString(13);
        String _editedBy= cursor.getString(14);
        String _uploadingTime = cursor.getString(15);
        String _nodeType = cursor.getString(16);
        String _area = cursor.getString(17);
        String _address = cursor.getString(18);
        String _latitude = cursor.getString(19);
        String _longitude= cursor.getString(20);
        int _categoryId= cursor.getInt(21);

        return new EntertainmentServiceProviderItem(
                _nodeId,
                _entSubCategoryId,
                _nodeName,
                _nodeNameBn,
                _dataName,
                _dataDate,
                _nodeDesignation,
                _nodeContact,
                _nodeEmail,
                _nodeAdditional,
                _nodeWebsite,
                _nodeFacebook,
                _nodeRegisteredWith,
                _nodeRegistrationNumber,
                _editedBy,
                _uploadingTime,
                _nodeType,
                _area,
                _address,
                _latitude,
                _longitude,
                _categoryId);
    }



}