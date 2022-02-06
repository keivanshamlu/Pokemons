package modules

object Modules {

    const val APP = ":app"
    const val DOMAIN = ":domain"
    const val CORE = ":core"
    const val NAVIGATION = ":navigation"
    const val DESIGN_SYSTEM = ":designSystem"

    object Data {
        private const val dir = ":data"
        const val DATA = ":$dir:data-core"
        const val DATA_REMOTE = "$dir:data-remote"
        const val DATA_LOCAL = "$dir:data-local"
    }
    object Feature {
        private const val dir = ":features"
        const val SHIFTS = "$dir:shifts"
        const val MAPS = "$dir:map"
    }
    object Utility {
        private const val dir = ":utility"
        const val BASES = "$dir:bases"
        const val BASES_ANDROID = "$dir:bases-android"
    }
}