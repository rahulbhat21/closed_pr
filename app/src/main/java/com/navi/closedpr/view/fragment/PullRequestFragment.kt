package com.navi.closedpr.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.navi.closedpr.BR
import com.navi.closedpr.R
import com.navi.closedpr.model.response.PullRequestResponse
import com.navi.closedpr.network.Resource

class PullRequestFragment private constructor() : BaseFragment() {
    private lateinit var dataBinding: ViewDataBinding

    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_REPO_NAME = "repo_name"
        private const val PULL_REQUEST_STATE_CLOSED = "closed"
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
        gitHubViewModel.getPullRequests(userName, repoName, PULL_REQUEST_STATE_CLOSED)
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
    }
}