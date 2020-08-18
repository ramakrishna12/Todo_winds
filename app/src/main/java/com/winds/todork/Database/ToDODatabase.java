package com.winds.todork.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.winds.todork.Dao.ToDODao;
import com.winds.todork.Entity.TODO;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

@Database(entities = TODO.class,version = 1)
public abstract class ToDODatabase extends RoomDatabase {

    public abstract ToDODao noteDao();

    private static ToDODatabase instance;

    public static synchronized ToDODatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, ToDODatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ToDODao noteDao;
        private PopulateDbAsyncTask(ToDODatabase noteDatabase){
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new TODO("Winds E World","SampleData Aded"));

            return null;
        }
    }
}
