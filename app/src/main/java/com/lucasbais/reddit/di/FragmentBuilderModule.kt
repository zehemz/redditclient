package com.lucasbais.reddit.di

import com.lucasbais.reddit.ui.post.PostDetailFragment
import com.lucasbais.reddit.ui.post.PostListFragment
import com.lucasbais.reddit.ui.splitter.SplitFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeItemListFragment(): PostListFragment

    @ContributesAndroidInjector
    abstract fun contributeItemDetailFragment(): PostDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeSplitterFragment(): SplitFragment
}