package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.model.CommonModel;


/**
 * Created by shamima.yasmin on 9/22/2017.
 * Methods are originally written by: Israt Mity and Arafat
 */


public abstract class BaseDBTable <ModelType extends CommonModel>  {

    //  name and id

    protected static final String KEY_IDENTIFIER_ID = "_id"; // 0 -integer
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


    protected Context tContext;

    public abstract void createTable();
    public abstract void insertItem(ContentValues rowValue, ModelType model);
    public abstract void updateItem(ContentValues rowValue, ModelType model);
    public abstract ModelType cursorToModel(Cursor cursor, CommonModel commonModel);
    public abstract boolean isFieldExist(int id);
    public abstract void delete(int id);
    public abstract void dropTable();



    protected SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    protected void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }


    protected String createTableQuery(String TABLE_NAME){
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER PRIMARY KEY, "
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
                + KEY_RATINGS + " TEXT, ";


        return CREATE_TABLE_SQL;
    }


    protected boolean isFieldExist(int id, String TABLE_NAME) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + id, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        closeDB();

        return exists;
    }


    public long insertItem(ModelType model, String TABLE_NAME) {
        if (!isFieldExist(model.getId())) {
            return insertItem(TABLE_NAME, model, model.getId(), model.getNameEn(), model.getNameBn(),
                    model.getHouseNo(), model.getBlock(), model.getRoad(), model.getArea(), model.getAreaBn(), model.getParentArea(), model.getWard(), model.getPoliceStation(), model.getNodeContact(), model.getNodeEmail(), model.getOtherInfo(),
                    model.getOpeningTime(), model.getClosingTime(), model.getOffDay(),
                    model.getLat(), model.getLon(),
                    model.getCategoryId(), model.getSubcat(), model.getRefNum(), model.getRatings());
        }
        else {
            return updateItem(TABLE_NAME, model, model.getId(), model.getNameEn(), model.getNameBn(),
                    model.getHouseNo(), model.getBlock(), model.getRoad(), model.getArea(), model.getAreaBn(), model.getParentArea(), model.getWard(), model.getPoliceStation(), model.getNodeContact(), model.getNodeEmail(), model.getOtherInfo(),
                    model.getOpeningTime(), model.getClosingTime(), model.getOffDay(),
                    model.getLat(), model.getLon(),
                    model.getCategoryId(), model.getSubcat(), model.getRefNum(), model.getRatings());
        }
    }



    public long insertItem(String TABLE_NAME, ModelType model, int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings) {

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

        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_SUBCATEGORY_ID, subcat);
        rowValue.put(KEY_REFERENCE, refNum);
        rowValue.put(KEY_RATINGS, ratings);

        insertItem(rowValue, model);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return insertedId;
    }


    private long updateItem(String TABLE_NAME, ModelType model, int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings) {

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

        rowValue.put(KEY_CATEGORY_ID, categoryId);
        rowValue.put(KEY_SUBCATEGORY_ID, subcat);
        rowValue.put(KEY_REFERENCE, refNum);
        rowValue.put(KEY_RATINGS, ratings);

        updateItem(rowValue, model);


        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return updatedId;
    }

    protected void delete(int id, String TABLE_NAME){
        DatabaseHelper databaseHelper = new DatabaseHelper(tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_IDENTIFIER_ID + " = " + id, null);

        database.close();
    }



    protected CommonModel cursorToModel(Cursor cursor) {

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



    public ArrayList <ModelType> getByAreaCategory(String ward, String place, int category, String TABLE_NAME) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " +  TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + " = " + category + " AND " + KEY_WARD + " = '"+ ward + "' AND " + "(" + KEY_AREA + "  = '" + place  + "' OR "  + KEY_PARENT_AREA + "  =  '" + place + "')", null);

        if (cursor.moveToFirst()) {
            do {

                list.add((ModelType)cursorToModel(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }


    public ModelType getNodeInfo(int nodeId, String TABLE_NAME){

        ModelType model = null;
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + nodeId, null);

        if (cursor.moveToFirst()) {
            do {
                model = (ModelType)cursorToModel(cursor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return model;
    }



    protected ArrayList <ModelType> getDataListFromId(int nodeId, String TABLE_NAME) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + nodeId, null);

        if (cursor.moveToFirst()) {
            do {
                list.add((ModelType)cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }

    protected ArrayList <ModelType> getAllData(String TABLE_NAME) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                list.add((ModelType) cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }


    protected void dropTable(String TABLE_NAME) {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        closeDB();
    }

}
