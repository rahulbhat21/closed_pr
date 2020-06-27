package com.navi.closedpr.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.navi.closedpr.BR
import com.navi.closedpr.R
import com.navi.closedpr.model.response.PullRequestResponse


class PullRequestAdapter(private val pullRequestList: MutableList<PullRequestResponse>) :
    RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pull_request,
            parent,
            false
        )
        return PullRequestViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(pullRequestList[position])
    }

    fun addMoreData(morePullRequests: List<PullRequestResponse>) {
        pullRequestList.addAll(morePullRequests)
        notifyDataSetChanged()
    }


    class PullRequestViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pullRequest: PullRequestResponse) {
            binding.setVariable(BR.pullRequest, pullRequest)
            binding.executePendingBindings()
        }
    }
}