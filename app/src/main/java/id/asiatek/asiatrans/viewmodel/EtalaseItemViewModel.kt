package id.asiatek.asiatrans.viewmodel
import id.asiatek.asiatrans.model.etalase.DataItemEtalase

class EtalaseItemViewModel(msg: DataItemEtalase) {

    val msg = msg
    val titles = msg.name!!.split(' ').joinToString(" ") { it.capitalize() }
    val create = msg.created_at!!.split(' ').joinToString(" ") { it.capitalize() }

    init {

    }
}