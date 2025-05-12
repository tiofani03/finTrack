import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.ksp)
    alias(libs.plugins.androidxRoom)
    alias(libs.plugins.kotlinCocoapods)

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
            baseName = "FintrackCoreData"
            isStatic = true
        }
    }

    cocoapods {
        version = "1.0"
        name = "FintrackCoreData"
        summary = "Some description for a Kotlin/Native module"
        homepage = "fintrack"
        podfile = project.file("../../iosApp/Podfile")

        ios.deploymentTarget = "13.0"

        pod("netfox") {
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
        pod("GoogleSignIn")

        framework {
            baseName = "FintrackCoreData"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.koin.core)
            implementation("com.google.android.gms:play-services-auth:21.0.0")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)

            api(libs.koin.core)
            api(libs.koin.test)
            api(libs.koin.compose)
            api(libs.kotlinx.datetime)

            // room
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            //datastore
            api(libs.androidx.datastore.preferences.core)

            // kmp firebase
            implementation("dev.gitlive:firebase-auth:1.8.0")
            implementation("dev.gitlive:firebase-firestore:1.8.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain {
            kotlin.srcDir("build/generated/ksp/metadata")
        }
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

val secretProperties = Properties().apply {
    load(rootProject.file("secret.properties").inputStream())
}

android {
    namespace = "com.tiooooo.fintrack.core.data"
    compileSdk = 35
    defaultConfig {
        minSdk = 24

        buildConfigField(
            "String",
            "CLIENT_ID",
            "\"${secretProperties["CLIENT_ID"]}\""
        )
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
