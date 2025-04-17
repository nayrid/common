plugins {
    id("common.base-conventions")
    `maven-publish`
}

publishing {
    repositories {
        maven {
            name = "nayridSnapshots"
            url = uri("https://repo.nayrid.com/snapshots")
            credentials {
                username = project.findProperty("nayridUsername") as String?
                    ?: System.getenv("MAVEN_NAME")
                password = project.findProperty("nayridPassword") as String?
                    ?: System.getenv("MAVEN_TOKEN")
            }
        }
        maven {
            name = "nayridReleases"
            url = uri("https://repo.nayrid.com/releases")
            credentials {
                username = project.findProperty("nayridUsername") as String?
                    ?: System.getenv("MAVEN_NAME")
                password = project.findProperty("nayridPassword") as String?
                    ?: System.getenv("MAVEN_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group as String
            version = rootProject.version as String
            from(components["java"])
        }
    }
}
