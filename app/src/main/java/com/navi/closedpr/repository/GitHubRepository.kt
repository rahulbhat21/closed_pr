package com.navi.closedpr.repository

import com.navi.closedpr.model.response.PullRequestResponse
import com.navi.closedpr.network.GitHubNetworkApiInterface
import com.navi.closedpr.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository(private val networkApiInterface: GitHubNetworkApiInterface) {
    suspend fun getPullRequests(
        userId: String,
        repoName: String,
        state: String,
        itemsPerPage:Int,
        pageNumber:Int
    ): Resource<List<PullRequestResponse>?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = networkApiInterface.getPullRequests(userId, repoName, state, itemsPerPage, pageNumber)
                if (response.isSuccessful) {
                    Resource.success(response.body())
                } else {
                    Resource.error(response.message())
                }
            } catch (e: Exception) {
                Resource.error(e.message, null)
            }
        }
    }
}