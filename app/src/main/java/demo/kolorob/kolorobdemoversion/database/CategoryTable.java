package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CategoryItem;

/**
 * Created by touhid on 12/26/15.
 *
 * @author touhid
 */
public class CategoryTable {
    private static final String TAG = CategoryTable.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.SERVICE_CATEGORY;

    private static final String KEY_ID = "_cat_id"; // 0 -integer
    private static final String KEY_NAME = "_cat_name"; // 1 - text
    private static final String KEY_IS_ACTIVE = "_is_active"; // 2 - text
    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.

    private Context tContext;

    public CategoryTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_NAME + " TEXT, "              // 1 - text
                + KEY_IS_ACTIVE + " TEXT "          // 2 - text
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

    public long insertItem(CategoryItem categoryItem){
        return insertItem(
                categoryItem.getId(),
                categoryItem.getCatName(),
                categoryItem.isCatActive()
        );
    }

    public long insertItem(int id, String name, boolean isActive) {
        if (isFieldExist(id)) {
            return updateItem(id, name, isActive);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_IS_ACTIVE, isActive);

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

    private long updateItem(int id, String name, boolean isActive) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_IS_ACTIVE, isActive + "");

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList<CategoryItem> getAllCategories() {
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

    private CategoryItem cursorToCategory(Cursor cursor) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        boolean isActive = cursor.getString(2).equals("true");
        return new CategoryItem(id, name, isActive);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        //Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
