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

class RepoFragment : Fragment() {

    companion object {
        fun newInstance() = RepoFragment()
    }

    private var repoName: String? = null
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

        repoName = arguments?.getString("RepoName")
        branches_title.text = "Open Pull Request"
        viewModel.getPullRequest(repoName ?: "", "open")
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
        setUpSwitch()
    }

    private fun setUpSwitch() {
        customSwitch.setOnCheckedChangeListener { compoundButton, _ ->
            animationLoading.visibility = View.VISIBLE
            if (compoundButton.isChecked) {
                branches_title.text = "Closed Pull Request"
                viewModel.getPullRequest(repoName ?: "", "closed")
            } else {
                branches_title.text = "Open Pull Request"
                viewModel.getPullRequest(repoName ?: "", "open")
            }
        }
    }


}