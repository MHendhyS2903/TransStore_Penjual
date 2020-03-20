package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.account_profile.UpdateRequest
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.EtalaseDetailNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.ui.tab_menu.tab_home.HomeTabFragment
import id.asiatek.asiatrans.utils.AppConstants
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EtalaseViewModel : BaseObservableViewModel<EtalaseDetailNavigator>() {

    fun getAccount() {
        Rx2AndroidNetworking.get(AppConstants.GetProfile)
            .addQueryParameter("token", SharedPref.getToken())
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(HomeTabFragment.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(HomeTabFragment.TAG, " bytesSent : $bytesSent")
                Log.d(HomeTabFragment.TAG, " bytesReceived : $bytesReceived")
                Log.d(HomeTabFragment.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    Log.d(HomeTabFragment.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(HomeTabFragment.TAG, "RESPONSE : $response")
                    var msg = ObjectMapper().readValue(response, MsgGmail::class.java)
                    if(msg.Status == true) {
                        navigator.onSuccessProfile(msg)
                    }
                }
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    navigator.onError()
                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
                    CommonUtils.logError(HomeTabFragment.TAG, e)
                }
            })
    }

    fun updateData(request: UpdateRequest) {
        navigator.showLoading()
        var params = request
        var jsonString = ObjectMapper().writeValueAsString(params)
        Log.d("ParamsLogin", jsonString)
        Rx2AndroidNetworking.post(AppConstants.PostUpdateAccount)
            .addQueryParameter("token", SharedPref.getToken())
            .addQueryParameter(params)
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(LoginActivity.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(LoginActivity.TAG, " bytesSent : $bytesSent")
                Log.d(LoginActivity.TAG, " bytesReceived : $bytesReceived")
                Log.d(LoginActivity.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    navigator.hideLoading()
                    Log.d(LoginActivity.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(LoginActivity.TAG, "RESPONSE : $response")
                    var msg = CommonUtils.mto(response, MsgAccountProfile::class.java)
                    navigator.onSuccessUpdate(msg)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    navigator.hideLoading()
                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
                    CommonUtils.logError(LoginActivity.TAG, e)
                }
            })
    }
}