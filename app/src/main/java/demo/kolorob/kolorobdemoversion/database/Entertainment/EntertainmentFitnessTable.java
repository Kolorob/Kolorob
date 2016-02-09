package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFitnessItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2016.
 */
public class EntertainmentFitnessTable {

    private static final String TAG = EntertainmentBookTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_BOOKSHOP_TABLE;
    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_SUB_CATEGORY_ID = "_ent_sub_category_id";
    private static final String KEY_ENT_YEAR = "_year_of_establishment"; // 1 - text
    private static final String KEY_ENT_WORKER = "_num_workers"; // 2 - text*/

    private static final String KEY_ENT_OFFER = "_offers"; //
    private static final String KEY_ENT_OFFERDETAILS = "_offer_details"; //
    private static final String KEY_SERVICETYPE = "_service_type"; //
    private static final String KEY_ENTTYPE = "_type"; //
    private static final String KEY_SERVICEDETAILS = "service_details"; //
    private Context tContext;

    public EntertainmentFitnessTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_SUB_CATEGORY_ID + "  INTEGER , " // 0 - int
                + KEY_ENT_YEAR + "  INTEGER, "              // 1 - text
                + KEY_ENT_WORKER + " INTEGER, "
                + KEY_ENT_OFFER + " TEXT, "
                + KEY_ENT_OFFERDETAILS + " TEXT, "
                + KEY_SERVICETYPE + " TEXT, "
                + KEY_ENTTYPE + " TEXT, "
                + KEY_SERVICEDETAILS + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_SUB_CATEGORY_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentFitnessItem entertainmentFitnessItem) {
        return insertItem(
                entertainmentFitnessItem.getNodeId(),
                entertainmentFitnessItem.getEntSubCategoryId(),
                entertainmentFitnessItem.getYearofestablishment(),
                entertainmentFitnessItem.getWorkers(),
                entertainmentFitnessItem.getOffers(),
                entertainmentFitnessItem.getOfferdetails(),
                entertainmentFitnessItem.getServicetype(),
                entertainmentFitnessItem.getType(),
                entertainmentFitnessItem.getServicedetails()
        );
    }

    private long insertItem(String nodeId, int entSubCategoryId, int yearofestablishment, int workers,
                            String offers, String offerdetails, String servicetype, String type,
                            String servicedetails) {


        if (isFieldExist(nodeId, entSubCategoryId)) {
            return updateItem(
                    nodeId,
                    entSubCategoryId,
                    yearofestablishment,
                    workers,
                    offers,
                    offerdetails,
                    servicetype,
                    type, servicedetails);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_ENT_YEAR, yearofestablishment);
        rowValue.put(KEY_ENT_WORKER, workers);
        rowValue.put(KEY_ENT_OFFER, offers);
        rowValue.put(KEY_ENT_OFFERDETAILS, offerdetails);
        rowValue.put(KEY_SERVICETYPE, servicetype);
        rowValue.put(KEY_ENTTYPE, type);
        rowValue.put(KEY_SERVICEDETAILS, servicedetails);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(String nodeId, int entSubCategoryId, int yearofestablishment, int workers,
                            String offers, String offerdetails, String servicetype, String type,
                            String servicedetails) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_ENT_YEAR, yearofestablishment);
        rowValue.put(KEY_ENT_WORKER, workers);
        rowValue.put(KEY_ENT_OFFER, offers);
        rowValue.put(KEY_ENT_OFFERDETAILS, offerdetails);
        rowValue.put(KEY_SERVICETYPE, servicetype);
        rowValue.put(KEY_ENTTYPE, type);
        rowValue.put(KEY_SERVICEDETAILS, servicedetails);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  AND " + KEY_SUB_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "", entSubCategoryId + ""});
        closeDB();
        return ret;

    }


    public boolean isFieldExist(String nodeId, int entSubCategoryId) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(1)) == entSubCategoryId && nodeId.equals(cursor.getString(0))) {
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


    public ArrayList<EntertainmentFitnessItem> getFitness(String idenId) {
        ArrayList<EntertainmentFitnessItem> FitnessList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_NODE_ID + " = '" + idenId + "'", null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                FitnessList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return FitnessList;
    }


    private EntertainmentFitnessItem cursorToSubCatList(Cursor cursor) {
        String _nodeId = cursor.getString(0);
        int _entSubCategoryId = cursor.getInt(1);
        int _yearofestablishment = cursor.getInt(2);
        int _workers = cursor.getInt(3);
        String _offers = cursor.getString(4);
        String _offerdetails = cursor.getString(5);
        String _servicetype = cursor.getString(6);
        String _type = cursor.getString(7);
        String _servicedetails = cursor.getString(8);

        return new EntertainmentFitnessItem(
                _nodeId,
                _entSubCategoryId,
                _yearofestablishment,
                _workers,
                _offers,
                _offerdetails,
                _servicetype,
                _type,
                _servicedetails);

    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
