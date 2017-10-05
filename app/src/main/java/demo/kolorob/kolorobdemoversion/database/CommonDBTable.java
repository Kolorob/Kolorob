package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by shamima.yasmin on 9/21/2017.
 */

public class CommonDBTable extends BaseDBTable <CommonModel> {

    //  DB configuration

    private static final String TAG = CommonDBTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.COMMON_DB;

    //  name and id

    private static final String KEY_IDENTIFIER_ID = "_id"; // 0 -integer
    private static final String KEY_NAME_ENG = "_nameen"; //
    private static final String KEY_NAME_BAN = "_namebn"; //

    //  contact info

    private static final String KEY_HOUSE_NO = "_houseno"; //
    private static final String KEY_BLOCK = "_block"; //
    private static final String KEY_ROAD = "_road"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_AREABN = "_areabn"; //
    private static final String KEY_PARENT_AREA = "_parentarea";//
    private static final String KEY_WARD = "_ward";
    private static final String KEY_POLICE_STATION = "_policestation"; //
    private static final String KEY_CONTACT_NO = "_node_contact"; //
    private static final String KEY_EMAIL_ADDRESS = "_node_email"; //
    private static final String KEY_OTHER_INFO = "_other"; //

    //  timing info

    private static final String KEY_OPENTIME = "_opentime"; //
    private static final String KEY_CLOSETIME = "_closetime"; //
    private static final String KEY_OFF_DAY = "_offday";

    //  mapping info

    private static final String KEY_LATITUDE = "_lat"; //
    private static final String KEY_LONGITUDE = "_lon"; //

    //  Other common details

    private static final String KEY_CATEGORY_ID = "_catid";
    private static final String KEY_SUBCATEGORY_ID = "_sref";
    private static final String KEY_REFERENCE = "_refnumm";
    private static final String KEY_RATINGS = "_rating";



    public CommonDBTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , "
                + KEY_NAME_ENG + "  TEXT, "
                + KEY_NAME_BAN + " TEXT, "

                + KEY_HOUSE_NO + " TEXT, "
                + KEY_BLOCK + " TEXT, "
                + KEY_ROAD + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_AREABN + " TEXT, "
                + KEY_PARENT_AREA + " TEXT, "
                + KEY_WARD + " TEXT, "
                + KEY_POLICE_STATION + " TEXT, "
                + KEY_CONTACT_NO + " TEXT, "
                + KEY_EMAIL_ADDRESS + " TEXT, "
                + KEY_OTHER_INFO + " TEXT, "

                + KEY_OPENTIME + " TEXT, "
                + KEY_CLOSETIME + " TEXT, "
                + KEY_OFF_DAY + " TEXT, "

                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "

                + KEY_CATEGORY_ID + " INTEGER, "
                + KEY_SUBCATEGORY_ID + " TEXT, "
                + KEY_REFERENCE+ " TEXT, "
                + KEY_RATINGS + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(CommonModel commonModel) {
        if (!isFieldExist(commonModel.getId())) {
            return insertItem(commonModel.getId(), commonModel.getNameEn(), commonModel.getNameBn(),
                commonModel.getHouseNo(), commonModel.getBlock(), commonModel.getRoad(), commonModel.getArea(), commonModel.getAreaBn(), commonModel.getParentArea(), commonModel.getWard(), commonModel.getPoliceStation(),
                commonModel.getNodeContact(), commonModel.getNodeEmail(), commonModel.getOtherInfo(),
                commonModel.getOpeningTime(), commonModel.getClosingTime(), commonModel.getOffDay(),
                commonModel.getLat(), commonModel.getLon(),
                commonModel.getCategoryId(), commonModel.getSubcat(), commonModel.getRefNum(), commonModel.getRatings()
            );
        }
        else
            return updateItem(commonModel.getId(), commonModel.getNameEn(), commonModel.getNameBn(),
                commonModel.getHouseNo(), commonModel.getBlock(), commonModel.getRoad(), commonModel.getArea(), commonModel.getAreaBn(), commonModel.getParentArea(), commonModel.getWard(), commonModel.getPoliceStation(),
                commonModel.getNodeContact(), commonModel.getNodeEmail(), commonModel.getOtherInfo(),
                commonModel.getOpeningTime(), commonModel.getClosingTime(), commonModel.getOffDay(),
                commonModel.getLat(), commonModel.getLon(),
                commonModel.getCategoryId(), commonModel.getSubcat(), commonModel.getRefNum(), commonModel.getRatings()
                );

    }


    public long insertItem(int id, String nameEn, String nameBn,
                           String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo,
                           String openingTime, String closingTime, String offDay,
                           String lat, String lon,
                           int catId, String subCat, String refNum, String ratings)
    {
        if (isFieldExist(id)) {
            return updateItem(
                    id, nameEn, nameBn,
                    houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo,
                    openingTime, closingTime, offDay,
                    lat, lon,
                    catId, subCat, refNum, ratings
            );
        }
        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_NAME_ENG, nameEn);
        rowValue.put(KEY_NAME_BAN, nameBn);

        rowValue.put(KEY_HOUSE_NO, houseNo);
        rowValue.put(KEY_BLOCK, block);
        rowValue.put(KEY_ROAD, road);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_AREABN, areaBn);
        rowValue.put(KEY_PARENT_AREA, parentArea);
        rowValue.put(KEY_WARD, ward);
        rowValue.put(KEY_POLICE_STATION, policeStation);
        rowValue.put(KEY_CONTACT_NO, nodeContact);
        rowValue.put(KEY_EMAIL_ADDRESS, nodeEmail);
        rowValue.put(KEY_OTHER_INFO, otherInfo);

        rowValue.put(KEY_OPENTIME, openingTime);
        rowValue.put(KEY_CLOSETIME, closingTime);
        rowValue.put(KEY_OFF_DAY, offDay);

        rowValue.put(KEY_LATITUDE, lat);
        rowValue.put(KEY_LONGITUDE, lon);

        rowValue.put(KEY_CATEGORY_ID, catId);
        rowValue.put(KEY_SUBCATEGORY_ID, subCat);
        rowValue.put(KEY_REFERENCE, refNum);
        rowValue.put(KEY_RATINGS, ratings);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return insertedId;
    }
    private long updateItem(
            int id, String nameEn, String nameBn,
            String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo,
            String openingTime, String closingTime, String offDay,
            String lat, String lon,
            int catId, String subCat, String refNum, String ratings
    ) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_NAME_ENG, nameEn);
        rowValue.put(KEY_NAME_BAN, nameBn);

        rowValue.put(KEY_HOUSE_NO, houseNo);
        rowValue.put(KEY_BLOCK, block);
        rowValue.put(KEY_ROAD, road);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_AREABN, areaBn);
        rowValue.put(KEY_PARENT_AREA, parentArea);
        rowValue.put(KEY_WARD, ward);
        rowValue.put(KEY_POLICE_STATION, policeStation);
        rowValue.put(KEY_CONTACT_NO, nodeContact);
        rowValue.put(KEY_EMAIL_ADDRESS, nodeEmail);
        rowValue.put(KEY_OTHER_INFO, otherInfo);

        rowValue.put(KEY_OPENTIME, openingTime);
        rowValue.put(KEY_CLOSETIME, closingTime);
        rowValue.put(KEY_OFF_DAY, offDay);

        rowValue.put(KEY_LATITUDE, lat);
        rowValue.put(KEY_LONGITUDE, lon);

        rowValue.put(KEY_CATEGORY_ID, catId);
        rowValue.put(KEY_SUBCATEGORY_ID, subCat);
        rowValue.put(KEY_REFERENCE, refNum);
        rowValue.put(KEY_RATINGS, ratings);


        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});

        closeDB();

        return updatedId;
    }

    public void delete(String ward,String area)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(CommonDBTable.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_WARD + " = '" + ward + "' AND " + "(" + KEY_AREA + " = '" + area + "')" + " OR " + "(" + KEY_PARENT_AREA + "  =  '" + area + "')", null);

        database.close();
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public boolean isFieldExist(int id) {
       return super.isFieldExist(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <CommonModel> getAllCommonByArea(String ward, String place) {

        ArrayList <CommonModel> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " +  TABLE_NAME + " WHERE " + KEY_WARD + " = '"+ ward + "' AND " + "(" + KEY_AREA + "  = '" + place + "')" + " OR " + "(" + KEY_PARENT_AREA + "  =  '" + place + "')", null);

        if (cursor.moveToFirst()) {
            do {

                list.add(cursorToModel(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }

    public ArrayList <CommonModel> getAllCommonByAreaCategory(String ward, String place, int category) {

        ArrayList <CommonModel> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " +  TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + " = " + category + " AND " + KEY_WARD + " = '"+ ward + "' AND " + "(" + KEY_AREA + "  = '" + place  + "' OR "  + KEY_PARENT_AREA + "  =  '" + place + "')", null);

        if (cursor.moveToFirst()) {
            do {

                list.add(cursorToModel(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }

    public ArrayList <CommonModel> getDataListFromId(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <CommonModel> getAllData(){
        return super.getAllData(TABLE_NAME);
    }

    public CommonModel getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }


    public CommonModel getNodeInfo(int Node) {

        Log.e(" ", "GetNodeInfo common " + Node);
        SQLiteDatabase db = openDB();
        CommonModel commonModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + Node , null);

        if (cursor.moveToFirst()) {
            do {
                commonModel = new CommonModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getString(14), cursor.getString(15), cursor.getString(16),
                        cursor.getString(17), cursor.getString(18),
                        cursor.getInt(19), cursor.getString(20), cursor.getString(21), cursor.getString(22));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return commonModel;
    }


    public CommonModel cursorToModel(Cursor cursor) {

        int _id = cursor.getInt(0);
        String _nameEn = cursor.getString(1);
        String _nameBn = cursor.getString(2);

        String _houseNo = cursor.getString(3);
        String _block = cursor.getString(4);
        String _road = cursor.getString(5);
        String _area = cursor.getString(6);
        String _areaBn = cursor.getString(7);
        String _parentArea = cursor.getString(8);
        String _ward = cursor.getString(9);
        String _policeStation = cursor.getString(10);
        String _nodeContact = cursor.getString(11);
        String _nodeEmail = cursor.getString(12);
        String _otherInfo = cursor.getString(13);

        String _openingTime = cursor.getString(14);
        String _closingTime = cursor.getString(15);
        String _offDay = cursor.getString(16);

        String _lat = cursor.getString(17);
        String _lon = cursor.getString(18);

        int _catId = cursor.getInt(19);
        String _subCat = cursor.getString(20);
        String _refNum = cursor.getString(21);
        String _ratings = cursor.getString(22);


        return new CommonModel(_id, _nameEn, _nameBn,
                _houseNo, _block, _road, _area, _areaBn, _parentArea, _ward, _policeStation, _nodeContact, _nodeEmail, _otherInfo,
                _openingTime, _closingTime, _offDay,
                _lat, _lon,
                _catId, _subCat, _refNum, _ratings);

    }

    public CommonModel cursorToModel(Cursor cursor, CommonModel commonModel){
        return null;
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
