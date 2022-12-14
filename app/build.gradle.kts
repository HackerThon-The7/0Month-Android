plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        applicationId = "com.yongjincompany.hackerthonandroid"
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    implementation(Dependency.Moshi.moshi)
    kapt(Dependency.Moshi.moshiCompiler)
    implementation(Dependency.Moshi.moshiKotlin)

    implementation(Dependency.coreKtx)
    implementation(Dependency.appcompat)
/*    implementation(Dependency.androidKtx)
    implementation(Dependency.fragmentKtx)*/

    implementation(Dependency.UI.material)
    //implementation(Dependency.UI.constraintLayout)

    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.mockito)
    androidTestImplementation(Dependency.Test.androidJunit)
    androidTestImplementation(Dependency.Test.espresso)

    implementation(Dependency.LocalStorage.room)
    kapt(Dependency.LocalStorage.roomCompiler)

    implementation(Dependency.Coroutine.core)
    implementation(Dependency.Coroutine.android)

    implementation(Dependency.Lifecycle.viewModel)
    implementation(Dependency.Lifecycle.liveData)
    implementation(Dependency.Lifecycle.runTime)

    implementation(Dependency.Navigation.navigationFragment)
    implementation(Dependency.Navigation.navigationUi)

    implementation(Dependency.CircleImageView.circleImage)

    implementation(Dependency.Glide.glideCore)
    annotationProcessor(Dependency.Glide.glideCompiler)

    implementation(Dependency.Animation.lottie)

    implementation(Dependency.UI.materialCalendar)
}