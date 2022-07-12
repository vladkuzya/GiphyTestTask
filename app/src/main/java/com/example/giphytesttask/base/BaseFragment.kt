package com.example.giphytesttask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    @LayoutRes
    protected abstract fun getContentView(): Int
    protected abstract fun setupDataBinding(dataBinding: ViewDataBinding?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = null
        val contentViewId = getContentView()
        if (contentViewId > 0) {
            val dataBinding =
                DataBindingUtil.inflate<ViewDataBinding>(inflater, contentViewId, container, false)
            view = dataBinding.root
            setupDataBinding(dataBinding)
        }
        return view
    }

}