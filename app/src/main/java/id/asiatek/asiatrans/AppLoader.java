package id.asiatek.asiatrans;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.androidnetworking.AndroidNetworking;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import id.asiatek.asiatrans.data.realm.RealmManager;
import id.asiatek.asiatrans.di.component.DaggerAppComponent;
import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppLoader extends Application implements HasActivityInjector {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    public CalligraphyConfig mCalligraphyConfig;

    public static Context appContext;

    private final String TAG = AppLoader.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        Realm.init(this);
        RealmManager.initializeRealmConfig();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        CalligraphyConfig.initDefault(mCalligraphyConfig);

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.enableLogging();
        AndroidNetworking.setConnectionQualityChangeListener((currentConnectionQuality, currentBandwidth) ->
                Log.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth)
        );
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}