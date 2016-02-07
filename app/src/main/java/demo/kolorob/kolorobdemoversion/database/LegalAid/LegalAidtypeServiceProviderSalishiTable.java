package demo.kolorob.kolorobdemoversion.database.LegalAid;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidSalishiItem;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class LegalAidtypeServiceProviderSalishiTable {
    private static final String TAG = LegalAidtypeServiceProviderSalishiTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.LEGAL_AID_LEGAL_ADVICE_TABLE;
    private static final String KEY_LEGAL_SALISHI_ID = "_sId";
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer

    private static final String KEY_LEGAL_AID_SUBCATEGORY_ID = "_legalaidSubCategoryId"; // 2 - text*/
    private static final String KEY_LEGAL_SALISHI_NAME = "_sservicename"; // 1 - text
    private static final String KEY_LEGAL_SALISHI_FREE = "_sfree"; //
    private static final String KEY_LEGAL_SALISHI_COST= "_scost"; //
    private static final String KEY_LEGAL_SALISHI_AUTHORITY = "_spersonauthority"; //
    private static final String KEY_SALISHI_REMARK = "_sremark"; //

    private Context tContext;

    public LegalAidtypeServiceProviderSalishiTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_LEGAL_SALISHI_ID + " TEXT , "
                + KEY_IDENTIFIER_ID + "  TEXT  , " // 0 - int "
                + KEY_LEGAL_AID_SUBCATEGORY_ID + " INTEGER, "
                + KEY_LEGAL_SALISHI_NAME + " TEXT, "
                + KEY_LEGAL_SALISHI_FREE + " TEXT, "
                + KEY_LEGAL_SALISHI_COST + " TEXT, "
                + KEY_LEGAL_SALISHI_AUTHORITY + " TEXT, "
                + KEY_SALISHI_REMARK + " TEXT,   PRIMARY KEY(" + KEY_LEGAL_SALISHI_ID + ", " + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(LegalAidSalishiItem legalAidSalishiItem) {
        return insertItem(
                legalAidSalishiItem.getsId(),
                legalAidSalishiItem.getIdentifierId(),
                legalAidSalishiItem.getLegalaidSubCategoryId(),
                legalAidSalishiItem.getSservicename(),
                legalAidSalishiItem.getSfree(),
                legalAidSalishiItem.getScost(),
                legalAidSalishiItem.getSpersonauthority(),
                legalAidSalishiItem.getSremark()
        );
    }

    private long insertItem(String sid, String identifierId, int legalaidSubCategoryId, String sservicename, String sfree, String scost, String spersonauthority, String sremark) {
        if (isFieldExist(sid,identifierId)) {
            return updateItem(
                    sid,
                    identifierId,
                    legalaidSubCategoryId,
                    sservicename,
                    sfree,
                    scost,
                    spersonauthority,
                    sremark);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_LEGAL_SALISHI_ID, sid);
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_LEGAL_AID_SUBCATEGORY_ID, legalaidSubCategoryId);
        rowValue.put(KEY_LEGAL_SALISHI_NAME, sservicename);
        rowValue.put(KEY_LEGAL_SALISHI_FREE, sfree);
        rowValue.put(KEY_LEGAL_SALISHI_COST, scost);
        rowValue.put(KEY_LEGAL_SALISHI_AUTHORITY, spersonauthority);
        rowValue.put(KEY_SALISHI_REMARK, sremark);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;}


    private long updateItem(String sid, String identifierId, int legalaidSubCategoryId, String sservicename, String sfree, String scost, String spersonauthority, String sremark) {

        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_LEGAL_SALISHI_ID, sid);
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_LEGAL_AID_SUBCATEGORY_ID, legalaidSubCategoryId);
        rowValue.put(KEY_LEGAL_SALISHI_NAME, sservicename);
        rowValue.put(KEY_LEGAL_SALISHI_FREE, sfree);
        rowValue.put(KEY_LEGAL_SALISHI_COST, scost);
        rowValue.put(KEY_LEGAL_SALISHI_AUTHORITY, spersonauthority);
        rowValue.put(KEY_SALISHI_REMARK, sremark);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  AND " + KEY_LEGAL_SALISHI_ID + " = ? ",
                new String[]{identifierId + "", sid + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(String sid,String id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (sid.equals(cursor.getString(0) )&& id.equals(cursor.getString(1))) {
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


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)


    public ArrayList<LegalAidSalishiItem> getLegalSalishi(String idenId) {
        ArrayList<LegalAidSalishiItem> SalishiList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_IDENTIFIER_ID+" = '"+idenId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                SalishiList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return SalishiList;
    }



    private LegalAidSalishiItem cursorToSubCatList(Cursor cursor) {
        String _sId = cursor.getString(0);
        String _identifierId = cursor.getString(1);
        int _legalaidSubCategoryId = cursor.getInt(2);
        String _sservicename = cursor.getString(3);
        String _sfree = cursor.getString(4);
        String _scost = cursor.getString(5);
        String _spersonauthority = cursor.getString(6);
        String _sremark = cursor.getString(7);


        return new LegalAidSalishiItem(_sId,_identifierId, _legalaidSubCategoryId,
                _sservicename, _sfree, _scost,_spersonauthority,_sremark);


    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}

