package com.lucasbais.reddit.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lucasbais.reddit.vo.RedditPost

/**
 * Main database description.
 */
@Database(
    entities = [RedditPost::class],
    version = 1,
    exportSchema = false
)
abstract class RedditDb : RoomDatabase() {

    abstract fun postDao(): PostDao
}

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: RedditPost)

    @Query("SELECT * FROM redditpost WHERE id=:postId")
    fun load(postId: String): LiveData<RedditPost>

    @Query("SELECT * FROM redditpost")
    fun getAll(): LiveData<List<RedditPost>>

    @Query("DELETE FROM redditpost")
    fun deleteAll()

    @Query("DELETE FROM redditpost WHERE id=:postId")
    fun delete(postId: String)
}