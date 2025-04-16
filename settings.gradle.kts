dependencyResolutionManagement {
    repositories {
        mavenCentral()
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

sequenceOf("api", "examine").forEach {
    include("${rootProject.name}-$it")
    project(":${rootProject.name}-$it").projectDir = file("${rootProject.name}-$it")
}
