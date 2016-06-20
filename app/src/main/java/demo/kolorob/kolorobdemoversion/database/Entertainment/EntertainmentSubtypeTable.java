package demo.kolorob.kolorobdemoversion.database.Entertainment;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTheatreItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentSubTypeItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by Mazharul.Islam1 on 6/20/2016.
 */
public class EntertainmentSubtypeTable {

    private static final String TAG = EntertainmentTheatreTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.ENT_THEATRE;
    public static final String KEY_NODE_ID = "_node_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_BN_LEVEL = "bn_label";
    private static final String KEY_EN_LEVEL = "_facilities"; // 1 - text
    private static final String KEY_FACILITIES = "facilities"; // 2 - text*/
    private static final String KEY_COLUMN_NAME = "column_name"; // 2 - text*/





    private Context tContext;

    public EntertainmentSubtypeTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_NODE_ID + " TEXT , "
                + KEY_NAME + "  TEXT , " // 0 - int
                + KEY_BN_LEVEL + "  TEXT, "              // 1 - text
                + KEY_EN_LEVEL + " TEXT, "
                + KEY_FACILITIES + " TEXT, "
                + KEY_COLUMN_NAME + " TEXT, PRIMARY KEY(" + KEY_NODE_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(EntertainmentSubTypeItem entertainmetSubTypeTable) {
        return insertItem(
                entertainmetSubTypeTable.getNodeId(),
                entertainmetSubTypeTable.getName(),
                entertainmetSubTypeTable.getBn_label(),
                entertainmetSubTypeTable.getEn_label(),
                entertainmetSubTypeTable.get_facilities(),
                entertainmetSubTypeTable.getColumn_name()
        );
    }

    private long insertItem(String nodeId, String name, String bn_label, String en_label, String _facilities,
                            String column_name) {



        if (isFieldExist(nodeId)) {
            return updateItem(
                    nodeId,
                    name,
                    bn_label,
                    en_label,
                    _facilities,
                    column_name);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_BN_LEVEL, bn_label);
        rowValue.put(KEY_EN_LEVEL, en_label);
        rowValue.put(KEY_FACILITIES, _facilities);
        rowValue.put(KEY_COLUMN_NAME, column_name);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(String nodeId, String name, String bn_label, String en_label, String _facilities,
                            String column_name) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NODE_ID, nodeId);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_BN_LEVEL, bn_label);
        rowValue.put(KEY_EN_LEVEL, en_label);
        rowValue.put(KEY_FACILITIES, _facilities);
        rowValue.put(KEY_COLUMN_NAME, column_name);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_NODE_ID + " = ?  ",
                new String[]{nodeId + ""});
        closeDB();
        return ret;

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





    public static EntertainmentSubTypeItem cursorToSubCatList(Cursor cursor) {
        String id = cursor.getString(0);
        String  name = cursor.getString(1);
        String bn_label = cursor.getString(2);
        String en_label = cursor.getString(3);
        String _facilities = cursor.getString(4);
        String column_name = cursor.getString(5);

        return new EntertainmentSubTypeItem(
                id,
                name,
                bn_label,
                en_label,
                _facilities,
                column_name


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
