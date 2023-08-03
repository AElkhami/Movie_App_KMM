plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight") version "2.0.0"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
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
        }
    }

    val ktorVersion = "2.3.2"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                //ktor
                implementation("io.ktor:ktor-server-core:$ktorVersion")

                // Serialization
                val serializationVersion = "1.5.1"
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                //sqlDelight
//                val sqlDelightVersion = "2.0.0"
//                implementation("app.cash.sqldelight:runtime:$sqlDelightVersion")
//                implementation("app.cash.sqldelight:coroutines-extensions:$sqlDelightVersion")

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
                implementation ("app.cash.sqldelight:android-driver:2.0.0")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

                // Import the Firebase BoM
                implementation (platform("com.google.firebase:firebase-bom:32.2.0"))
                implementation ("com.google.firebase:firebase-analytics-ktx")
            }
        }
        val androidUnitTest by getting

        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation ("app.cash.sqldelight:native-driver:2.0.0")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "com.example.taskplanner"
    compileSdk = 33
    defaultConfig {
        minSdk = 27
    }
}

sqldelight {
    databases {
        create("MovieDatabase") {
            packageName.set("com.example.sqldelight.database.moviedb")
            generateAsync.set(true)
        }
    }
}

object Versions {
    const val koin = "3.2.0"
}

object Deps {
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }
}