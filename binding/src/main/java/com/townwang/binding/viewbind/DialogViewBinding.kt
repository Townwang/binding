package com.townwang.binding.viewbind

import android.app.Dialog
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.townwang.binding.base.DialogDelegate
import com.townwang.binding.ext.inflateMethod
import kotlin.reflect.KProperty

class DialogViewBinding<T : ViewBinding>(
    classes: Class<T>,
    lifecycle: Lifecycle? = null
) : DialogDelegate<T>(lifecycle) {

    private var layoutInflater = classes.inflateMethod()

    override fun getValue(thisRef: Dialog, property: KProperty<*>): T {
        return viewBinding?.run {
            this

        } ?: let {

            val bind = layoutInflater.invoke(null, thisRef.layoutInflater) as T
            thisRef.setContentView(bind.root)
            return bind.apply { viewBinding = this }
        }

    }

}