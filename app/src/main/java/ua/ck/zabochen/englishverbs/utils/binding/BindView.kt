package ua.ck.zabochen.englishverbs.utils.binding

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun <T : View> Activity.bindView(@IdRes
                                 res: Int): Lazy<T> {
    return unsafeLazy { findViewById<T>(res) }
}

fun <T : View> Fragment.bindView(view: View,
                                 @IdRes
                                 res: Int): Lazy<T> {
    return unsafeLazy { view.findViewById<T>(res) }
}

private fun <T> unsafeLazy(initializer: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE, initializer)
}