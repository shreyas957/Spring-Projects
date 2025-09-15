rootProject.name = "Spring-Projects"
include("logging")
include("intro-spring")
include("spring-web")
include("spring-annotations")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include("spring-aop")