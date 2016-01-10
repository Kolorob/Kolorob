package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.Context;
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
    private static final String KEY_DATE_NAME  = "_dateName"; // 2 - text*/
    private static final String KEY_DATE_DATE = "_dateDate";
    private static final String KEY_NODE_DESIGNATION = "_nodeDesignation"; //
    private static final String KEY_NODE_CONTACT = "_nodeContact"; //
    private static final String KEY_NODE_EMAIL  = "_nodeEmail"; //
    private static final String KEY_NODE_ADDITIONAL = "_nodeAdditional"; //
    private static final String KEY_NODE_WEBSITE= "_nodeWebsite"; //
    private static final String KEY_NODE_FACEBOOK = "_nodeFacebook"; //
    private static final String KEY_NODE_REGISTERED_WITH = "_nodeRegisteredwith"; //
    private static final String KEY_NODE_REGISTRATION_NUMBER= "_nodeRegistationNumber"; //
    private static final String KEY_EDITED_BY  = "_editedBy"; //
    private static final String KEY_REF_NUM = "_refNum"; //

    private static final String KEY_TIME_STAMP  = "_timeStamp"; //
    private static final String KEY_NODE_TYPE  = "_nodeType"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_ADDRESS = "_address"; //
    private static final String KEY_LONGITUDE = "_longitude"; //
    private static final String KEY_LATITUDE  = "_latitude"; //
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
                + KEY_REF_NUM+ " INTEGER, "

                + KEY_TIME_STAMP + " TEXT, "
                + KEY_NODE_TYPE  + " TEXT, "
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


    public long insertItem(HealthServiceProviderItem healthServiceProviderItem){
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
        if (isFieldExist(identifierId,categoryId,eduSubCategoryId)) {
            return updateItem(
                    identifierId,
                    serviceProviderId,
                    eduSubCategoryId,
                    categoryId,
                    eduNameEng,
                    eduNameBan,
                    eduType,
                    hostelFacility,
                    transportFacility,
                    playground,
                    contactPersonDesignation,
                    contactNo,
                    emailAddress,
                    websiteLink,
                    fbLink,
                    registeredWith,
                    registrationNo,
                    totalStudents,
                    totalClasses,
                    totalTeachers,
                    courseProvided,
                    shift,
                    canteenFacility,
                    additionalInfo,
                    latitude,
                    longitude);
        }



}
