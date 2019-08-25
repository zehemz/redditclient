package com.lucasbais.reddit.ui.splitter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasbais.reddit.R
import com.lucasbais.reddit.di.Injectable
import com.lucasbais.reddit.ui.post.PostDetailFragment
import com.lucasbais.reddit.ui.post.PostListFragment
import com.lucasbais.reddit.vo.RedditPost
import kotlinx.android.synthetic.main.item_list_container.*

/**
 * This splitter fragment has the only responsibility to show
 * an splitted view
 */
class SplitFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_list_container, container, false)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.item_list_container, PostListFragment())
                .commit()
        }
        return rootView
    }

    /**
     * Returns true if it could be handled, false if not.
     */
    fun showItem(item: RedditPost): Boolean {
        if (item_detail_container == null) return false
        else {
            childFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.item_detail_container, PostDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(PostDetailFragment.ARG_POST_ID, item.id)
                        }
                    })
                    .commit()
            }
            return true
        }
    }
}