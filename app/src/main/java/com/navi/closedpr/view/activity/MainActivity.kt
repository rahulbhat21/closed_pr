package com.navi.closedpr.view.activity

import android.os.Bundle
import com.navi.closedpr.R
import com.navi.closedpr.view.fragment.UserInfoFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment(UserInfoFragment())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) finish()
        else super.onBackPressed()
    }
}