package id.asiatek.asiatrans.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.ui.tab_menu.tab_item.ItemAdapter
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel

@Module
class ItemTabFragmentModule {
    @Provides
    fun provideItemViewModel(): ItemTabViewModel {
        return ItemTabViewModel()
    }

    @Provides
    fun provideItemAdapter(context: Context): ItemAdapter {
        return ItemAdapter(context)
    }
}