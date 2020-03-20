package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.gmail.MsgGmail

interface EtalaseDetailNavigator {

    fun onSuccessProfile(msg: MsgGmail)
    fun onSuccessUpdate(msg: MsgAccountProfile)
    fun showLoading()
    fun hideLoading()
    fun onError()
    fun showMsg(msg:String)
}
