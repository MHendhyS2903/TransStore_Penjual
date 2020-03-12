package com.bpdsulteng.androidtvsliderimage.data.realm

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmObject
import javax.annotation.Generated

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("com.robohorse.robopojogenerator")
open class AccountObject : RealmObject() {

    @field:JsonProperty("updateby")
    var updateby: String? = null

    @field:JsonProperty("createdby")
    var createdby: String? = null

    @field:JsonProperty("status")
    var status: String? = null

    @field:JsonProperty("token")
    var token: String? = null

    @field:JsonProperty("reference")
    var reference: String? = null

    @field:JsonProperty("bankbook")
    var bankbook: String? = null

    @field:JsonProperty("title")
    var title: String? = null

    @field:JsonProperty("banknumber")
    var banknumber: String? = null

    @field:JsonProperty("bank")
    var bank: String? = null

    @field:JsonProperty("npwp")
    var npwp: String? = null

    @field:JsonProperty("npwpnumber")
    var npwpnumber: String? = null

    @field:JsonProperty("created_at")
    var created_at: String? = null

    @field:JsonProperty("updated_at")
    var updated_at: String? = null

    @field:JsonProperty("address")
    var address: String? = null

    @field:JsonProperty("ktp")
    var ktp: String? = null

    @field:JsonProperty("nik")
    var nik: String? = null

    @field:JsonProperty("identitytype")
    var identitytype: Int? = null

    @field:JsonProperty("password")
    var password: String? = null

    @field:JsonProperty("email")
    var email: String? = null

    @field:JsonProperty("hp1")
    var hp1: String? = null

    @field:JsonProperty("hp2")
    var hp2: String? = null

    @field:JsonProperty("owner")
    var owner: String? = null

    @field:JsonProperty("storename")
    var storename: String? = null

    @field:JsonProperty("domicilecity")
    var domicilecity: Int? = null

    override fun toString(): String {
        return "AccountObject(updateby=$updateby,createdby=$createdby,status=$status, token=$token, password=$password, reference=$reference, bankbook=$bankbook, email=$email, title=$title" +
                ", banknumber=$banknumber, bank=$bank, domicilecity=$domicilecity, npwp=$npwp, npwpnumber=$npwpnumber, updated_at=$updated_at" +
                ", created_at=$created_at, address=$address, ktp=$ktp, nik=$nik, identitytype=$identitytype, password=$password, hp1=$hp1, hp2=$hp2, owner=$owner, storename=$storename, domicilecity=$domicilecity)"
    }
}