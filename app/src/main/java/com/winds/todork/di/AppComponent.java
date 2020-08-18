package com.winds.todork.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        })
public interface AppComponent extends AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
