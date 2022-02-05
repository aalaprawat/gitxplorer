package `in`.aalap.gitxplorer.model

import com.google.gson.annotations.SerializedName

data class PullRequestDataModel(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("user")
    val userDataModel: UserDataModel? = null,
    @SerializedName("created_at")
    val createDate: String? = null,
    @SerializedName("closed_at")
    val closeDate: String? = null,
)