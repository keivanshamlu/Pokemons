import androidDeps.AndroidX.maps
import configs.androidFeature
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        kotlin(ANDROID)
        kotlin(KAPT)
        id(NAVIGATION_SAFEARGS_KOTLIN)
        id(ANDROID_EXTENSIONS)
        id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    }
    id("kotlin-android")
}
androidFeature()
dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.CORE))
    implementation(project(Modules.Utility.BASES))
    implementation(project(Modules.Utility.BASES_ANDROID))
    implementation(project(Modules.NAVIGATION))
    implementation(project(Modules.DESIGN_SYSTEM))
    implementation(maps)
}