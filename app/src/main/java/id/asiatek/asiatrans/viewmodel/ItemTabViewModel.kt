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
import id.asiatek.asiatrans.model.item.DataItem
import id.asiatek.asiatrans.model.item.MsgItemList
import id.asiatek.asiatrans.navigator.ItemNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.tab_menu.tab_item.ItemTabFragment
import id.asiatek.asiatrans.utils.AppConstants.GetProfile
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ItemTabViewModel : BaseObservableViewModel<ItemNavigator>(){

    private val itemsHome = ObservableArrayList<DataItem>()

    fun getProfile() {
        Rx2AndroidNetworking.get(GetProfile)
            .addQueryParameter("token", SharedPref.getToken())
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(ItemTabFragment.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(ItemTabFragment.TAG, " bytesSent : $bytesSent")
                Log.d(ItemTabFragment.TAG, " bytesReceived : $bytesReceived")
                Log.d(ItemTabFragment.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    Log.d(ItemTabFragment.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(ItemTabFragment.TAG, "RESPONSE : $response")
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
                    CommonUtils.logError(ItemTabFragment.TAG, e)
                }
            })
    }

    fun getOutletList() {
        navigator.showLoading()
        Rx2AndroidNetworking.get(GetProfile)
            .addQueryParameter("token",SharedPref.getToken())
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(ItemTabFragment.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(ItemTabFragment.TAG, " bytesSent : $bytesSent")
                Log.d(ItemTabFragment.TAG, " bytesReceived : $bytesReceived")
                Log.d(ItemTabFragment.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    navigator.hideLoading()
                    Log.d(ItemTabFragment.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(ItemTabFragment.TAG, "RESPONSE : $response")
                    var msg = ObjectMapper().readValue(response, MsgItemList::class.java)
                    if(msg.status == true) {
                        setItemsOutlet(msg.value as MutableList<DataItem>)
                        navigator.onSuccess()
                    }
                }
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    navigator.hideLoading()
                    navigator.onError()
                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
                    CommonUtils.logError(ItemTabFragment.TAG, e)
                }
            })
    }

    fun setItemsOutlet(items: MutableList<DataItem>) {
        itemsHome.clear()
        itemsHome.addAll(items)
    }

    @Bindable
    fun getItems(): ObservableArrayList<DataItem> = itemsHome
}
