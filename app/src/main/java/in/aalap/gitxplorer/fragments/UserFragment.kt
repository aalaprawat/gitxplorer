package `in`.aalap.gitxplorer.fragments

import `in`.aalap.gitxplorer.R
import `in`.aalap.gitxplorer.adapters.RepoAdapter
import `in`.aalap.gitxplorer.model.ReposDataModel
import `in`.aalap.gitxplorer.model.UserDataModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment : Fragment() {

    var repoAdapter: RepoAdapter = RepoAdapter()

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        repo_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        repo_recycler_view.adapter = repoAdapter

        val userName = arguments?.getString("UserName")
        viewModel.getRepoList(userName ?: "")
        viewModel.getUserDetails(userName ?: "")
        viewModel.userResponseMutableLiveDataModel.observe(viewLifecycleOwner) {
            animationLoading.visibility = View.GONE
            if (it == null) {
                animationView.visibility = View.VISIBLE
            } else {
                animationView.visibility = View.GONE
                setUpUserProfile(it)
            }
        }
        viewModel.userRepoMutableLiveDataModel.observe(viewLifecycleOwner) {
            animationLoading.visibility = View.GONE
            if (it == null) {
                animationView.visibility = View.VISIBLE
            } else {
                animationView.visibility = View.GONE
                setUpUserRepo(it)
            }
        }
    }

    private fun setUpUserRepo(it: ArrayList<ReposDataModel>?) {
        repoAdapter.differ.submitList(it)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUserProfile(it: UserDataModel) {
        Glide.with(this).load(it.avatarUrl).into(profile_pic);
        user_name.text = it.login ?: ""
        followers.text = "Followers\n" + it.followers?.toString()
        following.text = "Following\n" + it.following?.toString()
        public_repos.text = "Repositories\n" + it.publicRepos?.toString()
    }

}