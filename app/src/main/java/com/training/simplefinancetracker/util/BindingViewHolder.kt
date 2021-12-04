@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter", "unused")
package com.training.simplefinancetracker.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingViewHolder<T : ViewBinding> private constructor(val binding: T) :
    RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        bindingFactory: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
    ) : this(bindingFactory(LayoutInflater.from(parent.context), parent, false))
}