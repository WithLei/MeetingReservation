package com.android.renly.meetingreservation.injector.components;

import com.android.renly.meetingreservation.injector.modules.HomeFragModule;
import com.android.renly.meetingreservation.module.home.fullscreen.HomeFrag;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = HomeFragModule.class)
public interface HomeFragComponent {
    void inject(HomeFrag frag);
}
