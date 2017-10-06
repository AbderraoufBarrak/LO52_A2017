package fr.utbm.lpp.ffbad;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import fr.utbm.lpp.ffbad.data.db.FFbadDbHelper;

public class FFBadApplication extends Application {
    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        FFbadDbHelper helper = new FFbadDbHelper(this);
        db = helper.getReadableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
