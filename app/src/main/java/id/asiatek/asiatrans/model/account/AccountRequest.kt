package id.asiatek.asiatrans.model.account

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AccountRequest(

    @field:JsonProperty("auth")
    var auth: String = "",

    @field:JsonProperty("display")
    var display: String  = "",

    @field:JsonProperty("filterparams")
    var filterparams: String = ""

) : Parcelable