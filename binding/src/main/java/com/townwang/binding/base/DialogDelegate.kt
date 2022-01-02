package com.townwang.binding.base

import android.app.Dialog
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.hi.dhl.binding.observerWhenDestroyed
import kotlin.properties.ReadOnlyProperty

abstract class DialogDelegate<T : ViewBinding>(
    lifecycle: Lifecycle? = null
) : ReadOnlyProperty<Dialog, T> {

    protected var viewBinding: T? = null

    init {
        lifecycle?.observerWhenDestroyed { destroyed() }
    }

    fun destroyed() {
        viewBinding = null
    }
}