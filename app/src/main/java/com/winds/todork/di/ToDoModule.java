package com.winds.todork.di;

import com.winds.todork.view.Adapters.TodoAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */
@Module
public class ToDoModule {

    @Provides
    static TodoAdapter getTodoAdapter(){
        return  new TodoAdapter();

    }
}
