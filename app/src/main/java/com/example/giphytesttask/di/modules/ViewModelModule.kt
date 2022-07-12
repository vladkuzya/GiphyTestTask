package com.example.giphytesttask.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphytesttask.screens.gif_search.GifSearchViewModel
import com.example.giphytesttask.util.view_model.ViewModelFactory
import com.example.giphytesttask.util.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GifSearchViewModel::class)
    abstract fun bindGifSearchViewModel(gifSearchViewModel: GifSearchViewModel): ViewModel
}