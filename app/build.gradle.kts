plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    kotlin("kapt")
}

android {
    namespace = "com.example.countrynew"
    compileSdk = 34

    dataBinding {
        enable = true
    }

    defaultConfig {
        applicationId = "com.example.countrynew"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kapt {
        correctErrorTypes = true
        includeCompileClasspath = true // Bu ayarı `true` yap
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
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0") // Bu sürüm projen için uygunsa
        }
    }
}

dependencies {
    val lifeCycleExtensionVersion = "1.1.1"
    val supportVersion = "28.0.0"
    val retrofitVersion = "2.3.0"
    val glideVersion = "4.9.0"
    val rxJavaVersion = "2.1.1"
    val room_version = "2.6.1"
    val navVersion = "2.2.1"
    val preferencesVersion = "1.1.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.guava:guava:30.1-jre")
    //implementation("android.arch.lifecycle:extensions:$lifeCycleExtensionVersion")


    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")

    // To use Kotlin Symbol Processing (KSP) 
    // ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    //kapt("androidx.room:room-compiler:$roomVersion")
    //implementation("androidx.room:room:$roomVersion")
    //implementation("androidx.room:room-compiler:$roomVersion")
    //implementation("androidx.room:room-ktx:$roomVersion")
    //kapt("android.arch.persistence.room:compiler:1.1.1")


    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation("com.google.android.material:material:1.1.0")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation("io.reactivex.rxjava2:rxandroid:$rxJavaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //implementation("com.android.support:palette-v7:$supportVersion")
    //implementation("com.android.support:design:$supportVersion")
    implementation("androidx.preference:preference:$preferencesVersion")

}