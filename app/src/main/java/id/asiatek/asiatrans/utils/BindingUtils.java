package id.asiatek.asiatrans.utils;

import android.content.Context;
import android.util.Base64;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

import id.asiatek.asiatrans.model.account.DataAccountValue;
import id.asiatek.asiatrans.model.cart.DataCartOrder;
import id.asiatek.asiatrans.model.item.DataItemValue;
import id.asiatek.asiatrans.model.wishlist.DataWishlist;
import id.asiatek.asiatrans.ui.menu.ui.account.AccountAdapter;
import id.asiatek.asiatrans.ui.menu.ui.cart.CartAdapter;
import id.asiatek.asiatrans.ui.menu.ui.favorite.WishlistAdapter;
import id.asiatek.asiatrans.ui.menu.ui.home.HomeAdapter;

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("imageBase64")
    public static void setImageBase64(ImageView imageView, String base) {
        byte[] imageByteArray = Base64.decode(base, Base64.DEFAULT);
        Context context = imageView.getContext();
        Glide.with(context).load(imageByteArray).into(imageView);
    }

    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager viewPager, int item) {
        viewPager.setCurrentItem(item, true);
    }

    @BindingAdapter({"adapter"})
    public static void addItemsRecomend(RecyclerView recyclerView, List<DataItemValue> strings) {
        HomeAdapter adapter = (HomeAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(strings);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addItemsWishlist(RecyclerView recyclerView, List<DataWishlist> strings) {
        WishlistAdapter adapter = (WishlistAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(strings);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addItemsCart(RecyclerView recyclerView, List<DataCartOrder> strings) {
        CartAdapter adapterCart = (CartAdapter) recyclerView.getAdapter();
        if (adapterCart != null) {
            adapterCart.clearItems();
            adapterCart.addItems(strings);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addItemsAccount(RecyclerView recyclerView, List<DataAccountValue> strings) {
        AccountAdapter adapterAccount = (AccountAdapter) recyclerView.getAdapter();
        if (adapterAccount != null) {
            adapterAccount.clearItems();
            adapterAccount.addItems(strings);
        }
    }
}
