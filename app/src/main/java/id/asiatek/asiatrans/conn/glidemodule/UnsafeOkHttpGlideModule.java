package id.asiatek.asiatrans.conn.glidemodule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import id.asiatek.asiatrans.utils.NetworkUtils;
import okhttp3.OkHttpClient;

public class UnsafeOkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        OkHttpClient client = NetworkUtils.getUnsafeOkHttpClient().build();
        glide.register(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory(client));
    }
}
