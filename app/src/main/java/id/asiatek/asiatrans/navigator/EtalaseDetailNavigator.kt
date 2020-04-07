package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.account_profile.MsgAccountProfile
import id.asiatek.asiatrans.model.etalase.MsgEtalase
import id.asiatek.asiatrans.model.etalase.MsgEtalaseList
import id.asiatek.asiatrans.model.gmail.MsgGmail

interface EtalaseDetailNavigator {

    fun onSuccessProfile(msg: MsgGmail)
    fun onSuccessAdd(msg: MsgEtalase)
    fun onSuccessUpdate(msg: MsgEtalase)
    fun onSuccessDelete()
    fun onSuccess()
    fun showLoading()
    fun hideLoading()
    fun onError()
    fun showMsg(msg:String)
}
