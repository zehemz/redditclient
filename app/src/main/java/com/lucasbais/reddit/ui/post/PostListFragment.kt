package com.lucasbais.reddit.ui.post

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
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

    private lateinit var adapter: SimpleItemRecyclerViewAdapter

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

        adapter = SimpleItemRecyclerViewAdapter(emptyList())
        item_list?.adapter = adapter
        bindData()

        dismiss.setOnClickListener {
            viewModel.dismissAll()
        }
    }

    private fun bindData() {
        viewModel.posts.observe(this, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> adapter.apply {
                    result.data?.let {
                        setData(it)
                    }
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
            val post = values[position]
            holder.idView.text = post.title
            holder.contentView.text = post.author
            holder.comments.text =
                resources.getString(R.string.amount_of_comments, post.num_comments)


            Glide.with(this@PostListFragment)
                .load(post.thumbnail)
                .apply(holder.requestOptions)
                .into(holder.imageThumb)

            holder.dismiss.setOnClickListener {
                viewModel.dismiss(post.id)
            }

            with(holder.itemView) {
                tag = post
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        fun setData(data: List<RedditPost>) {
            values = data
            notifyDataSetChanged()
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
            val imageThumb: AppCompatImageView = view.image_thumb
            val comments: TextView = view.comments
            val dismiss: Button = view.dismiss_post

            val requestOptions: RequestOptions by lazy {
                RequestOptions()
                    .placeholder(ColorDrawable(Color.LTGRAY))
                    .transforms(CenterCrop())
            }
        }
    }

}