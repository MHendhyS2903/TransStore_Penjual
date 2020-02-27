package id.asiatek.asiatrans.model.cart

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartListRequest(

    @field:JsonProperty("usertoken")
    var usertoken: String = ""

) : Parcelable