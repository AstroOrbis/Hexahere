plugins {
    id("hexahere.minecraft")
    kotlin("jvm")
    alias(libs.plugins.ksp)
}

architectury {
    common("fabric", "forge")
}

dependencies {
    ksp(project(":processor"))

    // use api instead of implementation so downstream can see it maybe?
    implementation(project(":annotations"))

    implementation(libs.kotlin.stdlib)
    implementation(kotlin("reflect"))

    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation(libs.fabric.loader)
    modApi(libs.architectury)

    modApi(libs.hexcasting.common)

    modApi(libs.clothConfig.common)

    libs.mixinExtras.common.also {
        implementation(it)
        annotationProcessor(it)
    }
}


sourceSets {
    main {
        resources {
            srcDir("build/generated/ksp/main/resources")
        }
    }
}

// sync genned resources
// 2 tasks bc clobbering sux


val syncGeneratedLang by tasks.registering(Sync::class) {
    group = "build"
    description = "Copies KSP-generated lang files into common/src/main/resources/assets/hexahere/lang"

    dependsOn(tasks.named("kspKotlin"))

    val generatedLangDir = layout.buildDirectory.dir("generated/ksp/main/resources/assets/hexahere/lang")
    val sourceLangDir = layout.projectDirectory.dir("src/main/resources/assets/hexahere/lang")

    from(generatedLangDir)
    into(sourceLangDir)

    includeEmptyDirs = false
}

val syncGeneratedPatchouliPatterns by tasks.registering(Sync::class) {
    group = "build"
    description =
        "Copies KSP-generated Patchouli pattern entries into common/src/main/resources/assets/hexcasting/.../patterns"

    dependsOn(tasks.named("kspKotlin"))

    val generatedPatternsDir = layout.buildDirectory.dir(
        "generated/ksp/main/resources/assets/hexcasting/patchouli_books/thehexbook/en_us/entries/patterns"
    )
    val sourcePatternsDir = layout.projectDirectory.dir(
        "src/main/resources/assets/hexcasting/patchouli_books/thehexbook/en_us/entries/patterns"
    )

    from(generatedPatternsDir)
    into(sourcePatternsDir)

    includeEmptyDirs = false
}

val syncGeneratedResources by tasks.registering {
    group = "build"
    description = "Syncs all generated resources into source tree"

    dependsOn(syncGeneratedLang)
    dependsOn(syncGeneratedPatchouliPatterns)
}

// sync asap for anything that consumes the resources
tasks.named<ProcessResources>("processResources") {
    dependsOn(syncGeneratedResources)
    duplicatesStrategy = DuplicatesStrategy.WARN
}

tasks.named("compileKotlin") {
    dependsOn(syncGeneratedResources)
}

tasks.named("compileJava") {
    dependsOn(syncGeneratedResources)
}

tasks.named<org.gradle.jvm.tasks.Jar>("sourcesJar") {
    dependsOn(syncGeneratedResources)
}

tasks.withType<org.gradle.jvm.tasks.Jar> {
    duplicatesStrategy = DuplicatesStrategy.WARN
}
