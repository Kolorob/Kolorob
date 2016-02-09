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
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentBookShopItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2016.
 */
public class EntertainmentBookTable {

    private static final String TAG = EntertainmentBookTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_BOOKSHOP_TABLE;
    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_SUB_CATEGORY_ID = "_ent_sub_category_id";
    private static final String KEY_ENT_BORROWCOST = "_borrow_cost"; // 1 - text
    private static final String KEY_ENT_LENDING = "_lending_allowed"; // 2 - text*/

    private static final String KEY_ENT_MEMCOST = "_membership_cost"; //
    private static final String KEY_ENT_OFFERS = "_offers"; //
    private static final String KEY_ENT_OFFERDETAILS = " _offer_details"; //
    private static final String KEY_ENTFORFFP = "_membership_cost_ffp"; //
    private static final String KEY_ENTFORFOC = "_membership_cost_foc"; //
    private Context tContext;

    public EntertainmentBookTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_SUB_CATEGORY_ID + "  INTEGER , " // 0 - int
                + KEY_ENT_BORROWCOST + "  TEXT, "              // 1 - text
                + KEY_ENT_LENDING + " TEXT, "
                + KEY_ENT_MEMCOST + " TEXT, "
                + KEY_ENT_OFFERS + " TEXT, "
                + KEY_ENT_OFFERDETAILS + " TEXT, "
                + KEY_ENTFORFFP + " TEXT, "
                + KEY_ENTFORFOC + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_SUB_CATEGORY_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentBookShopItem entertainmentBookShopItem) {
        return insertItem(
                entertainmentBookShopItem.getNodeId(),
                entertainmentBookShopItem.getEntSubCategoryId(),
                entertainmentBookShopItem.getBorrowCost(),
                entertainmentBookShopItem.getLending(),
                entertainmentBookShopItem.getMemcost(),
                entertainmentBookShopItem.getOffers(),
                entertainmentBookShopItem.getOfferdetails(),
                entertainmentBookShopItem.getMemcostffp(),
                entertainmentBookShopItem.getMemcostfoc()
        );
    }

    private long insertItem(String nodeId, int entSubCategoryId, String borrowCost, String lending, String memcost,
                            String offers, String offerdetails, String memcostffp, String memcostfoc) {



        if (isFieldExist(nodeId,entSubCategoryId)) {
            return updateItem(
                    nodeId,
                    entSubCategoryId,
                    borrowCost,
                    lending,
                    memcost,
                    offers,
                    offerdetails,
                    memcostffp,memcostfoc);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_ENT_BORROWCOST, borrowCost);
        rowValue.put(KEY_ENT_LENDING, lending);
        rowValue.put(KEY_ENT_MEMCOST, memcost);
        rowValue.put(KEY_ENT_OFFERS, offers);
        rowValue.put(KEY_ENT_OFFERDETAILS, offerdetails);
        rowValue.put(KEY_ENTFORFFP, memcostffp);
        rowValue.put(KEY_ENTFORFOC, memcostfoc);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(String nodeId, int entSubCategoryId, String borrowCost, String lending, String memcost,
                            String offers, String offerdetails, String memcostffp, String memcostfoc) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_ENT_BORROWCOST, borrowCost);
        rowValue.put(KEY_ENT_LENDING, lending);
        rowValue.put(KEY_ENT_MEMCOST, memcost);
        rowValue.put(KEY_ENT_OFFERS, offers);
        rowValue.put(KEY_ENT_OFFERDETAILS, offerdetails);
        rowValue.put(KEY_ENTFORFFP, memcostffp);
        rowValue.put(KEY_ENTFORFOC, memcostfoc);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  AND " + KEY_SUB_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "", entSubCategoryId + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(String nodeId,int entSubCategoryId) {
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


    public ArrayList<EntertainmentBookShopItem> getBookshop(String idenId) {
        ArrayList<EntertainmentBookShopItem> BookList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+idenId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                BookList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return BookList;
    }



    private EntertainmentBookShopItem cursorToSubCatList(Cursor cursor) {
        String _nodeId = cursor.getString(0);
        int _entSubCategoryId = cursor.getInt(1);
        String _borrowCost = cursor.getString(2);
        String _lending = cursor.getString(3);
        String _memcost = cursor.getString(4);
        String _offers = cursor.getString(5);
        String _offerdetails = cursor.getString(6);
        String _memcostffp = cursor.getString(7);
        String _memcostfoc =cursor.getString(8);

        return   new EntertainmentBookShopItem(_nodeId,
                _entSubCategoryId,
                _borrowCost,
                _lending,
                _memcost,
                _offers,
                _offerdetails,
                _memcostffp,
                _memcostfoc);


    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
