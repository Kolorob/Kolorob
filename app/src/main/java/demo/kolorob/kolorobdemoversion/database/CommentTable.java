package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.RatingModel;

/**
 * Created by arafat 31 August.
 *
 * @author arafat
 */
public class CommentTable {
    private static final String TAG = RatingTable.class.getSimpleName();


    private static final String TABLE_NAME = DatabaseHelper.COMMENT;
    ArrayList<RatingModel>rm=new ArrayList<>();
    private static final String KEY_ID = "_id"; // 0 -integer
    private static final String KEY_PHONE = "_phone"; // 1 - text
    private static final String KEY_COMMENT = "_comment"; // 1 - text
    private static final String KEY_DATE = "_date"; // 2 - text
    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.

    private Context tContext;

    public CommentTable(Context context) {
        tContext = context;
        createTable();
    }




    private void createTable() {
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


    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
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


//    public boolean isFieldExist(String id,String phone,String comment) {
//        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//        if (cursor.moveToFirst()) {
//            do {
//                if (id.equals(cursor.getString(0))&&Integer.parseInt(cursor.getString(3))==cat_id&&Integer.parseInt(cursor.getString(2))==sub_cat_id) {
//                    cursor.close();
//                    closeDB();
//                    return true;
//                }
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return false;
//    }


    public boolean isFieldExist(String id, String phone, String comment) {
        // Lg.d(TAG, "isFieldExist : inside, id=" + id);
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

//    public ArrayList<RatingModel> getAllCategories() {
//        ArrayList<RatingModel> ciList = new ArrayList<>();
//
//        SQLiteDatabase db = openDB();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//
//                ciList.add(cursorToRating(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        closeDB();
//        return ciList;
//    }

    private CommentItem commentItem(Cursor cursor) {
        String id = cursor.getString(0);
        String phone = cursor.getString(1);
        String comment = cursor.getString(2);
        String date = cursor.getString(3);
        return new CommentItem(id, phone,comment, date);
    }


    public ArrayList<CommentItem> getAllFinancialSubCategoriesInfo(String id) {
        ArrayList<CommentItem> subCatList = new ArrayList<>();
        int ids=Integer.valueOf(id);
        Log.d("id","======="+ids);
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
     //   Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
       Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + ids, null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                subCatList.add(commentItem(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCatList;
    }


    public String getavg() {


        String avg = null;

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if (cursor.moveToFirst()) {
            do {
                int catid2 =cursor.getInt(0);
                avg=cursor.getString(1);
                String olddate=cursor.getString(2);

            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return avg;
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
    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        //Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
