package id.asiatek.asiatrans.model.wishlist

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class MsgWishlist(

    @field:JsonProperty("Status")
    val status: Boolean? = null,

    @field:JsonProperty("Messages")
    val messages: List<Any?>? = null,

    @field:JsonProperty("Value")
    val value: List<DataWishlist?>? = null
)