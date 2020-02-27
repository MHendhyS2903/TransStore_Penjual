package id.asiatek.asiatrans.model.account

import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class MsgAccount(

    @field:JsonProperty("Status")
    val status: Boolean? = null,

    @field:JsonProperty("Messages")
    val messages: List<Any?>? = null,

    @field:JsonProperty("Value")
    val value: List<DataAccountValue?>? = null
)