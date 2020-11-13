plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
}

group "eu.robocab.habical"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.mnode.ical4j:ical4j:3.0.20")
    implementation(platform("org.http4k:http4k-bom:3.275.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
    implementation("org.http4k:http4k-client-okhttp")
}
