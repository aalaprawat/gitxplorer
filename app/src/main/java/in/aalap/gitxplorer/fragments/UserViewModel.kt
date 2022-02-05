package `in`.aalap.gitxplorer.fragments

import `in`.aalap.gitxplorer.model.ReposDataModel
import `in`.aalap.gitxplorer.model.UserDataModel
import `in`.aalap.gitxplorer.network.RetrofitConfig
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val userResponseMutableLiveDataModel: MutableLiveData<UserDataModel?> = MutableLiveData()
    val userRepoMutableLiveDataModel: MutableLiveData<ArrayList<ReposDataModel>?> = MutableLiveData()

    fun getUserDetails(userName: String) {
        viewModelScope.launch {
            val userResponse = RetrofitConfig.urlService.getUserInfo(userName)
                .enqueue(object : Callback<UserDataModel> {
                    override fun onFailure(call: Call<UserDataModel>, t: Throwable) {
                        userResponseMutableLiveDataModel.postValue(null)
                    }

                    override fun onResponse(call: Call<UserDataModel>, response: Response<UserDataModel>) {
                        userResponseMutableLiveDataModel.postValue(response.body())
                    }
                })
        }
    }

    fun getRepoList(userName: String) {
        viewModelScope.launch {
            val userRepoResponse = RetrofitConfig.urlService.getRepoList(userName)
                .enqueue(object : Callback<ArrayList<ReposDataModel>> {
                    override fun onFailure(call: Call<ArrayList<ReposDataModel>>, t: Throwable) {
                        userRepoMutableLiveDataModel.postValue(null)
                    }

                    override fun onResponse(
                        call: Call<ArrayList<ReposDataModel>>,
                        response: Response<ArrayList<ReposDataModel>>
                    ) {
                        userRepoMutableLiveDataModel.postValue(response.body())
                    }
                })
        }
    }
}