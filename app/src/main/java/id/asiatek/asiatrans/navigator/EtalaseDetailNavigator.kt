package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.etalase.MsgEtalaseList
import id.asiatek.asiatrans.model.gmail.MsgGmail

interface EtalaseDetailNavigator {

    fun onSuccessProfile(msg: MsgGmail)
    fun onSuccessAdd(msg: MsgEtalaseList)
    fun showLoading()
    fun hideLoading()
    fun onError()
    fun showMsg(msg:String)
}
