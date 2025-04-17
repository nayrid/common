import com.diffplug.gradle.spotless.FormatExtension

plugins {
    `java-library`
    idea
    id("net.kyori.indra")
    id("net.kyori.indra.checkstyle")
    id("net.kyori.indra.licenser.spotless")
}

indra {
    mitLicense()

    javaVersions {
        target(21)
    }
}

indraSpotlessLicenser {
    licenseHeaderFile(rootProject.file("license.txt"))
}

spotless {
    fun FormatExtension.applyCommon() {
        trimTrailingWhitespace()
        endWithNewline()
        indentWithSpaces(4)
    }
    java {
        importOrderFile(rootProject.file(".spotless/common.importorder"))
        applyCommon()
    }
    kotlinGradle {
        applyCommon()
    }
}

dependencies {
    checkstyle(libs.stylecheck)

    compileOnlyApi(libs.jspecify)
    compileOnlyApi(libs.jetbrains.annotations)

    testImplementation(libs.junit.jupiterApi)
    testRuntimeOnly(libs.junit.jupiterEngine)
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

val isSnapshot = (rootProject.version as String).contains(("SNAPSHOT"))

tasks {
    javadoc {
        (options as StandardJavadocDocletOptions).apply {
            isFailOnError = false
            links(
                "https://jspecify.dev/docs/api/",
                "https://javadoc.io/doc/net.kyori/examination-api/1.3.0",
                "https://javadoc.io/doc/org.jetbrains/annotations/${libs.versions.org.jetbrains.annotations.get()}/"
            )
        }
    }
    test {
        useJUnitPlatform()
    }
}
