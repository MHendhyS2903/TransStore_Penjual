package id.asiatek.asiatrans.model.item

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemRequest(

    @field:JsonProperty("auth")
    var auth: String = "asjkldjlkajskldui212khkjdasd",

    @field:JsonProperty("display")
    var display: String  = "{\"limit\":20,\"page\":1,\"field\":\"id\",\"direction\":\"asc\"}",

    @field:JsonProperty("filterparams")
    var filterparams: String = "[{\"field\":\"status\",\"op\":\"eq\",\"value\":\"CREATED\"}]"

) : Parcelable