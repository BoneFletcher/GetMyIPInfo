package com.sdv.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel


abstract class BaseFragment : Fragment() {
    @Suppress("LeakingThis")
    abstract val viewModel: ViewModel

    protected abstract fun observeChanges()

}