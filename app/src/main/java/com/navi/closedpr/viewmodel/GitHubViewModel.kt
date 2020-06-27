package com.navi.closedpr.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.closedpr.model.response.PullRequestResponse
import com.navi.closedpr.network.Resource
import com.navi.closedpr.repository.GitHubRepository
import kotlinx.coroutines.launch

class GitHubViewModel(private val gitHubRepository: GitHubRepository) : ViewModel() {

    val pullRequestLiveData = MutableLiveData<Resource<List<PullRequestResponse>?>>()

    fun getPullRequests(
        userId: String,
        repoName: String,
        state: String,
        itemsPerPage: Int,
        pageNumber: Int
    ) {
        pullRequestLiveData.value = Resource.loading()
        viewModelScope.launch {
            pullRequestLiveData.value =
                gitHubRepository.getPullRequests(userId, repoName, state, itemsPerPage, pageNumber)
        }
    }

    fun isValidUserData(userName: String, repoName: String): Boolean {
        return userName.isNotEmpty() && repoName.isNotEmpty()
    }
}