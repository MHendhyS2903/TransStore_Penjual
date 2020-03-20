package id.asiatek.asiatrans.utils;

import android.content.Context;
import android.util.Base64;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

import id.asiatek.asiatrans.model.etalase.DataItemEtalase;
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseAdapter;

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
    public static void addItemsEtalase(RecyclerView recyclerView, List<DataItemEtalase> strings) {
        EtalaseAdapter adapter = (EtalaseAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(strings);
        }
    }
}
