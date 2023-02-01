package com.demo.githubtrend.common.navigation

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.whenStateAtLeast
import com.demo.githubtrend.R
import com.demo.githubtrend.common.extensions.hideKeyboard
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

inline fun LifecycleOwner.whenStateAtLeast(state: Lifecycle.State, crossinline block: () -> Unit) {
    if (lifecycle.currentState.isAtLeast(state)) {
        block()
    } else {
        lifecycle.coroutineScope.launch {
            lifecycle.whenStateAtLeast(state) { block() }
        }
    }
}

fun Fragment.popBackStack(fragmentManager: FragmentManager = requireActivity().supportFragmentManager) {
    requireActivity().popBackStack(fragmentManager)
}

fun FragmentActivity.popBackStack(fragmentManager: FragmentManager = supportFragmentManager) {
    if (fragmentManager.backStackEntryCount < 2) {
        finish()
    } else if (!fragmentManager.isStateSaved) {
        fragmentManager.popBackStack()
    }
}

fun <T : Fragment> Fragment.popBackStackTo(
    target: KClass<T>,
    inclusive: Boolean = false,
    smooth: Boolean = false,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) {
    if (smooth) requireActivity().smoothPopBackStackTo(target, inclusive, fragmentManager)
    else requireActivity().popBackStackTo(target, inclusive, fragmentManager)
}

fun <T : Fragment> FragmentActivity.smoothPopBackStackTo(
    target: KClass<T>,
    inclusive: Boolean = false,
    fragmentManager: FragmentManager = supportFragmentManager
) {
    val flag = if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0

    hideKeyboard()

    val tag = target.java.name
    with(fragmentManager) {
        if (backStackEntryCount == 0 || getBackStackEntryAt(0).name == tag && inclusive) {
            finish()
        } else {
            popBackStack(tag, flag)
        }
    }
}

fun <T : Fragment> FragmentActivity.popBackStackTo(
    target: KClass<T>,
    inclusive: Boolean = false,
    fragmentManager: FragmentManager = supportFragmentManager
) {
    val flag = if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0

    hideKeyboard()

    val tag = target.java.name
    with(fragmentManager) {
        if (backStackEntryCount == 0 || getBackStackEntryAt(0).name == tag && inclusive) {
            finish()
        } else {
            if (!popBackStackImmediate(tag, flag)) finish()
        }
    }
}

fun Fragment.popAndReplace(
    target: Fragment,
    popTo: KClass<out Fragment>? = null,
    @IdRes containerId: Int = R.id.content,
    tag: String = target.javaClass.canonicalName,
    addToBackStack: Boolean = true,
    inclusive: Boolean = false,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    requireActivity().hideKeyboard()
    with(fragmentManager) {
        popBackStack(
            popTo?.java?.name,
            if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0
        )
        commit(allowStateLoss = true) {
            // Preform immediate replace
            setCustomAnimations(enter, exit, popEnter, popExit)
            replace(containerId, target, tag)
            if (addToBackStack) addToBackStack(tag)
        }
    }
}

fun Fragment.popAndAdd(
    target: Fragment,
    popTo: KClass<out Fragment>? = null,
    @IdRes containerId: Int = R.id.content,
    tag: String = target.javaClass.canonicalName,
    addToBackStack: Boolean = true,
    inclusive: Boolean = false,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    requireActivity().hideKeyboard()
    with(fragmentManager) {
        popBackStack(
            popTo?.java?.name,
            if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0
        )

        commit(allowStateLoss = true) {
            // Preform immediate replace
            setCustomAnimations(enter, exit, popEnter, popExit)
            add(containerId, target, tag)
            if (addToBackStack) addToBackStack(tag)
        }
    }
}

fun Fragment.add(
    target: Fragment,
    @IdRes containerId: Int = R.id.content,
    tag: String = target.javaClass.canonicalName,
    addToBackStack: Boolean = true,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    requireActivity().hideKeyboard()
    fragmentManager.commit(allowStateLoss = true) {
        setCustomAnimations(enter, exit, popEnter, popExit)
        add(containerId, target, tag)
        if (addToBackStack) addToBackStack(tag)
    }
}

fun Fragment.replace(
    target: Fragment,
    @IdRes containerId: Int = R.id.content,
    addToBackStack: Boolean = true,
    tag: String = target.javaClass.canonicalName,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager,
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    requireActivity().hideKeyboard()
    fragmentManager.commit(allowStateLoss = true) {
        setCustomAnimations(enter, exit, popEnter, popExit)
        replace(containerId, target, tag)
        if (addToBackStack) addToBackStack(tag)
    }
}

fun FragmentActivity.replace(
    target: Fragment,
    @IdRes containerId: Int = R.id.content,
    tag: String = target.javaClass.canonicalName,
    addToBackStack: Boolean = true,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = supportFragmentManager,
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    hideKeyboard()
    fragmentManager.commit(allowStateLoss = true) {
        setCustomAnimations(enter, exit, popEnter, popExit)
        replace(containerId, target, tag)
        if (addToBackStack) addToBackStack(tag)
    }
}

fun FragmentActivity.popAndReplace(
    target: Fragment,
    popTo: KClass<out Fragment>? = null,
    @IdRes containerId: Int = R.id.content,
    tag: String = target.javaClass.canonicalName,
    addToBackStack: Boolean = true,
    inclusive: Boolean = false,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = supportFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    hideKeyboard()
    with(fragmentManager) {
        popBackStack(
            popTo?.java?.name,
            if (inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0
        )

        commit(allowStateLoss = true) {
            // Preform immediate replace
            setCustomAnimations(enter, exit, popEnter, popExit)
            replace(containerId, target, tag)
            if (addToBackStack) addToBackStack(tag)
        }
    }
}
