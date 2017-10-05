package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;



public class SubCategoryTableNew extends BaseDBTable <SubCategoryItemNew> {

    private static final String TABLE_NAME = DatabaseHelper.SUB_CATEGORY_NEW;
    private static final String KEY_ID = "_id";
    private static final String KEY_CAT_ID = "_cat_id"; // 0 -integer
    private static final String KEY_CAT_NAME = "_catname"; // 0 -integer
    private static final String KEY_SUB_CAT_ID = "_subcatid"; //
    private static final String KEY_SUB_CAT_LABEL_EN= "_subcatLabele";
    private static final String KEY_SUB_CAT_LABEL_BN= "_subcatLabelB";
    private static final String KEY_REF_ID = "_refId"; // 2 - text
    private static final String KEY_REF_NAME_EN = "_refname"; // 2 - text
    private static final String KEY_REF_NAME_BN = "_refnamebn"; // 2 - text



    public SubCategoryTableNew(Context context) {
        tContext = context;
        createTable();
    }

    public SubCategoryTableNew() {

    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER, "
                + KEY_CAT_ID + " INTEGER, "
                + KEY_CAT_NAME + " TEXT, "
                + KEY_SUB_CAT_ID + " INTEGER, "
                + KEY_SUB_CAT_LABEL_EN + " TEXT, "
                + KEY_SUB_CAT_LABEL_BN + " TEXT, "
                + KEY_REF_ID + " INTEGER, "
                + KEY_REF_NAME_EN + " TEXT, "
                + KEY_REF_NAME_BN + " TEXT, "
                + " PRIMARY KEY (" + KEY_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(SubCategoryItemNew subCategoryItemNew){
        return insertItem(
                subCategoryItemNew.getId(), subCategoryItemNew.getCatId(),
                subCategoryItemNew.getCatLabel(),subCategoryItemNew.getSubCatId(),
                subCategoryItemNew.getSubCatLabel(),subCategoryItemNew.getSubCatLabelBn(),
                subCategoryItemNew.getRefId(),subCategoryItemNew.getRefLabel(),subCategoryItemNew.getRefLabelBn()

        );
    }

    public long insertItem(int id, int catId, String catName, int subCatId, String subCatLabelNameEn, String subCatLabelNameBn, int refId, String refLabelEn, String refLabelBn) {
        if (isFieldExist(id)) {
            return updateItem(id,catId,catName,subCatId,subCatLabelNameEn,subCatLabelNameBn,refId,subCatLabelNameEn,subCatLabelNameBn);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_CAT_ID, catId);
        rowValue.put(KEY_CAT_NAME, catName);
        rowValue.put(KEY_SUB_CAT_ID, subCatId);
        rowValue.put(KEY_SUB_CAT_LABEL_EN,subCatLabelNameEn);
        rowValue.put(KEY_SUB_CAT_LABEL_BN, subCatLabelNameBn);
        rowValue.put(KEY_REF_ID,refId);
        rowValue.put(KEY_REF_NAME_EN,refLabelEn);
        rowValue.put(KEY_REF_NAME_BN,refLabelBn);
        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;
    }

    public boolean isFieldExist(int id) {
       return super.isFieldExist(id, TABLE_NAME, KEY_ID);
    }

    private long updateItem(int id, int catId, String catName, int subCatId, String subCatLabelNameEn, String subCatLabelNameBn, int refId, String refLabelEn, String refLabelBn) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_CAT_ID, catId);
        rowValue.put(KEY_CAT_NAME, catName);
        rowValue.put(KEY_SUB_CAT_ID, subCatId);
        rowValue.put(KEY_SUB_CAT_LABEL_EN,subCatLabelNameEn);
        rowValue.put(KEY_SUB_CAT_LABEL_BN, subCatLabelNameBn);
        rowValue.put(KEY_REF_ID,refId);
        rowValue.put(KEY_REF_NAME_EN,refLabelEn);
        rowValue.put(KEY_REF_NAME_BN,refLabelBn);
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }


    public ArrayList <Subcatholder> getcatSubCategories(int id) {


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

    public ArrayList <SubCategoryItemNew> getAllData() {
        return super.getAllData(TABLE_NAME);
    }

    public ArrayList <SubCategoryItemNew> getDataFromForeignKey(int id) {
       return super.getDataListFromId(id, TABLE_NAME, KEY_CAT_ID);
    }

    public SubCategoryItemNew getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_ID);
    }



    public SubCategoryItemNew cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        int cat_id = cursor.getInt(1);
        String catname = cursor.getString(2);
        int subcatheaderid = cursor.getInt(3);
        String headen = cursor.getString(4);
        String headbn = cursor.getString(5);
        int refid = cursor.getInt(6);
        String refLabel = cursor.getString(7);
        String refLabelBn = cursor.getString(8);
        return new SubCategoryItemNew(id,cat_id,catname, subcatheaderid,headen,headbn,refid,refLabel,refLabelBn);
    }

    public SubCategoryItemNew cursorToModel(Cursor cursor, CommonModel commonModel){
        return null;
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

    public SubCategoryItemNew getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        SubCategoryItemNew subCategoryItemNew = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                subCategoryItemNew = new SubCategoryItemNew(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getString(8));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return subCategoryItemNew;
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME, KEY_ID);
    }
}
