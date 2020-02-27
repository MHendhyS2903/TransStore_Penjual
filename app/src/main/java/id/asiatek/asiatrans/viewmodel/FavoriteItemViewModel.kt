package id.asiatek.asiatrans.viewmodel

import id.asiatek.asiatrans.model.wishlist.DataWishlist

class FavoriteItemViewModel(msg: DataWishlist) {

    val msg = msg
    val iditem = msg.iditem
    val created_at = msg.created_at
    val test = msg.title

    init {

    }
}