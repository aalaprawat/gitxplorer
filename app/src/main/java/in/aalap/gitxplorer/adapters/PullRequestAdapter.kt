package `in`.aalap.gitxplorer.adapters

import `in`.aalap.gitxplorer.R
import `in`.aalap.gitxplorer.model.PullRequestDataModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestAdapter : RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    inner class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var diffCallBack = object : DiffUtil.ItemCallback<PullRequestDataModel>() {
        override fun areItemsTheSame(
            oldItem: PullRequestDataModel,
            newItem: PullRequestDataModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: PullRequestDataModel,
            newItem: PullRequestDataModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pull_request, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullrequest = differ.currentList[position]
        holder.itemView.apply {
            title_repo.text = pullrequest.title
            pull_status.text = pullrequest.state
            profile_name.text = pullrequest.userDataModel?.login
            Glide.with(context).load(pullrequest.userDataModel?.avatarUrl ?: "").into(profile_pic)
            if (pullrequest.state.contains("open"))
                pull_status_image.setColorFilter(resources.getColor(android.R.color.holo_blue_light))
            else
                pull_status_image.setColorFilter(resources.getColor(android.R.color.holo_green_light))
            setOnClickListener {
                val args = Bundle()
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}