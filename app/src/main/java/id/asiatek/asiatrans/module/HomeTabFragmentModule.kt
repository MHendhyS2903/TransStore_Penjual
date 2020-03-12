package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel

@Module
class HomeTabFragmentModule {
    @Provides
    fun provideHomeViewModel(): HomeTabViewModel {
        return HomeTabViewModel()
    }
}