package id.asiatek.asiatrans.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.ui.menu.ui.account.AccountAdapter
import id.asiatek.asiatrans.viewmodel.AccountFragmentViewModel

@Module
class AccountFragmentModule {
    @Provides
    fun provideAccountFragmentViewModel() = AccountFragmentViewModel()

    @Provides
    fun provideAccountAdapter(context: Context) =
        AccountAdapter(context)
}