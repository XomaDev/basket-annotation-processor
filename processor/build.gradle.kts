plugins {
    id("java")
    kotlin("jvm")
    kotlin("kapt") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

kotlin {
    jvmToolchain(11)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.auto.service:auto-service:1.1.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // CommonMark (Markdown)
    implementation("org.commonmark:commonmark:0.19.0")
    implementation("org.commonmark:commonmark-ext-autolink:0.19.0")
    implementation("org.commonmark:commonmark-ext-task-list-items:0.19.0")

    implementation("com.charleskorn.kaml:kaml-jvm:0.53.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.3")

    implementation(fileTree("libs"))
    implementation(project(":annotations"))
}

configurations.implementation.get().isCanBeResolved = true

tasks.register<Copy>("copyImpl") {
    from(configurations.implementation)
    into("build/implementation")
}

tasks.shadowJar {
    archiveBaseName.set("processor-uber")
    archiveClassifier.set("")
    minimize()
}