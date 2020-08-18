package com.winds.todork.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.winds.todork.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */


public class CreateToDO extends AppCompatActivity implements View.OnClickListener {

    private EditText title;
    private EditText description;
    private Button btCancel,btDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.addNoteActivity_title);
        description = findViewById(R.id.addNoteActivity_description);
        btCancel = findViewById(R.id.btCancel);
        btDone = findViewById(R.id.btDone);
        btDone.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
               return true;
    }

    private void saveNote() {
        String note_title = title.getText().toString();
        String note_description = description.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("note_title", note_title);
        intent.putExtra("note_description", note_description);
        int id = getIntent().getIntExtra("id",-1);
        if (id != -1){
            intent.putExtra("id",id);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btDone) {
            saveNote();
        }else if (v.getId() == R.id.btCancel) {
            clearData();
        }

    }
    private void clearData() {
        title.getText().clear();
        description.getText().clear();
    }
}
