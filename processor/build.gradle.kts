plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ksp.api)
    implementation(project(":annotations"))
}
