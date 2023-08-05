package com.deny.myanimes.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AnimeModel(
    val data: MutableList<DataModel>? = null
): Serializable{

}

data class DataModel(
    @SerializedName("mal_id")
    val malId: Int?,
    val title: String?,
    val images: ImagesModel?,
    val trailer: TrailerModel?,
    val synopsis: String?
) : Serializable

data class ImagesModel(
    val jpg: JpgModel?
) : Serializable

data class JpgModel(
    @SerializedName("image_url")
    val imageUrl: String?
) : Serializable

data class TrailerModel(
    @SerializedName("youtube_id")
    val youtubeId: String?
): Serializable