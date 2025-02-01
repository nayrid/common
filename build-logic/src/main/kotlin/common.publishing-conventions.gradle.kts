plugins {
    id("common.base-conventions")
    `maven-publish`
}

publishing {
    repositories {
        maven {
            name = "kokirigladeSnapshots"
            url = uri("https://repo.kokirigla.de/snapshots")
            credentials {
                username = project.findProperty("kokirigladeUsername") as String?
                    ?: System.getenv("MAVEN_NAME")
                password = project.findProperty("kokirigladePassword") as String?
                    ?: System.getenv("MAVEN_SECRET")
            }
        }
        maven {
            name = "kokirigladeReleases"
            url = uri("https://repo.kokirigla.de/releases")
            credentials {
                username = project.findProperty("kokirigladeUsername") as String?
                    ?: System.getenv("MAVEN_NAME")
                password = project.findProperty("kokirigladePassword") as String?
                    ?: System.getenv("MAVEN_SECRET")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group as String + "." + rootProject.name
            version = rootProject.version as String
            from(components["java"])
        }
    }
}
