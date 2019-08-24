package com.lucasbais.reddit.di

import android.app.Application
import androidx.room.Room
import com.lucasbais.reddit.api.LiveDataCallAdapterFactory
import com.lucasbais.reddit.api.RedditService
import com.lucasbais.reddit.db.PostDao
import com.lucasbais.reddit.db.RedditDb
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(): RedditService {
        return Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(RedditService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): RedditDb {
        return Room
            .databaseBuilder(app, RedditDb::class.java, "reddit.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostDao(db: RedditDb): PostDao {
        return db.postDao()
    }
}