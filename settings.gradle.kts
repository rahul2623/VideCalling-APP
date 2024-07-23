pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://github.com/jitsi/jitsi-maven-repository/raw/master/releases")
        }
        maven {
            setUrl("https://maven.google.com")
            setUrl("https://www.jetpack.io")
        }
    }
}

rootProject.name = "VideoMeet"
include(":app")
