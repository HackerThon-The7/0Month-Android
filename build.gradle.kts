buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependency.GradlePlugin.android)
        classpath(Dependency.GradlePlugin.kotlin)
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}