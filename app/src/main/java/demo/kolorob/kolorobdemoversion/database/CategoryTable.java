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

    private static final String KEY_NAME = "name_en";
    private static final String KEY_NAME_BN = "name_bn";
    private static final String KEY_IS_ACTIVE = "is_active";



    public CategoryTable(Context context) {
        super(context);
    }

    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_NAME_BN + " TEXT, "
                + KEY_IS_ACTIVE + " TEXT "
                + " )";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(CategoryItem categoryItem){
        if(!isFieldExist(categoryItem.getId())){
            return insertItem(
                    categoryItem.getId(),
                    categoryItem.getNameEn(),
                    categoryItem.getNameBn(),
                    categoryItem.isCatActive()
            );
        }
        else{
            return updateItem(categoryItem);
        }
    }

    public long updateItem(CategoryItem categoryItem){
        return updateItem(categoryItem.getId(),
                categoryItem.getNameEn(),
                categoryItem.getNameBn(),
                categoryItem.isCatActive());
    }

    private long insertItem(int id, String name, String nameBn, boolean isActive) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_NAME_BN, nameBn);
        rowValue.put(KEY_IS_ACTIVE, isActive);

        SQLiteDatabase db = openDB();
        long insertedId = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return insertedId;
    }


    public boolean isFieldExist(int id) {
       return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, String name, String nameBn, boolean isActive) {

        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_NAME_BN, nameBn);
        rowValue.put(KEY_IS_ACTIVE, isActive + "");

        SQLiteDatabase db = openDB();
        long updatedId = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return updatedId;
    }

    public CategoryItem getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <CategoryItem> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
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

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

}
