package `in`.aalap.gitxplorer.adapters

import `in`.aalap.gitxplorer.R
import `in`.aalap.gitxplorer.model.ReposDataModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var diffCallBack = object : DiffUtil.ItemCallback<ReposDataModel>() {
        override fun areItemsTheSame(oldItem: ReposDataModel, newItem: ReposDataModel): Boolean {
            return oldItem.fullName == newItem.fullName
        }

        override fun areContentsTheSame(oldItem: ReposDataModel, newItem: ReposDataModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = differ.currentList[position]
        holder.itemView.apply {
            title.text = repo.fullName
            branches.text = repo.defaultBranch.toString()
            open_issues.text = repo.openIssues?.toString()
            forks.text = repo.forks?.toString()
            setOnClickListener {
                val args = Bundle()
                args.putString("RepoName", repo.pull?.substring(0, repo.pull.indexOf("{")))
                it.findNavController().navigate(R.id.action_userFragment_to_repoFragment, args)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}