package com.android.renly.meetingreservation.injector.components;


import com.android.renly.meetingreservation.injector.PerFragment;
import com.android.renly.meetingreservation.injector.modules.MineFragModule;
import com.android.renly.meetingreservation.module.mine.MineFrag;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = MineFragModule.class)
public interface MineFragComponent {
    void inject(MineFrag fragment);
}
