package id.asiatek.asiatrans.model.register

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * Email : tulusprabudi@gmail.com
 */

@Parcelize
data class RegisterRequest(

        @field:JsonProperty("hp1")
        var hp1: String = "",

        @field:JsonProperty("password")
        var password: String = "",

        @field:JsonProperty("email")
        var email: String = "",

        @field:JsonProperty("storename")
        var storename: String = ""

) : Parcelable