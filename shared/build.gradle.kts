import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight") version ("2.0.0")
    id("com.codingfeline.buildkonfig")
    id("kotlinx-serialization")
}

val ktorVersion = "2.3.2"
val coroutinesVersion = "1.7.3"
val dateTimeVersion = "0.4.0"
val mokoVersion = "0.16.1"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true

            it.compilations.all {
                kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
            }
        }
    }

    targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
        .forEach {
            it.binaries.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
                .forEach { lib ->
                    lib.isStatic = false
                    lib.linkerOpts.add("-lsqlite3")

                }
        }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:$mokoVersion")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                //image loading
                implementation("media.kamel:kamel-image:0.7.1")

                //ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

                // Serialization
                val serializationVersion = "1.5.1"
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                //sqlDelight
                implementation("app.cash.sqldelight:runtime:${Versions.sqlDelight}")
                implementation("app.cash.sqldelight:coroutines-extensions:${Versions.sqlDelight}")

                //koin
                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:${Versions.sqlDelight}")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                //Koin
                val koin_version = "3.4.1"
                implementation("io.insert-koin:koin-android:$koin_version")


                // Import the Firebase BoM
                implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
                implementation("com.google.firebase:firebase-analytics-ktx")
            }
        }
        val androidUnitTest by getting

        val iosMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation("app.cash.sqldelight:native-driver:${Versions.sqlDelight}")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

buildkonfig {
    packageName = "com.example.movieapp"
    val baseUrl = "BASE_URL"
    val authKeyKey = "AUTH_KEY"
    val posterBaseUrl = "POSTER_BASE_URL"
    val authKey: String = gradleLocalProperties(rootDir).getProperty("AUTH_KEY")

    defaultConfigs {
        buildConfigField(
            Type.STRING,
            baseUrl,
            "https://api.themoviedb.org"
        )
        buildConfigField(
            Type.STRING,
            authKeyKey,
            authKey
        )
        buildConfigField(
            Type.STRING,
            posterBaseUrl,
            "https://image.tmdb.org/t/p/original"
        )
    }
    defaultConfigs("debug") {
        buildConfigField(Type.STRING, baseUrl, "https://api.themoviedb.org")
        buildConfigField(Type.STRING, authKeyKey, authKey)
        buildConfigField(Type.STRING, posterBaseUrl, "https://image.tmdb.org/t/p/original")
    }
}

android {
    namespace = "com.example.taskplanner"
    compileSdk = 33
    defaultConfig {
        minSdk = 27
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create("MovieDatabase") {
            packageName.set("com.example.sqldelight.database.moviedb")
            generateAsync.set(false)
        }
        linkSqlite.set(true)
    }
}

//sqldelight {
//    database("MovieDatabase") {
//        packageName = "com.example.sqldelight.database.moviedb"
//        sourceFolders = listOf("sqldelight")
//    }
//}

dependencies {
    implementation("androidx.core:core:1.10.1")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}

object Versions {
    const val koin = "3.2.0"
    const val sqlDelight: String = "2.0.0"
}

object Deps {
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }
}