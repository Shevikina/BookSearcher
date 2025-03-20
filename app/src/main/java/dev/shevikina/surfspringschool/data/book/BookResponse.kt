package dev.shevikina.surfspringschool.data.book

import com.google.gson.annotations.SerializedName

data class BookResponseList(
    @SerializedName("items") val items: List<BookResponse>?
)
data class BookResponse(
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo?
)

data class VolumeInfo(
    @SerializedName("title") val title: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?
)

data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String?
)