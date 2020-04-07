package id.asiatek.asiatrans.model.item

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class MsgItem(

	@field:JsonProperty("Status")
	val status: Boolean? = null,

	@field:JsonProperty("Messages")
	val messages: List<Any?>? = null,

	@field:JsonProperty("Value")
	val value: Int? = null
)