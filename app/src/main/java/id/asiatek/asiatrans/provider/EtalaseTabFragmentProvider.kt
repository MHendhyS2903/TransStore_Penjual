package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.EtalaseTabFragmentModule
import id.asiatek.asiatrans.module.ItemTabFragmentModule
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseTabFragment

@Module
abstract class EtalaseTabFragmentProvider {
    @ContributesAndroidInjector(modules = [EtalaseTabFragmentModule::class])
    abstract fun provideEtalaseTabFragmentFactory(): EtalaseTabFragment
}