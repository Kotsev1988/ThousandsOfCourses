plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id ("kotlin-kapt")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies{
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.converter.gson)
}
