package com.jetbrains.kmpapp.another.native

import android.content.Context
import org.koin.core.annotation.Single

@Single(binds = [AnotherThingy::class])
actual class AnotherPlatformComponent(val context: Context) : AnotherThingy {

    actual override fun sayHello(): String = "I'm ANOTEHR Android - $context"
}