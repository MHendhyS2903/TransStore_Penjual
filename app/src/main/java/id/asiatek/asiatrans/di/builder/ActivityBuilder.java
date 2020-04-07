package id.asiatek.asiatrans.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import id.asiatek.asiatrans.module.AccountProfileActivityModule;
import id.asiatek.asiatrans.module.AccountStoreActivityModule;
import id.asiatek.asiatrans.module.EtalaseActivityModule;
import id.asiatek.asiatrans.module.LoginActivityModule;
import id.asiatek.asiatrans.module.MainTabActivityModule;
import id.asiatek.asiatrans.module.ProductActivityModule;
import id.asiatek.asiatrans.module.RegisterActivityModule;
import id.asiatek.asiatrans.provider.EtalaseTabFragmentProvider;
import id.asiatek.asiatrans.provider.HomeTabFragmentProvider;
import id.asiatek.asiatrans.provider.OrderTabFragmentProvider;
import id.asiatek.asiatrans.provider.ItemTabFragmentProvider;
import id.asiatek.asiatrans.ui.account.AccountProfileActivity;
import id.asiatek.asiatrans.ui.account.AccountStoreActivity;
import id.asiatek.asiatrans.ui.etalase.EtalaseActivity;
import id.asiatek.asiatrans.ui.login.LoginActivity;
import id.asiatek.asiatrans.ui.register.RegisterActivity;
import id.asiatek.asiatrans.ui.tab_menu.MainTabActivity;
import id.asiatek.asiatrans.ui.tab_menu.tab_item.ProductActivity;

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
            OrderTabFragmentProvider.class, EtalaseTabFragmentProvider.class})
    public abstract MainTabActivity bindMainTabActivity();

    @ContributesAndroidInjector(modules = {EtalaseActivityModule.class})
    public abstract EtalaseActivity bindEtalaseActivity();

    @ContributesAndroidInjector(modules = {ProductActivityModule.class})
    public abstract ProductActivity bindProductActivity();
}
