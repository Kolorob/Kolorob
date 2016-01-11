package demo.kolorob.kolorobdemoversion.database.Job;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 1/10/2016.
 */
public class JobServiceProviderTable {

    private static final String TAG = JobServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.JOB_SERVICE_PROVIDER_TABLE;
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer
    private static final String KEY_SERVICE_PROVIDER_ID = "_service_provider_id"; // 1 - text
    private static final String KEY_JOB_SUBCATEGORY_ID = "_job_subcategory_id"; // 2 - text*/
    private static final String KEY_CATEGORY_ID = "_category_id";

    private static final String KEY_CONTACT_PERSON_DESIGNATION = "_contact_person_designation"; //
    private static final String KEY_CONTACT_NO = "_contact_no"; //
    private static final String KEY_EMAIL_ADDRESS = "_email_address"; //
    private static final String KEY_WEBSITE_LINK = "_website_link"; //
    private static final String KEY_FB_LINK = "_fb_link"; //
    private static final String KEY_REGISTERED_WITH = "_registered_with"; //
    private static final String KEY_REGISTRATION_NO = "_registration_no"; //
    private static final String KEY_ADDITIONAL_INFO = "_additional_info"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_ADDRESS = "_address"; //
    private static final String KEY_LATITUDE = "_latitude"; //
    private static final String KEY_LONGITUDE = "_longitude"; //

    private Context tContext;

    public JobServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_SERVICE_PROVIDER_ID + "  INTEGER, "              // 1 - text
                + KEY_JOB_SUBCATEGORY_ID + " INTEGER, "
                + KEY_CATEGORY_ID + " INTEGER, "// 2 - text
                + KEY_CONTACT_PERSON_DESIGNATION + " TEXT, "
                + KEY_CONTACT_NO + " TEXT, "
                + KEY_EMAIL_ADDRESS + " TEXT, "
                + KEY_WEBSITE_LINK + " TEXT, "
                + KEY_FB_LINK + " TEXT, "
                + KEY_REGISTERED_WITH + " TEXT, "
                + KEY_REGISTRATION_NO + " TEXT, "
                + KEY_ADDITIONAL_INFO + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_ADDRESS + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, PRIMARY KEY( " + KEY_JOB_SUBCATEGORY_ID + ", " + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(JobServiceProviderItem jobServiceProviderItem) {
        return insertItem(jobServiceProviderItem.getIdentifierId(), jobServiceProviderItem.getServiceProviderId(),
                jobServiceProviderItem.getJobSubCategoryId(), jobServiceProviderItem.getCategoryId(),

                jobServiceProviderItem.getContactPersonDesignation(), jobServiceProviderItem.getContactNo(),
                jobServiceProviderItem.getEmailAddress(),jobServiceProviderItem.getWebsiteLink(), jobServiceProviderItem.getFbLink(),
                jobServiceProviderItem.getRegisteredWith(),jobServiceProviderItem.getRegistrationNo(),
                jobServiceProviderItem.getAdditionalInfo(),jobServiceProviderItem.getArea(), jobServiceProviderItem.getAddress(),
                jobServiceProviderItem.getLatitude(),jobServiceProviderItem.getLongitude());
    }


    public long insertItem(String identifierId,
                           String serviceProviderId,
                           int jobSubCategoryId,
                           int categoryId,
                           String contactPersonDesignation,
                           String contactNo,
                           String emailAddress,
                           String websiteLink,
                           String fbLink,
                           String registeredWith,
                           String registrationNo,
                           String additionalInfo,
                           String area,
                           String address,
                           String latitude,
                           String longitude) {
        if (isFieldExist(identifierId, categoryId, jobSubCategoryId)) {
            return updateItem(
                    identifierId,
                    serviceProviderId,
                    jobSubCategoryId,
                    categoryId,
                    contactPersonDesignation,
                    contactNo,
                    emailAddress,
                    websiteLink,
                    fbLink,
                    registeredWith,
                    registrationNo,
                    additionalInfo,
                    area,
                    address,
                    latitude,
                    longitude);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_SERVICE_PROVIDER_ID, serviceProviderId);
        rowValue.put(KEY_JOB_SUBCATEGORY_ID, jobSubCategoryId);
        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_CONTACT_PERSON_DESIGNATION, contactPersonDesignation);
        rowValue.put(KEY_CONTACT_NO, contactNo);
        rowValue.put(KEY_EMAIL_ADDRESS, emailAddress);
        rowValue.put(KEY_WEBSITE_LINK, websiteLink);
        rowValue.put(KEY_FB_LINK, fbLink);
        rowValue.put(KEY_REGISTERED_WITH, registeredWith);
        rowValue.put(KEY_REGISTRATION_NO, registrationNo);
        rowValue.put(KEY_ADDITIONAL_INFO, additionalInfo);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_ADDRESS, address);
        rowValue.put(KEY_LATITUDE, latitude);
        rowValue.put(KEY_LONGITUDE, longitude);

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
                if (id.equals(cursor.getString(0)) && Integer.parseInt(cursor.getString(3)) == cat_id && Integer.parseInt(cursor.getString(2)) == sub_cat_id) {
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

    private long updateItem(
            String identifierId,
            String serviceProviderId,
            int jobSubCategoryId,
            int categoryId,
            String contactPersonDesignation,
            String contactNo,
            String emailAddress,
            String websiteLink,
            String fbLink,
            String registeredWith,
            String registrationNo,
            String additionalInfo,
            String area,
            String address,
            String latitude,
            String longitude) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_SERVICE_PROVIDER_ID, serviceProviderId);
        rowValue.put(KEY_JOB_SUBCATEGORY_ID, jobSubCategoryId);
        rowValue.put(KEY_CATEGORY_ID, categoryId);

        rowValue.put(KEY_CONTACT_PERSON_DESIGNATION, contactPersonDesignation);
        rowValue.put(KEY_CONTACT_NO, contactNo);
        rowValue.put(KEY_EMAIL_ADDRESS, emailAddress);
        rowValue.put(KEY_WEBSITE_LINK, websiteLink);
        rowValue.put(KEY_FB_LINK, fbLink);
        rowValue.put(KEY_REGISTERED_WITH, registeredWith);
        rowValue.put(KEY_REGISTRATION_NO, registrationNo);
        rowValue.put(KEY_ADDITIONAL_INFO, additionalInfo);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_ADDRESS, address);
        rowValue.put(KEY_LATITUDE, latitude);
        rowValue.put(KEY_LONGITUDE, longitude);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND " + KEY_JOB_SUBCATEGORY_ID + " = ? AND " + KEY_CATEGORY_ID + " = ? ",
                new String[]{identifierId + "", jobSubCategoryId + "", categoryId + ""});
        closeDB();
        return ret;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<JobServiceProviderItem> getAllJobSubCategoriesInfo(int cat_id, int sub_cat_id) {
        ArrayList<JobServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id + " AND " + KEY_JOB_SUBCATEGORY_ID + "=" + sub_cat_id, null, null);

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
    public ArrayList<JobServiceProviderItem> getAllJobSubCategoriesInfo(int cat_id) {
        ArrayList<JobServiceProviderItem> subCatList = new ArrayList<>();
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
    public ArrayList<JobServiceProviderItem> getAllJobSubCategoriesInfoWithHead(int cat_id, String header) {
        ArrayList<JobServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                + " AND " + KEY_JOB_SUBCATEGORY_ID + " in (SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '" + header + "')", null);

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

    private JobServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _identifierId = cursor.getString(0);
        String _serviceProviderId = cursor.getString(1);
        int _jobSubCategoryId = cursor.getInt(2);
        int _categoryId = cursor.getInt(3);
        String _contactPersonDesignation = cursor.getString(4);
        String _contactNo = cursor.getString(5);
        String _emailAddress = cursor.getString(6);
        String _websiteLink = cursor.getString(7);
        String _fbLink = cursor.getString(8);
        String _registeredWith = cursor.getString(9);
        String _registrationNo = cursor.getString(10);
        String _additionalInfo = cursor.getString(11);
        String _area = cursor.getString(12);
        String _address = cursor.getString(13);
        String _latitude = cursor.getString(14);
        String _longitude = cursor.getString(15);

        return new JobServiceProviderItem(_identifierId, _serviceProviderId, _jobSubCategoryId,
                _categoryId,  _contactPersonDesignation, _contactNo, _emailAddress, _websiteLink,
                _fbLink, _registeredWith, _registrationNo, _additionalInfo, _area, _address, _latitude, _longitude);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
