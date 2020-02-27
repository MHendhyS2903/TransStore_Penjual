package id.asiatek.asiatrans.di.module;

import android.app.Application;
import android.content.Context;

import id.asiatek.asiatrans.R;
import id.asiatek.asiatrans.widget.RecyclerViewLoadMoreScroll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module
public class AppModule {

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @Singleton
    RecyclerViewLoadMoreScroll provideRecyclerViewLoadMoreScroll() {
        return new RecyclerViewLoadMoreScroll();
    }
}
