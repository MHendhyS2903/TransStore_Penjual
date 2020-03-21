package id.asiatek.asiatrans.model.etalase

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import java.io.File
import javax.annotation.Generated

@Parcelize
@Generated("com.robohorse.robopojogenerator")
data class EtalaseRequest(

        @field:JsonProperty("name")
        var name: String = "",

        @field:JsonProperty("idstore")
        var idstore: String = ""

) : Parcelable