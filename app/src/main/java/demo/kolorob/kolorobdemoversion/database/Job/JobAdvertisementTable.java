package demo.kolorob.kolorobdemoversion.database.Job;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobAdvertisementItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by mazharul.islam on 1/10/2016.
 */
public class JobAdvertisementTable {

    private static final String TAG = JobServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.JOB_SERVICE_ADVERTISEMENT_TABLE;
    private static final String KEY_ID = "_id";
    private static final String KEY_FORM_DATE = "form_date"; //
    private static final String KEY_POSITION = "position"; //
    private static final String KEY_INSTITUTION_NAME = "institute_name"; //
    private static final String KEY_INSTITUTION_NAME_BANGLA = "institute_name_bangla"; //
    private static final String KEY_POST_DATE = "post_date"; //
    private static final String KEY_APPLICATION_LAST_DATE = "application_last_date"; //
    private static final String KEY_LOCATION = "location"; //
    private static final String KEY_POST_TYPE = "post_type"; //
    private static final String KEY_JOB_RESPONSIBILITY = "job_responsibility"; //
    private static final String KEY_REQUIRED_EXPERIENCE = "required_experience"; //
    private static final String KEY_START_SALARY = "start_salary"; //
    private static final String KEY_END_SALARY = "end_salary"; //
    private static final String KEY_OTHER_BENEFITS = "other_benefits"; // 2 - text*/
    private static final String KEY_REFERENCE_PERSON = "reference_person";
    private static final String KEY_APPLICATION_MEDIUM = "application_medium"; //
    private static final String KEY_MAP_TYPE = "map_type"; //
    private static final String KEY_LATITUDE = "latitude"; //
    private static final String KEY_LONGITUDE = "longitude"; //
    private static final String KEY_ADDRESS_FLOOR = "address_floor"; //
    private static final String KEY_ADDRESS_HOUSE_NAME = "address_house_name"; //
    private static final String KEY_ADDRESS_HOUSE_NUMBER = "address_house_num"; //
    private static final String KEY_ADDRESS_ROAD = "address_road"; //
    private static final String KEY_ADDRESS_LINE = "address_line"; //
    private static final String KEY_ADDRESS_AVENUE = "address_avenue"; //
    private static final String KEY_ADDRESS_BLOCK = "address_block"; //
    private static final String KEY_ADDRESS_AREA = "address_area"; //
    private static final String KEY_ADDRESS_POST = "address_post"; // 2 - text*/
    private static final String KEY_ADDRESS_POLICE = "address_police";
    private static final String KEY_ADDRESS_CITY = "address_city"; //
    private static final String KEY_ADDRESS_COUNTRY = "address_country"; //
    private static final String KEY_MOBILE1 = "mobile1"; //
    private static final String KEY_MOBILE2 = "mobile2"; //
    private static final String KEY_EMAIL = "email"; //
    private static final String KEY_WEBSITE = "website"; //
    private static final String KEY_FACEBOOK = "facebook"; //
    private static final String KEY_CONTACT_DESIGNATION = "contact_designation"; //
    private static final String KEY_OPENING = "opening"; //
    private static final String KEY_BREAK = "breaks"; //
    private static final String KEY_CLOSING = "closing"; //
    private static final String KEY_OFF_DAY = "off_day"; //
    private static final String KEY_REMARKS = "remarks"; //
    private static final String KEY_REF1 = "ref1"; //
    private static final String KEY_REF2 = "ref2"; //
    private static final String KEY_REF3 = "ref3"; //
    private static final String KEY_REGISTERED_WITH = "reg_with"; //
    private static final String KEY_REGISTRATION_NO = "reg_num"; //
    private static final String KEY_JOB_TYPE = "job_type"; //
    private static final String KEY_COLLECTOR_NAME = "collector_name"; //
    private static final String KEY_USER_NAME = "username"; //
    private static final String KEY_STATUS = "status"; //
    private static final String KEY_SUBCATEGORY = "Subcategory"; //
    private static final String KEY_TYPESUBCATEGORY = "TypeSubcategory"; //

    private Context tContext;

    public JobAdvertisementTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + "  INTEGER , " // 0 - int
                + KEY_FORM_DATE + "  TEXT, "              // 1 - text
                + KEY_POSITION + " TEXT, "
                + KEY_INSTITUTION_NAME + " TEXT, "// 2 - text
                + KEY_INSTITUTION_NAME_BANGLA + " TEXT, "// 2 - text
                + KEY_POST_DATE + " TEXT, "
                + KEY_APPLICATION_LAST_DATE + " TEXT, "
                + KEY_LOCATION + " TEXT, "
                + KEY_POST_TYPE + " TEXT, "
                + KEY_JOB_RESPONSIBILITY + " TEXT, "
                + KEY_REQUIRED_EXPERIENCE + " TEXT, "
                + KEY_START_SALARY + " TEXT, "
                + KEY_END_SALARY + " TEXT, "
                + KEY_OTHER_BENEFITS + " TEXT, "
                + KEY_REFERENCE_PERSON + " TEXT, "
                + KEY_APPLICATION_MEDIUM + " TEXT, "
                + KEY_MAP_TYPE + "  TEXT, "              // 1 - text
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "// 2 - text
                + KEY_ADDRESS_FLOOR + " TEXT, "
                + KEY_ADDRESS_HOUSE_NAME + " TEXT, "
                + KEY_ADDRESS_HOUSE_NUMBER + " TEXT, "
                + KEY_ADDRESS_ROAD + " TEXT, "
                + KEY_ADDRESS_LINE + " TEXT, "
                + KEY_ADDRESS_AVENUE + " TEXT, "
                + KEY_ADDRESS_BLOCK + " TEXT, "
                + KEY_ADDRESS_AREA + " TEXT, "
                + KEY_ADDRESS_POST + " TEXT, "
                + KEY_ADDRESS_POLICE + " TEXT, "
                + KEY_ADDRESS_CITY + " TEXT, "
                + KEY_ADDRESS_COUNTRY + "  TEXT, "              // 1 - text
                + KEY_MOBILE1 + " TEXT, "
                + KEY_MOBILE2 + " TEXT, "// 2 - text
                + KEY_EMAIL + " TEXT, "
                + KEY_WEBSITE + " TEXT, "
                + KEY_FACEBOOK + " TEXT, "
                + KEY_CONTACT_DESIGNATION + " TEXT, "
                + KEY_OPENING+ " TEXT, "
                + KEY_BREAK+ " TEXT, "
                + KEY_CLOSING + " TEXT, "
                + KEY_OFF_DAY + " TEXT, "
                + KEY_REMARKS + " TEXT, "
                + KEY_REF1 + " TEXT, "
                + KEY_REF2 + " TEXT, "
                + KEY_REF3 + " TEXT, "
                + KEY_REGISTERED_WITH + "  TEXT, "              // 1 - text
                + KEY_REGISTRATION_NO + " TEXT, "
                + KEY_JOB_TYPE + " TEXT, "// 2 - text
                + KEY_COLLECTOR_NAME + " TEXT, "
                + KEY_USER_NAME + " TEXT, "
                + KEY_STATUS + " TEXT, "
                + KEY_SUBCATEGORY + " TEXT, "

                + KEY_TYPESUBCATEGORY + " TEXT, PRIMARY KEY( " + KEY_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(JobAdvertisementItem jobAdvertisementItem) {

        Log.d(">>>","Insert Itemxxx  "+jobAdvertisementItem.getId());
        return insertItem(


                jobAdvertisementItem.getId(),
                jobAdvertisementItem.getForm_date(),
                jobAdvertisementItem.getPosition(),
                jobAdvertisementItem.getInstitute_name(),
                jobAdvertisementItem.getInstitute_name_bangla(),
                jobAdvertisementItem.getPost_date(),
                jobAdvertisementItem.getApplication_last_date(),
                jobAdvertisementItem.getLocation(),
                jobAdvertisementItem.getPost_type(),
                jobAdvertisementItem.getJob_responsibility(),
                jobAdvertisementItem.getRequired_experience(),
                jobAdvertisementItem.getStart_salary(),
                jobAdvertisementItem.getEnd_salary(),
                jobAdvertisementItem.getOther_benefits(),
                jobAdvertisementItem.getReference_person(),
                jobAdvertisementItem.getApplication_medium(),
                jobAdvertisementItem.getMap_type(),
                jobAdvertisementItem.getLatitude(),
                jobAdvertisementItem.getLongitude(),
                jobAdvertisementItem.getAddress_floor(),
                jobAdvertisementItem.getAddress_house_name(),
                jobAdvertisementItem.getAddress_house_num(),
                jobAdvertisementItem.getAddress_road(),
                jobAdvertisementItem.getAddress_line(),
                jobAdvertisementItem.getAddress_avenue(),
                jobAdvertisementItem.getAddress_block(),
                jobAdvertisementItem.getAddress_area(),
                jobAdvertisementItem.getAddress_post(),
                jobAdvertisementItem.getAddress_police(),
                jobAdvertisementItem.getAddress_city(),
                jobAdvertisementItem.getAddress_country(),
                jobAdvertisementItem.getMobile1(),
                jobAdvertisementItem.getMobile2(),
                jobAdvertisementItem.getEmail(),
                jobAdvertisementItem.getWebsite(),
                jobAdvertisementItem.getFacebook(),
                jobAdvertisementItem.getContact_designation(),
                jobAdvertisementItem.getOpening(),
                jobAdvertisementItem.getBreaks(),
                jobAdvertisementItem.getClosing(),
                jobAdvertisementItem.getOff_day(),
                jobAdvertisementItem.getRemarks(),
                jobAdvertisementItem.getRef1(),
                jobAdvertisementItem.getRef2(),
                jobAdvertisementItem.getRef3(),
                jobAdvertisementItem.getReg_with(),
                jobAdvertisementItem.getReg_num(),
                jobAdvertisementItem.getJob_type(),
                jobAdvertisementItem.getCollector_name(),
                jobAdvertisementItem.getUser(),
                jobAdvertisementItem.getStatus(),
                jobAdvertisementItem.getSubcategory(),
                jobAdvertisementItem.getTypeSubcategory()
        );
    }

    private long insertItem(int id,
                            String form_date,
                            String position,
                            String institute_name,
                            String institute_name_bangla,
                            String post_date,
                            String application_last_date,
                            String location,
                            String post_type,
                            String job_responsibility,
                            String required_experience,
                            String start_salary,
                            String end_salary,
                            String other_benefits,
                            String reference_person,
                            String application_medium,
                            String map_type,
                            String latitude,
                            String longitude,
                            String address_floor,
                            String address_house_name,
                            String address_house_num,
                            String address_road,
                            String address_line,
                            String address_avenue,
                            String address_block,
                            String address_area,
                            String address_post,
                            String address_police,
                            String address_city,
                            String address_country,
                            String mobile1,
                            String mobile2,
                            String email,
                            String website,
                            String facebook,
                            String contact_designation,
                            String opening,
                            String breaks,
                            String closing,
                            String off_day,
                            String remarks,
                            String ref1,
                            String ref2,
                            String ref3,
                            String reg_with,
                            String reg_num,
                            String job_type,
                            String collector_name,
                            String user,
                            String status,
                            String subcategory,
                            String typeSubcategory) {

        if (isFieldExist(id)) {
            return updateItem(
            id,
             form_date,
             position,
            institute_name,
            institute_name_bangla,
             post_date,
            application_last_date,
            location,
            post_type,
             job_responsibility,
             required_experience,
            start_salary,
             end_salary,
             other_benefits,
             reference_person,
             application_medium,
            map_type,
            latitude,
            longitude,
            address_floor,
             address_house_name,
             address_house_num,
             address_road,
            address_line,
            address_avenue,
             address_block,
             address_area,
             address_post,
             address_police,
             address_city,
             address_country,
             mobile1,
             mobile2,
             email,
             website,
             facebook,
             contact_designation,
             opening,
             breaks,
             closing,
             off_day,
             remarks,
             ref1,
             ref2,
             ref3,
             reg_with,
             reg_num,
             job_type,
             collector_name,
             user,
             status,
             subcategory,
             typeSubcategory);
        }


        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_FORM_DATE, form_date);
        rowValue.put(KEY_POSITION, position);
        rowValue.put(KEY_INSTITUTION_NAME, institute_name);
        rowValue.put(KEY_INSTITUTION_NAME_BANGLA, institute_name_bangla);
        rowValue.put(KEY_POST_DATE, post_date);
        rowValue.put(KEY_APPLICATION_LAST_DATE, application_last_date);
        rowValue.put(KEY_LOCATION, location);
        rowValue.put(KEY_POST_TYPE, post_type);
        rowValue.put(KEY_JOB_RESPONSIBILITY, job_responsibility);
        rowValue.put(KEY_REQUIRED_EXPERIENCE, required_experience);
        rowValue.put(KEY_START_SALARY, start_salary);
        rowValue.put(KEY_END_SALARY, end_salary);
        rowValue.put(KEY_OTHER_BENEFITS, other_benefits);
        rowValue.put(KEY_REFERENCE_PERSON, reference_person);
        rowValue.put(KEY_APPLICATION_MEDIUM, application_medium);
        rowValue.put(KEY_MAP_TYPE, map_type);
        rowValue.put(KEY_LATITUDE, latitude);
        rowValue.put(KEY_LONGITUDE, longitude);
        rowValue.put(KEY_ADDRESS_FLOOR, address_floor);
        rowValue.put(KEY_ADDRESS_HOUSE_NAME, address_house_name);
        rowValue.put(KEY_ADDRESS_HOUSE_NUMBER, address_house_num);
        rowValue.put(KEY_ADDRESS_ROAD, address_road);
        rowValue.put(KEY_ADDRESS_LINE, address_line);
        rowValue.put(KEY_ADDRESS_AVENUE, address_avenue);
        rowValue.put(KEY_ADDRESS_BLOCK, address_block);
        rowValue.put(KEY_ADDRESS_AREA, address_area);
        rowValue.put(KEY_ADDRESS_POST, address_post);
        rowValue.put(KEY_ADDRESS_POLICE, address_police);
        rowValue.put(KEY_ADDRESS_CITY, address_city);
        rowValue.put(KEY_ADDRESS_COUNTRY, address_country);
        rowValue.put(KEY_MOBILE1, mobile1);
        rowValue.put(KEY_MOBILE2, mobile2);
        rowValue.put(KEY_EMAIL, email);
        rowValue.put(KEY_WEBSITE, website);
        rowValue.put(KEY_FACEBOOK, facebook);
        rowValue.put(KEY_CONTACT_DESIGNATION, contact_designation);
        rowValue.put(KEY_OPENING, opening);
        rowValue.put(KEY_BREAK, breaks);
        rowValue.put(KEY_CLOSING, closing);
        rowValue.put(KEY_OFF_DAY, off_day);
        rowValue.put(KEY_REMARKS,remarks);
        rowValue.put(KEY_REF1, ref1);
        rowValue.put(KEY_REF2, ref2);
        rowValue.put(KEY_REF3, ref3);
        rowValue.put(KEY_REGISTERED_WITH, reg_with);
        rowValue.put(KEY_REGISTRATION_NO, reg_num);
        rowValue.put(KEY_JOB_TYPE, job_type);
        rowValue.put(KEY_COLLECTOR_NAME, collector_name);
        rowValue.put(KEY_USER_NAME, user);
        rowValue.put(KEY_STATUS, status);
        rowValue.put(KEY_SUBCATEGORY, subcategory);
        rowValue.put(KEY_TYPESUBCATEGORY, typeSubcategory);
        
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        Log.d("insert_check_job ","&&&&&&&&"+ret);
        return ret;




    }

    private long updateItem(int id,
                            String form_date,
                            String position,
                            String institute_name,
                            String institute_name_bangla,
                            String post_date,
                            String application_last_date,
                            String location,
                            String post_type,
                            String job_responsibility,
                            String required_experience,
                            String start_salary,
                            String end_salary,
                            String other_benefits,
                            String reference_person,
                            String application_medium,
                            String map_type,
                            String latitude,
                            String longitude,
                            String address_floor,
                            String address_house_name,
                            String address_house_num,
                            String address_road,
                            String address_line,
                            String address_avenue,
                            String address_block,
                            String address_area,
                            String address_post,
                            String address_police,
                            String address_city,
                            String address_country,
                            String mobile1,
                            String mobile2,
                            String email,
                            String website,
                            String facebook,
                            String contact_designation,
                            String opening,
                            String breaks,
                            String closing,
                            String off_day,
                            String remarks,
                            String ref1,
                            String ref2,
                            String ref3,
                            String reg_with,
                            String reg_num,
                            String job_type,
                            String collector_name,
                            String user,
                            String status,
                            String subcategory,
                            String typeSubcategory) {


        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_FORM_DATE, form_date);
        rowValue.put(KEY_POSITION, position);
        rowValue.put(KEY_INSTITUTION_NAME, institute_name);
        rowValue.put(KEY_INSTITUTION_NAME_BANGLA, institute_name_bangla);
        rowValue.put(KEY_POST_DATE, post_date);
        rowValue.put(KEY_APPLICATION_LAST_DATE, application_last_date);
        rowValue.put(KEY_LOCATION, location);
        rowValue.put(KEY_POST_TYPE, post_type);
        rowValue.put(KEY_JOB_RESPONSIBILITY, job_responsibility);
        rowValue.put(KEY_REQUIRED_EXPERIENCE, required_experience);
        rowValue.put(KEY_START_SALARY, start_salary);
        rowValue.put(KEY_END_SALARY, end_salary);
        rowValue.put(KEY_OTHER_BENEFITS, other_benefits);
        rowValue.put(KEY_REFERENCE_PERSON, reference_person);
        rowValue.put(KEY_APPLICATION_MEDIUM, application_medium);
        rowValue.put(KEY_MAP_TYPE, map_type);
        rowValue.put(KEY_LATITUDE, latitude);
        rowValue.put(KEY_LONGITUDE, longitude);
        rowValue.put(KEY_ADDRESS_FLOOR, address_floor);
        rowValue.put(KEY_ADDRESS_HOUSE_NAME, address_house_name);
        rowValue.put(KEY_ADDRESS_HOUSE_NUMBER, address_house_num);
        rowValue.put(KEY_ADDRESS_ROAD, address_road);
        rowValue.put(KEY_ADDRESS_LINE, address_line);
        rowValue.put(KEY_ADDRESS_AVENUE, address_avenue);
        rowValue.put(KEY_ADDRESS_BLOCK, address_block);
        rowValue.put(KEY_ADDRESS_AREA, address_area);
        rowValue.put(KEY_ADDRESS_POST, address_post);
        rowValue.put(KEY_ADDRESS_POLICE, address_police);
        rowValue.put(KEY_ADDRESS_CITY, address_city);
        rowValue.put(KEY_ADDRESS_COUNTRY, address_country);
        rowValue.put(KEY_MOBILE1, mobile1);
        rowValue.put(KEY_MOBILE2, mobile2);
        rowValue.put(KEY_EMAIL, email);
        rowValue.put(KEY_WEBSITE, website);
        rowValue.put(KEY_FACEBOOK, facebook);
        rowValue.put(KEY_CONTACT_DESIGNATION, contact_designation);
        rowValue.put(KEY_OPENING, opening);
        rowValue.put(KEY_BREAK, breaks);
        rowValue.put(KEY_CLOSING, closing);
        rowValue.put(KEY_OFF_DAY, off_day);
        rowValue.put(KEY_REMARKS,remarks);
        rowValue.put(KEY_REF1, ref1);
        rowValue.put(KEY_REF2, ref2);
        rowValue.put(KEY_REF3, ref3);
        rowValue.put(KEY_REGISTERED_WITH, reg_with);
        rowValue.put(KEY_REGISTRATION_NO, reg_num);
        rowValue.put(KEY_JOB_TYPE, job_type);
        rowValue.put(KEY_COLLECTOR_NAME, collector_name);
        rowValue.put(KEY_USER_NAME, user);
        rowValue.put(KEY_STATUS, status);
        rowValue.put(KEY_SUBCATEGORY, subcategory);
        rowValue.put(KEY_TYPESUBCATEGORY, typeSubcategory);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;

    }




    public boolean isFieldExist(int id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0))==id) {
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


    public ArrayList<JobAdvertisementItem> jobAdvertisementItems() {
        ArrayList<JobAdvertisementItem> subCatList = new ArrayList<>();
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

    public ArrayList<JobAdvertisementItem> getSpecificJobElement(int id) {
        ArrayList<JobAdvertisementItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_ID+"="+id, null);

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



    private JobAdvertisementItem cursorToSubCatList(Cursor cursor) {
        int id= cursor.getInt(0);
        String form_date=cursor.getString(1);
        String position=cursor.getString(2);
        String institute_name=cursor.getString(3);
        String institute_name_bangla=cursor.getString(4);
        String post_date=cursor.getString(5);
        String application_last_date=cursor.getString(6);
        String location=cursor.getString(7);
        String post_type=cursor.getString(8);
        String job_responsibility=cursor.getString(9);
        String required_experience=cursor.getString(10);
        String start_salary=cursor.getString(11);
        String end_salary=cursor.getString(12);
        String other_benefits=cursor.getString(13);
        String reference_person=cursor.getString(14);
        String application_medium=cursor.getString(15);
        String map_type=cursor.getString(16);
        String latitude=cursor.getString(17);
        String longitude=cursor.getString(18);
        String address_floor=cursor.getString(19);
        String address_house_name=cursor.getString(20);
        String address_house_num=cursor.getString(21);
        String address_road=cursor.getString(22);
        String address_line=cursor.getString(23);
        String address_avenue=cursor.getString(24);
        String address_block=cursor.getString(25);
        String address_area=cursor.getString(26);
        String address_post=cursor.getString(27);
        String address_police=cursor.getString(28);
        String address_city=cursor.getString(29);
        String address_country=cursor.getString(30);
        String mobile1=cursor.getString(31);
        String mobile2=cursor.getString(32);
        String email=cursor.getString(33);
        String website=cursor.getString(34);
        String facebook=cursor.getString(35);
        String contact_designation=cursor.getString(36);
        String opening=cursor.getString(37);
        String breaks=cursor.getString(38);
        String closing=cursor.getString(39);
        String off_day=cursor.getString(40);
        String remarks=cursor.getString(41);
        String ref1=cursor.getString(42);
        String ref2=cursor.getString(43);
        String ref3=cursor.getString(44);
        String reg_with=cursor.getString(45);
        String reg_num=cursor.getString(46);
        String job_type=cursor.getString(47);
        String collector_name=cursor.getString(48);
        String user=cursor.getString(49);
        String status=cursor.getString(50);
        String subcategory=cursor.getString(51);
        String typeSubcategory=cursor.getString(52);

        return new JobAdvertisementItem(
                id,
                form_date,
                position,
                institute_name,
                institute_name_bangla,
                post_date,
                application_last_date,
                location,
                post_type,
                job_responsibility,
                required_experience,
                start_salary,
                end_salary,
                other_benefits,
                reference_person,
                application_medium,
                map_type,
                latitude,
                longitude,
                address_floor,
                address_house_name,
                address_house_num,
                address_road,
                address_line,
                address_avenue,
                address_block,
                address_area,
                address_post,
                address_police,
                address_city,
                address_country,
                mobile1,
                mobile2,
                email,
                website,
                facebook,
                contact_designation,
                opening,
                breaks,
                closing,
                off_day,
                remarks,
                ref1,
                ref2,
                ref3,
                reg_with,
                reg_num,
                job_type,
                collector_name,
                user,
                status,
                subcategory,
                typeSubcategory);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
