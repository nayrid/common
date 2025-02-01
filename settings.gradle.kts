dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            name = "kokiriglade releases"
            url = uri("https://repo.kokirigla.de/releases")
        }

    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "common"

sequenceOf("api").forEach {
    include("${rootProject.name}-$it")
    project(":${rootProject.name}-$it").projectDir = file(it)
}
