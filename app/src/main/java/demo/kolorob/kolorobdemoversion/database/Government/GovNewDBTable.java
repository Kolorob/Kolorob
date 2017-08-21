package demo.kolorob.kolorobdemoversion.database.Government;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2017.
 */
public class GovNewDBTable {
    private static final String TAG = GovNewDBTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.GOV_NEW;
    private static final String KEY_IDENTIFIER_ID = "_govid"; // 0 -integer
    private static final String KEY_NAME_ENG = "_nameen"; //
    private static final String KEY_NAME_BAN = "_namebn"; //
    private static final String KEY_SERVICE_NAME = "_service_name"; // 1 - text
    private static final String KEY_LATITUDE = "_lat"; //
    private static final String KEY_LONGITUDE = "_lon"; //
    private static final String KEY_HOUSE_NO = "_houseno"; //
    private static final String KEY_BLOCK = "_block"; //
    private static final String KEY_AREA = "_area"; //
    private static final String KEY_POLICE_STATION = "_policestation"; //
    private static final String KEY_EMAIL_ADDRESS = "_node_email"; //
    private static final String KEY_WARD = "_ward";
    private static final String KEY_ROAD = "_road"; //
    private static final String KEY_CONTACT_NO = "_node_contact"; //
    private static final String KEY_OTHER_INFO = "_other"; //
    private static final String KEY_AREABN = "_areabn"; //
    private static final String KEY_PARENT_AREA = "_parentarea";//
    private static final String KEY_OPENTIME = "_opentime"; //
    private static final String KEY_CLOSEATIME = "_closetime"; //
    private static final String KEY_OFF_DAY = "_offday";
    private static final String KEY_CATEGORY_ID = "_catid";
    private static final String KEY_REFERENCE = "_refnumm"; // 2 - text*/
    private static final String KEY_RATINGS = "_rating"; // 2 - text*/
    private static final String KEY_SUBCATEGORY_ID = "_sref"; // 2 - text*/




    private Context tContext;

    public GovNewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_NAME_ENG + "  TEXT, "              // 1 - text
                + KEY_NAME_BAN + " TEXT, "
                + KEY_SERVICE_NAME + " TEXT, "// 2 - text
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT, "
                + KEY_HOUSE_NO + " TEXT, "
                + KEY_BLOCK + " TEXT, "
                + KEY_AREA + " TEXT, "
                + KEY_POLICE_STATION + " TEXT, "
                + KEY_EMAIL_ADDRESS + " TEXT, "
                + KEY_WARD + " TEXT, "
                + KEY_ROAD + " TEXT, "
                + KEY_CONTACT_NO + " TEXT, "
                + KEY_OTHER_INFO + " TEXT, "
                + KEY_AREABN + " TEXT, "
                + KEY_PARENT_AREA + " TEXT, "
                + KEY_OPENTIME + " TEXT, "
                + KEY_CLOSEATIME + " TEXT, "
                + KEY_OFF_DAY + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER, "
                + KEY_REFERENCE+ " TEXT, "
                + KEY_RATINGS + " TEXT, "
                + KEY_SUBCATEGORY_ID + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(GovernmentNewDBModel governmentNewDBModel) {
        if (!isFieldExist(governmentNewDBModel.getGovid())) {
            return insertItem(governmentNewDBModel.getGovid(), governmentNewDBModel.getNameen(), governmentNewDBModel.getNamebn(),
                    governmentNewDBModel.getServicename(), governmentNewDBModel.getLat(), governmentNewDBModel.getLon(), governmentNewDBModel.getHouseno(),
                    governmentNewDBModel.getBlock(), governmentNewDBModel.getArea(), governmentNewDBModel.getPolicestation(), governmentNewDBModel.getNode_email(),
                    governmentNewDBModel.getWard(), governmentNewDBModel.getRoad(),
                    governmentNewDBModel.getNode_contact(), governmentNewDBModel.getOtherinfo(), governmentNewDBModel.getAreabn(), governmentNewDBModel.getParent_area(),
                    governmentNewDBModel.getOpeningtime(), governmentNewDBModel.getClosetime(), governmentNewDBModel.getOffday(), governmentNewDBModel.getCategoryId(),
                    governmentNewDBModel.getRefnumm(), governmentNewDBModel.getRatings(), governmentNewDBModel.getSubcat()
            );
        }
        else {
            return updateItem(governmentNewDBModel.getGovid(),governmentNewDBModel.getNameen(),governmentNewDBModel.getNamebn(),
                    governmentNewDBModel.getServicename(),governmentNewDBModel.getLat(),governmentNewDBModel.getLon(),governmentNewDBModel.getHouseno(),
                    governmentNewDBModel.getBlock(),governmentNewDBModel.getArea(),governmentNewDBModel.getPolicestation(),governmentNewDBModel.getNode_email(),
                    governmentNewDBModel.getWard(),governmentNewDBModel.getRoad(),
                    governmentNewDBModel.getNode_contact(),governmentNewDBModel.getOtherinfo(),governmentNewDBModel.getAreabn(), governmentNewDBModel.getParent_area(),
                    governmentNewDBModel.getOpeningtime(),governmentNewDBModel.getClosetime(),governmentNewDBModel.getOffday(),governmentNewDBModel.getCategoryId(),
                    governmentNewDBModel.getRefnumm(),governmentNewDBModel.getRatings(),governmentNewDBModel.getSubcat()
            );
        }
    }




    public long insertItem(int govid, String nameen, String namebn, String servicename, String lat,
                           String lon, String houseno, String block, String area, String policestation,
                           String node_email, String ward, String road, String node_contact, String otherinfo,String areabn, String parentarea,
                           String openingtime, String closetime, String offday, int categoryId, String refnumm, String ratings,
                           String subcat
    ) {
        if (isFieldExist(govid)) {
            return updateItem(
                    govid,
                    nameen,
                    namebn,
                    servicename,
                    lat,
                    lon,
                    houseno,
                    block,
                    area,
                    policestation,
                    node_email,
                    ward,
                    road,
                    node_contact,
                    otherinfo,areabn, parentarea,
                    openingtime,
                    closetime,
                    offday,categoryId,
                    refnumm,
                    ratings,
                    subcat
                    );
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, govid);
        rowValue.put(KEY_NAME_ENG, nameen);
        rowValue.put(KEY_NAME_BAN, namebn);
        rowValue.put(KEY_SERVICE_NAME, servicename);
        rowValue.put(KEY_LATITUDE, lat);
        rowValue.put(KEY_LONGITUDE, lon);

        rowValue.put(KEY_HOUSE_NO, houseno);
        rowValue.put(KEY_BLOCK, block);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_POLICE_STATION, policestation);
        rowValue.put(KEY_EMAIL_ADDRESS, node_email);
        rowValue.put(KEY_WARD, ward);
        rowValue.put(KEY_ROAD, road);
        rowValue.put(KEY_CONTACT_NO, node_contact);
        rowValue.put(KEY_OTHER_INFO, otherinfo);
        rowValue.put(KEY_AREABN, areabn);
        rowValue.put(KEY_PARENT_AREA, parentarea);
        rowValue.put(KEY_OPENTIME, openingtime);
        rowValue.put(KEY_CLOSEATIME, closetime);
        rowValue.put(KEY_OFF_DAY, offday);
        rowValue.put(KEY_CATEGORY_ID  , categoryId);
        rowValue.put(KEY_REFERENCE  , refnumm);
        rowValue.put(KEY_RATINGS  , ratings);
        rowValue.put(KEY_SUBCATEGORY_ID  , subcat );

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return ret;
    }
    private long updateItem(
            int govid, String nameen, String namebn, String servicename, String lat,
            String lon, String houseno, String block, String area, String policestation,
            String node_email, String ward, String road, String node_contact, String otherinfo,String areabn, String parentarea,
            String openingtime, String closetime, String offday, int categoryId, String refnumm, String ratings,
            String subcat
    ) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, govid);
        rowValue.put(KEY_NAME_ENG, nameen);
        rowValue.put(KEY_NAME_BAN, namebn);
        rowValue.put(KEY_SERVICE_NAME, servicename);
        rowValue.put(KEY_LATITUDE, lat);
        rowValue.put(KEY_LONGITUDE, lon);

        rowValue.put(KEY_HOUSE_NO, houseno);
        rowValue.put(KEY_BLOCK, block);
        rowValue.put(KEY_AREA, area);
        rowValue.put(KEY_POLICE_STATION, policestation);
        rowValue.put(KEY_EMAIL_ADDRESS, node_email);
        rowValue.put(KEY_WARD, ward);
        rowValue.put(KEY_ROAD, road);
        rowValue.put(KEY_CONTACT_NO, node_contact);
        rowValue.put(KEY_OTHER_INFO, otherinfo);
        rowValue.put(KEY_AREABN, areabn);
        rowValue.put(KEY_PARENT_AREA, parentarea);
        rowValue.put(KEY_OPENTIME, openingtime);
        rowValue.put(KEY_CLOSEATIME, closetime);
        rowValue.put(KEY_OFF_DAY, offday);
        rowValue.put(KEY_CATEGORY_ID  , categoryId);
        rowValue.put(KEY_REFERENCE  , refnumm);
        rowValue.put(KEY_RATINGS  , ratings);
        rowValue.put(KEY_SUBCATEGORY_ID  , subcat );

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{govid + ""});
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id==cursor.getInt(0)) {
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




    public GovernmentNewDBModel getgovNode2(int Node) {

        SQLiteDatabase db = openDB();
        GovernmentNewDBModel governmentNewItem=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_IDENTIFIER_ID+"="+Node, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                governmentNewItem=new GovernmentNewDBModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11),cursor.getString(12),cursor.getString(13),
                        cursor.getString(14),cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getString(19),
                        cursor.getInt(20),cursor.getString(21),cursor.getString(22),cursor.getString(23));
            }  while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return governmentNewItem;
    }

    private GovernmentNewDBModel cursorToSubCatList(Cursor cursor) {
        int _govid = cursor.getInt(0);
        String _nameen = cursor.getString(1);
        String _namebn = cursor.getString(2);
        String _service_name = cursor.getString(3);
        String _lat = cursor.getString(4);
        String _lon = cursor.getString(5);
        String _houseno = cursor.getString(6);
        String _block = cursor.getString(7);
        String _area = cursor.getString(8);
        String _policestation = cursor.getString(9);
        String _node_email = cursor.getString(10);
        String _ward = cursor.getString(11);
        String _road = cursor.getString(12);
        String _node_contact = cursor.getString(13);
        String _other = cursor.getString(14);
        String _areabn = cursor.getString(15);
        String _parentarea = cursor.getString(16);
        String _opentime = cursor.getString(17);
        String _closetime = cursor.getString(18);
        String _offday  = cursor.getString(19);
        int _catid=cursor.getInt(20);
        String _refnumm=cursor.getString(21);
        String _rating=cursor.getString(22);
        String _sref=cursor.getString(23);


       return new GovernmentNewDBModel(_govid,_nameen,_namebn,_service_name,
                _lat, _lon,_houseno,_block,_area,_policestation,_node_email,_ward,_road,_node_contact,_other,_areabn, _parentarea,
               _opentime,  _closetime,_offday,_catid,_refnumm,_sref,_rating);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
    public ArrayList<GovernmentNewDBModel> getAllGov(int id, String place) {
        ArrayList<GovernmentNewDBModel> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM "+  TABLE_NAME + " WHERE "+KEY_WARD + " = "+ id + " AND "+"("+KEY_AREA +"  = '"+ place + "')"+" OR "+"("+KEY_PARENT_AREA +"  =  '"+ place + "')", null);

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

    public void delete(String ward,String area)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(GovNewDBTable.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_WARD + "=" + ward + " AND "+"("+KEY_AREA +"  = '"+ area + "')"+" OR "+"("+KEY_PARENT_AREA +"  =  '"+ area + "')", null);
        database.close();
    }
}
