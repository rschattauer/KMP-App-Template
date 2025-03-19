package com.jetbrains.kmpapp.another.native

import org.koin.core.annotation.Single

@Single(binds = [AnotherThingy::class])
actual class AnotherPlatformComponent : AnotherThingy {
    actual override fun sayHello(): String = "I'm ANOTHER iOS"
}