plugins {
    kotlin("jvm") version "2.0.0"
    id("application")
    id("com.github.ben-manes.versions") version "0.51.0"
}

group = "org.mikeb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    implementation("io.github.oshai:kotlin-logging:7.0.0")
    implementation("org.slf4j:slf4j-api:2.0.16")

    runtimeOnly("ch.qos.logback:logback-classic:1.5.6")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
