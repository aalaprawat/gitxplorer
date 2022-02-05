package `in`.aalap.gitxplorer.network

import `in`.aalap.gitxplorer.model.PullRequestDataModel
import `in`.aalap.gitxplorer.model.ReposDataModel
import `in`.aalap.gitxplorer.model.UserDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * This are all the urls
 */

interface Urls {
    @GET("https://api.github.com/users/{userName}")
    fun getUserInfo(@Path("userName") userName: String): Call<UserDataModel>

    @GET("https://api.github.com/users/{userName}/repos")
    fun getRepoList(@Path("userName") userName: String): Call<ArrayList<ReposDataModel>>

    @GET("")
    fun getPullRequestForRepo(
        @Url repoUrl: String,
        @Query("state") status: String
    ): Call<ArrayList<PullRequestDataModel>>

}