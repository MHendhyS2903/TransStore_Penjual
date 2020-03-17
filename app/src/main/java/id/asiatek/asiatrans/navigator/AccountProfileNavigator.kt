package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail

interface AccountProfileNavigator {

    fun onSuccessProfile(msg: MsgGmail)
    fun showLoading()
    fun hideLoading()
    fun onError()
    fun showMsg(msg:String)
}
