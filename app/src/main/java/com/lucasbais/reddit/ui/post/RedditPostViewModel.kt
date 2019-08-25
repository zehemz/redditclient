package com.lucasbais.reddit.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucasbais.reddit.repository.RedditPostRepository
import com.lucasbais.reddit.vo.RedditPost
import com.lucasbais.reddit.vo.Resource
import javax.inject.Inject

class RedditPostViewModel @Inject constructor(private val repository: RedditPostRepository) :
    ViewModel() {
    val posts: LiveData<Resource<List<RedditPost>>> = repository.getTopPosts()

    fun detail(postId: String): LiveData<RedditPost> {
        return repository.getPost(postId)
    }

    fun dismissAll() {
        repository.deleteAll()
    }

    fun dismiss(postId: String) {
        repository.delete(postId)
    }
}
