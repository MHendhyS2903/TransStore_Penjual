package com.bpdsulteng.androidtvsliderimage.data.realm

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmObject
import javax.annotation.Generated

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("com.robohorse.robopojogenerator")
open class AccountObject : RealmObject() {

    @field:JsonProperty("fullname")
    var fullname: String? = null

    @field:JsonProperty("cellular")
    var cellular: String? = null

    @field:JsonProperty("email")
    var email: String? = null

    @field:JsonProperty("password")
    var password: String? = null

    @field:JsonProperty("photo")
    var photo: String? = null

    @field:JsonProperty("lat")
    var lat: Double? = null

    @field:JsonProperty("lng")
    var lng: Double? = null

    @field:JsonProperty("token")
    var token: String? = null

    @field:JsonProperty("fbtoken")
    var fbtoken: String? = null

    @field:JsonProperty("balance")
    var balance: String? = null

    @field:JsonProperty("otpcode")
    var otpcode: String? = null

    @field:JsonProperty("status")
    var status: String? = null

    @field:JsonProperty("created_at")
    var created_at: String? = null

    @field:JsonProperty("updated_at")
    var updated_at: String? = null

    @field:JsonProperty("dokuid")
    var dokuid: String? = null

    @field:JsonProperty("reference")
    var reference: String? = null

    @field:JsonProperty("idgoogle")
    var idgoogle: String? = null

    @field:JsonProperty("domicilecity")
    var domicilecity: Int? = null

    override fun toString(): String {
        return "AccountObject(balance=$balance,dokuid=$dokuid,fullname=$fullname, cellular=$cellular, password=$password, photo=$photo, lat=$lat, email=$email, lng=$lng" +
                ", fbtoken=$fbtoken, otpcode=$otpcode, domicilecity=$domicilecity, reference=$reference, idgoogle=$idgoogle, updated_at=$updated_at" +
                ", created_at=$created_at, token=$token, status=$status)"
    }
}