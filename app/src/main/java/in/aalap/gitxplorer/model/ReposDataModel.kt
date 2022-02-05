package `in`.aalap.gitxplorer.model

import com.google.gson.annotations.SerializedName

data class ReposDataModel(
    @SerializedName("full_name")
    val fullName: String? = "",
    @SerializedName("forks")
    val forks: Int? = 0,
    @SerializedName("open_issues")
    val openIssues: Int? = 0,
    @SerializedName("default_branch")
    val defaultBranch: String? = "",
    @SerializedName("pulls_url")
    val pull: String? = ""
)