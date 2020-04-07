package id.asiatek.asiatrans.model.item

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddProductRequest(

    @field:JsonProperty("store_token")
    var store_token: String = "",

    @field:JsonProperty("title")
    var title: String = "",

    @field:JsonProperty("description")
    var description: String = "",

    @field:JsonProperty("price")
    var price: Double? = null

) : Parcelable