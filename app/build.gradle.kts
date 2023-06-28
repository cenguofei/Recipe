plugins {
    id("com.android.application")
    id("kotlin-android")

    //navigation 传参
    id("androidx.navigation.safeargs.kotlin")

    //BindingAdapter
    //room
    id("kotlin-kapt")

    //传参
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.food"

    compileSdk = 33

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }

    defaultConfig {
        applicationId =  "com.example.food"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
}

dependencies {
    //gson
    implementation ("com.google.code.gson:gson:2.8.7")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    // ViewModel
    //Room
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha02")
    implementation ("androidx.activity:activity-ktx:1.2.0")
    implementation ("androidx.fragment:fragment-ktx:1.3.0")

    //by viewmodels
    implementation ("androidx.activity:activity-ktx:1.2.0")
    implementation ("androidx.fragment:fragment-ktx:1.3.0")


    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")

    //ShimmerRecyclerView
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
//    implementation ("com.todkars:shimmer-recyclerview:0.4.1")
//    implementation ("io.supercharge:shimmerlayout:2.1.0")

    //glide  三方库
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")


    //Room
    val room_version = "2.5.1"
    implementation("androidx.room:room-runtime:$room_version")
//    annotationProcessor "androidx.room:room-compiler:$room_version"
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


    val lifecycle_version = "2.4.0-alpha02"
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    //Jsoup   html转换为字符串
    implementation ("org.jsoup:jsoup:1.13.1")

    implementation ("androidx.core:core-ktx:1.3.2")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation( "com.google.android.material:material:1.3.0")
    implementation( "androidx.constraintlayout:constraintlayout:2.0.4")
    implementation( "androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.3.0")
}