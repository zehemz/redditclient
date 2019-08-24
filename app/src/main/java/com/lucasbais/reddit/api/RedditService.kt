package com.lucasbais.reddit.api

import androidx.lifecycle.LiveData
import com.lucasbais.reddit.vo.RedditListing
import retrofit2.http.GET

/**
 * Reddit Retrofit's interface.
 */
interface RedditService {

    @GET("top/.json")
    fun getTop(): LiveData<ApiResponse<RedditListing>>

}