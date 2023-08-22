import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.3"
    id("app.cash.sqldelight") version "2.0.0"
}

group = "com.example.movieapp"
version = "1.0.0"

object Versions {
    const val koin = "3.4.1"
    const val sqlDelight: String = "2.0.0"
    val ktorVersion = "2.3.2"
}

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    sourceSets {
        val jvmMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)

                implementation(project(":shared"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")

            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "movieapp"
            macOS {
                bundleID = "com.example.movieapp"
            }
        }
    }
}