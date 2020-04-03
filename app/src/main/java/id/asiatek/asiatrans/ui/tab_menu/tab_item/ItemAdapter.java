package id.asiatek.asiatrans.ui.tab_menu.tab_item;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.asiatek.asiatrans.R;
import id.asiatek.asiatrans.databinding.ItemEtalaseTabBinding;
import id.asiatek.asiatrans.databinding.ItemHomeTabBinding;
import id.asiatek.asiatrans.databinding.ProgressRvBinding;
import id.asiatek.asiatrans.model.etalase.DataItemEtalase;
import id.asiatek.asiatrans.model.item.DataItem;
import id.asiatek.asiatrans.viewmodel.EtalaseItemViewModel;
import id.asiatek.asiatrans.viewmodel.ItemViewModel;

public class ItemAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<DataItem> item = new ArrayList<>();
    private ItemAdapter.OnItemClickListener onItemClickListener;
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void clearItems() {
        item.clear();
    }

    public void addItems(List<DataItem> item) {
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    public DataItem getItemAtPosition(int position) {
        return item.get(position);
    }

    public int getListItem(){
        return item.size();
    }

    void setOnItemClickListener(ItemAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClickEtalase(View view, DataItem obj);
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
            ProgressRvBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_spinner_search, parent, false);
            vh = new ProgressViewHolder(binding);
        }
        else {
            ItemHomeTabBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_home_tab, parent, false);
            vh = new ItemViewHolder(binding);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemAdapter.ItemViewHolder) {
            ((ItemAdapter.ItemViewHolder) holder).bind(getItemAtPosition(position));
            ((ItemAdapter.ItemViewHolder) holder).itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickEtalase(view, getItemAtPosition(position));
                }
            });
        } else if (holder instanceof ItemAdapter.ProgressViewHolder) {
            ((ItemAdapter.ProgressViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return item == null ? 0 : item.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemHomeTabBinding binding;

        public ItemViewHolder(ItemHomeTabBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind(DataItem items) {
            ItemViewModel model = new ItemViewModel(items);
            binding.setVmItemHome(model);
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

