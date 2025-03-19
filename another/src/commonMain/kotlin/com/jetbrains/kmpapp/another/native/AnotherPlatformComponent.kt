package com.jetbrains.kmpapp.another.native

import org.koin.core.annotation.Single

@Single
expect class AnotherPlatformComponent {
    fun sayHello(): String
}