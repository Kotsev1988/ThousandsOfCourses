pluginManagement {
    repositories {
        google ()
//        {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ThousandsOfCourses"
include(":app")
include(":main")
include(":favorites")
include(":account")
include(":api")
include(":mylibrary")
include(":data")
include(":detail")
