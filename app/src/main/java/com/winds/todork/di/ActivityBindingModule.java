package com.winds.todork.di;

import com.winds.todork.view.ui.ToDOActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

@Module
public abstract class ActivityBindingModule {
    @ToDoScoped
@ContributesAndroidInjector(
        modules = {ToDoModule.class})

    abstract ToDOActivity toDOActivity();
}
