package id.asiatek.asiatrans.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewLoadMoreScroll extends RecyclerView.OnScrollListener {

    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private LinearLayoutManager mLayoutManager;

    public void setLoaded() {
        isLoading = false;
    }

    public boolean getLoaded() {
        return isLoading;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public RecyclerViewLoadMoreScroll() {

    }

    public void setLinearLayoutManager(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public RecyclerViewLoadMoreScroll(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (mLayoutManager != null) {
            if (mLayoutManager.getItemCount() > 9) {
                if (!isLoading && mLayoutManager.findLastVisibleItemPosition() == mLayoutManager.getItemCount() - 1) {
                    loadMoreData();
                    isLoading = true;
                }
            }
        }
        super.onScrolled(recyclerView, dx, dy);
    }

    private void loadMoreData() {
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}