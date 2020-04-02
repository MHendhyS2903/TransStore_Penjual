package id.asiatek.asiatrans.viewmodel

import id.asiatek.asiatrans.model.item.DataItem
import id.asiatek.asiatrans.utils.ClassUtils

class ItemViewModel(msg: DataItem) {

    val msg = msg
    val title = msg.title!!.split(' ').joinToString(" ") { it.capitalize() }
    val price = "Rp. " + ClassUtils.convertRupiah(msg.price)

    init {

    }
}