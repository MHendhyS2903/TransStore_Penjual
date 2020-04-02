package id.asiatek.asiatrans.model.item

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class MsgItemList(

	@field:JsonProperty("Status")
	val status: Boolean? = null,

	@field:JsonProperty("Messages")
	val messages: List<Any?>? = null,

	@field:JsonProperty("Value")
	val value: List<DataItem?>? = null
)