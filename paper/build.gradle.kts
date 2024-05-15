plugins {
    id("io.papermc.paperweight.userdev") version "1.5.11"
}

val lombok = "1.18.32"
val jetbrainsAnnotations = "24.1.0"
val wlib = "1.6.0"
val paper = "1.17.1-R0.1-20220414.034903-210"

dependencies {
    compileOnly("org.projectlombok:lombok:${lombok}")
    compileOnly("org.jetbrains:annotations:${jetbrainsAnnotations}")
    annotationProcessor("org.projectlombok:lombok:${lombok}")

    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:${paper}")

    implementation(project(":api"))
}