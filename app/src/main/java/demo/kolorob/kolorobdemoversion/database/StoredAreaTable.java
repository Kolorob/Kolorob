package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.StoredArea;

/**
 * Created by HP on 2/13/2017.
 */

public class StoredAreaTable {
    private static final String TAG = StoredAreaTable.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.AREASTORED;

    private static final String WARDID = "_ward_id"; // 0 -integer
    private static final String AREANAME = "_area_name"; // 1 - text
    private static final String AREANAMEBN = "_area_nameBn"; // 1 - text
    private static final String LOCATION = "_location"; // 1 - text

    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.

    private Context tContext;

    public StoredAreaTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + WARDID + " TEXT, " // 0 - int
                + AREANAME + " TEXT, "
                + AREANAMEBN + " TEXT, "
                + LOCATION + " TEXT, " // 1 - tex// 2 - text
                + " PRIMARY KEY("+WARDID+","+AREANAME+"))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(StoredArea categoryItem){
        return insertItem(
                categoryItem.getWardid(),
                categoryItem.getAreaid(),categoryItem.getAreaBn(),categoryItem.getLoc()

        );
    }

    public long insertItem(String id, String name,String areabn,String loc) {
        if (isFieldExist(id,name)) {
            return updateItem(id, name,areabn,loc);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(WARDID, id);
        rowValue.put(AREANAME, name);
        rowValue.put(AREANAMEBN, areabn);
        rowValue.put(LOCATION, loc);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }
    public ArrayList<String> getAllCatNames() {
        ArrayList<String> ciList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(1);
                ciList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ciList;
    }

    public boolean isFieldExist(String id,String key) {
        // Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (id.equals(cursor.getString(0))&& key.equals(cursor.getString(1))) {
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

    private long updateItem(String id, String name,String areabn,String loc) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(WARDID, id);
        rowValue.put(AREANAME, name);
        rowValue.put(AREANAMEBN, areabn);
        rowValue.put(LOCATION, loc);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, WARDID + " = ? AND "+AREANAME+ " =?",
                new String[]{id + "",name+ ""});
        closeDB();
        return ret;
    }
    public ArrayList<StoredArea> getAllstored() {
        ArrayList<StoredArea> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToSubCategory(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }
    public ArrayList<StoredArea> getstoredlocation(int id,String keyword) {
        ArrayList<StoredArea> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+ WARDID+ " = "+id+ " AND "+ AREANAME+" = '"+keyword+"'", null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToSubCategory(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }
    public void delete(String ward,String area)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(StoredAreaTable.this.tContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME, WARDID + "=" + ward + " and " + AREANAME + "= '"+ area +"'", null);
        database.close();
    }
   /* public ArrayList<CategoryItem> getAllCategories() {
        ArrayList<CategoryItem> ciList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {

                ciList.add(cursorToCategory(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ciList;
    }

    */
   private StoredArea cursorToSubCategory(Cursor cursor) {

       String wardid = cursor.getString(0);
       String areaname = cursor.getString(1);
       String areanamebn = cursor.getString(2);
       String loc = cursor.getString(3);
       return new StoredArea(wardid,areaname,areanamebn,loc);
   }
    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        //Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}