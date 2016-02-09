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
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFieldItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2016.
 */
public class EntertainmentFieldTable {
    private static final String TAG = EntertainmentFieldTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_FIELD;
    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_SUB_CATEGORY_ID = "_ent_sub_category_id";
    public static final String KEY_EVENTCOST = "_event_cost";
    private static final String KEY_PLAYCOST = "_playground_cost"; // 1 - text
    private static final String KEY_ENT_REMARKS = "_remarks"; //
    private static final String KEY_EVENTCOSTFFP = "_event_cost_ffp"; // 2 - text*/
    private static final String KEY_EVENTCOSTFOC = "_event_cost_foc"; //
    private static final String KEY_PLAYCOSTFFP = "_playground_cost_ffp"; //
    private static final String KEY_PLAYCOSTFOC = "_playground_cost_foc"; //

    private Context tContext;

    public EntertainmentFieldTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_SUB_CATEGORY_ID + "  TEXT , " // 0 - int
                + KEY_EVENTCOST + "  INTEGER, "              // 1 - text
                + KEY_PLAYCOST + " TEXT, "
                + KEY_ENT_REMARKS + " TEXT, "
                + KEY_EVENTCOSTFFP + " TEXT, "
                + KEY_EVENTCOSTFOC + " TEXT, "
                + KEY_PLAYCOSTFFP + " TEXT, "
                + KEY_PLAYCOSTFOC + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_SUB_CATEGORY_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentFieldItem entertainmentFieldItem) {
        return insertItem(
               entertainmentFieldItem.getNodeId(),
                entertainmentFieldItem.getEntSubCategoryId(),
                entertainmentFieldItem.getEventCost(),
                entertainmentFieldItem.getPlaygroundcost(),
                entertainmentFieldItem.getRemark(),
                entertainmentFieldItem.getEventcostffp(),
                entertainmentFieldItem.getEventcostfoc(),
                entertainmentFieldItem.getPlaygroundcostffp(),
                entertainmentFieldItem.getPlaygroundcostfoc()
        );
    }

    private long insertItem(String nodeId, int entSubCategoryId, String eventCost, String playgroundcost,
                            String remark, String eventcostffp, String eventcostfoc, String playgroundcostffp,
                            String playgroundcostfoc) {



        if (isFieldExist(nodeId,entSubCategoryId)) {
            return updateItem(
                    nodeId,
                    entSubCategoryId,
                    eventCost,
                    playgroundcost,
                    remark,
                    eventcostffp,
                    eventcostfoc,
                    playgroundcostffp,playgroundcostfoc);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_EVENTCOST, eventCost);
        rowValue.put(KEY_PLAYCOST, playgroundcost);
        rowValue.put(KEY_ENT_REMARKS, remark);
        rowValue.put(KEY_EVENTCOSTFFP, eventcostffp);
        rowValue.put(KEY_EVENTCOSTFOC, eventcostfoc);
        rowValue.put(KEY_PLAYCOSTFFP, playgroundcostffp);
        rowValue.put(KEY_PLAYCOSTFOC, playgroundcostfoc);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(String nodeId, int entSubCategoryId, String eventCost, String playgroundcost, String remark,
                            String eventcostffp, String eventcostfoc, String playgroundcostffp, String playgroundcostfoc) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_EVENTCOST, eventCost);
        rowValue.put(KEY_PLAYCOST, playgroundcost);
        rowValue.put(KEY_ENT_REMARKS, remark);
        rowValue.put(KEY_EVENTCOSTFFP, eventcostffp);
        rowValue.put(KEY_EVENTCOSTFOC, eventcostfoc);
        rowValue.put(KEY_PLAYCOSTFFP, playgroundcostffp);
        rowValue.put(KEY_PLAYCOSTFOC, playgroundcostfoc);
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?   AND "  + KEY_SUB_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "",  entSubCategoryId + ""});
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


    public ArrayList<EntertainmentFieldItem> getField(String idenId) {
        ArrayList<EntertainmentFieldItem> FieldList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+idenId+"'" , null);

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



    public static EntertainmentFieldItem cursorToSubCatList(Cursor cursor) {
        String _nodeId = cursor.getString(0);
        int _entSubCategoryId = cursor.getInt(1);
        String _eventCost = cursor.getString(2);
        String _playgroundcost = cursor.getString(3);
        String _remark = cursor.getString(4);
        String _eventcostffp = cursor.getString(5);
        String _eventcostfoc = cursor.getString(6);
        String _playgroundcostffp = cursor.getString(7);
        String _playgroundcostfoc = cursor.getString(8);
        return new EntertainmentFieldItem(
                _nodeId,
                _entSubCategoryId,
                _eventCost,
                _playgroundcost,
                _remark,
                _eventcostffp,
                _eventcostfoc,
                _playgroundcostffp,
                _playgroundcostfoc

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
