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
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTypeItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by Mazharul.Islam1 on 6/20/2016.
 */
public class EntertainmetTypeTable {

    private static final String TAG = EntertainmetTypeTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_DETAILS_INFO;
    public static final String KEY_NODE_ID = "node_id";
    public static final String KEY_RECREATION_PRICE = "name";
    public static final String KEY_RECREATION_REMARKS = "bn_label";
    private static final String KEY_TYPE = "_facilities"; // 1 - text
    private static final String KEY_SUB_TYPE = "subType"; // 2 - text*/
    private static final String KEY_ID = "id"; // 2 - text*/





    private Context tContext;

    public EntertainmetTypeTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_RECREATION_PRICE + "  TEXT , " // 0 - int
                + KEY_RECREATION_REMARKS + "  TEXT, "              // 1 - text
                + KEY_TYPE + " TEXT, "
                + KEY_SUB_TYPE + " TEXT, "
                + KEY_ID + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentTypeItem entertainmetTypeTable) {
        return insertItem(
                entertainmetTypeTable.getNodeId(),
                entertainmetTypeTable.getRecreation_price(),
                entertainmetTypeTable.getRecreation_remarks(),
                entertainmetTypeTable.getType(),

                entertainmetTypeTable.getSub_type(),entertainmetTypeTable.getSId()
        );
    }

    private long insertItem(String nodeId, String price, String remarks, String type, String subtype,String sid
                            ) {



        if (isFieldExist(sid)) {
            return updateItem(
                    nodeId,
                    price,
                    remarks,
                    type,
                    subtype,sid);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_RECREATION_PRICE,price );
        rowValue.put(KEY_RECREATION_REMARKS, remarks);
        rowValue.put(KEY_TYPE, type);
        rowValue.put(KEY_SUB_TYPE, subtype);
        rowValue.put(KEY_ID, sid);



        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(String nodeId, String price, String remarks, String type, String subtype,String sid) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_RECREATION_PRICE,price );
        rowValue.put(KEY_RECREATION_REMARKS, remarks);
        rowValue.put(KEY_TYPE, type);
        rowValue.put(KEY_SUB_TYPE, subtype);
        rowValue.put(KEY_ID, sid);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{nodeId + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(String sid) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (sid.equals(cursor.getString(5))) {
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


    public ArrayList<EntertainmentTypeItem> getEntTypeItem(String idenId) {
        ArrayList<EntertainmentTypeItem> FieldList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
       Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+idenId+"'" , null);
      //  Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                FieldList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return FieldList;
    }
    public ArrayList<EntertainmentTypeItem> getall() {
        ArrayList<EntertainmentTypeItem> FieldList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                FieldList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return FieldList;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)





    public static EntertainmentTypeItem cursorToSubCatList(Cursor cursor) {
        String id = cursor.getString(0);
        String price = cursor.getString(1);
        String remarks = cursor.getString(2);
        String type = cursor.getString(3);
        String subtype = cursor.getString(4);
        String sid = cursor.getString(5);

        return new EntertainmentTypeItem(
                id,
                price,
                remarks,
                type,
                subtype,sid



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
