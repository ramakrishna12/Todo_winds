package com.winds.todork.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */
@Module
abstract class AppModule {
    // expose Application as an injectable context
//    @Binds
//    abstract Context bindContext(Application application);
}
