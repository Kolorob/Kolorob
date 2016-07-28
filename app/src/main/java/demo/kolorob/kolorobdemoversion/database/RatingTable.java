package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import demo.kolorob.kolorobdemoversion.model.RatingModel;

/**
 * Created by touhid on 12/26/15.
 *
 * @author touhid
 */
public class RatingTable {
    private static final String TAG = RatingTable.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.RATING;
ArrayList<RatingModel>rm=new ArrayList<>();
    private static final String KEY_ID = "_id"; // 0 -integer
    private static final String KEY_AVG = "_name"; // 1 - text
    private static final String KEY_DATE = "_date"; // 2 - text
    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.

    private Context tContext;

    public RatingTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_AVG + " TEXT, "              // 1 - text
                + KEY_DATE + " TEXT "          // 2 - text
                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(RatingModel ratingModel){
        return insertItem(
                ratingModel.getId(),
                ratingModel.getRatingvalue(),
                ratingModel.getdate()
        );
    }

    public long insertItem(int id, String name, String date) {
        if (isFieldExist(id)) {
            return updateItem(id, name, date);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_AVG, name);
        rowValue.put(KEY_DATE, date);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
       // Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0)) == id) {
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

    private long updateItem(int id, String name, String date) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_AVG, name);
        rowValue.put(KEY_DATE, date );

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList<RatingModel> getAllCategories() {
        ArrayList<RatingModel> ciList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {

                ciList.add(cursorToRating(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ciList;
    }

    private RatingModel cursorToRating(Cursor cursor) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String date= java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        return new RatingModel(id, name, date);
    }
    public String getavg(int id) {


        String avg = null;

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id, null);

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
