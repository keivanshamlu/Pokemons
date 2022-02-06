import androidDeps.AppConfig
import androidDeps.groupDeps.room
import groupDepsModuleLevel.featureModuleBaseDependencies
import kotlinDeps.groupDeps.networking
import modules.Modules
import configs.androidApp

plugins {
    GradlePluginId.apply {
        id(ANDROID_APPLICATION)
        kotlin(ANDROID)
        kotlin(KAPT)
    }
}
androidApp(AppConfig.applicationId)
dependencies {

    Modules.run {
        implementation(project(DOMAIN))
        implementation(project(CORE))
        implementation(project(DESIGN_SYSTEM))
        implementation(project(NAVIGATION))
        implementation(project(Modules.Utility.BASES))
    }
    Modules.Data.run {
        implementation(project(DATA))
        implementation(project(DATA_REMOTE))
        implementation(project(DATA_LOCAL))
    }
    Modules.Utility.run {
        implementation(project(BASES))
        implementation(project(BASES_ANDROID))
    }
    Modules.Feature.run {
        implementation(project(SHIFTS))
        implementation(project(MAPS))
    }
    featureModuleBaseDependencies()
    room()
    networking()
}