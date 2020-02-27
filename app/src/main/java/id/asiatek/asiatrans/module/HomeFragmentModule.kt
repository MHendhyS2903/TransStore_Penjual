package id.asiatek.asiatrans.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.ui.menu.ui.home.HomeAdapter
import id.asiatek.asiatrans.viewmodel.HomeFragmentViewModel

@Module
class HomeFragmentModule {
    @Provides
    fun provideHomeFragmentViewModel() = HomeFragmentViewModel()

    @Provides
    fun provideOrderAdapter(context: Context) =
        HomeAdapter(context)
}