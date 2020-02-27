package id.asiatek.asiatrans.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.ui.menu.ui.cart.CartAdapter
import id.asiatek.asiatrans.viewmodel.CartFragmentViewModel

@Module
class CartFragmentModule {
    @Provides
    fun provideCartFragmentViewModel() = CartFragmentViewModel()

    @Provides
    fun provideCartAdapter(context: Context) =
        CartAdapter(context)
}