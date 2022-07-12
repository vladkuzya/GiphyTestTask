package com.example.giphytesttask.di

import android.app.Application
import com.example.giphytesttask.base.GifsApp
import com.example.giphytesttask.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityModule::class, AppModule::class, ContextModule::class]
)
interface AppComponent : AndroidInjector<GifsApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: GifsApp)
}