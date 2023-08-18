import dependencies.DataDep

plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
}

android {
    compileSdk = Config.Android.androidCompileSdkVersion
    buildToolsVersion = Config.Android.androidBuildToolsVersion

    defaultConfig {
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion
        version = Environments.Release.appVersionCode

        testInstrumentationRunner = Config.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Kotlin
    implementation(DataDep.kotlin)
    // JavaX
    implementation(DataDep.javax)
    // Network (Retrofit, OkHttp, Interceptor, Moshi)
    DataDep.retrofit.forEach { implementation(it) }
    // Room
    DataDep.room.forEach {
        api(it)
    }
    kapt(DataDep.roomKapt)
    //Paging
    implementation(DataDep.pagingCommon)
    // Coroutines
    implementation(DataDep.coroutineCore)
    //Androidx Annotation
    implementation(DataDep.androidxAnnotation)

    // Test Dependencies
    testImplementation(DataDep.Test.junit)
    testImplementation(DataDep.Test.assertJ)
    testImplementation(DataDep.Test.coroutines)
    testImplementation(DataDep.Test.testCore)
    testImplementation(DataDep.Test.testExtJunit)
    testImplementation(DataDep.Test.robolectric)
    testImplementation(DataDep.Test.roomTest)
}
