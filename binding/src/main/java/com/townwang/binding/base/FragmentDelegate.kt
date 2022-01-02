package com.townwang.binding.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hi.dhl.binding.observerWhenCreated
import com.hi.dhl.binding.observerWhenDestroyed
import kotlin.properties.ReadOnlyProperty


abstract class FragmentDelegate<T : ViewBinding>(
    fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    protected var viewBinding: T? = null

    init {
        fragment.lifecycle.observerWhenCreated {
            fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewOwner ->
                viewOwner.lifecycle.observerWhenDestroyed {
                    destroyed()
                }
            }
        }

    }

    private fun destroyed() {
        viewBinding = null
    }
}