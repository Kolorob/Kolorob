package demo.kolorob.kolorobdemoversion.database.NGO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;

import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by zahid on 2/8/2017.
 */
public class NGONewDBTable {
    private static final String TAG = NGONewDBTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.NGONEWDBTABLE;

    private static final String KEY_IDENTIFIER_ID = "_id"; // 0 -integer
    private static final String KEY_NGO_NAME_ENG = "_nameen"; //
    private static final String KEY_NGO_NAME_BN = "_namebn"; //

    private static final String KEY_NGO_SERVICES = "_services";
    private static final String KEY_NGO_SERVICES_FOR = "_services_for";
    private static final String KEY_NGO_SERVICES_OTHER = "_services_other";
    private static final String KEY_NGO_SERVICE_TYPE = "_service_type";
    private static final String KEY_DROP_TIME = "_drop_time";
    private static final String KEY_NGO_FEE = "_ngo_fee";

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
    private static final String KEY_NGO_REFERENCE = "_refnumm"; // 2 - text*/
    private static final String KEY_NGO_RATINGS = "_rating"; // 2 - text*/
    private static final String KEY_NGO_SUBCATEGORY_ID = "_sref"; // 2 - text*/




    private Context tContext;

    public NGONewDBTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_NGO_NAME_ENG + "  TEXT, "              // 1 - text
                + KEY_NGO_NAME_BN + " TEXT, "

                + KEY_NGO_SERVICES + " TEXT, "
                + KEY_NGO_SERVICES_FOR + " TEXT, "
                + KEY_NGO_SERVICES_OTHER + " TEXT, "
                + KEY_NGO_SERVICE_TYPE + " TEXT, "
                + KEY_DROP_TIME + " TEXT, "
                + KEY_NGO_FEE + " TEXT, "

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

                + KEY_PARENT_AREA + " TEXT, " /// parent area add

                + KEY_OPENTIME + " TEXT, "
                + KEY_CLOSEATIME + " TEXT, "
                + KEY_OFF_DAY + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER, "
                + KEY_NGO_REFERENCE+ " TEXT, "
                + KEY_NGO_RATINGS + " TEXT, "
                + KEY_NGO_SUBCATEGORY_ID + " TEXT, PRIMARY KEY(" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);

      /*  boolean newVersion = false;
        String new_column = KEY_PARENT_AREA;

        String ALTER_TABLE_SQL = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + new_column + " TEXT";

        Cursor c = db.rawQuery("PRAGMA table_info (" + TABLE_NAME + ")", null);

        if(c.moveToFirst()){
            do{
                if(c.getString(1).equals(new_column)){   // checking column name
                    newVersion = true;
                    break;
                }
            }while(c.moveToNext());
        }
        if(!newVersion){
            db.execSQL(ALTER_TABLE_SQL);
        }
        c.close();*/
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(NGONewDBModel ngoNewDBModel) {
        if (!isFieldExist(ngoNewDBModel.getNgoid())) {
            return insertItem(ngoNewDBModel.getNgoid(), ngoNewDBModel.getNameen(), ngoNewDBModel.getNamebn(),
                    ngoNewDBModel.getNgo_services(), ngoNewDBModel.getNgo_services_for(), ngoNewDBModel.getNgo_services_other(), ngoNewDBModel.getNgo_service_type(),
                    ngoNewDBModel.getDrop_time(), ngoNewDBModel.getNgo_fee(), ngoNewDBModel.getLat(), ngoNewDBModel.getLon(), ngoNewDBModel.getHouseno(),
                    ngoNewDBModel.getBlock(), ngoNewDBModel.getArea(), ngoNewDBModel.getPolicestation(), ngoNewDBModel.getNode_email(),
                    ngoNewDBModel.getWard(), ngoNewDBModel.getRoad(),
                    ngoNewDBModel.getNode_contact(), ngoNewDBModel.getOtherinfo(), ngoNewDBModel.getAreabn(),ngoNewDBModel.getParentarea(),
                    ngoNewDBModel.getOpeningtime(), ngoNewDBModel.getClosetime(), ngoNewDBModel.getOffday(), ngoNewDBModel.getCategoryId(),
                    ngoNewDBModel.getRefnumm(), ngoNewDBModel.getRatings(), ngoNewDBModel.getSubcat()
            );
        }
        else {
            return updateItem(ngoNewDBModel.getNgoid(), ngoNewDBModel.getNameen(), ngoNewDBModel.getNamebn(),
                    ngoNewDBModel.getNgo_services(), ngoNewDBModel.getNgo_services_for(), ngoNewDBModel.getNgo_services_other(), ngoNewDBModel.getNgo_service_type(), ngoNewDBModel.getDrop_time(), ngoNewDBModel.getNgo_fee(),
                    ngoNewDBModel.getLat(), ngoNewDBModel.getLon(), ngoNewDBModel.getHouseno(),
                    ngoNewDBModel.getBlock(), ngoNewDBModel.getArea(), ngoNewDBModel.getPolicestation(), ngoNewDBModel.getNode_email(),
                    ngoNewDBModel.getWard(), ngoNewDBModel.getRoad(),
                    ngoNewDBModel.getNode_contact(), ngoNewDBModel.getOtherinfo(), ngoNewDBModel.getAreabn(),ngoNewDBModel.getParentarea(),
                    ngoNewDBModel.getOpeningtime(), ngoNewDBModel.getClosetime(), ngoNewDBModel.getOffday(), ngoNewDBModel.getCategoryId(),
                    ngoNewDBModel.getRefnumm(), ngoNewDBModel.getRatings(), ngoNewDBModel.getSubcat()
            );
        }
    }




    public long insertItem(int ngoid, String nameen, String namebn, String ngo_services, String ngo_services_for, String ngo_services_other, String ngo_service_type, String drop_time, String ngo_fee, String lat,
                           String lon, String houseno, String block, String area, String policestation,
                           String node_email, String ward, String road, String node_contact, String otherinfo,String areabn,String parentarea,
                           String openingtime, String closetime, String offday, int categoryId, String refnumm, String ratings,
                           String subcat
    ) {
        if (isFieldExist(ngoid)) {
            return updateItem(
                    ngoid,
                    nameen,
                    namebn,

                    ngo_services,
                    ngo_services_for,
                    ngo_services_other,
                    ngo_service_type,
                    drop_time,
                    ngo_fee,

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
                    otherinfo,
                    areabn,

                    parentarea,

                    openingtime,
                    closetime,
                    offday,categoryId,
                    refnumm,
                    ratings,
                    subcat
            );
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, ngoid);
        rowValue.put(KEY_NGO_NAME_ENG, nameen);
        rowValue.put(KEY_NGO_NAME_BN, namebn);

        rowValue.put(KEY_NGO_SERVICES, ngo_services);
        rowValue.put(KEY_NGO_SERVICES_FOR, ngo_services_for);
        rowValue.put(KEY_NGO_SERVICES_OTHER, ngo_services_other);
        rowValue.put(KEY_NGO_SERVICE_TYPE, ngo_service_type);
        rowValue.put(KEY_DROP_TIME, drop_time);
        rowValue.put(KEY_NGO_FEE, ngo_fee);

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

        rowValue.put(KEY_PARENT_AREA,parentarea);

        rowValue.put(KEY_OPENTIME, openingtime);
        rowValue.put(KEY_CLOSEATIME, closetime);
        rowValue.put(KEY_OFF_DAY, offday);
        rowValue.put(KEY_CATEGORY_ID  , categoryId);
        rowValue.put(KEY_NGO_REFERENCE  , refnumm);
        rowValue.put(KEY_NGO_RATINGS  , ratings);
        rowValue.put(KEY_NGO_SUBCATEGORY_ID  , subcat );

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);


        closeDB();
        return ret;
    }
    private long updateItem(
            int ngoid, String nameen, String namebn, String ngo_services, String ngo_services_for, String ngo_services_other, String ngo_service_type, String drop_time, String ngo_fee,
            String lat, String lon, String houseno, String block, String area, String policestation,
            String node_email, String ward, String road, String node_contact, String otherinfo,String areabn,String parentarea,
            String openingtime, String closetime, String offday, int categoryId, String refnumm, String ratings,
            String subcat
    ) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, ngoid);
        rowValue.put(KEY_NGO_NAME_ENG, nameen);
        rowValue.put(KEY_NGO_NAME_BN, namebn);

        rowValue.put(KEY_NGO_SERVICES, ngo_services);
        rowValue.put(KEY_NGO_SERVICES_FOR, ngo_services_for);
        rowValue.put(KEY_NGO_SERVICES_OTHER, ngo_services_other);
        rowValue.put(KEY_NGO_SERVICE_TYPE, ngo_service_type);
        rowValue.put(KEY_DROP_TIME, drop_time);
        rowValue.put(KEY_NGO_FEE, ngo_fee);

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

        rowValue.put(KEY_PARENT_AREA,parentarea);

        rowValue.put(KEY_OPENTIME, openingtime);
        rowValue.put(KEY_CLOSEATIME, closetime);
        rowValue.put(KEY_OFF_DAY, offday);
        rowValue.put(KEY_CATEGORY_ID  , categoryId);
        rowValue.put(KEY_NGO_REFERENCE  , refnumm);
        rowValue.put(KEY_NGO_RATINGS  , ratings);
        rowValue.put(KEY_NGO_SUBCATEGORY_ID  , subcat );
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{ngoid + ""});
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







    public Vector<String> getAllNGOSubCategoriesInfo() {
        Vector<String> subCatList = new Vector<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        int cat_id=5;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                String  subCatLists = cursor.getString(cursor.getColumnIndex(KEY_NGO_NAME_ENG));

                subCatList.add(subCatLists);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }


    public ArrayList<String> getAllNGOSubCategoriesInfos() {
        ArrayList<String> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        int cat_id=5;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_CATEGORY_ID+"="+cat_id, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                String  subCatLists = cursor.getString(cursor.getColumnIndex(KEY_NGO_NAME_ENG));

                subCatList.add(subCatLists);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }




    public NGONewDBModel getngoNode2(int Node) {

        SQLiteDatabase db = openDB();
        NGONewDBModel ngoNewDBModel=null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_IDENTIFIER_ID+"="+Node, null);
        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                ngoNewDBModel = new NGONewDBModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                        cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18),
                        cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23),
                        cursor.getString(24), cursor.getInt(25), cursor.getString(26), cursor.getString(27), cursor.getString(28));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ngoNewDBModel;
    }




    private NGONewDBModel cursorToSubCatList(Cursor cursor) {
        int _ngoid = cursor.getInt(0);
        String _nameen = cursor.getString(1);
        String _namebn = cursor.getString(2);

        String _ngo_services = cursor.getString(3);
        String _ngo_services_for = cursor.getString(4);
        String _ngo_services_other = cursor.getString(5);
        String _ngo_service_type = cursor.getString(6);
        String _drop_time = cursor.getString(7);
        String _ngo_fee = cursor.getString(8);

        String _lat = cursor.getString(9);
        String _lon = cursor.getString(10);
        String _houseno = cursor.getString(11);
        String _block = cursor.getString(12);
        String _area = cursor.getString(13);
        String _policestation = cursor.getString(14);
        String _node_email = cursor.getString(15);
        String _ward = cursor.getString(16);
        String _road = cursor.getString(17);
        String _node_contact = cursor.getString(18);
        String _other = cursor.getString(19);
        String _areabn = cursor.getString(20);

        String _parentarea = cursor.getString(21);
        String _opentime  = cursor.getString(22);
        String _closetime = cursor.getString(23);
        String  _offday= cursor.getString(24);
        int _catid=cursor.getInt(25);
        String _refnumm=cursor.getString(26);
        String _rating=cursor.getString(27);
        String _sref=cursor.getString(28);


        return new NGONewDBModel(_ngoid,_nameen,_namebn, _ngo_services, _ngo_services_for, _ngo_services_other, _ngo_service_type, _drop_time, _ngo_fee,
                _lat, _lon,_houseno,_block,_area,_policestation,_node_email,_ward,_road,_node_contact,_other,_areabn,_parentarea,
                _opentime ,_closetime,_offday,_catid,_refnumm,_rating,_sref);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
    public ArrayList<NGONewDBModel> getAllNGO(String ward, String place) {
        ArrayList<NGONewDBModel> subCatList = new ArrayList<>();

        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM "+  TABLE_NAME + " WHERE "+KEY_WARD + " = '"+ ward + "' AND "+"("+KEY_AREA +"  = '"+ place + "')"+" OR "+"("+KEY_PARENT_AREA +"  =  '"+ place + "')", null);

        if (cursor.moveToFirst()) {
            do {
                subCatList.add(cursorToSubCatList(cursor));

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }
    public void delete(String ward,String area)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(NGONewDBTable.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_WARD + " = '" + ward + "' AND "+"("+KEY_AREA +"  = '"+ area + "')"+" OR "+"("+KEY_PARENT_AREA +"  =  '"+ area + "')", null);
        database.close();
    }
}
