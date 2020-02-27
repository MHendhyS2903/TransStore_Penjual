package com.bpdsulteng.androidtvsliderimage.data.realm

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmObject
import javax.annotation.Generated

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("com.robohorse.robopojogenerator")
open class AccountObject : RealmObject() {

    @field:JsonProperty("balance")
    var balance: Double? = null

    @field:JsonProperty("dokuid")
    var dokuid: String? = null

    @field:JsonProperty("brandsname")
    var brandsname: String? = null

    @field:JsonProperty("companytype")
    var companytype: String? = null

    @field:JsonProperty("owner")
    var owner: String? = null

    @field:JsonProperty("hp1")
    var hp1: String? = null

    @field:JsonProperty("hp2")
    var hp2: String? = null

    @field:JsonProperty("email")
    var email: String? = null

    @field:JsonProperty("identitytype")
    var identitytype: String? = null

    @field:JsonProperty("nik")
    var nik: String? = null

    @field:JsonProperty("ktp")
    var ktp: String? = null

    @field:JsonProperty("domicilecity")
    var domicilecity: String? = null

    @field:JsonProperty("address")
    var address: String? = null

    @field:JsonProperty("npwpnumber")
    var npwpnumber: String? = null

    @field:JsonProperty("npwp")
    var npwp: String? = null

    @field:JsonProperty("bank")
    var bank: String? = null

    @field:JsonProperty("banknumber")
    var banknumber: String? = null

    @field:JsonProperty("title")
    var title: String? = null

    @field:JsonProperty("bankowner")
    var bankowner: String? = null

    @field:JsonProperty("bankbook")
    var bankbook: String? = null

    @field:JsonProperty("status")
    var status: String? = null

    @field:JsonProperty("token")
    var token: String? = null

    @field:JsonProperty("created_at")
    var created_at: String? = null

    @field:JsonProperty("updated_at")
    var updated_at: String? = null

    override fun toString(): String {
        return "AccountObject(balance=$balance,dokuid=$dokuid,brandsname=$brandsname, companytype=$companytype, owner=$owner, hp1=$hp1, hp2=$hp2, email=$email, identitytype=$identitytype" +
                ", nik=$nik, ktp=$ktp, domicilecity=$domicilecity, address=$address, npwpnumber=$npwpnumber, npwp=$npwp" +
                ", bank=$bank, banknumber=$banknumber, title=$title, bankowner=$bankowner, bankbook=$bankbook" +
                ", status=$status, token=$token, created_at=$created_at, updated_at=$updated_at)"
    }
}