import java.util.Properties

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("kotlinx-serialization")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.example.basketballapp"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.basketballapp"
        minSdk = 31
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            val localProperties = Properties()
            val localPropertiesFile = rootProject.file("local.properties")
            localProperties.load(localPropertiesFile.reader())

            buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2023.10.01"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")

    //Splashscreen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // KotlinX Serialization
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    //Paging 3.0
    implementation ("androidx.paging:paging-compose:3.3.0-alpha02")
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.50")
    kapt ("androidx.hilt:hilt-compiler:1.1.0")

    //Shimmer
    implementation("com.valentinilk.shimmer:compose-shimmer:1.2.0")
    
    //Animated Bottom Bar
    implementation("com.exyte:animated-navigation-bar:1.0.0")
    
    implementation("io.github.ahmednader65:ComposeSingleRowCalender:1.0.1")
}