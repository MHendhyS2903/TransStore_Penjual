package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.HomeFragmentModule
import id.asiatek.asiatrans.ui.menu.ui.home.HomeFragment

@Module
abstract class HomeFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun provideHomeFragmentFactory(): HomeFragment
}