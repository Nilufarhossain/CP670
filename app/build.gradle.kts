plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("jacoco")
}

android {
    namespace = "com.example.androidassignments"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidassignments"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            // Enable coverage for Android instrumented tests
            enableAndroidTestCoverage = true
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest") // Ensure it runs after unit tests

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*",
        "**/*Test*.*", "**/*InstrumentedTest*.*"
    )

    // Java classes directory
    val javaClasses = fileTree(layout.buildDirectory.dir("intermediates/javac/debug/compileDebugJavaWithJavac/classes")) {
        exclude(fileFilter)
    }

    classDirectories.setFrom(javaClasses)
    sourceDirectories.setFrom(files("src/main/java"))

    executionData.setFrom(fileTree(layout.buildDirectory) {
        include("jacoco/testDebugUnitTest.exec")
    })
}

tasks.register<JacocoReport>("jacocoAndroidTestReport") {
    dependsOn("connectedDebugAndroidTest") // Ensure it runs after Android instrumentation tests

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*",
        "**/*Test*.*", "**/*AndroidTest*.*"
    )

    val javaClasses = fileTree(layout.buildDirectory.dir("intermediates/javac/debug/compileDebugJavaWithJavac/classes")) {
        exclude(fileFilter)
    }

    classDirectories.setFrom(javaClasses)
    sourceDirectories.setFrom(files("src/main/java"))

    executionData.setFrom(fileTree(layout.buildDirectory) {
        include("outputs/code_coverage/debugAndroidTest/connected/**/*.ec") // Default location for Android instrumentation coverage
    })
}

tasks.withType<Test> {
    finalizedBy(tasks.named("jacocoTestReport")) // Generate the unit test coverage report after tests run
}

tasks.withType<Test> {
    finalizedBy(tasks.named("jacocoAndroidTestReport")) // Generate the instrumentation test coverage report after tests run
}




dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.rules)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.intents)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core.v361)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation (libs.truth)
    androidTestImplementation (libs.mockito.android)
    testImplementation (libs.robolectric.v4121)


}