package com.navi.closedpr.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.navi.closedpr.R
import com.navi.closedpr.view.activity.BaseActivity
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment : BaseFragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                val userName = usernameEditText.text.toString()
                val repoName = reponameEditText.text.toString()
                if (gitHubViewModel.isValidUserData(userName, repoName)) {
                    baseActivity.launchFragment(PullRequestFragment.newInstance(userName, repoName))
                } else {
                    usernameEditText.error =
                        if (userName.isEmpty()) resources.getString(R.string.error_username) else null
                    reponameEditText.error =
                        if (repoName.isEmpty()) resources.getString(R.string.error_reponame) else null
                }
            }
        }
    }
}