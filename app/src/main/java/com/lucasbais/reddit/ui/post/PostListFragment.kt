package com.lucasbais.reddit.ui.post

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lucasbais.reddit.R
import com.lucasbais.reddit.di.Injectable
import com.lucasbais.reddit.vo.RedditPost
import com.lucasbais.reddit.vo.Status
import kotlinx.android.synthetic.main.item_content.view.*
import kotlinx.android.synthetic.main.item_list_content.*
import javax.inject.Inject

/**
 * This fragment holds the item lists.
 */
class PostListFragment : Fragment(), Injectable {

    interface OnPostListener {
        fun onClick(post: RedditPost)
    }

    // Listen post click
    private lateinit var onPostListener: OnPostListener

    @Inject
    /* default */ lateinit var viewModelFactory: ViewModelProvider.Factory

    /* default */ val viewModel: RedditPostViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_list_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SimpleItemRecyclerViewAdapter(emptyList())
        item_list?.adapter = adapter
        viewModel.posts.observe(this, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> adapter.apply {
                    setData(result.data!!)
                    notifyDataSetChanged()
                }
                Status.LOADING -> Log.i("LOADING", "getting posts")
                else -> Log.e("ERROR", "getting posts")
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPostListener)
            this.onPostListener = context
    }

    inner class SimpleItemRecyclerViewAdapter(
        private var values: List<RedditPost>
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as RedditPost
                onPostListener.onClick(item)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.title
            holder.contentView.text = item.author

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size
        fun setData(data: List<RedditPost>) {
            values = data
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }

}