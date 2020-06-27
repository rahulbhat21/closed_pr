package com.navi.closedpr.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navi.closedpr.BR
import com.navi.closedpr.R
import com.navi.closedpr.model.response.PullRequestResponse
import com.navi.closedpr.network.Resource
import kotlinx.android.synthetic.main.fragment_pull_request.*


class PullRequestFragment private constructor() : BaseFragment() {
    private lateinit var dataBinding: ViewDataBinding
    private var pageNumber = 1
    private var hasMorePages = true

    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_REPO_NAME = "repo_name"
        private const val PULL_REQUEST_STATE_CLOSED = "closed"
        private const val ITEMS_PER_PAGE = 10
        fun newInstance(userName: String, repoName: String): PullRequestFragment {
            return PullRequestFragment().apply {
                val bundle = Bundle()
                bundle.putString(KEY_USER_NAME, userName)
                bundle.putString(KEY_REPO_NAME, repoName)
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        val userName = arguments?.getString(KEY_USER_NAME) ?: ""
        val repoName = arguments?.getString(KEY_REPO_NAME) ?: ""
        gitHubViewModel.getPullRequests(
            userName, repoName, PULL_REQUEST_STATE_CLOSED,
            ITEMS_PER_PAGE, pageNumber
        )
        pullRequestRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = recyclerView.layoutManager?.childCount ?: 0
                val totalItemCount: Int = recyclerView.layoutManager?.itemCount ?: 0
                val firstVisibleItemPosition: Int =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (visibleItemCount + firstVisibleItemPosition >=
                    totalItemCount && firstVisibleItemPosition > 0 && !baseActivity.isLoading() && hasMorePages
                ) {
                    pageNumber++
                    gitHubViewModel.getPullRequests(
                        userName, repoName, PULL_REQUEST_STATE_CLOSED,
                        ITEMS_PER_PAGE, pageNumber
                    )
                }
            }
        })
    }

    private fun setupObservers() {
        gitHubViewModel.pullRequestLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> handlePullRequestData(resource.data)
                Resource.Status.ERROR -> handleApiFailure(resource.message)
                Resource.Status.LOADING -> baseActivity.updateProgressBar(true)
            }
        })
    }

    private fun handlePullRequestData(data: List<PullRequestResponse>?) {
        baseActivity.updateProgressBar(false)
        dataBinding.setVariable(BR.gitHubViewModel, gitHubViewModel)
        if (data.isNullOrEmpty()) {
            hasMorePages = false
            Toast.makeText(
                context,
                R.string.error_no_pr,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}