package id.asiatek.asiatrans.model.account_profile

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateRequest(

    @field:JsonProperty("owner")
    var owner: String = "",

    @field:JsonProperty("ktp")
    var ktp: String = "",

    @field:JsonProperty("hp1")
    var hp1: String = "",

    @field:JsonProperty("address")
    var address: String = "",

    @field:JsonProperty("npwp")
    var npwp: String = "",

    @field:JsonProperty("npwpnumber")
    var npwpnumber: String = ""

) : Parcelable