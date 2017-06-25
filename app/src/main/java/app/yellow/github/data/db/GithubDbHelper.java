package app.yellow.github.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GithubDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "github.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GithubPersistenceContract.GithubEntry.TABLE_NAME + " (" +
                    GithubPersistenceContract.GithubEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    GithubPersistenceContract.GithubEntry.COLUMN_NAME_URL + TEXT_TYPE + COMMA_SEP +
                    GithubPersistenceContract.GithubEntry.COLUMN_NAME_JSON + TEXT_TYPE + COMMA_SEP +
                    GithubPersistenceContract.GithubEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    " )";

    public GithubDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
