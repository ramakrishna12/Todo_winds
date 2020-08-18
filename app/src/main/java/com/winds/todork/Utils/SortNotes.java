package com.winds.todork.Utils;

import android.os.Build;

import com.winds.todork.Entity.TODO;

import java.util.Comparator;

import androidx.annotation.RequiresApi;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

public class SortNotes implements Comparator<TODO> {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compare(TODO note1, TODO note2) {
        return Boolean.compare(note1.isChecked(),note2.isChecked());
    }
}
