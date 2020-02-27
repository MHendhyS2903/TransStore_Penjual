package id.asiatek.asiatrans.viewmodel

import id.asiatek.asiatrans.model.cart.DataCartOrder
import id.asiatek.asiatrans.utils.ClassUtils

class CartItemViewModel(msg: DataCartOrder) {

    val msg = msg
    val orderid = msg.title.toString()
    val destination = "Rp. " + ClassUtils.convertRupiah(msg.subtotal)
    val qty = msg.qty.toString()

    init {

    }
}