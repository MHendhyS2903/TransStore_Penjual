package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.MainTabViewModel

@Module
class MainTabActivityModule {
    @Provides
    fun provideMainTabViewModel(): MainTabViewModel = MainTabViewModel()
}