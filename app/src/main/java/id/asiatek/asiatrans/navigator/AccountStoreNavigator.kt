package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.account_store.MsgAccountStore
import id.asiatek.asiatrans.model.gmail.MsgGmail

interface AccountStoreNavigator {

    fun onSuccessProfile(msg: MsgGmail)
    fun onSuccessUpdate(msg: MsgAccountStore)
    fun showLoading()
    fun hideLoading()
    fun onError()
    fun showMsg(msg:String)
}
