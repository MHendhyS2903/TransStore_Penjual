package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.login.MsgArea
import id.asiatek.asiatrans.model.register.MsgRegister

interface AccountNavigator {
    fun onSuccess(msg:MsgGmail)
    fun onErrorLogin()
    fun showLoading()
    fun hideLoading()
    fun showMsg(msg:String)
}