plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "es.iesjulianmarias.ejerciciosclase"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "es.iesjulianmarias.ejerciciosclase"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Para la base de datos (SQlite)
    implementation("androidx.room:room-runtime:2.8.5")
    implementation(libs.androidx.material3)
    ksp("androidx.room:room-compiler:2.8.5")
    implementation("androidx.room:room-ktx:2.8.5")

    // Para la navegacion entre ventanas de la aplicacion
    implementation("androidx.navigation:navigation-compose:2.9.7")

    // Para abrir un Custom Tab de un navegador
    implementation("androidx.browser:browser:1.8.0")

    // Para iconos de Lucide
    implementation("com.composables:icons-lucide:1.0.0")

    // Para iconos ya definidos de material
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Para imagenes
    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}