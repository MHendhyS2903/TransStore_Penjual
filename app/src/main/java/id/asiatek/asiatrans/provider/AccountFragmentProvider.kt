package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.AccountFragmentModule
import id.asiatek.asiatrans.ui.menu.ui.account.AccountFragment

@Module
abstract class AccountFragmentProvider {
    @ContributesAndroidInjector(modules = [AccountFragmentModule::class])
    abstract fun provideAccountFragmentFactory(): AccountFragment
}