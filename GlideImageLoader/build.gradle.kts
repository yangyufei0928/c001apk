import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.library")
    id("kotlin-android")
}

group = "com.github.mikaelzero"
setupLibraryModule {
    namespace = "net.mikaelzero.mojito.loader.glide"
    defaultConfig {
        minSdk = 16
    }
}

fun Project.setupLibraryModule(block: LibraryExtension.() -> Unit = {}) {
    setupBaseModule<LibraryExtension> {
        testOptions {
            unitTests.isIncludeAndroidResources = true
        }
        block()
    }
}

android {
    compileSdkVersion(34)

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 16
        targetSdk = 34

        buildConfigField("String", "VERSION_NAME", "\"1.0.0\"")
        buildConfigField("int",    "VERSION_CODE", "1")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


fun Project.setupAppModule(block: BaseAppModuleExtension.() -> Unit = {}) {
    setupBaseModule<BaseAppModuleExtension> {
        defaultConfig {
            versionCode = 1
            versionName = "1.0"
            vectorDrawables.useSupportLibrary = true
        }
        block()
    }
}

inline fun <reified T : BaseExtension> Project.setupBaseModule(crossinline block: T.() -> Unit = {}) {
    extensions.configure<BaseExtension>("android") {
        compileSdkVersion(34)
        defaultConfig {
            minSdk = 16
            targetSdk = 34
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }
        (this as T).block()
    }
}

fun BaseExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.okhttp)
    api(libs.glide.okhttp3.integration)
    implementation(project(":mojito"))
}
