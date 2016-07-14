package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by Yeakub Hassan Rafi on 26-Dec-15.
 */
public class SubCategoryTable {
    private static final String TAG = SubCategoryTable.class.getSimpleName();

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

    private Context tContext;

    public SubCategoryTable(Context context) {
        tContext = context;
        createTable();
    }

    public SubCategoryTable() {

    }

    private void createTable() {
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

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CAT_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToSubCategory(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }
    public ArrayList<String> getSubnameedu(int id,String head) {
        ArrayList<String> siList=new ArrayList<>();

        SQLiteDatabase db = openDB();
        int i=0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_CAT_ID+" = "+ id, null);


        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(2);
                int subid=cursor.getInt(5);
                siList.add(subid,name);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }
   /* public ArrayList<Integer> getSubid(int id,String head) {
        ArrayList<Integer> siList2=new ArrayList<>();

        SQLiteDatabase db = openDB();
        int i=0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_CAT_ID+" = "+ id +" AND "+KEY_SUB_CAT_HEADER+" = '"+head+"'", null);


        if (cursor.moveToFirst()) {
            do {
               ;
                int subid=cursor.getInt(1);
                siList2.add(i,subid);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList2;
    }*/
    public ArrayList<SubCategoryItem> getAllSubCategoriesHeader(int id,String head) {
        ArrayList<SubCategoryItem> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_CAT_ID+" = "+ id +" AND "+KEY_SUB_CAT_HEADER+" = '"+head+"'", null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToSubCategory(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }

    private SubCategoryItem cursorToSubCategory(Cursor cursor) {
        int cat_id = cursor.getInt(0);
        int id = cursor.getInt(1);
        String name = cursor.getString(2);
        String head = cursor.getString(3);
        String namebn = cursor.getString(4);
        String headbn = cursor.getString(5);
        return new SubCategoryItem(cat_id,id, name,head,namebn,headbn);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
