plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 34
    namespace = "net.mikaelzero.mojito.view.sketch"

    buildFeatures {
        buildConfig = true  // ✅ 打开 BuildConfig 生成
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

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.okhttp)
    implementation("io.github.panpf.sketch:sketch-gif:2.7.1")
    implementation(libs.androidx.exifinterface)
    implementation(project(":mojito"))
}
