plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mehyo.marvelcharacters"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mehyo.marvelcharacters"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    // Current version
    val koinVersion = "3.2.3"
    val navVersion = "2.5.3"
    val lifecycleVersion = "2.5.1"
    val retrofitVersion = "2.9.0"
    val okhttpVersion = "4.10.0"
    val coroutinesVersion = "1.6.4"
    val gsonVersion = "2.10"
    val coilVersion = "2.2.2"

    // Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:$koinVersion")

    //kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    //The Paging Library helps you load and display small chunks of data at a time
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    //Nav
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //LifeCycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    //retrofit library for http requests and Gson converter
    implementation( "com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")

    //Google Gson
    implementation( "com.google.code.gson:gson:$gsonVersion")

    //Image loading
    implementation("io.coil-kt:coil:$coilVersion")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}