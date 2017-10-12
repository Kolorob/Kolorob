package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;


/**
 * Created by shamima.yasmin on 9/21/2017.
 */


public abstract class CommonDBTable <ModelType extends CommonModel> extends BaseDBTable <ModelType> {

    //  name and id


    private static final String KEY_NAME_ENG = "name_en";
    private static final String KEY_NAME_BAN = "name_bn";

    //  contact info

    private static final String KEY_HOUSE_NO = "house_no";
    private static final String KEY_BLOCK = "block";
    private static final String KEY_ROAD = "road";
    private static final String KEY_AREA = "area";
    private static final String KEY_AREABN = "area_bn";
    private static final String KEY_PARENT_AREA = "parent_area";
    private static final String KEY_WARD = "ward";
    private static final String KEY_POLICE_STATION = "police_station";
    private static final String KEY_CONTACT_NO = "node_contact";
    private static final String KEY_EMAIL_ADDRESS = "node_email";
    private static final String KEY_OTHER_INFO = "other_info";

    //  timing info

    private static final String KEY_OPENTIME = "opening_time"; //
    private static final String KEY_CLOSETIME = "closing_time"; //
    private static final String KEY_OFF_DAY = "off_day";

    //  mapping info

    private static final String KEY_LATITUDE = "lat"; //
    private static final String KEY_LONGITUDE = "lon"; //

    //  Other common details

    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_SUBCATEGORY_ID = "subcat_id";
    private static final String KEY_REFERENCE = "references";
    private static final String KEY_RATINGS = "rating";


    public abstract void insertItem(ContentValues rowValue, ModelType model);
    public abstract void updateItem(ContentValues rowValue, ModelType model);
    public abstract ArrayList <ModelType> getByAreaCategory(String ward, String area, int category);


    public CommonDBTable(Context context) {
        super(context);
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


    public long updateItem(ModelType model, String TABLE_NAME){
        return updateItem(TABLE_NAME, model, model.getId(), model.getNameEn(), model.getNameBn(),
                model.getHouseNo(), model.getBlock(), model.getRoad(), model.getArea(), model.getAreaBn(), model.getParentArea(), model.getWard(), model.getPoliceStation(), model.getNodeContact(), model.getNodeEmail(), model.getOtherInfo(),
                model.getOpeningTime(), model.getClosingTime(), model.getOffDay(),
                model.getLat(), model.getLon(),
                model.getCategoryId(), model.getSubcat(), model.getRefNum(), model.getRatings());
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



    public ArrayList <ModelType> getByAreaCategory(String ward, String area, int category, String TABLE_NAME) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " +  TABLE_NAME + " WHERE " + KEY_CATEGORY_ID + " = " + category + " AND " + KEY_WARD + " = '"+ ward + "' AND " + "(" + KEY_AREA + "  = '" + area  + "' OR "  + KEY_PARENT_AREA + "  =  '" + area + "')", null);

        if (cursor.moveToFirst()) {
            do {

                list.add(cursorToModel(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }


    public void delete(String ward, String area, String TABLE_NAME) {
        DatabaseHelper databaseHelper = new DatabaseHelper(CommonDBTable.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_WARD + " = '" + ward + "' AND " + "(" + KEY_AREA + " = '" + area + "')" + " OR " + "(" + KEY_PARENT_AREA + "  =  '" + area + "')", null);

        database.close();
    }

    public void delete(int id, String TABLE_NAME){
        super.delete(id, TABLE_NAME);
    }

    public boolean isFieldExist(int id, String TABLE_NAME) {
       return super.isFieldExist(id, TABLE_NAME);
    }

    public ArrayList <ModelType> getByArea(String ward, String area, String TABLE_NAME) {

        ArrayList <ModelType> list = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery ("SELECT * FROM " +  TABLE_NAME + " WHERE " + KEY_WARD + " = '"+ ward + "' AND " + "(" + KEY_AREA + "  = '" + area + "')" + " OR " + "(" + KEY_PARENT_AREA + "  =  '" + area + "')", null);

        if (cursor.moveToFirst()) {
            do {

                list.add(cursorToModel(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return list;
    }



    protected ArrayList <ModelType> getDataListFromId(int id, String TABLE_NAME, String KEY){
        return super.getDataListFromId(id, TABLE_NAME, KEY);
    }

    public ArrayList <ModelType> getAllData(String TABLE_NAME){
        return super.getAllData(TABLE_NAME);
    }


    public ModelType getNodeInfo(int node, String TABLE_NAME) {

        SQLiteDatabase db = openDB();
        CommonModel commonModel = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_IDENTIFIER_ID + " = " + node , null);

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
        return (commonModel instanceof CommonModel) ? (ModelType) commonModel : null;
    }


    public ModelType cursorToModel(Cursor cursor) {

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


        return (ModelType) new CommonModel(_id, _nameEn, _nameBn,
                _houseNo, _block, _road, _area, _areaBn, _parentArea, _ward, _policeStation, _nodeContact, _nodeEmail, _otherInfo,
                _openingTime, _closingTime, _offDay,
                _lat, _lon,
                _catId, _subCat, _refNum, _ratings);

    }


    public void dropTable(String TABLE_NAME) {
        super.dropTable(TABLE_NAME);
    }
}
