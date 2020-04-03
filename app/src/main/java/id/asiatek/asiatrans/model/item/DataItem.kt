package id.asiatek.asiatrans.model.item

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
@Parcelize
data class DataItem(

	@field:JsonProperty("id")
	val id: String? = null,

	@field:JsonProperty("idstore")
	val idstore: String? = null,

	@field:JsonProperty("idcategory")
	val idcategory: String? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("photo")
	val photo: String? = null,

	@field:JsonProperty("price")
	val price: Double? = null,

	@field:JsonProperty("status")
	val status: String? = null,

	@field:JsonProperty("created_at")
	val created_at: String? = null,

	@field:JsonProperty("updated_at")
	val updated_at: String? = null
) : Parcelable