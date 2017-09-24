package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by Yeakub Hassan Rafi on 26-Dec-15.
 */
public class SubCategoryTable extends BaseDBTable <SubCategoryItem>{

    private static final String TABLE_NAME = DatabaseHelper.SUB_CATEGORY;

    private static final String KEY_CAT_ID = "_cat_id"; // 0 -integer
    private static final String KEY_SUB_CAT_ID = "_sub_cat_id"; // 1 -integer
    private static final String KEY_NAME = "_sub_cat_name"; // 2 - text
    private static final String KEY_SUB_CAT_HEADER= "_sub_cat_header";
    private static final String KEY_NAME_BN = "_sub_cat_name_bn"; // 2 - text
    private static final String KEY_SUB_CAT_HEADER_BN= "_sub_cat_header_bn";
    // : But boolean value,
    // for simplicity of the local table
    // structure it's kept as string.


    public SubCategoryTable(Context context) {
        tContext = context;
        createTable();
    }

    public SubCategoryTable() {

    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_CAT_ID + " INTEGER, "
                + KEY_SUB_CAT_ID + " INTEGER, "
                + KEY_NAME + " TEXT, "
                + KEY_SUB_CAT_HEADER + " TEXT, "
                + KEY_NAME_BN + " TEXT, "
                + KEY_SUB_CAT_HEADER_BN + " TEXT, "
                + " PRIMARY KEY("+KEY_CAT_ID+","+KEY_SUB_CAT_ID+"))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(SubCategoryItem subCategoryItem){
        return insertItem(
                subCategoryItem.getCatId(),
                subCategoryItem.getId(),
                subCategoryItem.getSubCatName(),
                subCategoryItem.getSubcatHeader(),
                subCategoryItem.getSubCatNameBn(),
                subCategoryItem.getSubCatHeaderBn()
        );
    }

    public long insertItem(int cat_id, int id, String name,String header,String namebn,String headerbn) {
        if (isFieldExist(id,cat_id)) {
            return updateItem(cat_id,id,name,header,namebn,headerbn);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_CAT_ID, cat_id);
        rowValue.put(KEY_SUB_CAT_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_SUB_CAT_HEADER,header);
        rowValue.put(KEY_NAME_BN, namebn);
        rowValue.put(KEY_SUB_CAT_HEADER_BN,headerbn);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    public boolean isFieldExist(int id,int cat_id) {
       // Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(1)) == id && Integer.parseInt(cursor.getString(0)) == cat_id ) {
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

    private long updateItem(int cat_id,int id, String name,String header,String namebn, String headerbn) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_CAT_ID, cat_id);
        rowValue.put(KEY_SUB_CAT_ID, id);
        rowValue.put(KEY_NAME, name);
        rowValue.put(KEY_SUB_CAT_HEADER,header);
        rowValue.put(KEY_NAME_BN, namebn);
        rowValue.put(KEY_SUB_CAT_HEADER_BN,headerbn);
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_CAT_ID + " = ? AND "+KEY_SUB_CAT_ID+" = ?",
                new String[]{cat_id + "",id+""});
        closeDB();
        return ret;
    }

    public ArrayList<Subcatholder> getcatSubCategories(int id) {


        ArrayList<Subcatholder> siList = new ArrayList<Subcatholder>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CAT_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            do {
                String catid2 =cursor.getString(4);
                int subcatid=cursor.getInt(1);
                siList.add(new Subcatholder(subcatid,catid2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }

    public ArrayList<SubCategoryItem> getAllSubCategories(int id) {
        ArrayList<SubCategoryItem> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CAT_ID + " = " + id , null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }


    public ArrayList<SubCategoryItem> getAllSubCategoriesHeader(int id,String head) {
        ArrayList<SubCategoryItem> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_CAT_ID+" = "+ id +" AND "+KEY_SUB_CAT_HEADER+" = '"+head+"'", null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }

    public SubCategoryItem cursorToModel(Cursor cursor) {
        int cat_id = cursor.getInt(0);
        int id = cursor.getInt(1);
        String name = cursor.getString(2);
        String head = cursor.getString(3);
        String namebn = cursor.getString(4);
        String headbn = cursor.getString(5);
        return new SubCategoryItem(cat_id,id, name,head,namebn,headbn);
    }

    public SubCategoryItem getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        SubCategoryItem subCategoryItem = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CAT_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                subCategoryItem = new SubCategoryItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCategoryItem;
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
