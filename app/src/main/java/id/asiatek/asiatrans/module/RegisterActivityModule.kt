package id.asiatek.asiatrans.module


import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.RegisterViewModel

@Module
class RegisterActivityModule {
    @Provides
    fun provideRegisterViewModel() = RegisterViewModel()
}