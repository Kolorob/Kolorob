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
        import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidLegalAdviceItem;
        import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class LegalAidtypeServiceProviderLegalAdviceTable {

    private static final String TAG = LegalAidtypeServiceProviderLegalAdviceTable.class.getSimpleName();
    private static final String TABLE_NAME = DatabaseHelper.LEGAL_AID_LEGAL_ADVICE_TABLE;
    private static final String KEY_LEGAL_ADVICE_ID = "_legaladviceId";
    private static final String KEY_IDENTIFIER_ID = "_identifier_id"; // 0 -integer
    private static final String KEY_LEGAL_SERVICE_NAME = "_legalaidservicename"; // 1 - text
    private static final String KEY_LEGAL_AID_SUBCATEGORY_ID = "_legalaidSubCategoryId"; // 2 - text*/

    private static final String KEY_LEGAL_SERVICE_FREE = "_legalaidfree"; //
    private static final String KEY_LEGAL_SERVICE_COST= "_legalaidcost"; //
    private static final String KEY_LEGAL_SERVICE_AUTHORITY = " _legalaidpersonauthority"; //
    private static final String KEY_LEGAL_REMARK = "_legalaidremark"; //

    private Context tContext;

    public LegalAidtypeServiceProviderLegalAdviceTable(Context context) {
        tContext = context;
        createTable();
    }

    private void createTable() {
        SQLiteDatabase db = openDB();

        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_LEGAL_ADVICE_ID + " INTEGER , "
                + KEY_IDENTIFIER_ID + "  INTEGER , " // 0 - int
                + KEY_LEGAL_SERVICE_NAME + "  TEXT, "              // 1 - text
                + KEY_LEGAL_AID_SUBCATEGORY_ID + " INTEGER, "
                + KEY_LEGAL_SERVICE_FREE + " TEXT, "
                + KEY_LEGAL_SERVICE_COST + " TEXT, "
                + KEY_LEGAL_SERVICE_AUTHORITY + " TEXT, "
                + KEY_LEGAL_REMARK + " TEXT, PRIMARY KEY(" + KEY_LEGAL_ADVICE_ID + ", " + KEY_IDENTIFIER_ID + "))";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }

    private SQLiteDatabase openDB() {
        return DatabaseManager.getInstance(tContext).openDatabase();
    }

    private void closeDB() {
        DatabaseManager.getInstance(tContext).closeDatabase();
    }

    public long insertItem(LegalAidLegalAdviceItem legalAidLegalAdviceItem) {
        return insertItem(
                legalAidLegalAdviceItem.getLegaladviceId(),
                legalAidLegalAdviceItem.getIdentifierId(),
                legalAidLegalAdviceItem.getLegalaidservicename(),
                legalAidLegalAdviceItem.getLegalaidSubCategoryId(),
                legalAidLegalAdviceItem.getLegalaidfree(),
                legalAidLegalAdviceItem.getLegalaidcost(),
                legalAidLegalAdviceItem.getLegalaidpersonauthority(),
                legalAidLegalAdviceItem.getLegalaidremark()
        );
    }

    private long insertItem(int legaladviceId,String identifierId , String legalaidservicename, int legalaidSubCategoryId,
                            String legalaidfree, String legalaidcost, String legalaidpersonauthority, String legalaidremark) {



        if (isFieldExist(legaladviceId,identifierId)) {
            return updateItem(
                    legaladviceId,
                    identifierId,
                    legalaidSubCategoryId,
                    legalaidservicename,
                    legalaidfree,
                    legalaidcost,
                    legalaidpersonauthority,
                    legalaidremark);

        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_LEGAL_ADVICE_ID, legaladviceId);
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_LEGAL_SERVICE_NAME, legalaidservicename);
        rowValue.put(KEY_LEGAL_AID_SUBCATEGORY_ID, legalaidSubCategoryId);
        rowValue.put(KEY_LEGAL_SERVICE_FREE, legalaidfree);
        rowValue.put(KEY_LEGAL_SERVICE_COST, legalaidcost);
        rowValue.put(KEY_LEGAL_SERVICE_AUTHORITY, legalaidpersonauthority);
        rowValue.put(KEY_LEGAL_REMARK, legalaidremark);

        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }

    private long updateItem(int legaladviceId,String identifierId, int legalaidSubCategoryId, String legalaidservicename, String legalaidfree, String legalaidcost, String legalaidpersonauthority, String legalaidremark) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_LEGAL_ADVICE_ID, legaladviceId);
        rowValue.put(KEY_IDENTIFIER_ID, identifierId);
        rowValue.put(KEY_LEGAL_SERVICE_NAME, legalaidservicename);
        rowValue.put(KEY_LEGAL_AID_SUBCATEGORY_ID, legalaidSubCategoryId);
        rowValue.put(KEY_LEGAL_SERVICE_FREE, legalaidfree);
        rowValue.put(KEY_LEGAL_SERVICE_COST, legalaidcost);
        rowValue.put(KEY_LEGAL_SERVICE_AUTHORITY, legalaidpersonauthority);
        rowValue.put(KEY_LEGAL_REMARK, legalaidremark);

        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_IDENTIFIER_ID + " = ?  AND " + KEY_LEGAL_ADVICE_ID + " = ? ",
                new String[]{identifierId + "", legaladviceId + ""});
        closeDB();
        return ret;

    }



    public boolean isFieldExist(int legaladviceId,String id) {
        //Lg.d(TAG, "isFieldExist : inside, id=" + id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                if (Integer.parseInt(cursor.getString(0)) == legaladviceId && id.equals(cursor.getString(1))) {
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


    public ArrayList<LegalAidLegalAdviceItem> getLegalAdvice(String idenId) {
        ArrayList<LegalAidLegalAdviceItem> AdviceList = new ArrayList<>();
        //System.out.println(cat_id+"  "+sub_cat_id);
        SQLiteDatabase db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_IDENTIFIER_ID+" = '"+idenId+"'" , null);

        if (cursor.moveToFirst()) {
            do {
                //System.out.println("abc="+cursor.getString(4));
                AdviceList.add(cursorToSubCatList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return AdviceList;
    }



    private LegalAidLegalAdviceItem cursorToSubCatList(Cursor cursor) {
        int _legaladviceId = cursor.getInt(0);
        String _identifierId = cursor.getString(1);
        int _legalaidSubCategoryId = cursor.getInt(2);
        String _legalaidservicename = cursor.getString(3);
        String _legalaidfree = cursor.getString(4);
        String _legalaidcost = cursor.getString(5);
        String _legalaidpersonauthority = cursor.getString(6);
        String _legalaidremark = cursor.getString(7);


        return   new LegalAidLegalAdviceItem(_legaladviceId,_identifierId, _legalaidSubCategoryId,
                _legalaidservicename, _legalaidfree, _legalaidcost,_legalaidpersonauthority,_legalaidremark);


    }

    public void dropTable() {
        SQLiteDatabase db = openDB();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        createTable();
        Lg.d(TAG, "Table dropped and recreated.");
        closeDB();
    }

}

