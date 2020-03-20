package id.asiatek.asiatrans.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseAdapter
import id.asiatek.asiatrans.viewmodel.EtalaseTabViewModel
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel
import id.asiatek.asiatrans.viewmodel.ItemTabViewModel

@Module
class EtalaseTabFragmentModule {
    @Provides
    fun provideEtalaseViewModel(): EtalaseTabViewModel {
        return EtalaseTabViewModel()
    }

    @Provides
    fun provideEtalaseAdapter(context: Context): EtalaseAdapter {
        return EtalaseAdapter(context)
    }
}