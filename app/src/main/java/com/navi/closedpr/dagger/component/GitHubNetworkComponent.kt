package com.navi.closedpr.dagger.component

import com.navi.closedpr.dagger.module.NetworkModule
import com.navi.closedpr.network.GitHubNetworkApiInterface
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface GitHubNetworkComponent {
    fun getGitHubNetworkApi(): GitHubNetworkApiInterface
}