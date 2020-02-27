package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.MenuViewModel

@Module
class MenuFragmentModule {
    @Provides
    fun provideMenuFragmentViewModel() = MenuViewModel()
}