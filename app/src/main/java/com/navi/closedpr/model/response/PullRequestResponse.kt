package com.navi.closedpr.model.response

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*


data class PullRequestResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("created_at")
    val createdDate: String,
    @SerializedName("closed_at")
    val closedDate: String,
    @SerializedName("user")
    val user: User

) {

    fun getFormattedDateTime(dateInString: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date? = sdf.parse(dateInString)
        val outputFormatTime =
            SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        val outputFormatDate =
            SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        date?.let {
            return outputFormatTime.format(it).plus(" ".plus(outputFormatDate.format(it)))
        }
        return ""
    }
}

data class User(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userAvatarUrl: String
)