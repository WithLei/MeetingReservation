package com.android.renly.meetingreservation.injector.modules;

import com.android.renly.meetingreservation.injector.PerFragment;
import com.android.renly.meetingreservation.module.mine.MineFrag;
import com.android.renly.meetingreservation.module.mine.MineFragPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MineFragModule {
    private final MineFrag mView;

    public MineFragModule(MineFrag mView){
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public MineFragPresenter provideMineFragPresenter(){
        return new MineFragPresenter(mView);
    }
}
