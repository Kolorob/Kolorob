package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;



public class SubCategoryTableNew extends BaseDBTable <SubCategoryItemNew> {

    private static final String TABLE_NAME = DatabaseHelper.SUB_CATEGORY_NEW;

    private static final String KEY_CAT_ID = "cat_id";
    private static final String KEY_CAT_NAME = "cat_name";
    private static final String KEY_SUB_CAT_ID = "subcat_id";
    private static final String KEY_SUB_CAT_LABEL_EN = "subcat_name";
    private static final String KEY_SUB_CAT_LABEL_BN = "subcat_bn";
    private static final String KEY_REF_ID = "ref_id";
    private static final String KEY_REF_NAME_EN = "ref_name";
    private static final String KEY_REF_NAME_BN = "ref_bn";



    public SubCategoryTableNew(Context context) {
        super(context);
    }


    public void createTable() {

        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_IDENTIFIER_ID + " INTEGER, "
                + KEY_CAT_ID + " INTEGER, "
                + KEY_CAT_NAME + " TEXT, "
                + KEY_SUB_CAT_ID + " INTEGER, "
                + KEY_SUB_CAT_LABEL_EN + " TEXT, "
                + KEY_SUB_CAT_LABEL_BN + " TEXT, "
                + KEY_REF_ID + " INTEGER, "
                + KEY_REF_NAME_EN + " TEXT, "
                + KEY_REF_NAME_BN + " TEXT, "
                + " PRIMARY KEY (" + KEY_IDENTIFIER_ID + "))";

        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(SubCategoryItemNew subCategoryItemNew){

        if(isFieldExist(subCategoryItemNew.getId())){
            return updateItem(subCategoryItemNew);
        }

        return insertItem(
                subCategoryItemNew.getId(), subCategoryItemNew.getCatId(),
                subCategoryItemNew.getCatLabel(),subCategoryItemNew.getSubCatId(),
                subCategoryItemNew.getSubCatLabel(),subCategoryItemNew.getSubCatLabelBn(),
                subCategoryItemNew.getRefId(),subCategoryItemNew.getRefLabel(),subCategoryItemNew.getRefLabelBn()

        );
    }

    public long updateItem(SubCategoryItemNew subCategoryItemNew){
        return updateItem(
                subCategoryItemNew.getId(), subCategoryItemNew.getCatId(),
                subCategoryItemNew.getCatLabel(),subCategoryItemNew.getSubCatId(),
                subCategoryItemNew.getSubCatLabel(),subCategoryItemNew.getSubCatLabelBn(),
                subCategoryItemNew.getRefId(),subCategoryItemNew.getRefLabel(),subCategoryItemNew.getRefLabelBn()
        );
    }


    private long insertItem(int id, int catId, String catName, int subCatId, String subCatLabelNameEn, String subCatLabelNameBn, int refId, String refLabelEn, String refLabelBn) {


        ContentValues rowValue = new ContentValues();

        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_CAT_ID, catId);
        rowValue.put(KEY_CAT_NAME, catName);
        rowValue.put(KEY_SUB_CAT_ID, subCatId);
        rowValue.put(KEY_SUB_CAT_LABEL_EN, subCatLabelNameEn);
        rowValue.put(KEY_SUB_CAT_LABEL_BN, subCatLabelNameBn);
        rowValue.put(KEY_REF_ID, refId);
        rowValue.put(KEY_REF_NAME_EN, refLabelEn);
        rowValue.put(KEY_REF_NAME_BN, refLabelBn);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);

        closeDB();
        return ret;
    }

    public boolean isFieldExist(int id) {
       return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, int catId, String catName, int subCatId, String subCatLabelNameEn, String subCatLabelNameBn, int refId, String refLabelEn, String refLabelBn) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_IDENTIFIER_ID, id);
        rowValue.put(KEY_CAT_ID, catId);
        rowValue.put(KEY_CAT_NAME, catName);
        rowValue.put(KEY_SUB_CAT_ID, subCatId);
        rowValue.put(KEY_SUB_CAT_LABEL_EN, subCatLabelNameEn);
        rowValue.put(KEY_SUB_CAT_LABEL_BN, subCatLabelNameBn);
        rowValue.put(KEY_REF_ID, refId);
        rowValue.put(KEY_REF_NAME_EN, refLabelEn);
        rowValue.put(KEY_REF_NAME_BN, refLabelBn);
        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }


    public ArrayList <Subcatholder> getcatSubCategories(int id) {


        ArrayList<Subcatholder> siList = new ArrayList<>();

        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_CAT_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            do {
                int subcatid = cursor.getInt(6);
                String name = cursor.getString(7);
                String name_bn = cursor.getString(8);
                siList.add(new Subcatholder(subcatid, name, name_bn));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return siList;
    }

    public SubCategoryItemNew getNodeInfo(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public ArrayList <SubCategoryItemNew> getDataListFromId(int id) {
        return super.getDataListFromId(id, TABLE_NAME, KEY_IDENTIFIER_ID);
    }

    public SubCategoryItemNew getNodeFromForeignKey(int id){
        return super.getNodeInfo(id, TABLE_NAME, KEY_CAT_ID);
    }

    public ArrayList <SubCategoryItemNew> getDataListFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_CAT_ID);
    }

    public ArrayList <SubCategoryItemNew> getAllData() {
        return super.getAllData(TABLE_NAME);
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


    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }

    public void delete(int id){
        super.delete(id, TABLE_NAME);
    }
}
