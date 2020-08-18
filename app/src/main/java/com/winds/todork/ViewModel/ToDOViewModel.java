package com.winds.todork.ViewModel;

import android.app.Application;

import com.winds.todork.Entity.TODO;
import com.winds.todork.Repository.ToDORepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */


public class ToDOViewModel extends AndroidViewModel {

    private ToDORepository noteRepository;
    private LiveData<List<TODO>> allNotes;

    public ToDOViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new ToDORepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(TODO note) {
        noteRepository.insert(note);
    }

    public void update(TODO note) {
        noteRepository.update(note);
    }

    public void delete(TODO note) {
        noteRepository.delete(note);
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }

    public LiveData<List<TODO>> getAllNotes() {
        return allNotes;
    }

}
