package com.lucasbais.reddit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.lucasbais.reddit.AppExecutors
import com.lucasbais.reddit.api.*
import com.lucasbais.reddit.db.PostDao
import com.lucasbais.reddit.vo.RedditPost
import com.lucasbais.reddit.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles RedditPost instances.
 */
@Singleton
class RedditPostRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val postDao: PostDao,
    private val redditService: RedditService
) {

    fun getTopPosts(): LiveData<Resource<List<RedditPost>>> {
        return object : NetworkBoundResource<List<RedditPost>, List<RedditPost>>(appExecutors) {
            override fun saveCallResult(item: List<RedditPost>) = item.forEach { value ->
                postDao.insert(value)
            }

            override fun shouldFetch(data: List<RedditPost>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<RedditPost>> = postDao.getAll()
            override fun createCall(): LiveData<ApiResponse<List<RedditPost>>> {
                val liveData = redditService.getTop()
                return Transformations.map(liveData) { value ->
                    when (value) {
                        is ApiSuccessResponse -> ApiSuccessResponse(
                            value.body.data.children.map { innerValue -> innerValue.data }
                        )
                        is ApiErrorResponse -> ApiErrorResponse(value.errorMessage)
                        is ApiEmptyResponse -> ApiEmptyResponse<List<RedditPost>>()
                    }
                }
            }

        }.asLiveData()
    }

    fun getPost(postId: String): LiveData<RedditPost> {
        return postDao.load(postId)
    }

    fun deleteAll() {
        appExecutors.diskIO().execute {
            postDao.deleteAll()
        }
    }

    fun delete(postId: String) {
        appExecutors.diskIO().execute {
            postDao.delete(postId)
        }
    }
}