package com.winds.todork.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Ramakrishna on 18-Aug-20.
 */
@Scope
@Documented
@Retention(RUNTIME)
@interface ToDoScoped {
}
