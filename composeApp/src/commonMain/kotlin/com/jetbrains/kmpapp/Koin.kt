package com.jetbrains.kmpapp

import com.jetbrains.kmpapp.another.AnotherNativeModule
import com.jetbrains.kmpapp.another.native.AnotherPlatformComponent
import com.jetbrains.kmpapp.data.IdGenerator
import com.jetbrains.kmpapp.native.PlatformComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.*
import org.koin.mp.KoinPlatform

@Module
@ComponentScan("com.jetbrains.kmpapp.data")
class DataModule {

    @Single
    fun json() = Json { ignoreUnknownKeys = true }

    @Single
    fun httpClient(
        json: Json,
        anotherThingy: AnotherPlatformComponent,
    ) = HttpClient {
        install(ContentNegotiation) {
            // TODO Fix API so it serves application/json
            json(json, contentType = ContentType.Any)
        }
        defaultRequest {
            header("X-Custom-Header", anotherThingy.sayHello())
        }
    }
}

@Module
@ComponentScan("com.jetbrains.kmpapp.screens")
class ViewModelModule

@Module(includes = [DataModule::class, ViewModelModule::class, NativeModule::class, AnotherNativeModule::class])
class AppModule

@Module
@ComponentScan
expect class NativeModule()

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        modules(
            AppModule().module,
        )
        config?.invoke(this)
    }
    val hello = KoinPlatform.getKoin().get<PlatformComponent>().sayHello()
    println(hello)

    val idGen = KoinPlatform.getKoin().get<IdGenerator> { parametersOf("_prefix_") }.generate()
    println("Id => $idGen")
}
