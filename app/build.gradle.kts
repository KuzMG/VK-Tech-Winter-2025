plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.avito.tech.vk_tech_winter_2025"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.avito.tech.vk_tech_winter_2025"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    val media3_version = "1.5.1"
    implementation("androidx.media3:media3-exoplayer:$media3_version")
    implementation("androidx.media3:media3-ui:$media3_version")
    implementation("androidx.media3:media3-common:$media3_version")


    val dagger_version = "2.51.1"
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")

    val picasso_version="2.8"
    implementation("com.squareup.picasso:picasso:$picasso_version")


    implementation("androidx.fragment:fragment-ktx:1.8.4")
    val lifecycle_version="2.8.7"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    val coroutines_version="1.3.9"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")


    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}