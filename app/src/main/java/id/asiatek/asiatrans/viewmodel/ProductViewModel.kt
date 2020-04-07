package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.model.etalase.EtalaseRequest
import id.asiatek.asiatrans.model.item.AddProductRequest
import id.asiatek.asiatrans.model.item.*
import id.asiatek.asiatrans.navigator.ItemNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.etalase.EtalaseActivity
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.utils.AppConstants.*
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductViewModel : BaseObservableViewModel<ItemNavigator>() {

    var title = ""
    var id = ""
    var price = ""
    var description = ""

    fun AddProduct(request: AddProductRequest) {
        navigator.showLoading()
        var params = request
        var jsonString = ObjectMapper().writeValueAsString(params)
        Log.d("ParamsLogin", jsonString)
        Rx2AndroidNetworking.post(AddProduct)
            .addQueryParameter("token", SharedPref.getToken())
            .addQueryParameter(params)
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(EtalaseActivity.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(EtalaseActivity.TAG, " bytesSent : $bytesSent")
                Log.d(EtalaseActivity.TAG, " bytesReceived : $bytesReceived")
                Log.d(EtalaseActivity.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    navigator.hideLoading()
                    Log.d(EtalaseActivity.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(EtalaseActivity.TAG, "RESPONSE : $response")
                    var msg = CommonUtils.mto(response, MsgItem::class.java)
                    navigator.onSuccessAdd(msg)
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

    fun updateProduct(request: UpdateProductRequest) {
        navigator.showLoading()
        var params = request
        var jsonString = ObjectMapper().writeValueAsString(params)
        Log.d("ParamsLogin", jsonString)
        Rx2AndroidNetworking.post(UpdateProduct)
            .addQueryParameter("token", SharedPref.getToken())
            .addQueryParameter("id", id)
            .addQueryParameter(params)
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(EtalaseActivity.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(EtalaseActivity.TAG, " bytesSent : $bytesSent")
                Log.d(EtalaseActivity.TAG, " bytesReceived : $bytesReceived")
                Log.d(EtalaseActivity.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    navigator.hideLoading()
                    Log.d(EtalaseActivity.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(EtalaseActivity.TAG, "RESPONSE : $response")
                    navigator.onSuccess()
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

//    fun deleteEtalase() {
//        navigator.showLoading()
//        Rx2AndroidNetworking.post(AppConstants.PostUpdateAccount)
//            .addQueryParameter("token", SharedPref.getToken())
//            .addQueryParameter("id", id)
//            .build()
//            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
//                Log.d(EtalaseActivity.TAG, " timeTakenInMillis : $timeTakenInMillis")
//                Log.d(EtalaseActivity.TAG, " bytesSent : $bytesSent")
//                Log.d(EtalaseActivity.TAG, " bytesReceived : $bytesReceived")
//                Log.d(EtalaseActivity.TAG, " isFromCache : $isFromCache")
//            }
//            .stringSingle
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<String> {
//                override fun onSuccess(response: String) {
//                    navigator.hideLoading()
//                    Log.d(EtalaseActivity.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
//                    Log.d(EtalaseActivity.TAG, "RESPONSE : $response")
//                    navigator.onSuccessDelete()
//                }
//
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onError(e: Throwable) {
//                    navigator.hideLoading()
//                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
//                    CommonUtils.logError(LoginActivity.TAG, e)
//                }
//            })
//    }
}