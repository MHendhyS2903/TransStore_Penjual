package id.asiatek.asiatrans.navigator

import id.asiatek.asiatrans.model.account.DataAccountValue
import id.asiatek.asiatrans.model.account.MsgAccount
import id.asiatek.asiatrans.model.cart.MsgCart
import id.asiatek.asiatrans.model.item.MsgItem
import id.asiatek.asiatrans.model.wishlist.MsgWishlist

interface MenuNavigator{
    fun onSuccessDisplay(msg: MsgItem)
    fun onSuccessAccount(msg: MsgAccount)
    fun onSuccessWishlist(msg: MsgWishlist)
    fun onSuccessCart(msg: MsgCart)
    fun onSuccess()
    fun onErrorDisplay()
    fun showLoading()
    fun hideLoading()
    fun showMsg(msg:String)
}