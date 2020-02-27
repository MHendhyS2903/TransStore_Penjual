package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.ItemDetailViewModel

@Module
class ItemDetailActivityModule {
    @Provides
    fun provideItemDetailViewModel() = ItemDetailViewModel()
}