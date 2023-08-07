buildscript {
    dependencies {
        val kotlin_version = "1.9.0"
        classpath ("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath ("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.13.3")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
