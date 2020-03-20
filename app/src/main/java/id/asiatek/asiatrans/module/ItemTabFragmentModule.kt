package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel

@Module
class ItemTabFragmentModule {
    @Provides
    fun provideItemViewModel(): ItemTabViewModel {
        return ItemTabViewModel()
    }
}