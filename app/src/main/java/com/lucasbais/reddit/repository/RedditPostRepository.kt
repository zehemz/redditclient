package com.lucasbais.reddit.repository

import androidx.lifecycle.LiveData
import com.lucasbais.reddit.vo.RedditPost
import com.lucasbais.reddit.vo.Resource

/**
 * Repository that handles RedditPost instances.
 */
interface RedditPostRepository {

    fun getTopPosts(): LiveData<Resource<List<RedditPost>>>

    fun getPost(id: Long): LiveData<Resource<RedditPost>>
}