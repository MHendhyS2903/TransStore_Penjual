package id.asiatek.asiatrans.model.account

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class DataAccountValue(

    @field:JsonProperty("fullname")
    val fullname: String? = null,

    @field:JsonProperty("cellular")
    val cellular: String? = null,

    @field:JsonProperty("email")
    val email: String? = null,

    @field:JsonProperty("password")
    val password: String? = null,

    @field:JsonProperty("photo")
    val photo: String? = null,

    @field:JsonProperty("lat")
    val lat: Double? = null,

    @field:JsonProperty("lng")
    val lng: Double? = null,

    @field:JsonProperty("token")
    val token: String? = null,

    @field:JsonProperty("fbtoken")
    val fbtoken: String? = null,

    @field:JsonProperty("balance")
    val balance: String? = null,

    @field:JsonProperty("otpcode")
    val otpcode: String? = null,

    @field:JsonProperty("status")
    val status: String? = null,

    @field:JsonProperty("created_at")
    val created_at: Any? = null,

    @field:JsonProperty("updated_at")
    val updated_at: Any? = null,

    @field:JsonProperty("dokuid")
    val dokuid: String? = null,

    @field:JsonProperty("reference")
    val reference: String? = null,

    @field:JsonProperty("idgoogle")
    val idgoogle: String? = null,

    @field:JsonProperty("domicilecity")
    val domicilecity: Int? = null
)