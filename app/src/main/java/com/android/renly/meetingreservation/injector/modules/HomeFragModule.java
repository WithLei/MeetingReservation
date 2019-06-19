package com.android.renly.meetingreservation.injector.modules;

import com.android.renly.meetingreservation.api.bean.TestA;
import com.android.renly.meetingreservation.api.bean.TestB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragModule {
    @Singleton
    @Provides
    public TestB provideTestB(TestA testA) {
        return new TestB(testA);
    }

    @Singleton
    @Provides
    public TestA provideTestA() {
        return new TestA();
    }
}
