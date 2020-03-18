package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.AccountProfileViewModel
import id.asiatek.asiatrans.viewmodel.AccountStoreViewModel
import id.asiatek.asiatrans.viewmodel.MainTabViewModel

@Module
class AccountStoreActivityModule {
    @Provides
    fun provideAccountStoreViewModel(): AccountStoreViewModel = AccountStoreViewModel()
}