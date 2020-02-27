package id.asiatek.asiatrans.ui.menu.ui.account;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.asiatek.asiatrans.R;
import id.asiatek.asiatrans.databinding.ItemAccountBinding;
import id.asiatek.asiatrans.databinding.ProgressRvBinding;
import id.asiatek.asiatrans.model.account.DataAccountValue;
import id.asiatek.asiatrans.viewmodel.AccountItemViewModel;

public class AccountAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<DataAccountValue> item = new ArrayList<>();
    private AccountAdapter.OnItemClickListener onItemClickListener;
    private static Context context;

    public AccountAdapter(Context context) {
        AccountAdapter.context = context;
    }

    public void clearItems() {
        item.clear();
    }

    public void addItems(List<DataAccountValue> item) {
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    public DataAccountValue getItemAtPosition(int position) {
        return item.get(position);
    }

    public int getListItem() {
        return item.size();
    }

    void setOnItemClickListener(AccountAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClickItem(View view, DataAccountValue obj);
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
            vh = new AccountAdapter.ProgressViewHolder(binding);
        }
        else {
            ItemAccountBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_account, parent, false);
            vh = new AccountAdapter.ItemViewHolder(binding);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AccountAdapter.ItemViewHolder) {
            ((AccountAdapter.ItemViewHolder) holder).bind(getItemAtPosition(position));
            ((AccountAdapter.ItemViewHolder) holder).itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickItem(view, getItemAtPosition(position));
                }
            });
        } else if (holder instanceof AccountAdapter.ProgressViewHolder) {
            ((AccountAdapter.ProgressViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return item == null ? 0 : item.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemAccountBinding binding;

        public ItemViewHolder(ItemAccountBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public void bind(DataAccountValue itemsAccount) {
            AccountItemViewModel modelAccount = new AccountItemViewModel(itemsAccount);
            binding.setVmItemAccount(modelAccount);
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

