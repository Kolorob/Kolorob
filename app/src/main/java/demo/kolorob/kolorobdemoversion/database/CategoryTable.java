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
public class CategoryTable extends BaseDBTable <CategoryItem> {

    private static final String TABLE_NAME = DatabaseHelper.SERVICE_CATEGORY;
    private static final String KEY_ID = "_cat_id"; // 0 -integer
    private static final String KEY_NAME = "_cat_name"; // 1 - text
    private static final String KEY_NAME_BN = "_cat_nameBn";
    private static final String KEY_IS_ACTIVE = "_is_active"; // 2 - text
    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.


    public CategoryTable(Context context) {
        tContext = context;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_NAME + " TEXT, "              // 1 - text
                + KEY_NAME_BN + " TEXT, "
                + KEY_IS_ACTIVE + " TEXT "          // 2 - text
                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(CategoryItem categoryItem){
        return insertItem(
                categoryItem.getId(),
                categoryItem.getNameEn(),
                categoryItem.getNameBn(),
                categoryItem.isCatActive()
        );
    }

    public long insertItem(int id, String name, String nameBn, boolean isActive) {
        if (isFieldExist(id)) {
            return updateItem(id, name, nameBn, isActive);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_NAME_BN, nameBn);
        rowValue.put(KEY_IS_ACTIVE, isActive);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return insertedId;
    }


    public boolean isFieldExist(int id) {
       return super.isFieldExist(id, TABLE_NAME, KEY_ID);
    }

    private long updateItem(int id, String name, String nameBn, boolean isActive) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_NAME_BN, nameBn);
        rowValue.put(KEY_IS_ACTIVE, isActive + "");

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return updatedId;
    }

    public ArrayList <CategoryItem> getAllData() {
       return super.getAllData(TABLE_NAME);
    }

    public CategoryItem cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String nameBn = cursor.getString(2);
        boolean isActive = cursor.getString(3).equals("true");
        return new CategoryItem(id, name, nameBn, isActive);
    }

    public CategoryItem getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_ID);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

    public CategoryItem getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        CategoryItem categoryItem = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                categoryItem = new CategoryItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3).equals("true"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return categoryItem;
    }
}
