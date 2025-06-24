import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.ksp)
    alias(libs.plugins.androidxRoom)

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
            api(libs.kotlinx.datetime)

            // room
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            //datastore
            api(libs.androidx.datastore.preferences.core)

            // kmp firebase
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.gitlive.firebase.firestore)
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
