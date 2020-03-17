package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.AccountProfileViewModel
import id.asiatek.asiatrans.viewmodel.MainTabViewModel

@Module
class AccountProfileActivityModule {
    @Provides
    fun provideAccountProfileViewModel(): AccountProfileViewModel = AccountProfileViewModel()
}