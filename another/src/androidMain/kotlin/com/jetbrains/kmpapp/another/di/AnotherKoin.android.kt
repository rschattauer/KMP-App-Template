package com.jetbrains.kmpapp.another.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.jetbrains.kmpapp.another.native")
actual class AnotherNativeModule