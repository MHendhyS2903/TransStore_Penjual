package id.asiatek.asiatrans.model.login

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(

        @field:JsonProperty("cellular")
        var cellular: String = "",

        @field:JsonProperty("password")
        var password: String = ""

) : Parcelable