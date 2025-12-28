import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "navigation"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.koin.core)
            implementation(libs.play.services.auth)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)

            api(libs.koin.core)
            api(libs.koin.test)
            api(libs.koin.compose)

            api(libs.voyager.navigator)
            api(libs.voyager.transitions)
            api(libs.voyager.tabNavigator)
            api(libs.voyager.bottomSheetNavigator)
            implementation(project(":core:component"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain {
            kotlin.srcDir("build/generated/ksp/metadata")
        }
    }
}
android {
    namespace = "com.tiooooo.fintrack.core.navigation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig { minSdk = libs.versions.android.minSdk.get().toInt() }
    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    }
}
