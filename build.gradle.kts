plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.google.gms.services) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrainsCompose)
    id("com.google.devtools.ksp") version "2.0.0-1.0.24" apply false
}
