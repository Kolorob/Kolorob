package demo.kolorob.kolorobdemoversion.database.LegalAid;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTheatreItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTypeItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LeagalAidDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by Mazharul.Islam1 on 6/20/2016.
 */
public class LegalAidDetailsTable {

    private static final String TAG = LegalAidDetailsTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.Legal_Aid_Details_Table;
    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_LEGAL_COST = "legal_cost";
    public static final String KEY_lEGAL_RESPONSIBLE_PERSON = "legal_responsible_person";
    private static final String KEY_LEGAL_REMARKS = "legal_remarks"; // 1 - text
    private static final String KEY_TYPE = "type"; // 2 - text*/
    private static final String KEY_SUB_TYPE = "sub_type"; // 2 - text*/

    private Context tContext;

    public LegalAidDetailsTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_LEGAL_COST + "  TEXT , " // 0 - int
                + KEY_lEGAL_RESPONSIBLE_PERSON + "  TEXT, "              // 1 - text
                + KEY_LEGAL_REMARKS + "  TEXT, "              // 1 - text
                + KEY_TYPE + " TEXT, "
                + KEY_SUB_TYPE + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(LeagalAidDetailsItem leagalAidDetailsItem) {
        return insertItem(
                leagalAidDetailsItem.getNodeId(),
                leagalAidDetailsItem.getLagal_cost(),
                leagalAidDetailsItem.getLegal_responsible_person(),
                leagalAidDetailsItem.getLagal_remarks(),
                leagalAidDetailsItem.getType(),
                leagalAidDetailsItem.getSub_type()
        );
    }

    private long insertItem(String nodeId, String legal_cost, String responsible_person,String legal_remarks, String type, String subtype
    ) {
        if (isFieldExist(nodeId)) {
            return updateItem(
                    nodeId,
                    legal_cost,
                    responsible_person,
                    legal_remarks,
                    type,
                    subtype);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_LEGAL_COST, legal_cost);
        rowValue.put(KEY_lEGAL_RESPONSIBLE_PERSON,responsible_person );
        rowValue.put(KEY_LEGAL_REMARKS, legal_remarks);
        rowValue.put(KEY_TYPE, type);
        rowValue.put(KEY_SUB_TYPE, subtype);



        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        Log.d("Legal Insert","======"+ret);
        closeDB();
        return ret;
    }

    private long updateItem( String nodeId,
                             String legal_cost,
                             String responsible_person,
                             String legal_remarks,
                             String type,
                             String subtype) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_LEGAL_COST,legal_cost );
        rowValue.put(KEY_lEGAL_RESPONSIBLE_PERSON, responsible_person);
        rowValue.put(KEY_LEGAL_REMARKS, legal_remarks);
        rowValue.put(KEY_TYPE, type);
        rowValue.put(KEY_SUB_TYPE, subtype);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{nodeId + ""});
        closeDB();
        return ret;

    }


    public ArrayList<LeagalAidDetailsItem> getAllLegalAidSubCategoriesInfo(int id) {
        ArrayList<LeagalAidDetailsItem> subCatList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_LEGAL_REMARKS+"="+id, null);
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



    public boolean isFieldExist(String nodeId) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (nodeId.equals(cursor.getString(0))) {
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





    public static LeagalAidDetailsItem cursorToSubCatList(Cursor cursor) {
        String id = cursor.getString(0);
        String  legal_cost = cursor.getString(1);
        String legal_responsible_person = cursor.getString(2);
        String legal_remark = cursor.getString(3);
        String type = cursor.getString(4);
        String subtype = cursor.getString(5);


        return new LeagalAidDetailsItem(
                id,
                legal_cost,
                legal_responsible_person,
                legal_remark,
                type,
                subtype



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
