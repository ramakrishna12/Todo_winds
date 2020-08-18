package com.winds.todork.Repository;


import android.app.Application;
import android.os.AsyncTask;


import com.winds.todork.Dao.ToDODao;
import com.winds.todork.Database.ToDODatabase;
import com.winds.todork.Entity.TODO;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

public class ToDORepository {

    private ToDODao noteDao;
    private LiveData<List<TODO>> allNotes;

    public ToDORepository(Application application){
        ToDODatabase noteDatabase = ToDODatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(TODO note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(TODO note){
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(TODO note){
        new DeleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<TODO>> getAllNotes(){
        return allNotes;
    }

    private class InsertAsyncTask extends AsyncTask<TODO,Void,Void>{

        private ToDODao noteDao;

        InsertAsyncTask(ToDODao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(TODO... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<TODO,Void,Void>{

        private ToDODao noteDao;

        UpdateAsyncTask(ToDODao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(TODO... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<TODO,Void,Void>{

        private ToDODao noteDao;

        DeleteAsyncTask(ToDODao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(TODO... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{

        private ToDODao noteDao;

        DeleteAllAsyncTask(ToDODao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}

