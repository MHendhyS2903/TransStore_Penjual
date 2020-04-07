package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.item.MsgItem

interface ItemNavigator {
    fun showLoading()
    fun hideLoading()
    fun onSuccess()
    fun onSuccessProfile(msg: MsgGmail)
    fun onSuccessAdd(msg: MsgItem)
    fun onSuccessUpdate(msg: MsgItem)
    fun onError()
    fun showMsg(msg:String)
}