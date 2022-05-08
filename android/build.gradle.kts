plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

//    publishing {
//        singleVariant("release") {
//            withSourcesJar()
//            withJavadocJar()
//        }
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("release-android") {
//                from(components["release"])
//                groupId = "dev.zotov.bloc"
//                artifactId = "android"
//                version = "0.0.1"
//            }
//        }
//    }
//}