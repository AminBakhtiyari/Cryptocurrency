package dependencies

object DataDep {
    const val kotlin = Dependencies.KotlinDep.kotlin
    const val javax = Dependencies.JavaDep.javax
    const val coroutineCore = Dependencies.CoroutinesDep.coroutineCore
    const val androidxAnnotation = Dependencies.CoreDep.androidxAnnotation
    val retrofit = listOf(
        Dependencies.RetrofitDep.retrofit,
        Dependencies.RetrofitDep.moshiConverter,
        Dependencies.RetrofitDep.loggingInterceptor
    )

    val room = listOf(
        Dependencies.RoomDep.roomRuntime,
        Dependencies.RoomDep.roomKtx,
        Dependencies.RoomDep.roomPaging
    )
    const val roomKapt = Dependencies.RoomDep.roomCompilerKapt
    const val pagingCommon = Dependencies.PagingDep.pagingCommon

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val mockitoKotlin = Dependencies.TestDep.mockitoKotlin
        const val mockitoInline = Dependencies.TestDep.mockitoInline
        const val assertJ = Dependencies.TestDep.assertJ
        const val testCore = Dependencies.TestDep.testCore
        const val testExtJunit = Dependencies.TestDep.testExtJunit
        const val robolectric = Dependencies.TestDep.robolectric
        const val roomTest = Dependencies.TestDep.roomTest
    }
}