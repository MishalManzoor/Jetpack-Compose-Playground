import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-android")
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "de.jensklingenberg.jetpackcomposeplayground"
        minSdk = 21
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    namespace = "de.jensklingenberg.jetpackcomposeplayground"

}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler"
        )
        freeCompilerArgs += listOf(

            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_compiler"
        )
    }
}
val compose_version = libs.versions.compose.version.get()

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.compose.animation:animation-core:$compose_version")
    implementation("androidx.compose.animation:animation:$compose_version")
    implementation("androidx.compose.foundation:foundation-layout:$compose_version")
    implementation("androidx.compose.foundation:foundation:$compose_version")
    implementation("androidx.compose.material:material-icons-core:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.runtime:runtime-livedata:$compose_version")
    implementation("androidx.compose.runtime:runtime-rxjava2:$compose_version")
    implementation("androidx.compose.ui:ui-geometry:$compose_version")
    implementation("androidx.compose.ui:ui-graphics:$compose_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.ui:ui-viewbinding:$compose_version")
    implementation("androidx.compose.ui:ui-text:$compose_version")
    implementation("androidx.compose.ui:ui-util:$compose_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    androidTestImplementation("androidx.compose.ui:ui-test:$compose_version")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.6.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test:core:1.6.1")
    androidTestImplementation("androidx.test:rules:1.6.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
    androidTestImplementation("org.mockito:mockito-core:5.12.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    androidTestImplementation("com.adevinta.android:barista:4.3.0") {
        exclude(group = "org.jetbrains.kotlin")
    }
}