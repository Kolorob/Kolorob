package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.Entertainment;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;

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

                + KEY_TIME_STAMP + " TEXT, "
                + KEY_NODE_TYPE + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER, , PRIMARY KEY(" + KEY_CATEGORY_ID + ", " + KEY_REF_NUM + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public long insertItem(HealthServiceProviderItem healthServiceProviderItem) {
        return insertItem(
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

    public long insertItem(String nodeId,
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
        rowValue.put(KEY_TIME_STAMP  , nameBn);
        rowValue.put(KEY_NODE_TYPE  , nameBn);
        rowValue.put(KEY_AREA   , timeStamp );
        rowValue.put(KEY_ADDRESS   , nodeType );
        rowValue.put(KEY_LATITUDE  , area );
        rowValue.put(KEY_LONGITUDE  , address );
        rowValue.put(KEY_CATEGORY_ID , totalTeachers);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    public boolean isFieldExist(String id,int cat_id,int sub_cat_id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&&Integer.parseInt(cursor.getString(3))==cat_id&&Integer.parseInt(cursor.getString(2))==sub_cat_id) {
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

    private long updateItem(String identifierId,
                            String serviceProviderId,
                            int eduSubCategoryId,
                            int categoryId,
                            String eduNameEng,
                            String eduNameBan,
                            String eduType,
                            String hostelFacility,
                            String transportFacility,
                            String playground,
                            String contactPersonDesignation,
                            String contactNo,
                            String emailAddress,
                            String websiteLink,
                            String fbLink,
                            String registeredWith,
                            String registrationNo,
                            String totalStudents,
                            String totalClasses,
                            String totalTeachers,
                            String courseProvided,
                            String shift,
                            String canteenFacility,
                            String additionalInfo,
                            String latitude,
                            String longitude) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID , identifierId);
        rowValue.put(KEY_NODE_NAME , serviceProviderId);
        rowValue.put(KEY_DATE_NAME  , eduSubCategoryId);
        rowValue.put(KEY_DATE_DATE,categoryId);
        rowValue.put(KEY_NODE_DESIGNATION , eduNameEng);
        rowValue.put(KEY_NODE_CONTACT ,eduNameBan );
        rowValue.put(KEY_NODE_EMAIL  , eduType);
        rowValue.put(KEY_NODE_ADDITIONAL , hostelFacility);
        rowValue.put(KEY_NODE_WEBSITE,transportFacility);
        rowValue.put(KEY_NODE_FACEBOOK , playground);
        rowValue.put(KEY_NODE_REGISTERED_WITH ,contactPersonDesignation);
        rowValue.put(KEY_NODE_REGISTRATION_NUMBER ,nodeRegisteredwith );
        rowValue.put(KEY_EDITED_BY , contactNo);
        rowValue.put(KEY_REF_NUM   , emailAddress);
        rowValue.put(KEY_TIME_STAMP  , websiteLink);
        rowValue.put(KEY_NODE_TYPE  , fbLink);
        rowValue.put(KEY_AREA   , registeredWith);
        rowValue.put(KEY_ADDRESS   , registrationNo);
        rowValue.put(KEY_LATITUDE  , totalStudents);
        rowValue.put(KEY_LONGITUDE  , totalClasses);
        rowValue.put(KEY_CATEGORY_ID , totalTeachers);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND "+KEY_EDU_SUBCATEGORY_ID + " = ? AND "+KEY_CATEGORY_ID + " = ? ",
                new String[]{identifierId + "",eduSubCategoryId+"",categoryId+""});
        closeDB();
        return ret;
    }

}