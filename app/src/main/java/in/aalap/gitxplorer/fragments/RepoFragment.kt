package `in`.aalap.gitxplorer.fragments

import `in`.aalap.gitxplorer.R
import `in`.aalap.gitxplorer.adapters.PullRequestAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.repo_fragment.*
import kotlinx.android.synthetic.main.repo_fragment.animationLoading
import kotlinx.android.synthetic.main.repo_fragment.animationView
import kotlinx.android.synthetic.main.user_fragment.*

class RepoFragment : Fragment() {

    companion object {
        fun newInstance() = RepoFragment()
    }

    private lateinit var viewModel: RepoViewModel
    private val adapterRepoRequest = PullRequestAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RepoViewModel::class.java]
        pull_request_recyclerView.layoutManager = LinearLayoutManager(context)
        pull_request_recyclerView.adapter = adapterRepoRequest

        val repoName = arguments?.getString("RepoName")
        viewModel.getPullRequest(repoName ?: "")
        viewModel.pullRequestForRepo?.observe(viewLifecycleOwner) {
            animationLoading.visibility = View.GONE

            if (it == null) {
                animationView.visibility = View.VISIBLE
                animationViewNoResult.visibility = View.GONE
            } else {
                if (it.size == 0) {
                    animationView.visibility = View.GONE
                    animationViewNoResult.visibility = View.VISIBLE
                } else {
                    animationView.visibility = View.GONE
                    animationViewNoResult.visibility = View.GONE
                    adapterRepoRequest.differ.submitList(it)
                }
            }
        }
    }


}