package com.lucasbais.reddit.vo

/**
 * API response Reddit post listing.
 */
data class RedditListing(val data: Data) {

    data class Data(val children: List<RedditPost>)
}
