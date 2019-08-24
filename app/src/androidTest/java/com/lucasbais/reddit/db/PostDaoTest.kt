package com.lucasbais.reddit.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4

import com.lucasbais.reddit.vo.RedditPost
import com.lucasbais.reditapp.LiveDataTestUtil.getValue
import junit.framework.Assert.assertTrue

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val testEntity = testEntity()
        db.postDao().insert(testEntity)
        val loaded: RedditPost = getValue(db.postDao().load(testEntity.id))
        // in data classes equals and hash are automatically generated, this test is enough.
        assertThat(loaded, `is`(testEntity))
    }

    @Test
    fun insertTwoAndLoadAll() {
        val testEntity = testEntity()
        db.postDao().insert(testEntity)
        db.postDao().insert(testEntityTwo())
        val loaded: List<RedditPost> = getValue(db.postDao().getAll())
        // in data classes equals and hash are automatically generated, this test is enough.
        assertTrue(loaded.contains(testEntity))
        assertThat(loaded.size, `is`(2))
    }

    fun testEntity(): RedditPost =
        RedditPost(
            "1",
            "some_title",
            "author",
            123123L,
            "http://some_image.com",
            23L
        )

    fun testEntityTwo(): RedditPost =
        RedditPost(
            "2",
            "some_title",
            "author",
            123123L,
            "http://some_image.com",
            23L
        )
}