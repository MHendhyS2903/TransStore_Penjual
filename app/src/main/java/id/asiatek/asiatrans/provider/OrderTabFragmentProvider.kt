package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.OrderTabFragmentModule
import id.asiatek.asiatrans.ui.tab_menu.tab_order.OrderTabFragment

@Module
abstract class OrderTabFragmentProvider {
    @ContributesAndroidInjector(modules = [OrderTabFragmentModule::class])
    abstract fun provideInboxTabFragmentFactory(): OrderTabFragment
}