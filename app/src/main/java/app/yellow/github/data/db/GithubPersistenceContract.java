package app.yellow.github.data.db;

import android.provider.BaseColumns;

public final class GithubPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private GithubPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class GithubEntry implements BaseColumns {
        public static final String TABLE_NAME = "github_db";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_JSON = "json";
        public static final String COLUMN_NAME_DATE = "date";
    }
}