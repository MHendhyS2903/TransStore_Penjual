package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.HomeTabFragmentModule
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment

@Module
abstract class HomeTabFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeTabFragmentModule::class])
    abstract fun provideHomeTabFragmentFactory(): HomeTabFragment
}