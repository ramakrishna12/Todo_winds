package com.winds.todork.Dao;




import com.winds.todork.Entity.TODO;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

@Dao
public interface ToDODao {

    @Insert
    void insert(TODO note);

    @Delete
    void delete(TODO note);

    @Update
    void update(TODO note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    @Query("SELECT * FROM note_table")
    LiveData<List<TODO>> getAllNotes();
}
