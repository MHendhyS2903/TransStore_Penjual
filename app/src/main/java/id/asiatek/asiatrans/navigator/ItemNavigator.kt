package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail

interface ItemNavigator {
    fun showLoading()
    fun hideLoading()
    fun onSuccess()
    fun onSuccessProfile(msg: MsgGmail)
    fun onError()
    fun showMsg(msg:String)
}