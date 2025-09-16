rootProject.name = "Spring-Projects"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include("logging")
include("intro-spring")
include("spring-web")
include("spring-annotations")
include("spring-aop")
include("spring-transaction-management")