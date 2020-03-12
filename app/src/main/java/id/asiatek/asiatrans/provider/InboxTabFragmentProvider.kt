package id.asiatek.asiatrans.provider

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.asiatek.asiatrans.module.HomeTabFragmentModule
import id.asiatek.asiatrans.module.InboxTabFragmentModule
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment
import id.asiatek.asiatrans.ui.tab_menu.tab_inbox.InboxTabFragment

@Module
abstract class InboxTabFragmentProvider {
    @ContributesAndroidInjector(modules = [InboxTabFragmentModule::class])
    abstract fun provideInboxTabFragmentFactory(): InboxTabFragment
}