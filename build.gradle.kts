
plugins {
    `maven-publish`
}

buildscript {

    val kotlin_version by rootProject.extra { "1.5.30" }
    val android_plugin_version by rootProject.extra { "3.6.0" }


    repositories {
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$android_plugin_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
