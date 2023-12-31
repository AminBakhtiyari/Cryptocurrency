import dependencies.DomainDep
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Config.Plugins.kotlin)
    id(Config.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(DomainDep.kotlin)
    implementation(DomainDep.coroutineCore)

    // JavaX
    implementation(DomainDep.javax)

    //Paging
    implementation(DomainDep.pagingCommon)

    // Test Dependencies
    testImplementation(DomainDep.Test.junit)
    testImplementation(DomainDep.Test.assertJ)
    testImplementation(DomainDep.Test.mockitoKotlin)
    testImplementation(DomainDep.Test.mockitoInline)
    testImplementation(DomainDep.Test.coroutines)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.5"
}