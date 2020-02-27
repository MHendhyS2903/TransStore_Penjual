package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.model.wishlist.DataWishlist
import id.asiatek.asiatrans.model.wishlist.MsgWishlist
import id.asiatek.asiatrans.model.wishlist.WishlistRequest
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.utils.AppConstants
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteFragmentViewModel : BaseObservableViewModel<MenuNavigator>() {

    private val itemsWishlist = ObservableArrayList<DataWishlist>()

    fun getWishlist(req: WishlistRequest) {
        navigator.showLoading()
        var params = req
        var jsonString = ObjectMapper().writeValueAsString(params)
        Log.d("ParamsLogin", jsonString)
        Rx2AndroidNetworking.get(AppConstants.getWishlist)
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
                    var msg = ObjectMapper().readValue(response, MsgWishlist::class.java)
                    if(msg.status == true) {
                        setItems(msg.value as MutableList<DataWishlist>)
                        navigator.onSuccessWishlist(msg)
                    }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    navigator.hideLoading()
                    navigator.onErrorDisplay()
                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
                    CommonUtils.logError(LoginActivity.TAG, e)
                }
            })
    }

    fun setItems(items: MutableList<DataWishlist>) {
        itemsWishlist.clear()
        itemsWishlist.addAll(items)
    }

    @Bindable
    fun getItems(): ObservableArrayList<DataWishlist> = itemsWishlist
}
