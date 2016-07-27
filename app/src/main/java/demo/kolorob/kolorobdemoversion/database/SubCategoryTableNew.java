package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.Lg;


public class SubCategoryTableNew {
    private static final String TAG = SubCategoryTableNew.class.getSimpleName();

    private static final String TABLE_NAME = DatabaseHelper.SUB_CATEGORY_NEW;

    private static final String KEY_CAT_ID = "_cat_id"; // 0 -integer
    private static final String KEY_CAT_NAME = "_catname"; // 0 -integer
    private static final String KEY_SUB_CAT_ID = "_subcatheaderid"; //
    private static final String KEY_SUB_CAT_HEADER_EN= "_headen";
    private static final String KEY_SUB_CAT_HEADER_BN= "_headbn";
    private static final String KEY_REF_ID = "_subcatid"; // 2 - text
    private static final String KEY_REF_NAME_EN = "_subcatname"; // 2 - text
    private static final String KEY_REF_NAME_BN = "_subcatnamebn"; // 2 - text


    private Context tContext;

    public SubCategoryTableNew(Context context) {
        tContext = context;
        createTable();
    }

    public SubCategoryTableNew() {

    }

    private void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_CAT_ID + " INTEGER, "
                + KEY_CAT_NAME + " TEXT, "
                + KEY_SUB_CAT_ID + " INTEGER, "
                + KEY_SUB_CAT_HEADER_EN + " TEXT, "
                + KEY_SUB_CAT_HEADER_BN + " TEXT, "
                + KEY_REF_ID + " INTEGER, "
                + KEY_REF_NAME_EN + " TEXT, "
                + KEY_REF_NAME_BN + " TEXT, "
                + " PRIMARY KEY("+KEY_CAT_ID+","+KEY_SUB_CAT_ID+","+KEY_REF_ID+"))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(SubCategoryItemNew subCategoryItemNew){
        return insertItem(
                subCategoryItemNew.getCatId(),
                subCategoryItemNew.getCatName(),subCategoryItemNew.getSubCatHeaderId(),
                subCategoryItemNew.getSubCatHeaderNameEn(),subCategoryItemNew.getSubCatHeaderNameBn(),
                subCategoryItemNew.getSubCatId(),subCategoryItemNew.getSubCatNameEn(),subCategoryItemNew.getSubCatNameBn()

        );
    }

    public long insertItem(int catId, String catName, int subCatHeaderId, String subCatHeaderNameEn, String subCatHeaderNameBn, int subCatId, String subCatNameEn, String subCatNameBn) {
        if (isFieldExist(catId,subCatHeaderId,subCatId)) {
            return updateItem(catId,catName,subCatHeaderId,subCatHeaderNameEn,subCatHeaderNameBn,subCatId,subCatNameEn,subCatNameBn);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_CAT_ID, catId);
        rowValue.put(KEY_CAT_NAME, catName);
        rowValue.put(KEY_SUB_CAT_ID, subCatHeaderId);
        rowValue.put(KEY_SUB_CAT_HEADER_EN,subCatHeaderNameEn);
        rowValue.put(KEY_SUB_CAT_HEADER_BN, subCatHeaderNameBn);
        rowValue.put(KEY_REF_ID,subCatId);
        rowValue.put(KEY_REF_NAME_EN,subCatNameEn);
        rowValue.put(KEY_REF_NAME_BN,subCatNameBn);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;
    }

    public boolean isFieldExist(int id,int cat_id,int refid) {
       // Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == id &&cursor.getInt(2) == cat_id &&cursor.getInt(5) == refid ) {
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

    private long updateItem( int catId, String catName, int subCatHeaderId, String subCatHeaderNameEn, String subCatHeaderNameBn, int subCatId, String subCatNameEn, String subCatNameBn) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_CAT_ID, catId);
        rowValue.put(KEY_CAT_NAME, catName);
        rowValue.put(KEY_SUB_CAT_ID, subCatHeaderId);
        rowValue.put(KEY_SUB_CAT_HEADER_EN,subCatHeaderNameEn);
        rowValue.put(KEY_SUB_CAT_HEADER_BN, subCatHeaderNameBn);
        rowValue.put(KEY_REF_ID,subCatId);
        rowValue.put(KEY_REF_NAME_EN,subCatNameEn);
        rowValue.put(KEY_REF_NAME_BN,subCatNameBn);
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_CAT_ID + " = ? AND "+KEY_SUB_CAT_ID+" = ?",
                new String[]{catId + "",subCatHeaderId+""});
        closeDB();
        return ret;
    }
    public ArrayList<Subcatholder> getcatSubCategories(int id) {


        ArrayList<Subcatholder> siList = new ArrayList<Subcatholder>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CAT_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            do {
                String catid2 =cursor.getString(7);
                int subcatid=cursor.getInt(5);
                siList.add(new Subcatholder(subcatid,catid2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }
    public ArrayList<SubCategoryItemNew> getAllSubCat() {
        ArrayList<SubCategoryItemNew> siList = new ArrayList<>();

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
    public ArrayList<SubCategoryItemNew> getAllSubCategories(int id) {
        ArrayList<SubCategoryItemNew> siList = new ArrayList<>();

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
    public ArrayList<SubCategoryItemNew> getAllSubCategories() {
        ArrayList<SubCategoryItemNew> siList = new ArrayList<>();

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
    public ArrayList<String> getSubnameedu(int id) {
        ArrayList<String> siList=new ArrayList<>();

//        Log.d("CategoryId","======="+id);

        SQLiteDatabase db = openDB();
        int i=0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_CAT_ID+" = "+ id, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(7);
                siList.add(i,name);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }

    public int  getRefId(String name) {
        int ids=0;
        Log.d("Ent Name","######"+name);

        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_REF_NAME_BN+" = '"+name+"'", null);

        if (cursor.moveToFirst()) {
            do {
                ids = cursor.getInt(5);
                Log.d("cursor","######"+ids);
            } while (cursor.moveToNext());
        }




        cursor.close();
        closeDB();
        return ids;
    }
    public int  getSubcategoryId(String name) {
        int ids=0;
        Log.d("Ent Name","######"+name);

        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_SUB_CAT_HEADER_BN+" = '"+name+"'", null);

        if (cursor.moveToFirst()) {
            do {
                ids = cursor.getInt(2);
                Log.d("cursor","######"+ids);
            } while (cursor.moveToNext());
        }




        cursor.close();
        closeDB();
        return ids;
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
    public ArrayList<SubCategoryItemNew> getAllSubCategoriesHeader(int id,String head) {
        ArrayList<SubCategoryItemNew> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+KEY_CAT_ID+" = "+ id +" AND "+KEY_SUB_CAT_HEADER_EN+" = '"+head+"'", null);

        if (cursor.moveToFirst()) {
            do {
                siList.add(cursorToSubCategory(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }

    private SubCategoryItemNew cursorToSubCategory(Cursor cursor) {
        int cat_id = cursor.getInt(0);
        String catname = cursor.getString(1);
        int subcatheaderid = cursor.getInt(2);
        String headen = cursor.getString(3);
        String headbn = cursor.getString(4);
        int subcatid = cursor.getInt(5);
        String subcatname = cursor.getString(6);
        String subcatnamebn = cursor.getString(7);
        return new SubCategoryItemNew(cat_id,catname, subcatheaderid,headen,headbn,subcatid,subcatname,subcatnamebn);
    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }
}
