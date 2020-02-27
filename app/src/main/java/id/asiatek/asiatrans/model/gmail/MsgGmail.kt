package id.asiatek.asiatrans.model.gmail

import com.bpdsulteng.androidtvsliderimage.data.realm.AccountObject
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Generated

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("com.robohorse.robopojogenerator")
data class MsgGmail(

		@field:JsonProperty("Status")
		val Status: Boolean? = null,

		@field:JsonProperty("Value")
		val Value: AccountObject? = null

)