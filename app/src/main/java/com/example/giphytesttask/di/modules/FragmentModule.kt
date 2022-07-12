package com.example.giphytesttask.di.modules

import com.example.giphytesttask.screens.gif_search.GifSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun provideSearchGifFragment(): GifSearchFragment
}