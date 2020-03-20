package id.asiatek.asiatrans.model.etalase

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
@Parcelize
data class DataItemEtalase(

	@field:JsonProperty("id")
	val id: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("idstore")
	var idstore: String? = null,

	@field:JsonProperty("status")
	val status: String? = null,

	@field:JsonProperty("created_at")
	val created_at: String? = null,

	@field:JsonProperty("updated_at")
	val updated_at: String? = null
) : Parcelable