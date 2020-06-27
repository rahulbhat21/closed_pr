package com.navi.closedpr.view.activity

import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.navi.closedpr.R

open class BaseActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        progressBar = findViewById(R.id.progressBar)
    }

    fun launchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun updateProgressBar(isLoading: Boolean) {
        progressBar?.visibility = if (isLoading) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            );
            View.VISIBLE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            View.GONE
        }
    }

    fun isLoading(): Boolean {
        return progressBar?.visibility == View.VISIBLE
    }
}