package `in`.aalap.gitxplorer.model

import com.google.gson.annotations.SerializedName

data class PullRequestDataModel(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("user")
    val userDataModel: UserDataModel? = null
)