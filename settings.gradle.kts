pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "enchantments"
include("api")
include("paper")
include("paper:wlib")
findProject(":paper:wlib")?.name = "wlib"
