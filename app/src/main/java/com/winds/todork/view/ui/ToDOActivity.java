package com.winds.todork.view.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.winds.todork.Entity.TODO;
import com.winds.todork.R;
import com.winds.todork.ViewModel.ToDOViewModel;
import com.winds.todork.view.Adapters.TodoAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

public class ToDOActivity extends AppCompatActivity implements View.OnClickListener {

    private ToDOViewModel noteViewModel;
    private RecyclerView recyclerView;

    private Button btButton;
    private SearchView searchView;


    private TodoAdapter noteAdapter;

    public static final int ADD_NOTE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        btButton = findViewById(R.id.btButton);
        recyclerView = findViewById(R.id.noteActivity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        noteAdapter = new TodoAdapter();
        recyclerView.setAdapter(noteAdapter);



        noteViewModel = ViewModelProviders.of(this).get(ToDOViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<TODO>>() {
            @Override
            public void onChanged(@Nullable List<TODO> notes) {
                // update Recycler View
                noteAdapter.setNotes(notes);
            }
        });

        btButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                noteAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                noteAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ToDOActivity.this, CreateToDO.class);
        startActivityForResult(intent,ADD_NOTE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String note_title = data.getStringExtra("note_title");
            String note_description = data.getStringExtra("note_description");

            noteViewModel.insert(new TODO(note_title,note_description));

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }


}
