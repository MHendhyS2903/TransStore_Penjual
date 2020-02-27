package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.CartFragmentModule
import id.asiatek.asiatrans.ui.menu.ui.cart.CartFragment

@Module
abstract class CartFragmentProvider {
    @ContributesAndroidInjector(modules = [CartFragmentModule::class])
    abstract fun provideAccountFragmentFactory(): CartFragment
}