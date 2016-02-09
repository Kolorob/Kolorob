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
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTheatreItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 2/9/2016.
 */
public class EntertainmentTheatreTable {

    private static final String TAG = EntertainmentTheatreTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_THEATRE;
    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_SUB_CATEGORY_ID = "_ent_sub_category_id";
    public static final String KEY_THEATRE_ID = "_theatre_id";
    private static final String KEY_ENT_EVENTTYPE = "_event_type"; // 1 - text
    private static final String KEY_ENT_EVENTFEE = "_event_fee"; // 2 - text*/

    private static final String KEY_ENT_EVENTDATE = "_event_date"; //
    private static final String KEY_ENT_REMARKS = "_remarks"; //
    private static final String KEY_ENT_EVENFEEFFP = "_event_fee_ffp"; //
    private static final String KEY_ENT_EVENTFEEFOC = "_event_fee_foc"; //

    private Context tContext;

    public EntertainmentTheatreTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_SUB_CATEGORY_ID + "  INTEGER , " // 0 - int
                + KEY_THEATRE_ID + "  INTEGER, "              // 1 - text
                + KEY_ENT_EVENTTYPE + " TEXT, "
                + KEY_ENT_EVENTFEE + " TEXT, "
                + KEY_ENT_EVENTDATE + " TEXT, "
                + KEY_ENT_REMARKS + " TEXT, "
                + KEY_ENT_EVENFEEFFP + " TEXT, "
                + KEY_ENT_EVENTFEEFOC + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + ", " + KEY_SUB_CATEGORY_ID + "," + KEY_THEATRE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentTheatreItem entertainmentTheatreItem) {
        return insertItem(
               entertainmentTheatreItem.getNodeId(),
                entertainmentTheatreItem.getEntSubCategoryId(),
                entertainmentTheatreItem.getTheatreId(),
                entertainmentTheatreItem.getEventtype(),
                entertainmentTheatreItem.getEventfee(),
                entertainmentTheatreItem.getEventdate(),
                entertainmentTheatreItem.getRemarks(),
                entertainmentTheatreItem.getEventfeeffp(),
                entertainmentTheatreItem.getEventfeefoc()
        );
    }

    private long insertItem(String nodeId, int entSubCategoryId, int theatreId, String eventtype, String eventfee,
                            String eventdate, String remarks, String eventfeeffp, String eventfeefoc) {



        if (isFieldExist(nodeId,entSubCategoryId)) {
            return updateItem(
                    nodeId,
                    entSubCategoryId,
                    theatreId,
                    eventtype,
                    eventfee,
                    eventdate,
                    remarks,
                    eventfeeffp,eventfeefoc);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_THEATRE_ID, theatreId);
        rowValue.put(KEY_ENT_EVENTTYPE, eventtype);
        rowValue.put(KEY_ENT_EVENTFEE, eventfee);
        rowValue.put(KEY_ENT_EVENTDATE, eventdate);
        rowValue.put(KEY_ENT_REMARKS, remarks);
        rowValue.put(KEY_ENT_EVENFEEFFP, eventfeeffp);
        rowValue.put(KEY_ENT_EVENTFEEFOC, eventfeefoc);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(String nodeId, int entSubCategoryId, int theatreId, String eventtype, String eventfee,
                            String eventdate, String remarks, String eventfeeffp, String eventfeefoc) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_SUB_CATEGORY_ID, entSubCategoryId);
        rowValue.put(KEY_THEATRE_ID, theatreId);
        rowValue.put(KEY_ENT_EVENTTYPE, eventtype);
        rowValue.put(KEY_ENT_EVENTFEE, eventfee);
        rowValue.put(KEY_ENT_EVENTDATE, eventdate);
        rowValue.put(KEY_ENT_REMARKS, remarks);
        rowValue.put(KEY_ENT_EVENFEEFFP, eventfeeffp);
        rowValue.put(KEY_ENT_EVENTFEEFOC, eventfeefoc);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?   AND " + KEY_THEATRE_ID + " = ? AND " + KEY_SUB_CATEGORY_ID + " = ? ",
                new String[]{nodeId + "",theatreId + "",  entSubCategoryId + ""});
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


    public ArrayList<EntertainmentTheatreItem> getTheatre(String idenId) {
        ArrayList<EntertainmentTheatreItem> TheatreList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_NODE_ID+" = '"+idenId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                TheatreList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return TheatreList;
    }



    public static EntertainmentTheatreItem cursorToSubCatList(Cursor cursor) {
        String _nodeId = cursor.getString(0);
        int _entSubCategoryId = cursor.getInt(1);
        int _theatreId = cursor.getInt(2);
        String _eventtype = cursor.getString(3);
        String _eventfee = cursor.getString(4);
        String _eventdate = cursor.getString(5);
        String _remarks = cursor.getString(6);
        String _eventfeeffp = cursor.getString(7);
        String _eventfeefoc = cursor.getString(8);
        return new EntertainmentTheatreItem(
                _nodeId,
                _entSubCategoryId,
                _theatreId,
                _eventtype,
                _eventfee,
                _eventdate,
                _remarks,
                _eventfeeffp,
                _eventfeefoc

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
