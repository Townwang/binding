package com.townwang.binding.viewbind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.townwang.binding.ext.inflateMethod
import com.townwang.binding.ext.inflateMethodWithViewGroup
import java.lang.reflect.Method
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewGroupViewBinding<T : ViewBinding>(
    classes: Class<T>,
    val inflater: LayoutInflater,
    val viewGroup: ViewGroup? = null
) : ReadOnlyProperty<ViewGroup, T> {

    private var viewBinding: T? = null
    private var layoutInflater: Method = if (viewGroup != null) {
        classes.inflateMethodWithViewGroup()
    } else {
        classes.inflateMethod()
    }

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T {
        return viewBinding?.run {
            this

        } ?: let {

            val bind: T
            if (viewGroup != null) {
                bind = layoutInflater.invoke(null, inflater, viewGroup) as T
            } else {
                bind = layoutInflater.invoke(null, inflater) as T
            }

            bind.apply {
                if (viewGroup == null) {
                    thisRef.addView(bind.root)
                }
                viewBinding = this
            }
        }
    }

    private fun destroyed() {
        viewBinding = null
    }
}