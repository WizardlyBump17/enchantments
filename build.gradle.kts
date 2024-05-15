plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "8.6" apply false
}

allprojects {
    apply(plugin = "java")

    group = "com.wizardlybump17.enchantments"
    version = "0.0.1"
}

subprojects {
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release.set(16)
        }

        java {
            withJavadocJar()
            withSourcesJar()
        }

        javadoc {
            dependsOn(getTasksByName("delombok", false))
            options {
                this as StandardJavadocDocletOptions
                addBooleanOption("Xdoclint:none", true)
                addStringOption("Xmaxwarns", "1")
            }
        }

        publishing {
            repositories {
                maven {
                    url = uri("https://maven.pkg.github.com/WizardlyBump17/enchantments")
                    credentials {
                        username = (project.findProperty("gpr.user") ?: System.getenv("USERNAME")) as String
                        password = (project.findProperty("gpr.key") ?: System.getenv("TOKEN")) as String
                    }
                }
            }

            publications {
                create<MavenPublication>("maven") {
                    from(project.components["java"])
                }
            }
        }
    }
}