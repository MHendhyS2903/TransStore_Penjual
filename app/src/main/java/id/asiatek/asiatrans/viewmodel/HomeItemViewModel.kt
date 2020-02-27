package id.asiatek.asiatrans.viewmodel

import android.graphics.Color
import id.asiatek.asiatrans.model.item.DataItemValue
import id.asiatek.asiatrans.utils.ClassUtils

class HomeItemViewModel(msg: DataItemValue) {

    val msg = msg
    //    val customerName = msg.customername!!.toString().split(' ').joinToString(" ") { it.capitalize() } + "(" + msg.idappuser + ")"
    val title = msg.title
    val description = msg.description
    val price = "Rp. " + ClassUtils.convertRupiah(msg.price)
    val status = fnStatus(msg.status!!)
//    val statusColor = fnStatusColor(msg.status!!)

    private fun fnStatus(status: String): String {
        var result = status
        when (status) {
            "waiting" -> result = "Pesanan Baru"
            "active" -> result = "Pesanan Aktif"
        }
        return result
    }

//    private fun fnStatusColor(status: String): Int {
//        var result = "FFFFFF"
//        when (status) {
//            "waiting" -> result = "#ff1744"
//            "active" -> result = "#008577"
//        }
//
//        return Color.parseColor(result)
//    }

    init {

    }
}