package id.asiatek.asiatrans.viewmodel
import id.asiatek.asiatrans.model.etalase.DataItemEtalase

class EtalaseItemViewModel(msg: DataItemEtalase) {

    val msg = msg
    val title = msg.name!!.split(' ').joinToString(" ") { it.capitalize() }
//    val photoURI = UploadURL+msg.photo

    init {

    }
}