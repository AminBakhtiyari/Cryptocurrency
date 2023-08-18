import dependencies.UiDep

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.dagger)
    id(Config.Plugins.jetbrainsKotlin)
}

android {
    compileSdkVersion(Config.Android.androidCompileSdkVersion)
    buildToolsVersion(Config.Android.androidBuildToolsVersion)

    defaultConfig {
        applicationId(Environments.Release.appId)
        minSdkVersion(Config.Android.androidMinSdkVersion)
        targetSdkVersion(Config.Android.androidTargetSdkVersion)
        versionCode(Environments.Release.appVersionCode)
        versionName(Environments.Release.appVersionName)

        testInstrumentationRunner(Config.testRunner)
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
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
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.presentation))

    // Core Dependencies
    implementation(UiDep.kotlin)
    implementation(UiDep.coreKtx)
    implementation(UiDep.appCompat)
    implementation(UiDep.material)
    implementation(UiDep.constraint)
    implementation(UiDep.navigationFragmentKtx)
    implementation(UiDep.navigationUiKtx)
    implementation(UiDep.activityKtx)
    // LifeCycle
    UiDep.LifeCycle.forEach {
        implementation(it)
    }
    // Dagger-Hilt
    UiDep.DaggerHilt.forEach {
        implementation(it)
    }
    UiDep.DaggerHiltKapt.forEach {
        kapt(it)
    }
    // Coroutines
    UiDep.Coroutines.forEach {
        implementation(it)
    }
    // Glide
    implementation(UiDep.glide)
    kapt(UiDep.glideKapt)
    // Timber
    implementation(UiDep.timber)
    // Lottie animation
    implementation(UiDep.lottie)

    //shimmer
    implementation(UiDep.shimmer)

    //paging
    implementation(UiDep.paging)

    // Test Dependencies
    testImplementation(UiDep.Test.junit)
    testImplementation(UiDep.Test.assertJ)
    testImplementation(UiDep.Test.mockitoKotlin)
    testImplementation(UiDep.Test.mockitoInline)
    testImplementation(UiDep.Test.coroutines)
    testImplementation(UiDep.Test.androidxArchCore)
    testImplementation(UiDep.Test.robolectric)
    testImplementation(UiDep.Test.testExtJunit)
}
