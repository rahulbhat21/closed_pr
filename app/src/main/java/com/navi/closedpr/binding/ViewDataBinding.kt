package com.navi.closedpr.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.navi.closedpr.model.response.PullRequestResponse
import com.navi.closedpr.view.adapter.PullRequestAdapter


class ViewDataBinding {

    companion object {
        @BindingAdapter("populatePullRequest")
        @JvmStatic
        fun populatePullRequest(
            recyclerView: RecyclerView,
            pullRequestList: List<PullRequestResponse>?
        ) {
            if (!pullRequestList.isNullOrEmpty()) {
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.adapter = PullRequestAdapter(pullRequestList)
            }
        }

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(
            imageView: ImageView,
            imageUrl: String
        ) {
            Glide.with(imageView).load(imageUrl).into(imageView)
        }
    }
}