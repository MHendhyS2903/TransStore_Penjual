package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.gmail.MsgGmail
import id.asiatek.asiatrans.model.login.MsgArea
import id.asiatek.asiatrans.model.register.MsgRegister

/*
 * Date : 09-09-2019
 * Created by Tulus Prabudi
 * tulusprabudi@gmail.com
 * */

interface LoginNavigator {
    fun onSuccessLogin(msg:MsgGmail)
    fun onSuccessRegister(msg:MsgRegister)
    fun onSuccessGmail(msg:MsgGmail)
    fun onSuccessArea(msg: MsgArea)
    fun onErrorLogin()
    fun showLoading()
    fun hideLoading()
    fun showMsg(msg:String)
}