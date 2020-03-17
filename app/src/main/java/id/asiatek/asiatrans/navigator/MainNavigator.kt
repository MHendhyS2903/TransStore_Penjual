package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail

interface MainNavigator {
    fun onPageSelected(position: Int)

    fun onSuccessProfile(msg: MsgGmail)
    fun showLoading()
    fun hideLoading()
    fun onError()
    fun showMsg(msg:String)
}
