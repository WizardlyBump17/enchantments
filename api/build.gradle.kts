val lombok = "1.18.32"
val jetbrainsAnnotations = "24.1.0"
val wlib = "1.6.0"

repositories {
    maven {
        url = uri("https://maven.pkg.github.com/WizardlyBump17/WLib")
        credentials {
            username = (project.findProperty("gpr.user") ?: System.getenv("USERNAME")) as String
            password = (project.findProperty("gpr.key") ?: System.getenv("TOKEN")) as String
        }
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok:${lombok}")
    compileOnly("org.jetbrains:annotations:${jetbrainsAnnotations}")
    annotationProcessor("org.projectlombok:lombok:${lombok}")

    implementation("com.wizardlybump17.wlib:objects:${wlib}")
}