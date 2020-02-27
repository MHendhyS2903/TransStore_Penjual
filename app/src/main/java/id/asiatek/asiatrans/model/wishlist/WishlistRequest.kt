package id.asiatek.asiatrans.model.wishlist

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WishlistRequest(

    @field:JsonProperty("usertoken")
    var usertoken: String = ""

) : Parcelable