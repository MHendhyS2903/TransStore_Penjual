package id.asiatek.asiatrans.ui.menu.ui.favorite;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.asiatek.asiatrans.R;
import id.asiatek.asiatrans.databinding.ItemFavoriteBinding;
import id.asiatek.asiatrans.databinding.ProgressRvBinding;
import id.asiatek.asiatrans.model.wishlist.DataWishlist;
import id.asiatek.asiatrans.viewmodel.FavoriteItemViewModel;

public class WishlistAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<DataWishlist> item = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private static Context context;

    public WishlistAdapter(Context context) {
        WishlistAdapter.context = context;
    }

    public void clearItems() {
        item.clear();
    }

    public void addItems(List<DataWishlist> item) {
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    public DataWishlist getItemAtPosition(int position) {
        return item.get(position);
    }

    public int getListItem() {
        return item.size();
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickItem(View view, DataWishlist obj);
    }

    @Override
    public int getItemViewType(int position) {
        return item.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == VIEW_PROG) {
            ProgressRvBinding binding = DataBindingUtil.inflate(inflater, R.layout.progress_rv, parent, false);
            vh = new ProgressViewHolder(binding);
        }
        else {
            ItemFavoriteBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_favorite, parent, false);
            vh = new ItemViewHolder(binding);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(getItemAtPosition(position));
            ((ItemViewHolder) holder).itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickItem(view, getItemAtPosition(position));
                }
            });
        } else if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return item == null ? 0 : item.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemFavoriteBinding binding;

        public ItemViewHolder(ItemFavoriteBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind(DataWishlist itemsWishlist) {
            FavoriteItemViewModel modelWishlist = new FavoriteItemViewModel(itemsWishlist);
            binding.setVmItemFavorite(modelWishlist);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressRvBinding binding;

        public ProgressViewHolder(ProgressRvBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind() {
            binding.progressBar.setIndeterminate(true);
        }
    }
}

