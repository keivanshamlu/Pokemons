import groupDepsModuleLevel.baseDependencies
import kotlinDeps.groupDeps.networking
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(JAVA_LIBRARY)
        id(KOTLIN)
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(project(Modules.Data.DATA))
    implementation(project(Modules.Utility.BASES))

    baseDependencies()
    networking()
}