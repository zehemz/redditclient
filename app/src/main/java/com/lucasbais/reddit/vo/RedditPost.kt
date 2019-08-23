package com.lucasbais.reddit.vo

/**
 * Data class that represents https://github.com/deviget/Android/blob/master/top.json data -> child
 * It represents a reddit post.
 */
data class RedditPost(
    val id: String,
    val title: String,
    val author: String,
    val created_utc: Long,
    val thumbnail: String,
    val num_comments: Long
)