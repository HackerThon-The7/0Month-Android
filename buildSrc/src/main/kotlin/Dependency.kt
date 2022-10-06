object Dependency {

    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val androidKtx = "androidx.activity:activity-ktx:${Version.androidKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"

    object UI {
        const val material = "com.google.android.material:material:${Version.UI.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.UI.material}"
        const val materialCalendar = "com.github.prolificinteractive:material-calendarview:${Version.UI.calendar}"
    }

    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Version.GradlePlugin.gradle}"
        const val kotlin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.GradlePlugin.kotlin}"
    }

    object Test {
        const val junit = "junit:junit:${Version.Test.junit}"
        const val mockito = "org.mockito:mockito-core:${Version.Test.mockito}"
        const val androidJunit = "androidx.test.ext:junit:${Version.Test.androidJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.Test.espresso}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutine.coroutine}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutine.coroutine}"
    }

    object LocalStorage {
        const val room = "androidx.room:room-ktx:${Version.LocalStorage.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.LocalStorage.room}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Lifecycle.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Lifecycle.lifecycle}"
        const val runTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Lifecycle.lifecycle}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Version.Moshi.moshi}"
        const val moshiCompiler = "com.squareup.moshi:moshi-kotlin-codegen:${Version.Moshi.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.Moshi.moshiKotlin}"
    }

    object Navigation {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Version.Navigation.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.Navigation.navigation}"
    }

    object CircleImageView {
        const val circleImage = "de.hdodenhof:circleimageview:${Version.CircleImageView.circleImage}"
    }

    object Glide {
        const val glideCore = "com.github.bumptech.glide:glide:${Version.Glide.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.Glide.glide}"
    }

    object Animation {
        const val lottie = "com.airbnb.android:lottie:${Version.Animation.lottie}"
    }
}