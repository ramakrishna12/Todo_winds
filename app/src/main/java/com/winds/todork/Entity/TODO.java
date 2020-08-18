package com.winds.todork.Entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * Created by Ramakrishna on 18-Aug-20.
 */

@Entity(tableName = "note_table")
public class TODO {

    @PrimaryKey(autoGenerate = true)
    int id;

    private String title;
    private    String description;
    private  boolean isChecked;

    public TODO(String title, String description) {
        this.title = title;
        this.description = description;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
