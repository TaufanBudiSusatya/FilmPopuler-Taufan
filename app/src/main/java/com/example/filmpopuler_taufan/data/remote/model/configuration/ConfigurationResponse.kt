package com.example.filmpopuler_taufan.data.remote.model.configuration

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmpopuler_taufan.data.remote.model.configuration.ConfigurationResponse.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

//perintah untuk memberikan nama table dengan anotosi Entity
@Entity(tableName = TABLE_NAME)
data class ConfigurationResponse(
    //@SerializedName annotation ialah
    // untuk menyamakan key pada model dan key pada JSON.
    @SerializedName("change_keys")
    val changeKeys: List<String>,
    @SerializedName("images")
    val images: Images
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


    //mendeklrasikan variable TABLE_NAME yang berisikan
    //tbl_configuration
    companion object {
        const val TABLE_NAME = "tbl_configuration"
    }
}