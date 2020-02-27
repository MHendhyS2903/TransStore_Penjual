package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.FavoriteFragmentModule
import id.asiatek.asiatrans.ui.menu.ui.favorite.FavoriteFragment

@Module
abstract class FavoriteFragmentProvider {
    @ContributesAndroidInjector(modules = [FavoriteFragmentModule::class])
    abstract fun provideFavoriteFragmentFactory(): FavoriteFragment
}