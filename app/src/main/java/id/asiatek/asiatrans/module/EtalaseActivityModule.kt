package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.AccountProfileViewModel
import id.asiatek.asiatrans.viewmodel.EtalaseViewModel
import id.asiatek.asiatrans.viewmodel.MainTabViewModel

@Module
class EtalaseActivityModule {
    @Provides
    fun provideEtalaseViewModel(): EtalaseViewModel = EtalaseViewModel()
}