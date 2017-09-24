package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.Ward;


/**
 * Created by arafat 31 August.
 *
 * @author arafat
 */
public class CommentTable extends BaseDBTable <CommentItem>{


    private static final String TABLE_NAME = DatabaseHelper.COMMENT;

    private static final String KEY_ID = "_id"; // 0 -integer
    private static final String KEY_PHONE = "_phone"; // 1 - text
    private static final String KEY_COMMENT = "_comment"; // 1 - text
    private static final String KEY_DATE = "_date"; // 2 - text
    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.


    public CommentTable(Context context) {
        tContext = context;
        createTable();
    }


    public void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + "  TEXT , " // 0 - int
                + KEY_PHONE + "  TEXT, "              // 1 - text
                + KEY_COMMENT + "  TEXT, "              // 1 - text
                + KEY_DATE + " TEXT, PRIMARY KEY(" + KEY_ID + ", " + KEY_PHONE + ", " + KEY_COMMENT + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(CommentItem commentItem){
        return insertItem(
                commentItem.getService_id(),
                commentItem.getMob_no(),
                commentItem.getComment(),
                commentItem.getDate()

        );
    }

    public long insertItem(String id, String mobile, String commment,String date) {
        if (isFieldExist(id,mobile,commment)) {
            return updateItem(id, mobile,commment, date);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_PHONE, mobile);
        rowValue.put(KEY_COMMENT, commment);
        rowValue.put(KEY_DATE, date);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }



    public boolean isFieldExist(String id, String phone, String comment) {

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&&phone.equals(cursor.getString(1))&&comment.equals(cursor.getString(2))) {
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

    private long updateItem(String id, String phone, String commment, String date) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_PHONE, phone);
        rowValue.put(KEY_COMMENT, commment);
        rowValue.put(KEY_DATE, date);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }



    public String getdate(int id) {
        String olddate=null;
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            do {

                olddate=cursor.getString(2);

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return olddate;
    }

    public CommentItem getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        CommentItem commentItem = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                commentItem = new CommentItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return commentItem;
    }

    public CommentItem cursorToModel(Cursor cursor){
        return null; // will implement later if required
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
