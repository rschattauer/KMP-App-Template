package com.jetbrains.kmpapp.another.native

import android.content.Context
import org.koin.core.annotation.Single

@Single
actual class AnotherPlatformComponent(val context: Context) {
    actual fun sayHello(): String = "I'm ANOTEHR Android - $context"
}