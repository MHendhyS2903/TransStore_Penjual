package id.asiatek.asiatrans.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import id.asiatek.asiatrans.module.AccountDetailActivityModule;
import id.asiatek.asiatrans.module.ItemDetailActivityModule;
import id.asiatek.asiatrans.module.LoginActivityModule;
import id.asiatek.asiatrans.module.MenuFragmentModule;
import id.asiatek.asiatrans.module.RegisterActivityModule;
import id.asiatek.asiatrans.provider.AccountFragmentProvider;
import id.asiatek.asiatrans.provider.CartFragmentProvider;
import id.asiatek.asiatrans.provider.FavoriteFragmentProvider;
import id.asiatek.asiatrans.provider.HomeFragmentProvider;
import id.asiatek.asiatrans.ui.detail.ItemDetailActivity;
import id.asiatek.asiatrans.ui.login.LoginActivity;
import id.asiatek.asiatrans.ui.menu.MenuActivity;
import id.asiatek.asiatrans.ui.menu.ui.account.AccountDetailActivity;
import id.asiatek.asiatrans.ui.register.RegisterActivity;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {LoginActivityModule.class})
    public abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {ItemDetailActivityModule.class})
    public abstract ItemDetailActivity bindItemDetailActivity();

    @ContributesAndroidInjector(modules = {RegisterActivityModule.class})
    public abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = {MenuFragmentModule.class, HomeFragmentProvider.class, FavoriteFragmentProvider.class, CartFragmentProvider.class, AccountFragmentProvider.class})
    public abstract MenuActivity bindMenuActivity();

    @ContributesAndroidInjector(modules = {AccountDetailActivityModule.class})
    public abstract AccountDetailActivity bindAccountDetailActivity();
}
