package com.navi.closedpr.view.fragment

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.navi.closedpr.view.activity.BaseActivity
import com.navi.closedpr.viewmodel.GitHubViewModel
import com.navi.closedpr.viewmodel.GitHubViewModelFactory

open class BaseFragment : Fragment() {
    protected lateinit var gitHubViewModel: GitHubViewModel
    protected lateinit var baseActivity: BaseActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
        gitHubViewModel = ViewModelProviders.of(this, GitHubViewModelFactory())
            .get(GitHubViewModel::class.java)
    }

    open fun handleApiFailure(message: String?) {
        baseActivity.updateProgressBar(false)
        message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }
}