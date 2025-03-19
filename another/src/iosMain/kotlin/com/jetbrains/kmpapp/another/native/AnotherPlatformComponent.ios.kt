package com.jetbrains.kmpapp.another.native

import org.koin.core.annotation.Single

@Single
actual class AnotherPlatformComponent {
    actual fun sayHello(): String = "I'm ANOTHER iOS"
}