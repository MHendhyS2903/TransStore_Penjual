package id.asiatek.asiatrans.module


import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.LoginViewModel

@Module
class LoginActivityModule {
    @Provides
    fun provideLoginViewModel() = LoginViewModel()
}