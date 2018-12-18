package com.android.renly.meetingreservation.injector.components;

import android.content.Context;

import com.android.renly.meetingreservation.injector.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();

}
