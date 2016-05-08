package demo.kolorob.kolorobdemoversion.database.LegalAid;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class LegalAidServiceProviderTable {
    private static final String TAG = LegalAidServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.LEGAL_AID_SERVICE_PROVIDER_TABLE;
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer
    private static final String KEY_SERVICE_PROVIDER_ID = "_service_provider_id"; // 1 - text
    private static final String KEY_LEGAL_AID_SUBCATEGORY_ID = "_legal_aid_subcategory_id"; // 2 - text*/
    private static final String KEY_CATEGORY_ID = "_category_id";
    private static final String KEY_LEGAL_AID_NAME_ENG = "_legal_aid_name_eng"; //
    private static final String KEY_LEGAL_AID_NAME_BAN = "_legal_aid_name_ban"; //
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
    private static final String KEY_OPENTIME = "_openingtime"; //
    private static final String KEY_BREAKTIME = "_breaktime"; //
    private static final String KEY_CLOSEATIME = "_closingtime"; //
    private static final String KEY_LANDMARK = "_landmark"; //
    private static final String KEY_ROAD = "_road"; //
    private static final String KEY_BLOCK = "_block"; //
    private static final String KEY_BREAKTIME2 = "_breaktime2"; //
    private static final String KEY_ADTIME = "_additionaltime";

    private Context tContext;

    public LegalAidServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_SERVICE_PROVIDER_ID + "  INTEGER, "              // 1 - text
                + KEY_LEGAL_AID_SUBCATEGORY_ID + " INTEGER, "
                + KEY_CATEGORY_ID + " INTEGER, "// 2 - text
                + KEY_LEGAL_AID_NAME_ENG + " TEXT, "
                + KEY_LEGAL_AID_NAME_BAN + " TEXT, "
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
                + KEY_LONGITUDE + " TEXT,"
                + KEY_OPENTIME + " TEXT, "
                + KEY_BREAKTIME + " TEXT, "
                + KEY_CLOSEATIME + " TEXT, "
                + KEY_LANDMARK + " TEXT, "
                + KEY_ROAD+ " TEXT, "
                + KEY_BLOCK + " TEXT, "
                + KEY_BREAKTIME2 + " TEXT, "
                + KEY_ADTIME + " TEXT, PRIMARY KEY(" + KEY_CATEGORY_ID + ", " + KEY_LEGAL_AID_SUBCATEGORY_ID + ", " + KEY_SERVICE_PROVIDER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(LegalAidServiceProviderItem legalAidServiceProviderItem) {
        return insertItem(legalAidServiceProviderItem.getIdentifierId(), legalAidServiceProviderItem.getServiceProviderId(),
                legalAidServiceProviderItem.getLegalaidSubCategoryId(), legalAidServiceProviderItem.getCategoryId(),
                legalAidServiceProviderItem.getLegalaidNameEng(), legalAidServiceProviderItem.getLegalaidNameBan(),
                legalAidServiceProviderItem.getContactPersonDesignation(), legalAidServiceProviderItem.getContactNo(),
                legalAidServiceProviderItem.getEmailAddress(), legalAidServiceProviderItem.getWebsiteLink(), legalAidServiceProviderItem.getFbLink(),
                legalAidServiceProviderItem.getRegisteredWith(), legalAidServiceProviderItem.getRegistrationNo(),
                legalAidServiceProviderItem.getAdditionalInfo(), legalAidServiceProviderItem.getArea(), legalAidServiceProviderItem.getAddress(),
                legalAidServiceProviderItem.getLatitude(), legalAidServiceProviderItem.getLongitude(),
                legalAidServiceProviderItem.getOpeningtime(),
                legalAidServiceProviderItem.getBreaktime(),
                legalAidServiceProviderItem.getClosingtime(),
                legalAidServiceProviderItem.getLandmark(),
                legalAidServiceProviderItem.getRoad(),
                legalAidServiceProviderItem.getBlock(),
                legalAidServiceProviderItem.getBreaktime2(),
                legalAidServiceProviderItem.getAdditionaltime()
                );
    }


    public long insertItem(String identifierId,
                           String serviceProviderId,
                           int legalaidSubCategoryId,
                           int categoryId,
                           String legalaidNameEng,
                           String legalaidNameBan,
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
                           String longitude
            , String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime) {
        if (isFieldExist(identifierId, categoryId, legalaidSubCategoryId)) {
            return updateItem(
                    identifierId,
                    serviceProviderId,
                    legalaidSubCategoryId,
                    categoryId,
                    legalaidNameEng,
                    legalaidNameBan,
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
                    longitude,openingtime,
                    breaktime,
                    closingtime,
                    landmark,
                    road,
                    block,
                    breaktime2,
                    additionaltime);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_SERVICE_PROVIDER_ID, serviceProviderId);
        rowValue.put(KEY_LEGAL_AID_SUBCATEGORY_ID, legalaidSubCategoryId);
        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_LEGAL_AID_NAME_ENG, legalaidNameEng);
        rowValue.put(KEY_LEGAL_AID_NAME_BAN, legalaidNameBan);
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
    public LegalAidServiceProviderItem getlegNode2(String Node) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + "=" + Node, null);
LegalAidServiceProviderItem legalAidServiceProviderItem=null;
        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                legalAidServiceProviderItem=new LegalAidServiceProviderItem(cursor.getString(0),cursor.getString(1),
                        cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),
                        cursor.getString(8),cursor.getString(9),
                        cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),
                        cursor.getString(16), cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),cursor.getString(21),cursor.getString(22),
                        cursor.getString(23),cursor.getString(24),cursor.getString(25));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return legalAidServiceProviderItem;
    }
    public ArrayList<LegalAidServiceProviderItem> Legnames(int cat_id,String head,String a,String place) {
        String subcatnames=null;
        subcatnames=a;

        ArrayList<LegalAidServiceProviderItem> nameslist=new ArrayList<>();

        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                + " AND "+KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_LEGAL_AID_SUBCATEGORY_ID + "=" + "(SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_name = '"+subcatnames+"')", null);


        if (cursor.moveToFirst()) {
            do {


                nameslist.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return  nameslist;
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
            int legalaidSubCategoryId,
            int categoryId,
            String legalaidNameEng,
            String legalaidNameBan,
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
            String longitude, String openingtime, String breaktime, String closingtime, String landmark, String road, String block, String breaktime2, String additionaltime) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_SERVICE_PROVIDER_ID, serviceProviderId);
        rowValue.put(KEY_LEGAL_AID_SUBCATEGORY_ID, legalaidSubCategoryId);
        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_LEGAL_AID_NAME_ENG, legalaidNameEng);
        rowValue.put(KEY_LEGAL_AID_NAME_BAN, legalaidNameBan);
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
        rowValue.put(KEY_OPENTIME , openingtime);
        rowValue.put(KEY_BREAKTIME  , breaktime);
        rowValue.put(KEY_CLOSEATIME  , closingtime);
        rowValue.put(KEY_LANDMARK  , landmark);
        rowValue.put(KEY_ROAD  , road );
        rowValue.put(KEY_BLOCK   , block );
        rowValue.put(KEY_BREAKTIME2  , breaktime2 );
        rowValue.put(KEY_ADTIME  , additionaltime );
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ? AND " + KEY_LEGAL_AID_SUBCATEGORY_ID + " = ? AND " + KEY_CATEGORY_ID + " = ? ",
                new String[]{identifierId + "", legalaidSubCategoryId + "", categoryId + ""});
        closeDB();
        return ret;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<LegalAidServiceProviderItem> getAllLegalAidSubCategoriesInfo(int cat_id, int sub_cat_id) {
        ArrayList<LegalAidServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id + " AND " + KEY_LEGAL_AID_SUBCATEGORY_ID + "=" + sub_cat_id, null, null);

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
    public ArrayList<LegalAidServiceProviderItem> getAllLegalAidSubCategoriesInfo(int cat_id) {
        ArrayList<LegalAidServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id +" ORDER BY " +KEY_LEGAL_AID_NAME_ENG, null);

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


    public Vector<String> getAllLegalAidSubCategoriesInfo() {
        Vector<String> subCatList = new Vector<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        int cat_id=5;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                String  subCatLists = cursor.getString(cursor.getColumnIndex(KEY_LEGAL_AID_NAME_ENG));

                subCatList.add(subCatLists);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }


    public ArrayList<LegalAidServiceProviderItem> getAllLegalAidSubCategoriesInfoWithHead(int cat_id, String header) {
        ArrayList<LegalAidServiceProviderItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
                + " AND " + KEY_LEGAL_AID_SUBCATEGORY_ID + " in (SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '" + header + "')", null);

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

    private LegalAidServiceProviderItem cursorToSubCatList(Cursor cursor) {
        String _identifierId = cursor.getString(0);
        String _serviceProviderId = cursor.getString(1);
        int _legalaidSubCategoryId = cursor.getInt(2);
        int _categoryId = cursor.getInt(3);
        String _legalaidNameEng = cursor.getString(4);
        String _legalaidBan = cursor.getString(5);
        String _contactPersonDesignation = cursor.getString(6);
        String _contactNo = cursor.getString(7);
        String _emailAddress = cursor.getString(8);
        String _websiteLink = cursor.getString(9);
        String _fbLink = cursor.getString(10);
        String _registeredWith = cursor.getString(11);
        String _registrationNo = cursor.getString(12);
        String _additionalInfo = cursor.getString(13);
        String _area = cursor.getString(14);
        String _address = cursor.getString(15);
        String _latitude = cursor.getString(16);
        String _longitude = cursor.getString(17);
        String _openingtime=cursor.getString(18);
        String _breaktime=cursor.getString(19);
        String _closingtime=cursor.getString(20);
        String _landmark=cursor.getString(21);
        String _road=cursor.getString(22);
        String _block=cursor.getString(23);
        String _breaktime2=cursor.getString(24);
        String _additionaltime=cursor.getString(25);

        return new LegalAidServiceProviderItem(_identifierId, _serviceProviderId, _legalaidSubCategoryId,
                _categoryId, _legalaidNameEng, _legalaidBan, _contactPersonDesignation, _contactNo, _emailAddress, _websiteLink,
                _fbLink, _registeredWith, _registrationNo, _additionalInfo, _area, _address, _latitude, _longitude,_openingtime,
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
