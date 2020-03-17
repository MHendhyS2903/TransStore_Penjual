package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.HomeNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment
import id.asiatek.asiatrans.utils.AppConstants.GetProfile
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeTabViewModel : BaseObservableViewModel<HomeNavigator>(){

//    fun getProfile() {
//        Rx2AndroidNetworking.get(GetProfile)
//            .addQueryParameter("token", SharedPref.getToken())
//            .build()
//            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
//                Log.d(HomeTabFragment.TAG, " timeTakenInMillis : $timeTakenInMillis")
//                Log.d(HomeTabFragment.TAG, " bytesSent : $bytesSent")
//                Log.d(HomeTabFragment.TAG, " bytesReceived : $bytesReceived")
//                Log.d(HomeTabFragment.TAG, " isFromCache : $isFromCache")
//            }
//            .stringSingle
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<String> {
//                override fun onSuccess(response: String) {
//                    Log.d(HomeTabFragment.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
//                    Log.d(HomeTabFragment.TAG, "RESPONSE : $response")
//                    var msg = ObjectMapper().readValue(response, MsgGmail::class.java)
//                    if(msg.Status == true) {
//                        navigator.onSuccessProfile(msg)
//                    }
//                }
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onError(e: Throwable) {
//                    navigator.onError()
//                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
//                    CommonUtils.logError(HomeTabFragment.TAG, e)
//                }
//            })
//    }
}
