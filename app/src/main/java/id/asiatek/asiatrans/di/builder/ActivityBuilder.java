package id.asiatek.asiatrans.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import id.asiatek.asiatrans.module.AccountProfileActivityModule;
import id.asiatek.asiatrans.module.AccountStoreActivityModule;
import id.asiatek.asiatrans.module.LoginActivityModule;
import id.asiatek.asiatrans.module.MainTabActivityModule;
import id.asiatek.asiatrans.module.RegisterActivityModule;
import id.asiatek.asiatrans.provider.HomeTabFragmentProvider;
import id.asiatek.asiatrans.provider.InboxTabFragmentProvider;
import id.asiatek.asiatrans.provider.ItemTabFragmentProvider;
import id.asiatek.asiatrans.ui.account.AccountProfileActivity;
import id.asiatek.asiatrans.ui.account.AccountStoreActivity;
import id.asiatek.asiatrans.ui.login.LoginActivity;
import id.asiatek.asiatrans.ui.register.RegisterActivity;
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity;
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {LoginActivityModule.class})
    public abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {RegisterActivityModule.class})
    public abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = {AccountProfileActivityModule.class})
    public abstract AccountProfileActivity bindAccountProfileActivity();

    @ContributesAndroidInjector(modules = {AccountStoreActivityModule.class})
    public abstract AccountStoreActivity bindAccountStoreActivity();

    @ContributesAndroidInjector(modules = {MainTabActivityModule.class, HomeTabFragmentProvider.class, ItemTabFragmentProvider.class,
            InboxTabFragmentProvider.class })
    public abstract MainTabActivity bindMainTabActivity();
}
