package id.asiatek.asiatrans.ui.menu.ui.cart;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Result;

import id.asiatek.asiatrans.R;
import id.asiatek.asiatrans.databinding.ItemCartBinding;
import id.asiatek.asiatrans.databinding.ProgressRvBinding;
import id.asiatek.asiatrans.model.cart.CartListRequest;
import id.asiatek.asiatrans.model.cart.DataCartOrder;
import id.asiatek.asiatrans.utils.AppConstants;
import id.asiatek.asiatrans.viewmodel.CartFragmentViewModel;
import id.asiatek.asiatrans.viewmodel.CartItemViewModel;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class CartAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<DataCartOrder> item = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private static Context context;

    public CartAdapter(Context context) {
        CartAdapter.context = context;
    }

    public void clearItems() {
        item.clear();
    }

    public void addItems(List<DataCartOrder> item) {
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    public DataCartOrder getItemAtPosition(int position) {
        return item.get(position);
    }

    public int getListItem() {
        return item.size();
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClickItem(View view, DataCartOrder obj);
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
            ItemCartBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_cart, parent, false);
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
        ItemCartBinding binding;

        private Retrofit retrofit;
        private Retrofit twohRetro;

        public ItemViewHolder(ItemCartBinding v) {
            super(v.getRoot());
            binding = v;
        }

        public interface TWOHAPIService {

            //... rest of code

            @FormUrlEncoded
            @POST(AppConstants.updateQty)
            Call<ResponseBody> postMessage(@FieldMap HashMap<String, String> params);
        }

        private void initializeRetrofit(){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            twohRetro = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        private void postMessage(HashMap<String, String> params){
            initializeRetrofit();

            TWOHAPIService apiService = twohRetro.create(TWOHAPIService.class);
            Call<ResponseBody> result = apiService.postMessage(params);
            result.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    try {
                        if(response.body()!=null)
                            Toast.makeText(null," response message "+response.body().string(),Toast.LENGTH_LONG).show();
                        if(response.errorBody()!=null)
                            Toast.makeText(null," response message "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        public void plus(){
            binding.txtQty.setText(String.valueOf(Integer.parseInt(binding.txtQty.getText().toString()) + 1));
            String value = binding.txtQty.getText().toString();

            HashMap<String, String> params = new HashMap<>();
            params.put("qty", value);
            params.put("usertoken", "MDg1Nzg2NTE4MDg4");
            params.put("iditem", "5dcbbd3bca8611348cf3930f");
            postMessage(params);
        }

        public void minus(){
            binding.txtQty.setText(String.valueOf(Integer.parseInt(binding.txtQty.getText().toString()) - 1));
            String value = binding.txtQty.getText().toString();

            HashMap<String, String> params = new HashMap<>();
            params.put("qty", value);
            params.put("usertoken", "MDg1Nzg2NTE4MDg4");
            params.put("iditem", "5dcbbd3bca8611348cf3930f");
            postMessage(params);
        }

        public void bind(DataCartOrder itemsCart) {
            CartItemViewModel modelCart = new CartItemViewModel(itemsCart);
            binding.setVmItemCart(modelCart);

            binding.btnPlus.setOnClickListener(view ->
                    plus()
            );
//
            binding.btnMinus.setOnClickListener(view ->
                    minus()
            );

//            binding.btnMinus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    binding.txtQty.setText(String.valueOf(Integer.parseInt(binding.txtQty.getText().toString()) - 1));

//                    Toast.makeText(null, "Jumlah tidak bisa kurang dari 0", Toast.LENGTH_SHORT).show();
//                }
//            });
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

