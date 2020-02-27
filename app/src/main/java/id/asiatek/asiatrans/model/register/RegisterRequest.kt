package id.asiatek.asiatrans.model.register

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * Email : tulusprabudi@gmail.com
 */

@Parcelize
data class RegisterRequest(

        @field:JsonProperty("email")
        var email: String = "",

        @field:JsonProperty("owner")
        var owner: String = "",

        @field:JsonProperty("hp1")
        var hp1: String = "",

        @field:JsonProperty("domicilecity")
        var domicilecity: Int = 70,

        @field:JsonProperty("password")
        var password: String = ""

) : Parcelable