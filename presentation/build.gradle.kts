import dependencies.PresentationDep

plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id (Config.Plugins.kotlinParcelize)
}

android {
    compileSdk = Config.Android.androidCompileSdkVersion
    buildToolsVersion = Config.Android.androidBuildToolsVersion
    defaultConfig {
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion
        version = Environments.Release.appVersionCode

        testInstrumentationRunner = Config.testRunner

        // Configs
        buildConfigField("String", "BASE_URL", "\"" + Environments.Release.baseUrl + "\"")
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


}

dependencies {
    implementation(project(Modules.domain))

    implementation(PresentationDep.kotlin)
    implementation(PresentationDep.coroutineCore)
    // Dagger-Hilt (used for @InjectViewModel)
    PresentationDep.daggerHilt.forEach {
        implementation(it)
    }
    PresentationDep.daggerHiltKapt.forEach {
        kapt(it)
    }
    // JavaX
    implementation(PresentationDep.javax)
    // LifeCycle
    PresentationDep.lifeCycle.forEach {
        implementation(it)
    }

    //Paging
    implementation(PresentationDep.paging)

    // Timber
    implementation(PresentationDep.timber)

    // Test Dependencies
    testImplementation(PresentationDep.Test.junit)
    testImplementation(PresentationDep.Test.assertJ)
    testImplementation(PresentationDep.Test.mockitoKotlin)
    testImplementation(PresentationDep.Test.mockitoInline)
    testImplementation(PresentationDep.Test.coroutines)
    testImplementation(PresentationDep.Test.androidxArchCore)
    testImplementation(PresentationDep.Test.robolectric)
    testImplementation(PresentationDep.Test.testExtJunit)

}
