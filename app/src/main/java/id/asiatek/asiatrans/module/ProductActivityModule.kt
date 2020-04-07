package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.ProductViewModel

@Module
class ProductActivityModule {
    @Provides
    fun provideProductViewModel(): ProductViewModel = ProductViewModel()
}