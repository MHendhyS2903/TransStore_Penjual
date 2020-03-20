package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.data.prefs.SharedPref
import id.asiatek.asiatrans.model.etalase.DataItemEtalase
import id.asiatek.asiatrans.model.etalase.MsgEtalaseList
import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.navigator.EtalaseNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.tab_menu.tab_etalase.EtalaseTabFragment
import id.asiatek.asiatrans.utils.AppConstants.GetEtalaseList
import id.asiatek.asiatrans.utils.AppConstants.GetProfile
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EtalaseTabViewModel : BaseObservableViewModel<EtalaseNavigator>(){

    private val itemsEtalase = ObservableArrayList<DataItemEtalase>()

    fun getProfile() {
        Rx2AndroidNetworking.get(GetProfile)
            .addQueryParameter("token", SharedPref.getToken())
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(EtalaseTabFragment.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(EtalaseTabFragment.TAG, " bytesSent : $bytesSent")
                Log.d(EtalaseTabFragment.TAG, " bytesReceived : $bytesReceived")
                Log.d(EtalaseTabFragment.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    Log.d(EtalaseTabFragment.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(EtalaseTabFragment.TAG, "RESPONSE : $response")
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
                    CommonUtils.logError(EtalaseTabFragment.TAG, e)
                }
            })
    }

    fun getEtalaseList() {
        navigator.showLoading()
        Rx2AndroidNetworking.get(GetEtalaseList)
            .addQueryParameter("idstore",SharedPref.getToken())
            .build()
            .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                Log.d(EtalaseTabFragment.TAG, " timeTakenInMillis : $timeTakenInMillis")
                Log.d(EtalaseTabFragment.TAG, " bytesSent : $bytesSent")
                Log.d(EtalaseTabFragment.TAG, " bytesReceived : $bytesReceived")
                Log.d(EtalaseTabFragment.TAG, " isFromCache : $isFromCache")
            }
            .stringSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(response: String) {
                    navigator.hideLoading()
                    Log.d(EtalaseTabFragment.TAG, "onResponse isMainThread : ${Looper.myLooper() == Looper.getMainLooper()}")
                    Log.d(EtalaseTabFragment.TAG, "RESPONSE : $response")
                    var msg = ObjectMapper().readValue(response, MsgEtalaseList::class.java)
                    if(msg.status == true) {
                        setItemsEtalase(msg.value as MutableList<DataItemEtalase>)
                        navigator.onSuccess()
                    }
                }
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    navigator.hideLoading()
                    navigator.onError()
                    CommonUtils.getErrorBody(e)?.let { navigator.showMsg(it) }
                    CommonUtils.logError(EtalaseTabFragment.TAG, e)
                }
            })
    }

    fun setItemsEtalase(items: MutableList<DataItemEtalase>) {
        itemsEtalase.clear()
        itemsEtalase.addAll(items)
    }

    @Bindable
    fun getItemsEtalase(): ObservableArrayList<DataItemEtalase> = itemsEtalase
}
