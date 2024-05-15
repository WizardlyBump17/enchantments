plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.5.11" apply false
    id("java")
}

allprojects {
    apply(plugin = "java")

    group = "com.wizardlybump17.enchantments"
    version = "0.0.1"
}

subprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/WizardlyBump17/WLib")
            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("USERNAME")) as String
                password = (project.findProperty("gpr.key") ?: System.getenv("TOKEN")) as String
            }
        }
    }

    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release.set(16)
        }
    }
}