package `in`.aalap.gitxplorer.model

import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("login")
    val login: String? = "",
    @SerializedName("public_repos")
    val publicRepos: Int? = 0,
    @SerializedName("followers")
    val followers: Int? = 0,
    @SerializedName("following")
    val following: Int? = 0,
    @SerializedName("avatar_url")
    val avatarUrl: String? = "",
)