package id.asiatek.asiatrans.viewmodel

import android.os.Looper
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import com.fasterxml.jackson.databind.ObjectMapper
import id.asiatek.asiatrans.conn.rx2androidnetworking.Rx2AndroidNetworking
import id.asiatek.asiatrans.model.account.AccountRequest
import id.asiatek.asiatrans.model.account.DataAccountValue
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.item.DataItemValue
import id.asiatek.asiatrans.model.item.ItemRequest
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.navigator.MenuNavigator
import id.asiatek.asiatrans.ui.base.BaseObservableViewModel
import id.asiatek.asiatrans.ui.login.LoginActivity
import id.asiatek.asiatrans.utils.AppConstants
import id.asiatek.asiatrans.utils.ClassUtils
import id.asiatek.asiatrans.utils.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AccountItemViewModel(msg: DataAccountValue) {

    val msg = msg

    val fullname = msg.fullname
    val email = msg.email
    val lat = ClassUtils.convertRupiah(msg.lat)
    val lng = ClassUtils.convertRupiah(msg.lng)

    init {

    }
}
