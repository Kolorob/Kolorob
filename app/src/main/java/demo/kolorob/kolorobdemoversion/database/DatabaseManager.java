package demo.kolorob.kolorobdemoversion.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by touhid on 12/26/15.
 * @author touhid
 */
public class DatabaseManager {

    private static DatabaseManager mInstance;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    private AtomicInteger mOpenCounter;

    private DatabaseManager(Context context) {
        this.mDatabase = null;
        this.mOpenCounter = new AtomicInteger();
        this.mDBHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseManager(context);
        }
        return mInstance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = mDBHelper.getWritableDatabase();
        }
        if (mDatabase == null || !mDatabase.isOpen()) {
            mDatabase = mDBHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }

}
