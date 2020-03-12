package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.model.gmail.GmailRequest
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.login.LoginRequest
import id.asiatek.asiatrans.model.login.MsgArea
import id.asiatek.asiatrans.model.register.MsgRegister
import id.asiatek.asiatrans.model.register.RegisterRequest
import id.asiatek.asiatrans.navigator.LoginNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.utils.AppConstants.*
import id.asiatek.asiatrans.utils.CommonUtils
import id.asiatek.asiatrans.utils.CommonUtils.mto
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.utils.AppConstants.PostLoginHP
import id.asiatek.asiatrans.utils.AppConstants.PostRegister
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterViewModel : BaseObservableViewModel<LoginNavigator>() {

    fun registerData(request: RegisterRequest) {
        navigator.showLoading()
        var params = request
        var jsonString = ObjectMapper().writeValueAsString(params)
        Log.d("ParamsLogin", jsonString)
        Rx2AndroidNetworking.post(PostRegister)
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
                    var msg = mto(response, MsgRegister::class.java)
                    navigator.onSuccessRegister(msg)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    navigator.hideLoading()
                    navigator.onErrorLogin()
                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
                    CommonUtils.logError(LoginActivity.TAG, e)
                }
            })
    }
}