package com.lucasbais.reddit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lucasbais.reddit.R
import com.lucasbais.reddit.ui.post.PostDetailFragment
import com.lucasbais.reddit.ui.post.PostListFragment
import com.lucasbais.reddit.ui.splitter.SplitFragment
import com.lucasbais.reddit.vo.RedditPost
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

/**
 * Main activity navigation.
 */
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector,
    PostListFragment.OnPostListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (savedInstanceState == null) showSplitFragment()
    }


    override fun onClick(post: RedditPost) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is SplitFragment) {
            val couldShowDetail = fragment.showItem(post)
            if (!couldShowDetail) {
                showDetailFragment(post)
            }
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is SplitFragment) {
            super.onBackPressed()
        } else {
            showSplitFragment()
        }
    }

    private fun showSplitFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SplitFragment())
            .commit()
    }

    private fun showDetailFragment(post: RedditPost) {
        supportFragmentManager.apply {
            beginTransaction()
                .setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
                .replace(R.id.container, PostDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(PostDetailFragment.ARG_POST_ID, post.id)
                    }
                })
                .commit()
        }
    }
}
