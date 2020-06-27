package com.navi.closedpr.model.response

import com.google.gson.annotations.SerializedName


data class PullRequestResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("created_at")
    val createdDate: String,
    @SerializedName("closed_at")
    val closedDate: String,
    @SerializedName("user")
    val user: User

)

data class User(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userAvatarUrl: String
)