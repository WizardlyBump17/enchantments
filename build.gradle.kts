plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.5.11"
    id("java")
}

group = "com.wizardlybump17.resourcepack"
version = "0.0.1"

repositories {
    mavenCentral()
}

val lombok = "1.18.32"
val jetbrainsAnnotations = "24.1.0"
val paper = "1.17.1-R0.1-20220414.034903-210"

dependencies {
    compileOnly("org.projectlombok:lombok:${lombok}")
    compileOnly("org.jetbrains:annotations:${jetbrainsAnnotations}")
    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:${paper}")
    annotationProcessor("org.projectlombok:lombok:${lombok}")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(16)
    }

    assemble {
        dependsOn(reobfJar)
    }
}