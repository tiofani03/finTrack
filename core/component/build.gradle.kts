import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)

}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "component"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.koin.core)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)

            api(compose.material3)
            api(compose.ui)
            api(compose.components.uiToolingPreview)
            api(compose.materialIconsExtended)
            api(libs.image.loader)

            api(libs.voyager.koin)
            api(libs.voyager.navigator)
            api(libs.voyager.screenModel)
            api(libs.voyager.transitions)
            api(libs.voyager.tabNavigator)
            api(libs.voyager.bottomSheetNavigator)
            api(libs.voyager.viewmodelKmp)
            api(libs.adaptive)
            api(libs.adaptive.layout)
            api(libs.adaptive.navigation)
            implementation(libs.material3.window.size.class1)
            api(libs.pullrefresh)
            api(libs.cmptoast)
            api(libs.vico.multiplatform.m3)
            implementation(project(":core:data"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.tiooooo.fintrack.core.component"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
