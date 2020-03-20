package id.asiatek.asiatrans.module

import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.viewmodel.HomeTabViewModel
import id.asiatek.asiatrans.viewmodel.InboxTabViewModel

@Module
class InboxTabFragmentModule {
    @Provides
    fun provideInboxViewModel(): InboxTabViewModel {
        return InboxTabViewModel()
    }
}