package id.asiatek.asiatrans.module


import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.AccountDetailViewModel
import id.asiatek.asiatrans.viewmodel.LoginViewModel

@Module
class AccountDetailActivityModule {
    @Provides
    fun provideAccountDetailViewModel() = AccountDetailViewModel()
}