package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.OrderTabViewModel

@Module
class OrderTabFragmentModule {
    @Provides
    fun provideInboxViewModel(): OrderTabViewModel {
        return OrderTabViewModel()
    }
}