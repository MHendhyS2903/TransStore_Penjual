package id.asiatek.asiatrans.model.wishlist

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class DataWishlist(

    @field:JsonProperty("id")
    val id: Any? = null,

    @field:JsonProperty("usertoken")
    val usertoken: Any? = null,

    @field:JsonProperty("iditem")
    val iditem: Any? = null,

    @field:JsonProperty("qty")
    val qty: Int? = null,

    @field:JsonProperty("subtotal")
    val subtotal: Double? = null,

    @field:JsonProperty("notes")
    val notes: String? = null,

    @field:JsonProperty("idstore")
    val idstore: Any? = null,

    @field:JsonProperty("idcategory")
    val idcategory: Any? = null,

    @field:JsonProperty("title")
    val title: String? = null,

    @field:JsonProperty("status")
    val status: Any? = null,

    @field:JsonProperty("price")
    val price: Double? = null,

    @field:JsonProperty("photo")
    val photo: String? = null,

    @field:JsonProperty("description")
    val description: String? = null,

    @field:JsonProperty("created_at")
    val created_at: Any? = null,

    @field:JsonProperty("updated_at")
    val updated_at: Any? = null
)