package demo.kolorob.kolorobdemoversion.database.Financial;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 6/27/2016.
 */
public class FinancialServiceContactInfoTable {


    private static final String TAG = FinancialServiceContactInfoTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_CONTACT;
    private static final String KEY_FIN_NODE_ID = "_finId"; // 0 -integer
    private static final String KEY_FIN_NODE_FLOOR = "_floor"; //
    private static final String KEY_FIN_NODE_HOUSENAME = "_housename"; //
    private static final String KEY_FIN_NODE_HOUSENO  = "_houseno"; //
    private static final String KEY_FIN_NODE_ROAD = "_road"; //
    private static final String KEY_FIN_NODE_LINE = "_line"; //
    private static final String KEY_FIN_NODE_AVENUE = "_avenue"; //
    private static final String KEY_FIN_NODE_BLOCK = "_block"; //
    private static final String KEY_FIN_AREA = "_area"; //
    private static final String KEY_FIN_NODE_LANDMARK = "_landmark"; //
    private static final String KEY_FIN_NODE_POSTOFFICE = "_postoffice"; //
    private static final String KEY_FIN_NODE_POLICESTATION = "_policestation"; //
    private static final String KEY_FIN_NODE_CITY = "_city"; //
    private static final String KEY_FIN_NODE_COUNTRY = "_country"; //
    private static final String KEY_FIN_NODE_CONTACT = "_node_contact"; //
    private static final String KEY_FIN_NODE_CONTACT2 = "_node_contact2"; //
    private static final String KEY_FIN_NODE_EMAIL= "_node_email"; //
    private static final String KEY_FIN_NODE_WEBSITE = "_node_website"; //
    private static final String KEY_FIN_NODE_FACEBOOK= "_node_facebook"; //
    private static final String KEY_FIN_NODE_DESIGNATION = "_node_designation"; //

private  FinancialServiceProviderItem financialServiceProviderItem;
    private Context tContext;


    public FinancialServiceContactInfoTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FIN_NODE_ID + "  TEXT , " // 0 - int
                + KEY_FIN_NODE_FLOOR + "  TEXT, "              // 1 - text
                + KEY_FIN_NODE_HOUSENAME + " TEXT, "
                + KEY_FIN_NODE_HOUSENO + " TEXT, "
                + KEY_FIN_NODE_ROAD + " TEXT, "
                + KEY_FIN_NODE_LINE + " TEXT, "
                + KEY_FIN_NODE_AVENUE + " TEXT, "
                + KEY_FIN_NODE_BLOCK + " TEXT, "
                + KEY_FIN_AREA + " TEXT, "
                + KEY_FIN_NODE_LANDMARK + " TEXT, "
                + KEY_FIN_NODE_POSTOFFICE+ " TEXT, "
                + KEY_FIN_NODE_POLICESTATION + " TEXT, "
                + KEY_FIN_NODE_CITY + " TEXT, "
                + KEY_FIN_NODE_COUNTRY + " TEXT, "
                + KEY_FIN_NODE_CONTACT + " TEXT, "
                + KEY_FIN_NODE_CONTACT2 + " TEXT, "
                + KEY_FIN_NODE_EMAIL + " TEXT,"
                + KEY_FIN_NODE_WEBSITE + " TEXT, "
                + KEY_FIN_NODE_FACEBOOK + " TEXT, "
                + KEY_FIN_NODE_DESIGNATION + " TEXT, PRIMARY KEY(" + KEY_FIN_NODE_ID+ "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }
    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }
    public long insertItem(FinancialContactInfoItem financialContactInfoItem) {
        return insertItem(financialContactInfoItem.getFinId(),financialContactInfoItem.getFloor(),
                financialContactInfoItem.getHousename(),financialContactInfoItem.getHouseno(),
                financialContactInfoItem.getRoad(),financialContactInfoItem.getLine(),financialContactInfoItem.getAvenue(),
                financialContactInfoItem.getBlock(),financialContactInfoItem.getArea(),financialContactInfoItem.getLandmark(),
                financialContactInfoItem.getPostoffice(),financialContactInfoItem.getPolicestation(),
                financialContactInfoItem.getCity(),financialContactInfoItem.getCountry(),financialContactInfoItem.getNode_contact(),
                financialContactInfoItem.getNode_contact2(),financialContactInfoItem.getNode_email(),financialContactInfoItem.getNode_website(),
                financialContactInfoItem.getNode_facebook(),financialContactInfoItem.getNode_designation());
    }

    private long insertItem(String finId,String floor, String housename, String houseno,
                            String road, String line, String avenue, String block, String area,
                            String landmark, String postoffice, String policestation, String city,
                            String country, String node_contact, String node_contact2, String node_email,
                            String node_website,String node_facebook, String node_designation) {
        if (isFieldExist(finId)) {
            return updateItem(
                    finId,
                    floor,
                    housename,
                    houseno,
                    road,
                    line,
                    avenue,
                    block,
                    area,
                    landmark,
                    postoffice,
                    policestation,
                    city,
                    country,
                    node_contact,
                    node_contact2,
                    node_email,node_website,
                    node_facebook,
                    node_designation
                 );
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID, finId);
        rowValue.put(KEY_FIN_NODE_FLOOR,  floor);
        rowValue.put(KEY_FIN_NODE_HOUSENAME, housename);
        rowValue.put(KEY_FIN_NODE_HOUSENO, houseno);
        rowValue.put(KEY_FIN_NODE_ROAD,  road);
        rowValue.put(KEY_FIN_NODE_LINE ,  line);
        rowValue.put(KEY_FIN_NODE_AVENUE ,avenue);
        rowValue.put(KEY_FIN_NODE_BLOCK , block);
        rowValue.put(KEY_FIN_AREA, area);
        rowValue.put(KEY_FIN_NODE_LANDMARK,landmark);
        rowValue.put(KEY_FIN_NODE_POSTOFFICE , postoffice);
        rowValue.put(KEY_FIN_NODE_POLICESTATION, policestation);
        rowValue.put(KEY_FIN_NODE_CITY, city);
        rowValue.put(KEY_FIN_NODE_COUNTRY, country);
        rowValue.put(KEY_FIN_NODE_CONTACT, node_contact);
        rowValue.put(KEY_FIN_NODE_CONTACT2, node_contact2);
        rowValue.put(KEY_FIN_NODE_EMAIL, node_email);
        rowValue.put(KEY_FIN_NODE_WEBSITE , node_website);
        rowValue.put(KEY_FIN_NODE_FACEBOOK  , node_facebook);
        rowValue.put(KEY_FIN_NODE_DESIGNATION  , node_designation);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;


    }

    private long  updateItem(String finId,String floor, String housename, String houseno,
                             String road, String line, String avenue, String block, String area,
                             String landmark, String postoffice, String policestation, String city,
                             String country, String node_contact, String node_contact2, String node_email,
                             String node_website,String node_facebook, String node_designation) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_FIN_NODE_ID, finId);
        rowValue.put(KEY_FIN_NODE_FLOOR,  floor);
        rowValue.put(KEY_FIN_NODE_HOUSENAME, housename);
        rowValue.put(KEY_FIN_NODE_HOUSENO, houseno);
        rowValue.put(KEY_FIN_NODE_ROAD,  road);
        rowValue.put(KEY_FIN_NODE_LINE ,  line);
        rowValue.put(KEY_FIN_NODE_AVENUE ,avenue);
        rowValue.put(KEY_FIN_NODE_BLOCK , block);
        rowValue.put(KEY_FIN_AREA, area);
        rowValue.put(KEY_FIN_NODE_LANDMARK,landmark);
        rowValue.put(KEY_FIN_NODE_POSTOFFICE , postoffice);
        rowValue.put(KEY_FIN_NODE_POLICESTATION, policestation);
        rowValue.put(KEY_FIN_NODE_CITY, city);
        rowValue.put(KEY_FIN_NODE_COUNTRY, country);
        rowValue.put(KEY_FIN_NODE_CONTACT, node_contact);
        rowValue.put(KEY_FIN_NODE_CONTACT2, node_contact2);
        rowValue.put(KEY_FIN_NODE_EMAIL, node_email);
        rowValue.put(KEY_FIN_NODE_WEBSITE , node_website);
        rowValue.put(KEY_FIN_NODE_FACEBOOK  , node_facebook);
        rowValue.put(KEY_FIN_NODE_DESIGNATION  , node_designation);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;

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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    private FinancialContactInfoItem cursorToSubCatList(Cursor cursor) {
        String _finId = cursor.getString(0);
        String _floor = cursor.getString(1);
        String _housename = cursor.getString(2);

        String _houseno = cursor.getString(3);
        String _road = cursor.getString(4);
        String _line = cursor.getString(5);

        String _avenue = cursor.getString(6);
        String _block = cursor.getString(7);
        String _area = cursor.getString(8);

        String _landmark = cursor.getString(9);
        String _postoffice = cursor.getString(10);
        String _policestation = cursor.getString(11);

        String _city = cursor.getString(12);
        String _country = cursor.getString(13);
        String _node_contact = cursor.getString(14);
        String _node_contact2 = cursor.getString(15);

        String _node_email = cursor.getString(16);
        String _node_website = cursor.getString(17);
        String _node_facebook=cursor.getString(18);

        String _node_designation=cursor.getString(19);

        return new FinancialContactInfoItem(_finId,_floor,_housename,_houseno,_road,_line,_avenue,_block,_area,_landmark,_postoffice,_policestation,
                _city,_country,_node_contact,_node_contact2,_node_email,_node_website,_node_facebook,_node_designation
        );

    }



    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
