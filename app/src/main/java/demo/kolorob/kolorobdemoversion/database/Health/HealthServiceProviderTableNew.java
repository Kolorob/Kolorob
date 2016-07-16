package demo.kolorob.kolorobdemoversion.database.Health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;

import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by arafat on 11 July 2016.
 */
public class HealthServiceProviderTableNew {

    private static final String TAG = HealthServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.HEALTH_PROVIDER_TABLE_NEW;

    private static final String KEY_ID= "id";
    private static final String KEY_NODE_NAME= "node_name";
    private static final String KEY_NODE_BN= "node_bn";
    private static final String KEY_INSTITUTE_TYPE= "institute_type";
    private static final String KEY_SPOKEN_LANG= "spoken_lang";
    private static final String KEY_CAPACITY= "capacity";
    private static final String KEY_MALE_DOCTORS= "male_doctors";
    private static final String KEY_FEMALE_DOCTORS= "female_doctors";
    private static final String KEY_PATIENT_DOCTOR_RATIO= "patient_doctor_ratio";
    private static final String KEY_MALE_NURSE= "male_nurse";
    private static final String KEY_FEMALE_NURSE= "female_nurse";
    private static final String KEY_PATIENT_NURSE_RATIO= "patient_nurse_ratio";
    private static final String KEY_LON= "lon";
    private static final String KEY_LAT= "lat";
    private static final String KEY_NODE_ID= "node_id";
    private static final String KEY_FLOOR= "floor";
    private static final String KEY_HOUSE_NAME= "house_name";
    private static final String KEY_HOUSE_NO= "house_no";
    private static final String KEY_ROAD= "road";
    private static final String KEY_LINE= "line";
    private static final String KEY_AVENUE= "avenue";
    private static final String KEY_BLOCK= "block";
    private static final String KEY_AREA= "area";
    private static final String KEY_LANDMARK= "landmark";
    private static final String KEY_POST_OFFICE= "post_office";
    private static final String KEY_POLICE_STATION= "police_station";
    private static final String KEY_CITY= "city";
    private static final String KEY_COUNTRY= "country";
    private static final String KEY_NODE_CONTACT= "node_contact";
    private static final String KEY_NODE_CONTACT2= "node_contact2";
    private static final String KEY_NODE_WEBSITE= "node_website";
    private static final String KEY_NODE_FACEBOOK= "node_facebook";
    private static final String KEY_NODE_DESIGNATION= "node_designation";
    private static final String KEY_ADDRESS= "address";
    private static final String KEY_OPENING_TIME= "opening_time";
    private static final String KEY_BREAK_TIME= "break_time";
    private static final String KEY_CLOSING_TIME= "closing_time";
    private static final String KEY_OFF_DAY= "off_day";
    private static final String KEY_NODE_REGISTERED_WITH= "node_registered_with";
    private static final String KEY_NODE_REGISTERED_NUMBER= "node_registered_number";
    private static final String KEY_UPDATED_DRUGS= "updated_drugs";
    private static final String KEY_NUM_TRAINED_PHARMACIST= "num_trained_pharmacist";
    private static final String KEY_DOCTOR_AVAILABLE= "doctor_available";
    private static final String KEY_PHARMACY_SPECIALITY= "pharmacy_speciality";
    private static final String KEY_PHARMACY_FREE= "pharmacy_free";
    private static final String KEY_FREE_SERVICE_FOR= "free_service_for";
    private static final String KEY_PHARMACY_FREE_SERVICE= "pharmacy_free_service";
    private static final String KEY_PHARMACY_FEE= "pharmacy_fee";
    private static final String KEY_PHARMACY_REMARKS= "pharmacy_remarks";
    private static final String KEY_PHARMACY_PRIVACY= "pharmacy_privacy";
    private static final String KEY_QUALITY_EQUIPMENTS= "quality_equipments";
    private static final String KEY_GENERAL_FREE= "general_free";
    private static final String KEY_GENERAL_FREE_FOR= "general_free_for";
    private static final String KEY_GENERAL_FREE_SERVICES= "general_free_services";
    private static final String KEY_GENERAL_COST= "general_cost";
    private static final String KEY_GENERAL_REMARK= "general_remark";
    private static final String KEY_EMERGENCY_FREE= "emergency_free";
    private static final String KEY_EMERGENCY_FREE_FOR= "emergency_free_for";
    private static final String KEY_EMERGENCY_FREE_SERVICES= "emergency_free_services";
    private static final String KEY_EMERGENCY_COST= "emergency_cost";
    private static final String KEY_EMERGENCY_REMARK= "emergency_remark";
    private static final String KEY_AMBULANCE_FREE= "ambulance_free";
    private static final String KEY_AMBULANCE_FREE_FOR= "ambulance_free_for";
    private static final String KEY_AMBULANCE_FREE_SERVICES= "ambulance_free_services";
    private static final String KEY_AMBULANCE_COST= "ambulance_cost";
    private static final String KEY_AMBULANCE_REMARK= "ambulance_remark";
    private static final String KEY_MATERNITY_FREE= "maternity_free";
    private static final String KEY_MATERNITY_FREE_FOR= "maternity_free_for";
    private static final String KEY_MATERNITY_FREE_SERVICES= "maternity_free_services";
    private static final String KEY_MATERNITY_COST= "maternity_cost";
    private static final String KEY_MATERNITY_REMARK= "maternity_remark";
    private static final String KEY_MATERNITY_COMPLICATION= "maternity_complication";
    private static final String KEY_MATERNITY_PRIVACY= "maternity_privacy";
    private static final String KEY_MATERNITY_NEWBORN_CARE= "maternity_newborn_care";
    private static final String KEY_FAMILY_SERVICES= "family_services";
    private static final String KEY_FAMILY_CONTRACEPTIVE_AVAILABLE= "family_contraceptive_available";
    private static final String KEY_FAMILY_CONTRACEPTIVE= "family_contraceptive";
    private static final String KEY_FAMILY_PRIVACY= "family_privacy";
    private static final String KEY_CATEGORY= "category";
    private static final String KEY_REFERENCES= "referencesx";



    private Context tContext;


    public HealthServiceProviderTableNew(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + "  TEXT, "
                + KEY_NODE_NAME + "  TEXT, "
                + KEY_NODE_BN + "  TEXT, "
                + KEY_INSTITUTE_TYPE + "  TEXT, "
                + KEY_SPOKEN_LANG + "  TEXT, "
                + KEY_CAPACITY + "  TEXT, "
                + KEY_MALE_DOCTORS + "  TEXT, "
                + KEY_FEMALE_DOCTORS + "  TEXT, "
                + KEY_PATIENT_DOCTOR_RATIO + "  TEXT, "
                + KEY_MALE_NURSE + "  TEXT, "
                + KEY_FEMALE_NURSE + "  TEXT, "
                + KEY_PATIENT_NURSE_RATIO + "  TEXT, "
                + KEY_LON + "  TEXT, "
                + KEY_LAT + "  TEXT, "
                + KEY_NODE_ID + "  TEXT, "
                + KEY_FLOOR + "  TEXT, "
                + KEY_HOUSE_NAME + "  TEXT, "
                + KEY_HOUSE_NO + "  TEXT, "
                + KEY_ROAD + "  TEXT, "
                + KEY_LINE + "  TEXT, "
                + KEY_AVENUE + "  TEXT, "
                + KEY_BLOCK + "  TEXT, "
                + KEY_AREA + "  TEXT, "
                + KEY_LANDMARK + "  TEXT, "
                + KEY_POST_OFFICE + "  TEXT, "
                + KEY_POLICE_STATION + "  TEXT, "
                + KEY_CITY + "  TEXT, "
                + KEY_COUNTRY + "  TEXT, "
                + KEY_NODE_CONTACT + "  TEXT, "
                + KEY_NODE_CONTACT2 + "  TEXT, "
                + KEY_NODE_WEBSITE + "  TEXT, "
                + KEY_NODE_FACEBOOK + "  TEXT, "
                + KEY_NODE_DESIGNATION + "  TEXT, "
                + KEY_ADDRESS + "  TEXT, "
                + KEY_OPENING_TIME + "  TEXT, "
                + KEY_BREAK_TIME + "  TEXT, "
                + KEY_CLOSING_TIME + "  TEXT, "
                + KEY_OFF_DAY + "  TEXT, "
                + KEY_NODE_REGISTERED_WITH + "  TEXT, "
                + KEY_NODE_REGISTERED_NUMBER + "  TEXT, "
                + KEY_UPDATED_DRUGS + "  TEXT, "
                + KEY_NUM_TRAINED_PHARMACIST + "  TEXT, "
                + KEY_DOCTOR_AVAILABLE + "  TEXT, "
                + KEY_PHARMACY_SPECIALITY + "  TEXT, "
                + KEY_PHARMACY_FREE + "  TEXT, "
                + KEY_FREE_SERVICE_FOR + "  TEXT, "
                + KEY_PHARMACY_FREE_SERVICE + "  TEXT, "
                + KEY_PHARMACY_FEE + "  TEXT, "
                + KEY_PHARMACY_REMARKS + "  TEXT, "
                + KEY_PHARMACY_PRIVACY + "  TEXT, "
                + KEY_QUALITY_EQUIPMENTS + "  TEXT, "
                + KEY_GENERAL_FREE + "  TEXT, "
                + KEY_GENERAL_FREE_FOR + "  TEXT, "
                + KEY_GENERAL_FREE_SERVICES + "  TEXT, "
                + KEY_GENERAL_COST + "  TEXT, "
                + KEY_GENERAL_REMARK + "  TEXT, "
                + KEY_EMERGENCY_FREE + "  TEXT, "
                + KEY_EMERGENCY_FREE_FOR + "  TEXT, "
                + KEY_EMERGENCY_FREE_SERVICES + "  TEXT, "
                + KEY_EMERGENCY_COST + "  TEXT, "
                + KEY_EMERGENCY_REMARK + "  TEXT, "
                + KEY_AMBULANCE_FREE + "  TEXT, "
                + KEY_AMBULANCE_FREE_FOR + "  TEXT, "
                + KEY_AMBULANCE_FREE_SERVICES + "  TEXT, "
                + KEY_AMBULANCE_COST + "  TEXT, "
                + KEY_AMBULANCE_REMARK + "  TEXT, "
                + KEY_MATERNITY_FREE + "  TEXT, "
                + KEY_MATERNITY_FREE_FOR + "  TEXT, "
                + KEY_MATERNITY_FREE_SERVICES + "  TEXT, "
                + KEY_MATERNITY_COST + "  TEXT, "
                + KEY_MATERNITY_REMARK + "  TEXT, "
                + KEY_MATERNITY_COMPLICATION + "  TEXT, "
                + KEY_MATERNITY_PRIVACY + "  TEXT, "
                + KEY_MATERNITY_NEWBORN_CARE + "  TEXT, "
                + KEY_FAMILY_SERVICES + "  TEXT, "
                + KEY_FAMILY_CONTRACEPTIVE_AVAILABLE + "  TEXT, "
                + KEY_FAMILY_CONTRACEPTIVE + "  TEXT, "
                + KEY_FAMILY_PRIVACY + "  TEXT, "
                + KEY_CATEGORY + "  TEXT, "
                + KEY_REFERENCES + " TEXT, PRIMARY KEY(" + KEY_ID + "))";


        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    public long insertItemHealth(HealthServiceProviderItemNew healthServiceProviderItemNew) {
        return insertItemHealth(
                healthServiceProviderItemNew.getId(),
                healthServiceProviderItemNew.getNode_name(),
                healthServiceProviderItemNew.getNode_bn(),
                healthServiceProviderItemNew.getInstitute_type(),
                healthServiceProviderItemNew.getSpoken_lang(),
                healthServiceProviderItemNew.getCapacity(),
                healthServiceProviderItemNew.getMale_doctors(),
                healthServiceProviderItemNew.getFemale_doctors(),
                healthServiceProviderItemNew.getPatient_doctor_ratio(),
                healthServiceProviderItemNew.getMale_nurse(),
                healthServiceProviderItemNew.getFemale_nurse(),
                healthServiceProviderItemNew.getPatient_nurse_ratio(),
                healthServiceProviderItemNew.getLon(),
                healthServiceProviderItemNew.getLat(),
                healthServiceProviderItemNew.getNode_id(),
                healthServiceProviderItemNew.getFloor(),
                healthServiceProviderItemNew.getHouse_name(),
                healthServiceProviderItemNew.getHouse_no(),
                healthServiceProviderItemNew.getRoad(),
                healthServiceProviderItemNew.getLine(),
                healthServiceProviderItemNew.getAvenue(),
                healthServiceProviderItemNew.getBlock(),
                healthServiceProviderItemNew.getArea(),
                healthServiceProviderItemNew.getLandmark(),
                healthServiceProviderItemNew.getPost_office(),
                healthServiceProviderItemNew.getPolice_station(),
                healthServiceProviderItemNew.getCity(),
                healthServiceProviderItemNew.getCountry(),
                healthServiceProviderItemNew.getNode_contact(),
                healthServiceProviderItemNew.getNode_contact2(),
                healthServiceProviderItemNew.getNode_website(),
                healthServiceProviderItemNew.getNode_facebook(),
                healthServiceProviderItemNew.getNode_designation(),
                healthServiceProviderItemNew.getAddress(),
                healthServiceProviderItemNew.getOpening_time(),
                healthServiceProviderItemNew.getBreak_time(),
                healthServiceProviderItemNew.getClosing_time(),
                healthServiceProviderItemNew.getOff_day(),
                healthServiceProviderItemNew.getNode_registered_with(),
                healthServiceProviderItemNew.getNode_registered_number(),
                healthServiceProviderItemNew.getUpdated_drugs(),
                healthServiceProviderItemNew.getNum_trained_pharmacist(),
                healthServiceProviderItemNew.getDoctor_available(),
                healthServiceProviderItemNew.getPharmacy_speciality(),
                healthServiceProviderItemNew.getPharmacy_free(),
                healthServiceProviderItemNew.getFree_service_for(),
                healthServiceProviderItemNew.getPharmacy_free_service(),
                healthServiceProviderItemNew.getPharmacy_fee(),
                healthServiceProviderItemNew.getPharmacy_remarks(),
                healthServiceProviderItemNew.getPharmacy_privacy(),
                healthServiceProviderItemNew.getQuality_equipments(),
                healthServiceProviderItemNew.getGeneral_free(),
                healthServiceProviderItemNew.getGeneral_free_for(),
                healthServiceProviderItemNew.getGeneral_free_services(),
                healthServiceProviderItemNew.getGeneral_cost(),
                healthServiceProviderItemNew.getGeneral_remark(),
                healthServiceProviderItemNew.getEmergency_free(),
                healthServiceProviderItemNew.getEmergency_free_for(),
                healthServiceProviderItemNew.getEmergency_free_services(),
                healthServiceProviderItemNew.getEmergency_cost(),
                healthServiceProviderItemNew.getEmergency_remark(),
                healthServiceProviderItemNew.getAmbulance_free(),
                healthServiceProviderItemNew.getAmbulance_free_for(),
                healthServiceProviderItemNew.getAmbulance_free_services(),
                healthServiceProviderItemNew.getAmbulance_cost(),
                healthServiceProviderItemNew.getAmbulance_remark(),
                healthServiceProviderItemNew.getMaternity_free(),
                healthServiceProviderItemNew.getMaternity_free_for(),
                healthServiceProviderItemNew.getMaternity_free_services(),
                healthServiceProviderItemNew.getMaternity_cost(),
                healthServiceProviderItemNew.getMaternity_remark(),
                healthServiceProviderItemNew.getMaternity_complication(),
                healthServiceProviderItemNew.getMaternity_privacy(),
                healthServiceProviderItemNew.getMaternity_newborn_care(),
                healthServiceProviderItemNew.getFamily_services(),
                healthServiceProviderItemNew.getFamily_contraceptive_available(),
                healthServiceProviderItemNew.getFamily_contraceptive(),
                healthServiceProviderItemNew.getFamily_privacy(),
                healthServiceProviderItemNew.getCategory(),
                healthServiceProviderItemNew.getReferences()

        );
    }

    public long insertItemHealth(
            String id,
            String node_name,
            String node_bn,
            String institute_type,
            String spoken_lang,
            String capacity,
            String male_doctors,
            String female_doctors,
            String patient_doctor_ratio,
            String male_nurse,
            String female_nurse,
            String patient_nurse_ratio,
            String lon,
            String lat,
            String node_id,
            String floor,
            String house_name,
            String house_no,
            String road,
            String line,
            String avenue,
            String block,
            String area,
            String landmark,
            String post_office,
            String police_station,
            String city,
            String country,
            String node_contact,
            String node_contact2,
            String node_website,
            String node_facebook,
            String node_designation,
            String address,
            String opening_time,
            String break_time,
            String closing_time,
            String off_day,
            String node_registered_with,
            String node_registered_number,
            String updated_drugs,
            String num_trained_pharmacist,
            String doctor_available,
            String pharmacy_speciality,
            String pharmacy_free,
            String free_service_for,
            String pharmacy_free_service,
            String pharmacy_fee,
            String pharmacy_remarks,
            String pharmacy_privacy,
            String quality_equipments,
            String general_free,
            String general_free_for,
            String general_free_services,
            String general_cost,
            String general_remark,
            String emergency_free,
            String emergency_free_for,
            String emergency_free_services,
            String emergency_cost,
            String emergency_remark,
            String ambulance_free,
            String ambulance_free_for,
            String ambulance_free_services,
            String ambulance_cost,
            String ambulance_remark,
            String maternity_free,
            String maternity_free_for,
            String maternity_free_services,
            String maternity_cost,
            String maternity_remark,
            String maternity_complication,
            String maternity_privacy,
            String maternity_newborn_care,
            String family_services,
            String family_contraceptive_available,
            String family_contraceptive,
            String family_privacy,
            String category,
            String references) {
        if (isFieldExist(id)) {
            return updateItem(
                    id,
                    node_name,
                    node_bn,
                    institute_type,
                    spoken_lang,
                    capacity,
                    male_doctors,
                    female_doctors,
                    patient_doctor_ratio,
                    male_nurse,
                    female_nurse,
                    patient_nurse_ratio,
                    lon,
                    lat,
                    node_id,
                    floor,
                    house_name,
                    house_no,
                    road,
                    line,
                    avenue,
                    block,
                    area,
                    landmark,
                    post_office,
                    police_station,
                    city,
                    country,
                    node_contact,
                    node_contact2,
                    node_website,
                    node_facebook,
                    node_designation,
                    address,
                    opening_time,
                    break_time,
                    closing_time,
                    off_day,
                    node_registered_with,
                    node_registered_number,
                    updated_drugs,
                    num_trained_pharmacist,
                    doctor_available,
                    pharmacy_speciality,
                    pharmacy_free,
                    free_service_for,
                    pharmacy_free_service,
                    pharmacy_fee,
                    pharmacy_remarks,
                    pharmacy_privacy,
                    quality_equipments,
                    general_free,
                    general_free_for,
                    general_free_services,
                    general_cost,
                    general_remark,
                    emergency_free,
                    emergency_free_for,
                    emergency_free_services,
                    emergency_cost,
                    emergency_remark,
                    ambulance_free,
                    ambulance_free_for,
                    ambulance_free_services,
                    ambulance_cost,
                    ambulance_remark,
                    maternity_free,
                    maternity_free_for,
                    maternity_free_services,
                    maternity_cost,
                    maternity_remark,
                    maternity_complication,
                    maternity_privacy,
                    maternity_newborn_care,
                    family_services,
                    family_contraceptive_available,
                    family_contraceptive,
                    family_privacy,
                    category,
                    references);
        }

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID,  id  );

        rowValue.put(KEY_NODE_NAME,  node_name  );
        rowValue.put(KEY_NODE_BN,  node_bn  );
        rowValue.put(KEY_INSTITUTE_TYPE,  institute_type  );
        rowValue.put(KEY_SPOKEN_LANG,  spoken_lang  );
        rowValue.put(KEY_CAPACITY,  capacity  );
        rowValue.put(KEY_MALE_DOCTORS,  male_doctors  );
        rowValue.put(KEY_FEMALE_DOCTORS,  female_doctors  );
        rowValue.put(KEY_PATIENT_DOCTOR_RATIO,  patient_doctor_ratio  );
        rowValue.put(KEY_MALE_NURSE,  male_nurse  );
        rowValue.put(KEY_FEMALE_NURSE,  female_nurse  );
        rowValue.put(KEY_PATIENT_NURSE_RATIO,  patient_nurse_ratio  );
        rowValue.put(KEY_LON,  lon  );
        rowValue.put(KEY_LAT,  lat  );
        rowValue.put(KEY_NODE_ID,  node_id  );
        rowValue.put(KEY_FLOOR,  floor  );
        rowValue.put(KEY_HOUSE_NAME,  house_name  );
        rowValue.put(KEY_HOUSE_NO,  house_no  );
        rowValue.put(KEY_ROAD,  road  );
        rowValue.put(KEY_LINE,  line  );
        rowValue.put(KEY_AVENUE,  avenue  );
        rowValue.put(KEY_BLOCK,  block  );
        rowValue.put(KEY_AREA,  area  );
        rowValue.put(KEY_LANDMARK,  landmark  );
        rowValue.put(KEY_POST_OFFICE,  post_office  );
        rowValue.put(KEY_POLICE_STATION,  police_station  );
        rowValue.put(KEY_CITY,  city  );
        rowValue.put(KEY_COUNTRY,  country  );
        rowValue.put(KEY_NODE_CONTACT,  node_contact  );
        rowValue.put(KEY_NODE_CONTACT2,  node_contact2  );
        rowValue.put(KEY_NODE_WEBSITE,  node_website  );
        rowValue.put(KEY_NODE_FACEBOOK,  node_facebook  );
        rowValue.put(KEY_NODE_DESIGNATION,  node_designation  );
        rowValue.put(KEY_ADDRESS,  address  );
        rowValue.put(KEY_OPENING_TIME,  opening_time  );
        rowValue.put(KEY_BREAK_TIME,  break_time  );
        rowValue.put(KEY_CLOSING_TIME,  closing_time  );
        rowValue.put(KEY_OFF_DAY,  off_day  );
        rowValue.put(KEY_NODE_REGISTERED_WITH,  node_registered_with  );
        rowValue.put(KEY_NODE_REGISTERED_NUMBER,  node_registered_number  );
        rowValue.put(KEY_UPDATED_DRUGS,  updated_drugs  );
        rowValue.put(KEY_NUM_TRAINED_PHARMACIST,  num_trained_pharmacist  );
        rowValue.put(KEY_DOCTOR_AVAILABLE,  doctor_available  );
        rowValue.put(KEY_PHARMACY_SPECIALITY,  pharmacy_speciality  );
        rowValue.put(KEY_PHARMACY_FREE,  pharmacy_free  );
        rowValue.put(KEY_FREE_SERVICE_FOR,  free_service_for  );
        rowValue.put(KEY_PHARMACY_FREE_SERVICE,  pharmacy_free_service  );
        rowValue.put(KEY_PHARMACY_FEE,  pharmacy_fee  );
        rowValue.put(KEY_PHARMACY_REMARKS,  pharmacy_remarks  );
        rowValue.put(KEY_PHARMACY_PRIVACY,  pharmacy_privacy  );
        rowValue.put(KEY_QUALITY_EQUIPMENTS,  quality_equipments  );
        rowValue.put(KEY_GENERAL_FREE,  general_free  );
        rowValue.put(KEY_GENERAL_FREE_FOR,  general_free_for  );
        rowValue.put(KEY_GENERAL_FREE_SERVICES,  general_free_services  );
        rowValue.put(KEY_GENERAL_COST,  general_cost  );
        rowValue.put(KEY_GENERAL_REMARK,  general_remark  );
        rowValue.put(KEY_EMERGENCY_FREE,  emergency_free  );
        rowValue.put(KEY_EMERGENCY_FREE_FOR,  emergency_free_for  );
        rowValue.put(KEY_EMERGENCY_FREE_SERVICES,  emergency_free_services  );
        rowValue.put(KEY_EMERGENCY_COST,  emergency_cost  );
        rowValue.put(KEY_EMERGENCY_REMARK,  emergency_remark  );
        rowValue.put(KEY_AMBULANCE_FREE,  ambulance_free  );
        rowValue.put(KEY_AMBULANCE_FREE_FOR,  ambulance_free_for  );
        rowValue.put(KEY_AMBULANCE_FREE_SERVICES,  ambulance_free_services  );
        rowValue.put(KEY_AMBULANCE_COST,  ambulance_cost  );
        rowValue.put(KEY_AMBULANCE_REMARK,  ambulance_remark  );
        rowValue.put(KEY_MATERNITY_FREE,  maternity_free  );
        rowValue.put(KEY_MATERNITY_FREE_FOR,  maternity_free_for  );
        rowValue.put(KEY_MATERNITY_FREE_SERVICES,  maternity_free_services  );
        rowValue.put(KEY_MATERNITY_COST,  maternity_cost  );
        rowValue.put(KEY_MATERNITY_REMARK,  maternity_remark  );
        rowValue.put(KEY_MATERNITY_COMPLICATION,  maternity_complication  );
        rowValue.put(KEY_MATERNITY_PRIVACY,  maternity_privacy  );
        rowValue.put(KEY_MATERNITY_NEWBORN_CARE,  maternity_newborn_care  );
        rowValue.put(KEY_FAMILY_SERVICES,  family_services  );
        rowValue.put(KEY_FAMILY_CONTRACEPTIVE_AVAILABLE,  family_contraceptive_available  );
        rowValue.put(KEY_FAMILY_CONTRACEPTIVE,  family_contraceptive  );
        rowValue.put(KEY_FAMILY_PRIVACY,  family_privacy  );
        rowValue.put(KEY_CATEGORY,  category  );
        rowValue.put(KEY_REFERENCES,  references  );


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
    public boolean isFieldExist(String id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))) {
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
    //    public ArrayList<HealthServiceProviderItem> Heanames(int cat_id,String head,String a,String place) {
//        String subcatnames=null;
//        subcatnames=a;
//
//        ArrayList<HealthServiceProviderItem> nameslist=new ArrayList<>();
//
//        SQLiteDatabase db = openDB();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + "=" + cat_id
//                + " AND "+KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_REF_NUM + "=" + "(SELECT _sub_cat_id from " + DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_name = '"+subcatnames+"')", null);
//
//
//        if (cursor.moveToFirst()) {
//            do {
//
//
//                nameslist.add(cursorToSubCatList(cursor));
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return  nameslist;
//    }
    private long updateItem( String id,
                             String node_name,
                             String node_bn,
                             String institute_type,
                             String spoken_lang,
                             String capacity,
                             String male_doctors,
                             String female_doctors,
                             String patient_doctor_ratio,
                             String male_nurse,
                             String female_nurse,
                             String patient_nurse_ratio,
                             String lon,
                             String lat,
                             String node_id,
                             String floor,
                             String house_name,
                             String house_no,
                             String road,
                             String line,
                             String avenue,
                             String block,
                             String area,
                             String landmark,
                             String post_office,
                             String police_station,
                             String city,
                             String country,
                             String node_contact,
                             String node_contact2,
                             String node_website,
                             String node_facebook,
                             String node_designation,
                             String address,
                             String opening_time,
                             String break_time,
                             String closing_time,
                             String off_day,
                             String node_registered_with,
                             String node_registered_number,
                             String updated_drugs,
                             String num_trained_pharmacist,
                             String doctor_available,
                             String pharmacy_speciality,
                             String pharmacy_free,
                             String free_service_for,
                             String pharmacy_free_service,
                             String pharmacy_fee,
                             String pharmacy_remarks,
                             String pharmacy_privacy,
                             String quality_equipments,
                             String general_free,
                             String general_free_for,
                             String general_free_services,
                             String general_cost,
                             String general_remark,
                             String emergency_free,
                             String emergency_free_for,
                             String emergency_free_services,
                             String emergency_cost,
                             String emergency_remark,
                             String ambulance_free,
                             String ambulance_free_for,
                             String ambulance_free_services,
                             String ambulance_cost,
                             String ambulance_remark,
                             String maternity_free,
                             String maternity_free_for,
                             String maternity_free_services,
                             String maternity_cost,
                             String maternity_remark,
                             String maternity_complication,
                             String maternity_privacy,
                             String maternity_newborn_care,
                             String family_services,
                             String family_contraceptive_available,
                             String family_contraceptive,
                             String family_privacy,
                             String category,
                             String references) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID,  id  );
        rowValue.put(KEY_NODE_NAME,  node_name  );
        rowValue.put(KEY_NODE_BN,  node_bn  );
        rowValue.put(KEY_INSTITUTE_TYPE,  institute_type  );
        rowValue.put(KEY_SPOKEN_LANG,  spoken_lang  );
        rowValue.put(KEY_CAPACITY,  capacity  );
        rowValue.put(KEY_MALE_DOCTORS,  male_doctors  );
        rowValue.put(KEY_FEMALE_DOCTORS,  female_doctors  );
        rowValue.put(KEY_PATIENT_DOCTOR_RATIO,  patient_doctor_ratio  );
        rowValue.put(KEY_MALE_NURSE,  male_nurse  );
        rowValue.put(KEY_FEMALE_NURSE,  female_nurse  );
        rowValue.put(KEY_PATIENT_NURSE_RATIO,  patient_nurse_ratio  );
        rowValue.put(KEY_LON,  lon  );
        rowValue.put(KEY_LAT,  lat  );
        rowValue.put(KEY_NODE_ID,  node_id  );
        rowValue.put(KEY_FLOOR,  floor  );
        rowValue.put(KEY_HOUSE_NAME,  house_name  );
        rowValue.put(KEY_HOUSE_NO,  house_no  );
        rowValue.put(KEY_ROAD,  road  );
        rowValue.put(KEY_LINE,  line  );
        rowValue.put(KEY_AVENUE,  avenue  );
        rowValue.put(KEY_BLOCK,  block  );
        rowValue.put(KEY_AREA,  area  );
        rowValue.put(KEY_LANDMARK,  landmark  );
        rowValue.put(KEY_POST_OFFICE,  post_office  );
        rowValue.put(KEY_POLICE_STATION,  police_station  );
        rowValue.put(KEY_CITY,  city  );
        rowValue.put(KEY_COUNTRY,  country  );
        rowValue.put(KEY_NODE_CONTACT,  node_contact  );
        rowValue.put(KEY_NODE_CONTACT2,  node_contact2  );
        rowValue.put(KEY_NODE_WEBSITE,  node_website  );
        rowValue.put(KEY_NODE_FACEBOOK,  node_facebook  );
        rowValue.put(KEY_NODE_DESIGNATION,  node_designation  );
        rowValue.put(KEY_ADDRESS,  address  );
        rowValue.put(KEY_OPENING_TIME,  opening_time  );
        rowValue.put(KEY_BREAK_TIME,  break_time  );
        rowValue.put(KEY_CLOSING_TIME,  closing_time  );
        rowValue.put(KEY_OFF_DAY,  off_day  );
        rowValue.put(KEY_NODE_REGISTERED_WITH,  node_registered_with  );
        rowValue.put(KEY_NODE_REGISTERED_NUMBER,  node_registered_number  );
        rowValue.put(KEY_UPDATED_DRUGS,  updated_drugs  );
        rowValue.put(KEY_NUM_TRAINED_PHARMACIST,  num_trained_pharmacist  );
        rowValue.put(KEY_DOCTOR_AVAILABLE,  doctor_available  );
        rowValue.put(KEY_PHARMACY_SPECIALITY,  pharmacy_speciality  );
        rowValue.put(KEY_PHARMACY_FREE,  pharmacy_free  );
        rowValue.put(KEY_FREE_SERVICE_FOR,  free_service_for  );
        rowValue.put(KEY_PHARMACY_FREE_SERVICE,  pharmacy_free_service  );
        rowValue.put(KEY_PHARMACY_FEE,  pharmacy_fee  );
        rowValue.put(KEY_PHARMACY_REMARKS,  pharmacy_remarks  );
        rowValue.put(KEY_PHARMACY_PRIVACY,  pharmacy_privacy  );
        rowValue.put(KEY_QUALITY_EQUIPMENTS,  quality_equipments  );
        rowValue.put(KEY_GENERAL_FREE,  general_free  );
        rowValue.put(KEY_GENERAL_FREE_FOR,  general_free_for  );
        rowValue.put(KEY_GENERAL_FREE_SERVICES,  general_free_services  );
        rowValue.put(KEY_GENERAL_COST,  general_cost  );
        rowValue.put(KEY_GENERAL_REMARK,  general_remark  );
        rowValue.put(KEY_EMERGENCY_FREE,  emergency_free  );
        rowValue.put(KEY_EMERGENCY_FREE_FOR,  emergency_free_for  );
        rowValue.put(KEY_EMERGENCY_FREE_SERVICES,  emergency_free_services  );
        rowValue.put(KEY_EMERGENCY_COST,  emergency_cost  );
        rowValue.put(KEY_EMERGENCY_REMARK,  emergency_remark  );
        rowValue.put(KEY_AMBULANCE_FREE,  ambulance_free  );
        rowValue.put(KEY_AMBULANCE_FREE_FOR,  ambulance_free_for  );
        rowValue.put(KEY_AMBULANCE_FREE_SERVICES,  ambulance_free_services  );
        rowValue.put(KEY_AMBULANCE_COST,  ambulance_cost  );
        rowValue.put(KEY_AMBULANCE_REMARK,  ambulance_remark  );
        rowValue.put(KEY_MATERNITY_FREE,  maternity_free  );
        rowValue.put(KEY_MATERNITY_FREE_FOR,  maternity_free_for  );
        rowValue.put(KEY_MATERNITY_FREE_SERVICES,  maternity_free_services  );
        rowValue.put(KEY_MATERNITY_COST,  maternity_cost  );
        rowValue.put(KEY_MATERNITY_REMARK,  maternity_remark  );
        rowValue.put(KEY_MATERNITY_COMPLICATION,  maternity_complication  );
        rowValue.put(KEY_MATERNITY_PRIVACY,  maternity_privacy  );
        rowValue.put(KEY_MATERNITY_NEWBORN_CARE,  maternity_newborn_care  );
        rowValue.put(KEY_FAMILY_SERVICES,  family_services  );
        rowValue.put(KEY_FAMILY_CONTRACEPTIVE_AVAILABLE,  family_contraceptive_available  );
        rowValue.put(KEY_FAMILY_CONTRACEPTIVE,  family_contraceptive  );
        rowValue.put(KEY_FAMILY_PRIVACY,  family_privacy  );
        rowValue.put(KEY_CATEGORY,  category  );
        rowValue.put(KEY_REFERENCES,  references  );


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ? ",
                new String[]{id + ""});
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
//    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfo(int cat_id,int sub_cat_id) {
//        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+" AND "+KEY_REF_NUM+"="+sub_cat_id, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                subCatList.add(cursorToSubCatList(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }

//    public Vector<String> getAllEntertainmentSubCategoriesInfo() {
//        Vector<String> subCatList = new Vector<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        int cat_id=2;
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                String  subCatLists = cursor.getString(cursor.getColumnIndex(KEY_NODE_NAME));
//
//                subCatList.add(subCatLists);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }
//
//    public ArrayList<String> getAllEntertainmentSubCategoriesInfos() {
//        ArrayList<String> subCatList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        int cat_id=2;
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                String  subCatLists = cursor.getString(cursor.getColumnIndex(KEY_NODE_NAME));
//
//                subCatList.add(subCatLists);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }
public ArrayList<HealthServiceProviderItemNew> getAllHealthSubCategoriesInfo() {
    ArrayList<HealthServiceProviderItemNew> subCatList = new ArrayList<>();
    SQLiteDatabase db = openDB();
    Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

    if (cursor.moveToFirst()) {
        do {
            System.out.println("abc="+cursor.getString(4));
            subCatList.add(cursorToSubCatList(cursor));
        } while (cursor.moveToNext());
    }
    cursor.close();
    closeDB();
    return subCatList;
   }

//    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfo(int cat_id) {
//        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id+ " ORDER BY " +KEY_NODE_NAME, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                subCatList.add(cursorToSubCatList(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }

//    public ArrayList<HealthServiceProviderItem> getAllHealthSubCategoriesInfoWithHead(int cat_id,String header) {
//        ArrayList<HealthServiceProviderItem> subCatList = new ArrayList<>();
//        //System.out.println(cat_id+"  "+sub_cat_id);
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id
//                + " AND " +KEY_REF_NUM + " in (SELECT _sub_cat_id from "+ DatabaseHelper.SUB_CATEGORY + " WHERE _sub_cat_header = '"+header+"')", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                //System.out.println("abc="+cursor.getString(4));
//                subCatList.add(cursorToSubCatList(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return subCatList;
//    }

    public ArrayList<HealthServiceProviderItemNew> getHealthData(String node_id) {
        ArrayList<HealthServiceProviderItemNew> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ KEY_ID +" = "+node_id, null);

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


    public ArrayList<HealthServiceProviderItemNew> Heanames(int cat_id,int refId,String a,String place) {
        String subcatnames=null;
        subcatnames=a;


        String refids= String.valueOf(refId);

          refids=","+refids+",";



        ArrayList<HealthServiceProviderItemNew> nameslist=new ArrayList<>();

        SQLiteDatabase db = openDB();


        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_REFERENCES+ " LIKE '%"+refids+"%'" , null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_AREA+" = '"+place+"'"  , null);
      //  Log.d("Ref Id","======"+"SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_AREA+" = '"+place+"'"  + " AND "+ KEY_REFERENCES+ " LIKE '%"+refids+"%'" + "=" +refId);
//        Toast.makeText(this, +cursor,
//                Toast.LENGTH_LONG).show();



        if (cursor.moveToFirst()) {
            do {


                nameslist.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return  nameslist;
    }

    private HealthServiceProviderItemNew cursorToSubCatList(Cursor cursor) {
        String id= cursor.getString(0);
        String node_name= cursor.getString(1);
        String node_bn= cursor.getString(2);
        String institute_type= cursor.getString(3);
        String spoken_lang= cursor.getString(4);
        String capacity= cursor.getString(5);
        String male_doctors= cursor.getString(6);
        String female_doctors= cursor.getString(7);
        String patient_doctor_ratio= cursor.getString(8);
        String male_nurse= cursor.getString(9);
        String female_nurse= cursor.getString(10);
        String patient_nurse_ratio= cursor.getString(11);
        String lon= cursor.getString(12);
        String lat= cursor.getString(13);
        String node_id= cursor.getString(14);
        String floor= cursor.getString(15);
        String house_name= cursor.getString(16);
        String house_no= cursor.getString(17);
        String road= cursor.getString(18);
        String line= cursor.getString(19);
        String avenue= cursor.getString(20);
        String block= cursor.getString(21);
        String area= cursor.getString(22);
        String landmark= cursor.getString(23);
        String post_office= cursor.getString(24);
        String police_station= cursor.getString(25);
        String city= cursor.getString(26);
        String country= cursor.getString(27);
        String node_contact= cursor.getString(28);
        String node_contact2= cursor.getString(29);
        String node_website= cursor.getString(30);
        String node_facebook= cursor.getString(31);
        String node_designation= cursor.getString(32);
        String address= cursor.getString(33);
        String opening_time= cursor.getString(34);
        String break_time= cursor.getString(35);
        String closing_time= cursor.getString(36);
        String off_day= cursor.getString(37);
        String node_registered_with= cursor.getString(38);
        String node_registered_number= cursor.getString(39);
        String updated_drugs= cursor.getString(40);
        String num_trained_pharmacist= cursor.getString(41);
        String doctor_available= cursor.getString(42);
        String pharmacy_speciality= cursor.getString(43);
        String pharmacy_free= cursor.getString(44);
        String free_service_for= cursor.getString(45);
        String pharmacy_free_service= cursor.getString(46);
        String pharmacy_fee= cursor.getString(47);
        String pharmacy_remarks= cursor.getString(48);
        String pharmacy_privacy= cursor.getString(49);
        String quality_equipments= cursor.getString(50);
        String general_free= cursor.getString(51);
        String general_free_for= cursor.getString(52);
        String general_free_services= cursor.getString(53);
        String general_cost= cursor.getString(54);
        String general_remark= cursor.getString(55);
        String emergency_free= cursor.getString(56);
        String emergency_free_for= cursor.getString(57);
        String emergency_free_services= cursor.getString(58);
        String emergency_cost= cursor.getString(59);
        String emergency_remark= cursor.getString(60);
        String ambulance_free= cursor.getString(61);
        String ambulance_free_for= cursor.getString(62);
        String ambulance_free_services= cursor.getString(63);
        String ambulance_cost= cursor.getString(64);
        String ambulance_remark= cursor.getString(65);
        String maternity_free= cursor.getString(66);
        String maternity_free_for= cursor.getString(67);
        String maternity_free_services= cursor.getString(68);
        String maternity_cost= cursor.getString(69);
        String maternity_remark= cursor.getString(70);
        String maternity_complication= cursor.getString(71);
        String maternity_privacy= cursor.getString(72);
        String maternity_newborn_care= cursor.getString(73);
        String family_services= cursor.getString(74);
        String family_contraceptive_available= cursor.getString(75);
        String family_contraceptive= cursor.getString(76);
        String family_privacy= cursor.getString(77);
        String category= cursor.getString(78);
        String references=cursor.getString(79);


        return new HealthServiceProviderItemNew(
                id,
                node_name,
                node_bn,
                institute_type,
                spoken_lang,
                capacity,
                male_doctors,
                female_doctors,
                patient_doctor_ratio,
                male_nurse,
                female_nurse,
                patient_nurse_ratio,
                lon,
                lat,
                node_id,
                floor,
                house_name,
                house_no,
                road,
                line,
                avenue,
                block,
                area,
                landmark,
                post_office,
                police_station,
                city,
                country,
                node_contact,
                node_contact2,
                node_website,
                node_facebook,
                node_designation,
                address,
                opening_time,
                break_time,
                closing_time,
                off_day,
                node_registered_with,
                node_registered_number,
                updated_drugs,
                num_trained_pharmacist,
                doctor_available,
                pharmacy_speciality,
                pharmacy_free,
                free_service_for,
                pharmacy_free_service,
                pharmacy_fee,
                pharmacy_remarks,
                pharmacy_privacy,
                quality_equipments,
                general_free,
                general_free_for,
                general_free_services,
                general_cost,
                general_remark,
                emergency_free,
                emergency_free_for,
                emergency_free_services,
                emergency_cost,
                emergency_remark,
                ambulance_free,
                ambulance_free_for,
                ambulance_free_services,
                ambulance_cost,
                ambulance_remark,
                maternity_free,
                maternity_free_for,
                maternity_free_services,
                maternity_cost,
                maternity_remark,
                maternity_complication,
                maternity_privacy,
                maternity_newborn_care,
                family_services,
                family_contraceptive_available,
                family_contraceptive,
                family_privacy,
                category,
                references);
    }

}
