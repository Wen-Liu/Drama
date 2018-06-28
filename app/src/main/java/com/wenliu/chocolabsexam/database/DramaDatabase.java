package com.wenliu.chocolabsexam.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.wenliu.chocolabsexam.DramaApplication;


@Database(entities = DramaEntry.class, version = 1, exportSchema = false)
public abstract class DramaDatabase extends RoomDatabase {

    private static DramaDatabase sInstance;

    public static DramaDatabase getDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                                    DramaDatabase.class, "drama").build();
        }
        return sInstance;
    }

    public static void onDestroy() {
        sInstance = null;
    }

    public abstract DramaDao getDramaDao();

}
