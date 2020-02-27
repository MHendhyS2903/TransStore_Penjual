package id.asiatek.asiatrans.model.gmail

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * Email : tulusprabudi@gmail.com
 */

@Parcelize
data class GmailRequest(
        @field:JsonProperty("idgoogle")
        var idgoogle: String = "",

        @field:JsonProperty("email")
        var email: String = "",

        @field:JsonProperty("owner")
        var owner: String = ""

) : Parcelable