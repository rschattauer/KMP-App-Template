package com.jetbrains.kmpapp.another.native

import org.koin.core.annotation.Single

interface AnotherThingy {
    fun sayHello(): String
}

@Single(binds = [AnotherThingy::class])
expect class AnotherPlatformComponent : AnotherThingy {
    override fun sayHello(): String
}