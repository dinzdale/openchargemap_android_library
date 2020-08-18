import org.jfrog.build.extractor.clientConfiguration.ClientConfigurationFields
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import org.jfrog.gradle.plugin.artifactory.dsl.ResolverConfig

plugins {
    `maven-publish`
    id("com.jfrog.artifactory") version "4.15.1"
}

ext {
    val comtechWrapperVersion = rootProject.properties.get("currentVersion").toString()
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val build_sdk_version by rootProject.extra { 28 }
    val build_min_sdk_version by rootProject.extra { 26 }
    val build_tools by rootProject.extra { "28.0.3" }
    val kotlin_version by rootProject.extra { "1.3.31" }
    val android_plugin_version by rootProject.extra { "3.6.0" }
    // = false for production releases, always!!!
    // = true, only for local release debugging/logging
    val debuggable_release by rootProject.extra { false }
    // = true for production releases, always!!!
    // = false, only for local release debugging/logging
    val minifiy_release by rootProject.extra { true }
    val shrink_resources_release by rootProject.extra { true }
    val kotlin_coroutines_version by rootProject.extra { "1.3.3" }
    val retrofit_version: String by rootProject.extra { "2.9.0" }
    val room_version: String by rootProject.extra { "2.2.5" }

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$android_plugin_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jfrog.buildinfo:build-info-extractor-gradle:4.15.1")
        classpath("com.google.gms:google-services:4.3.3")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.1.0")
        classpath ("com.github.dcendents:android-maven-gradle-plugin:2.1")

    }
}

allprojects {
    apply(plugin = "com.jfrog.artifactory")
    apply(plugin = "maven-publish")
    repositories {
        google()
        jcenter()
    }
}


tasks.findByName("artifactoryPublish")?.let {
    if (it is org.jfrog.gradle.plugin.artifactory.task.ArtifactoryTask) {
        it.skip = true
    }
}

subprojects.filter {
    it.name.compareTo("mapcommon",true) == 0 || it.name.compareTo("comtechmap",true) == 0 }
    .forEach {
    val thisProject = it
    val thisArtifactoryPublishTask = thisProject.tasks.findByName("artifactoryPublish")?.let { it as org.jfrog.gradle.plugin.artifactory.task.ArtifactoryTask }
    thisArtifactoryPublishTask?.dependsOn("assembleRelease")

    thisProject.publishing {
        publications {
            create<MavenPublication>("aar") {
                groupId = thisProject.group.toString()
                artifactId = thisProject.name.toString()
                version = properties.get("currentVersion").toString()
                artifact("${thisProject.buildDir}/outputs/aar/${thisProject.name}-release.aar")
            }
        }
        thisArtifactoryPublishTask?.let { artifactoryTask ->
            thisProject.publishing.publications.findByName("aar")?.let { aarPublication ->
                artifactoryTask.publications(aarPublication)
            }
        }
    }
}


artifactory {
    val artifactory_contextUrl = properties.get("artifactory_contextUrl")
    clientConfig.setIncludeEnvVars(true)
    setContextUrl(artifactory_contextUrl)

    publish(delegateClosureOf<PublisherConfig> {
        repository(closureOf<groovy.lang.GroovyObject> {
            setProperty(ClientConfigurationFields.REPO_KEY, "libs-release-local")
            setProperty(ClientConfigurationFields.USERNAME, properties.get("artifactory_user"))
            setProperty(ClientConfigurationFields.PASSWORD, properties.get("artifactory_password"))
            setProperty(ClientConfigurationFields.MAVEN, true)
        })
        defaults(delegateClosureOf<groovy.lang.GroovyObject> {
            setProperty("publishArtifacts", true)
            setPublishPom(true)
        })
    })
    resolve(delegateClosureOf<ResolverConfig> {
        repository(delegateClosureOf<groovy.lang.GroovyObject> {
            setProperty(ClientConfigurationFields.CONTEXT_URL, properties.get("artifactory_contextUrl"))
            setProperty(ClientConfigurationFields.REPO_KEY, "libs-release")
            setProperty(ClientConfigurationFields.USERNAME, properties.get("artifactory_user"))
            setProperty(ClientConfigurationFields.PASSWORD, properties.get("artifactory_password"))
            setProperty(ClientConfigurationFields.MAVEN, true)
        })
    })

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
