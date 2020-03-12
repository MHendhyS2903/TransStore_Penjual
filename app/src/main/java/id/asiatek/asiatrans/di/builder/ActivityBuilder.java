package id.asiatek.asiatrans.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import id.asiatek.asiatrans.module.LoginActivityModule;
import id.asiatek.asiatrans.module.RegisterActivityModule;
import id.asiatek.asiatrans.ui.login.LoginActivity;
import id.asiatek.asiatrans.ui.register.RegisterActivity;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {LoginActivityModule.class})
    public abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {RegisterActivityModule.class})
    public abstract RegisterActivity bindRegisterActivity();
}
