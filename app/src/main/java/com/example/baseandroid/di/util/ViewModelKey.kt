package com.example.baseandroid.di.util

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>) {
}