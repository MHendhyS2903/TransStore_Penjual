package id.asiatek.asiatrans.ui.tab_menu.tab_etalase;


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
import id.asiatek.asiatrans.databinding.ProgressRvBinding;
import id.asiatek.asiatrans.model.etalase.DataItemEtalase;
import id.asiatek.asiatrans.viewmodel.EtalaseItemViewModel;

public class EtalaseAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<DataItemEtalase> item = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context context;

    public EtalaseAdapter(Context context) {
        this.context = context;
    }

    public void clearItems() {
        item.clear();
    }

    public void addItems(List<DataItemEtalase> item) {
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    public DataItemEtalase getItemAtPosition(int position) {
        return item.get(position);
    }

    public int getListItem(){
        return item.size();
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClickEtalase(View view, DataItemEtalase obj);
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
            ItemEtalaseTabBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_etalase_tab, parent, false);
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
                    onItemClickListener.onItemClickEtalase(view, getItemAtPosition(position));
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
        ItemEtalaseTabBinding binding;

        public ItemViewHolder(ItemEtalaseTabBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind(DataItemEtalase items) {
            EtalaseItemViewModel model = new EtalaseItemViewModel(items);
            binding.setVmItemEtalase(model);
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

