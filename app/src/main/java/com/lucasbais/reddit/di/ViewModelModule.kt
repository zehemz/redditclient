package com.lucasbais.reddit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucasbais.reddit.ui.post.RedditPostViewModel
import com.lucasbais.reddit.viewmodel.ViewModelFactory


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RedditPostViewModel::class)
    abstract fun bindRedditViewModel(redditPostViewModel: RedditPostViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}