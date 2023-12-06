pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()
        //jcenter() se encuentra sin actualizaciones, pero lo inserto por si acaso
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Código que aparecía en las consultas de video: maven {url 'https://jitpack.io'}
        jcenter()
    }
}

rootProject.name = "Transporte Fem"
include(":app")
 