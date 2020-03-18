package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail

interface HomeNavigator {
    fun showLoading()
    fun hideLoading()
    fun onSuccess()
    fun onError()
    fun showMsg(msg:String)
    fun toLogOut()
}