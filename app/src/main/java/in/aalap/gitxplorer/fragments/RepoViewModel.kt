package `in`.aalap.gitxplorer.fragments

import `in`.aalap.gitxplorer.model.PullRequestDataModel
import `in`.aalap.gitxplorer.network.RetrofitConfig
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoViewModel : ViewModel() {
    val pullRequestForRepo: MutableLiveData<ArrayList<PullRequestDataModel>?> = MutableLiveData()
    fun getPullRequest(repoName: String, status: String) {
        viewModelScope.launch {
            RetrofitConfig.urlService.getPullRequestForRepo(repoName, status)
                .enqueue(object : Callback<ArrayList<PullRequestDataModel>> {
                    override fun onFailure(
                        call: Call<ArrayList<PullRequestDataModel>>,
                        t: Throwable
                    ) {
                        pullRequestForRepo.postValue(null)
                    }

                    override fun onResponse(
                        call: Call<ArrayList<PullRequestDataModel>>,
                        response: Response<ArrayList<PullRequestDataModel>>
                    ) {
                        pullRequestForRepo.postValue(response.body())
                    }
                })
        }
    }
}