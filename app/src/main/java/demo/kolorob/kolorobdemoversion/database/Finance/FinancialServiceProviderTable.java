package demo.kolorob.kolorobdemoversion.database.Finance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;

/**
 * Created by mity on 1/11/16.
 */
public class FinancialServiceProviderTable {

    private static final String TAG = FinancialServiceProviderTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.FINANCIAL_SERVICE_PROVIDER;

    private static final String KEY_FIN_NODE_ID = "_nodeId"; // 0 -integer
    private static final String KEY_FIN_NODE_NAME = "_nodeName"; // 1 - text
    private static final String KEY_FIN_DATE_NAME  = "_dateName"; // 2 - text*/
    private static final String KEY_FIN_DATE = "_dateDate";
    private static final String KEY_FIN_NODE_DESIGNATION = "_nodeDesignation"; //
    private static final String KEY_FIN_NODE_CONTACT = "_nodeContact"; //
    private static final String KEY_FIN_NODE_EMAIL  = "_nodeEmail"; //
    private static final String KEY_FIN_NODE_ADDITIONAL = "_nodeAdditional"; //
    private static final String KEY_FIN_NODE_WEBSITE= "_nodeWebsite"; //
    private static final String KEY_FIN_NODE_FACEBOOK = "_nodeFacebook"; //
    private static final String KEY_FIN_NODE_REGISTERED_WITH = "_nodeRegisteredwith"; //
    private static final String KEY_FIN_NODE_REGISTRATION_NUMBER= "_nodeRegistationNumber"; //
    private static final String KEY_FIN_EDITED_BY  = "_editedBy"; //
    private static final String KEY_FIN_REF_NUM = "_refNum"; //

    private static final String KEY_FIN_TIME_STAMP  = "_timeStamp"; //
    private static final String KEY_FIN_NODE_TYPE  = "_nodeType"; //
    private static final String KEY_FIN_AREA = "_area"; //
    private static final String KEY_FIN_ADDRESS = "_address"; //
    private static final String KEY_FIN_LONGITUDE = "_longitude"; //
    private static final String KEY_FIN_LATITUDE  = "_latitude"; //
    private static final String KEY_CATEGORY_ID = "_categoryId"; //


    private Context tContext;


    public FinancialServiceProviderTable(Context context) {
        tContext = context;
        createTable();
    }
    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_FIN_NODE_ID + "  INTEGER , " // 0 - int
                + KEY_FIN_NODE_NAME + "  TEXT, "              // 1 - text
                + KEY_FIN_DATE_NAME + " TEXT, "
                + KEY_FIN_DATE + " TEXT, "// 2 - text
                + KEY_FIN_NODE_DESIGNATION + " TEXT, "
                + KEY_FIN_NODE_CONTACT + " TEXT, "
                + KEY_FIN_NODE_EMAIL + " TEXT, "
                + KEY_FIN_NODE_ADDITIONAL + " TEXT, "
                + KEY_FIN_NODE_WEBSITE + " TEXT, "
                + KEY_FIN_NODE_FACEBOOK + " TEXT, "
                + KEY_FIN_NODE_REGISTERED_WITH + " TEXT, "
                + KEY_FIN_NODE_REGISTRATION_NUMBER + " TEXT, "
                + KEY_FIN_EDITED_BY + " TEXT, "
                + KEY_FIN_REF_NUM+ " INTEGER, "

                + KEY_FIN_TIME_STAMP + " TEXT, "
                + KEY_FIN_NODE_TYPE  + " TEXT, "
                + KEY_FIN_AREA + " TEXT, "
                + KEY_FIN_ADDRESS + " TEXT, "
                + KEY_FIN_LATITUDE + " TEXT, "
                + KEY_FIN_LONGITUDE + " TEXT, "
                + KEY_CATEGORY_ID + " INTEGER, , PRIMARY KEY(" + KEY_FIN_NODE_ID+ ", " + KEY_FIN_REF_NUM + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }
    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }
    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

}
