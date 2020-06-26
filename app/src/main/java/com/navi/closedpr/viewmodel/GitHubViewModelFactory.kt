package com.navi.closedpr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.navi.closedpr.dagger.component.DaggerGitHubNetworkComponent
import com.navi.closedpr.repository.GitHubRepository

class GitHubViewModelFactory :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            GitHubViewModel::class.java -> {
                val gitHubRepository = GitHubRepository(
                    DaggerGitHubNetworkComponent.builder().build().getGitHubNetworkApi()
                )
                @Suppress("UNCHECKED_CAST")
                return GitHubViewModel(gitHubRepository) as T
            }
            else -> {
                throw IllegalArgumentException("No ViewModel Found for this modelClass.")
            }
        }
    }
}