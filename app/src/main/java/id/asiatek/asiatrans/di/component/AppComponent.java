package id.asiatek.asiatrans.di.component;

import android.app.Application;

import id.asiatek.asiatrans.AppLoader;
import id.asiatek.asiatrans.conn.ApiModule;
import id.asiatek.asiatrans.di.builder.ActivityBuilder;
import id.asiatek.asiatrans.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApiModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(AppLoader app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
