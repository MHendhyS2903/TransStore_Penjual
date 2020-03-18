package id.asiatek.asiatrans.model.account_store

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateRequest(

    @field:JsonProperty("storename")
    var storename: String = "",

    @field:JsonProperty("bankbook")
    var bankbook: String = "",

    @field:JsonProperty("banknumber")
    var banknumber: String = "",

    @field:JsonProperty("bank")
    var bank: String = ""

) : Parcelable