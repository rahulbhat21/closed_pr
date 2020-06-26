package com.navi.closedpr.network

import com.navi.closedpr.model.response.PullRequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubNetworkApiInterface {
    @GET("repos/{user_id}/{repo_name}/pulls")
    suspend fun getPullRequests(
        @Path(value = "user_id") userId: String, @Path(value = "repo_name") repoName: String,
        @Query("state") state: String
    ): Response<List<PullRequestResponse>>
}