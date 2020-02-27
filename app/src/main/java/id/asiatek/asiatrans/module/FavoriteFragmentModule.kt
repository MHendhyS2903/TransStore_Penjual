package id.asiatek.asiatrans.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.asiatek.asiatrans.ui.menu.ui.favorite.WishlistAdapter
import id.asiatek.asiatrans.viewmodel.FavoriteFragmentViewModel

@Module
class FavoriteFragmentModule {
    @Provides
    fun provideFavoriteFragmentViewModel() = FavoriteFragmentViewModel()

    @Provides
    fun provideWishlistAdapter(context: Context) =
        WishlistAdapter(context)
}