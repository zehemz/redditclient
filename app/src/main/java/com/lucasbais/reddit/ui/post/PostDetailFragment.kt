package com.lucasbais.reddit.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lucasbais.reddit.R
import com.lucasbais.reddit.di.Injectable
import kotlinx.android.synthetic.main.item_detail_content.*
import javax.inject.Inject

/**
 * A fragment representing a single Item detail screen.
 */
class PostDetailFragment : Fragment(), Injectable {


    @Inject
    /* default */ lateinit var viewModelFactory: ViewModelProvider.Factory

    /* default */ val viewModel: RedditPostViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_POST_ID)) {
                it.getString(ARG_POST_ID)?.let { idValue ->
                    viewModel.detail(idValue).observe(this, Observer {
                        title.text = it.title
                        author.text = it.author
                        Glide.with(this@PostDetailFragment)
                            .load(it.thumbnail).apply(RequestOptions().fitCenter())
                            .into(image)
                    })
                }
            }
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_POST_ID = "com.lucasbais.reddit.ARG_POST_ID"
    }
}
